package org.jetbrains.plugin.devkt.clojure.psi.impl.symbols;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.navigation.ItemPresentation;
import org.jetbrains.kotlin.com.intellij.openapi.application.Application;
import org.jetbrains.kotlin.com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.kotlin.com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.kotlin.com.intellij.openapi.util.TextRange;
import org.jetbrains.kotlin.com.intellij.psi.*;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.ClojureIcons;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.lexer.TokenSets;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.*;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;
import org.jetbrains.plugin.devkt.clojure.psi.impl.ImportOwner;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ListDeclarations;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojureKeywords;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojurePsiFactory;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * @author ilyas
 */
public class ClSymbolImpl extends ClojurePsiElementImpl implements ClSymbol {
	public ClSymbolImpl(ASTNode node) {
		super(node);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		PsiReference fakeClassReference = new MyFakeClassPsiReference();
		return new PsiReference[]{this, fakeClassReference};
	}

	@Override
	public PsiReference getReference() {
		return this;
	}

	@Override
	public String toString() {
		return "ClSymbol";
	}

	public PsiElement getElement() {
		return this;
	}

	public TextRange getRangeInElement() {
		final PsiElement refNameElement = getReferenceNameElement();
		if (refNameElement != null) {
			final int offsetInParent = refNameElement.getStartOffsetInParent();
			return new TextRange(offsetInParent, offsetInParent + refNameElement.getTextLength());
		}
		return new TextRange(0, getTextLength());
	}

	@Nullable
	public PsiElement getReferenceNameElement() {
		final ASTNode lastChild = getNode().getLastChildNode();
		if (lastChild == null) return null;
		for (IElementType elementType : TokenSets.REFERENCE_NAMES.getTypes()) {
			if (lastChild.getElementType() == elementType) return lastChild.getPsi();
		}

		return null;
	}

	@Nullable
	public String getReferenceName() {
		PsiElement nameElement = getReferenceNameElement();
		if (nameElement != null) {
			if (nameElement.getNode().getElementType() == ClojureTokenTypes.symATOM) return nameElement.getText();
		}
		return null;
	}

	@NotNull
	public ResolveResult[] multiResolve(boolean incomplete) {
		return ResolveResult.EMPTY_ARRAY;
	}

	public PsiElement setName(@NotNull @NonNls String newName) throws IncorrectOperationException {
		final ASTNode newNode = ClojurePsiFactory.getInstance(getProject()).createSymbolNodeFromText(newName);
		getParent().getNode().replaceChild(getNode(), newNode);
		return newNode.getPsi();
	}

	@Override
	public Icon getIcon(int flags) {
		return ClojureIcons.SYMBOL;
	}

	@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				final String name = getName();
				return name == null ? "<undefined>" : name;
			}

			public @NotNull
			String getLocationString() {
				String name = getContainingFile().getName();
				//todo show namespace
				return "(in " + name + ")";
			}

			@Nullable
			public Icon getIcon(boolean open) {
				return ClSymbolImpl.this.getIcon(0);
			}

			@Nullable
			public TextAttributesKey getTextAttributesKey() {
				return null;
			}
		};
	}

	public @Nullable
	ClSymbol getRawQualifierSymbol() {
		return findChildByClass(ClSymbol.class);
	}

	/**
	 * For references, which hasn't prefix
	 * (import '(prefix symbol))
	 * (require '(prefix symbol))
	 * (require '[prefix symbol])
	 * (require '(prefix [symbol :as id]))
	 *
	 * @return qualifier symbol
	 */
	public @Nullable
	ClSymbol getQualifierSymbol() {
		final ClSymbol rawQualifierSymbol = getRawQualifierSymbol();
		if (rawQualifierSymbol != null) return rawQualifierSymbol;
		final PsiElement parent = getParent();
		return getQualifiedNameInner(parent, false);
	}

	private ClSymbol getQualifiedNameInner(PsiElement parent, boolean onlyRequireOrUse) {
		if (parent instanceof ClList) {
			return getListQualifier(onlyRequireOrUse, (ClList) parent, parent.getParent(), false);
		} else if (parent instanceof ClVector && ImportOwner.isSpecialVector((ClVector) parent)) {
			final ClSymbol[] symbols = ((ClVector) parent).getAllSymbols();
			return symbols[0] == this ? getQualifiedNameInner(parent.getParent(), true) : null;
		} else if (parent instanceof ClVector) {
			return getVectorQualifier(onlyRequireOrUse, (ClVector) parent, parent.getParent(), false);
		}
		return null;
	}

	private ClSymbol getListQualifier(boolean onlyRequireOrUse, ClList list, PsiElement listParent, boolean isQuoted) {
		if (listParent instanceof ClQuotedForm) {
			return getListQualifier(onlyRequireOrUse, list, listParent.getParent(), true);
		} else if (listParent instanceof ClList) {
			final PsiElement listParentFirstSymbol = ((ClList) listParent).getFirstNonLeafElement();
			if (listParentFirstSymbol instanceof ClSymbol || listParentFirstSymbol instanceof ClKeyword) {
				final String name;
				if (listParentFirstSymbol instanceof ClSymbol) {
					name = ((ClSymbol) listParentFirstSymbol).getNameString();
				} else {
					name = ((ClKeyword) listParentFirstSymbol).getName();
				}
				boolean isOk = isOk(onlyRequireOrUse, isQuoted, name, false);
				final ClSymbol firstSymbol = list.getFirstSymbol();
				if (isOk && firstSymbol != this) {
					return firstSymbol;
				}
			}
		}
		return null;
	}

	private ClSymbol getVectorQualifier(boolean onlyRequireOrUse, ClVector vector, PsiElement list, boolean isQuoted) {
		if (list instanceof ClList) {
			final PsiElement firstSymbol = ((ClList) list).getFirstNonLeafElement();
			if (firstSymbol instanceof ClSymbol || firstSymbol instanceof ClKeyword) {
				final String name;
				if (firstSymbol instanceof ClSymbol) {
					name = ((ClSymbol) firstSymbol).getNameString();
				} else {
					name = ((ClKeyword) firstSymbol).getName();
				}
				boolean isOk = false;
				isOk = isOk(onlyRequireOrUse, isQuoted, name, isOk);
				if (isOk) {
					final PsiElement firstNonLeafElement = vector.getFirstNonLeafElement();
					if (firstNonLeafElement != null && firstNonLeafElement != this && firstNonLeafElement instanceof ClSymbol) {
						return (ClSymbol) firstNonLeafElement;
					}
				}
			}
		} else if (list instanceof ClQuotedForm) {
			return getVectorQualifier(onlyRequireOrUse, vector, list.getParent(), true);
		}
		return null;
	}

	private boolean isOk(boolean onlyRequireOrUse, boolean isQuoted, @Nullable String name, boolean isOk) {
		if ((ClojureKeywords.IMPORT.equals(name) || ListDeclarations.IMPORT.equals(name)) && !onlyRequireOrUse) isOk = true;
		else if ((ClojureKeywords.REQUIRE.equals(name) || ClojureKeywords.USE.equals(name)) && !isQuoted) isOk = true;
		else if ((ListDeclarations.REQUIRE.equals(name) || ListDeclarations.USE.equals(name)) && isQuoted) isOk = true;
		return isOk;
	}

	public boolean isQualified() {
		return getQualifierSymbol() != null;
	}

	@Override
	public String getName() {
		return getNameString();
	}

	@Nullable
	public PsiElement getSeparatorToken() {
		return findChildByType(TokenSets.DOTS);
	}

	public @Nullable
	PsiElement resolve() {
		return null;
	}

	@NotNull
	public String getCanonicalText() {
		return getText();
	}

	@Contract(pure = true)
	private @NotNull
	List<PsiElement> multipleResolveResults() {
		return Collections.emptyList();
	}

	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
		PsiElement nameElement = getReferenceNameElement();
		if (nameElement != null) {
			ASTNode node = nameElement.getNode();
			ASTNode newNameNode = ClojurePsiFactory.getInstance(getProject()).createSymbolNodeFromText(newElementName);
			assert newNameNode != null && node != null;
			node.getTreeParent().replaceChild(node, newNameNode);
		}
		return this;
	}

	public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
		if (isReferenceTo(element)) return this;
		final PsiFile file = getContainingFile();
		if (element instanceof PsiClass && (file instanceof ClojureFile)) {
			// todo test me!!
			final PsiClass clazz = (PsiClass) element;
			final Application application = ApplicationManager.getApplication();
			application.runWriteAction(() -> {
				final ClNs ns = ((ClojureFile) file).findOrCreateNamespaceElement();
				ns.addImportForClass(ClSymbolImpl.this, clazz);
			});
			return this;
		}
		return this;
	}

	public boolean isReferenceTo(PsiElement element) {
		return multipleResolveResults().contains(element);
	}

	@NotNull
	public Object[] getVariants() {
		return PsiElement.EMPTY_ARRAY;
	}

	public boolean isSoft() {
		return false;
	}

	@NotNull
	public String getNameString() {
		return getText();
	}

	private class MyFakeClassPsiReference implements PsiReference {
		public PsiElement getElement() {
			return ClSymbolImpl.this;
		}

		public TextRange getRangeInElement() {
			return new TextRange(0, 0);
		}

		public PsiElement resolve() {
			for (PsiElement element : multipleResolveResults()) {
				if (element instanceof PsiClass) {
					return element;
				}
			}
			return null;
		}

		@NotNull
		public String getCanonicalText() {
			return ClSymbolImpl.this.getCanonicalText();
		}

		public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
			return null;
		}

		public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
			return null;
		}

		public boolean isReferenceTo(PsiElement element) {
			return ClSymbolImpl.this.isReferenceTo(element);
		}

		@NotNull
		public Object[] getVariants() {
			return new Object[0];
		}

		public boolean isSoft() {
			return false;
		}
	}
}
