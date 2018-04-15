package org.jetbrains.plugin.devkt.clojure.psi.impl.defs;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.navigation.ItemPresentation;
import org.jetbrains.kotlin.com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.StubBasedPsiElement;
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.ClojureIcons;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.*;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ClListBaseImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClDefStub;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author ilyas
 */
public class ClDefImpl extends ClListBaseImpl<ClDefStub> implements ClDef, StubBasedPsiElement<ClDefStub> {
	public ClDefImpl(ClDefStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClDefImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClDef";
	}

	/**
	 * @return Name of string symbol defined
	 */
	@Nullable
	public ClSymbol getNameSymbol() {
		final ClSymbol first = findChildByClass(ClSymbol.class);
		if (first == null) return null;
		return PsiTreeUtil.getNextSiblingOfType(first, ClSymbol.class);
	}

	public String getDefinedName() {
		ClSymbol sym = getNameSymbol();
		if (sym != null) {
			String name = sym.getText();
			assert name != null;
			return name;
		}
		return "";
	}

	@Override
	@Nullable
	public String getName() {
		ClDefStub stub = getStub();
		if (stub != null) {
			return stub.getName();
		}

		return getDefinedName();
	}

	@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return getPresentationText();
			}

			@Nullable
			public String getLocationString() {
				String name = getContainingFile().getName();
				//todo show namespace
				return "(in " + name + ")";
			}

			@Nullable
			public Icon getIcon(boolean open) {
				return ClDefImpl.this.getIcon(0);
			}

			@Nullable
			public TextAttributesKey getTextAttributesKey() {
				return null;
			}
		};
	}

	public String getPresentationText() {
		final StringBuffer buffer = new StringBuffer();
		final String name = getName();
		if (name == null) return "<undefined>";
		buffer.append(name).append(" ");
		buffer.append(getParameterString());

		return buffer.toString();
	}

	public String getDocString() {
		PsiElement element = getSecondNonLeafElement();
		if (element == null) return null;
		element = element.getNextSibling();
		while (element != null && isWrongElement(element)) {
			element = element.getNextSibling();
		}
		// For doc String
		final String s = processString(element);
		if (s != null) return s;

		final ClMetadata meta = getMeta();
		if (meta == null) return null;
		return processMetadata(meta);
	}

	private String processString(PsiElement element) {
		if (element instanceof ClLiteral && element.getFirstChild().getNode().getElementType() == ClojureTokenTypes.STRING_LITERAL) {
			final String rawText = element.getText();
			final String str = StringUtil.trimStart(StringUtil.trimEnd(rawText, "\""), "\"");
			return str.replace("\n  ", "\n").replace("\n", "<br/>");
		}
		return null;
	}

	private String processMetadata(@NotNull ClMetadata meta) {
		final StringBuffer buffer = new StringBuffer();
		final ClojurePsiElement args = meta.getValue("arglists");
		if (args != null) {
			if (args instanceof ClQuotedForm) {
				ClQuotedForm form = (ClQuotedForm) args;
				if (form.getQuotedElement() instanceof ClList) {
					ClList list = (ClList) form.getQuotedElement();
					final ArrayList<String> chunks = new ArrayList<>();
					if (list != null) {
						for (PsiElement element : list.getChildren()) {
							if (element instanceof ClVector) {
								chunks.add(element.getText());
							}
						}
					}
					buffer.append("Arguments:\n");
					for (String chunk : chunks) {
						buffer.append("<b>").append(chunk.trim()).append("</b>").append("\n");
					}
					buffer.append("<br/>");
				}
			}
		}

		final ClojurePsiElement value = meta.getValue("doc");
		if (value != null) {
			buffer.append(processString(value));
		}
		return buffer.toString();
	}


	@Override
	public Icon getIcon(int flags) {
		return ClojureIcons.FUNCTION;
	}

	public PsiElement setName(@NotNull @NonNls String name) throws IncorrectOperationException {
		final ClSymbol sym = getNameSymbol();
		if (sym != null) sym.setName(name);
		return this;
	}

	@Override
	public int getTextOffset() {
		ClDefStub stub = getStub();
		if (stub != null) {
			return stub.getTextOffset();
		}

		final ClSymbol symbol = getNameSymbol();
		if (symbol != null) {
			return symbol.getTextRange().getStartOffset();
		}
		return super.getTextOffset();
	}

	public String getParameterString() {
		final ClVector params = findChildByClass(ClVector.class);
		return params == null ? "" : params.getText();
	}

	public ClMetadata getMeta() {
		for (PsiElement element : getChildren()) {
			if (element instanceof ClMetadata) {
				return (ClMetadata) element;
			}
		}
		return null;
	}

	public String getMethodInfo() {
		final ClSymbol sym = getNameSymbol();
		if (sym == null) return "";
		PsiElement next = sym.getNextSibling();
		while (next instanceof LeafPsiElement) next = next.getNextSibling();
		return next == null ? "" : next.getText();
	}
}
