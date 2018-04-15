package org.jetbrains.plugin.devkt.clojure.psi.impl.ns;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiClass;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.ResolveState;
import org.jetbrains.kotlin.com.intellij.psi.StubBasedPsiElement;
import org.jetbrains.kotlin.com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClListLike;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ClListBaseImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojureKeywords;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojurePsiFactory;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojurePsiUtil;

/**
 * @author ilyas
 */
public class ClNsImpl extends ClListBaseImpl<ClNsStub> implements ClNs, StubBasedPsiElement<ClNsStub> {

	public ClNsImpl(ClNsStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClNsImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClNs";
	}

	@Override
	@Nullable
	public String getName() {
		ClNsStub stub = getStub();
		if (stub != null) {
			return stub.getName();
		}

		return getDefinedName();
	}

	/**
	 * @return Name of string symbol defined
	 */
	@Nullable
	public ClSymbol getNameSymbol() {
		PsiElement element = getSecondNonLeafElement();
		while (element != null && !(element instanceof ClSymbol)) {
			element = element.getNextSibling();
		}
		if (element != null) {
			return (ClSymbol) element;
		}
		return null;
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

	public PsiElement setName(@NotNull @NonNls String name) throws IncorrectOperationException {
		//todo implement me
		return this;
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
		return false;
	}

	@Override
	public int getTextOffset() {
		ClNsStub stub = getStub();
		if (stub != null) {
			return stub.getTextOffset();
		}

		final ClSymbol symbol = getNameSymbol();
		if (symbol != null) {
			return symbol.getTextRange().getStartOffset();
		}
		return super.getTextOffset();
	}

	public ClList findImportClause(@Nullable final PsiElement place) {
		final PsiElement element = ContainerUtil.find(getChildren(), psiElement -> psiElement instanceof ClList && (place == null || ClojurePsiUtil.isStrictlyBefore(psiElement, place)) && ClojureKeywords.IMPORT.equals(((ClList) psiElement).getHeadText()));
		return (ClList) element;
	}

	@NotNull
	public ClList findOrCreateImportClause(@Nullable PsiElement place) {
		final ClList imports = findImportClause(place);
		if (imports != null) return imports;
		return addFreshImportClause();
	}

	public ClList findImportClause() {
		return findImportClause(null);
	}

	@NotNull
	public ClList findOrCreateImportClause() {
		return findOrCreateImportClause(null);
	}

	public ClListLike addImportForClass(PsiElement place, PsiClass clazz) {
		commitDocument();
		final ClojurePsiFactory factory = ClojurePsiFactory.getInstance(getProject());
		final ClList importClause = findOrCreateImportClause(place);
		return factory.findOrCreateJavaImportForClass(clazz, importClause);
	}

	@NotNull
	protected ClList addFreshImportClause() {
		commitDocument();
		final ClSymbol first = getFirstSymbol();
		final ClSymbol nsSymbol = getNameSymbol();
		final PsiElement preamble = findGenClassPreamble();

		final PsiElement anchor = (preamble != null ? preamble : nsSymbol != null ? nsSymbol : first);
		assert first != null;

		final ClojurePsiFactory factory = ClojurePsiFactory.getInstance(getProject());
		final ClList list = factory.createListFromText(":import ");
		return (ClList) addAfter(list, anchor);
	}

	protected PsiElement findGenClassPreamble() {
		return ContainerUtil.find(getChildren(), psiElement -> (psiElement instanceof ClList) && (ClojureKeywords.GEN_CLASS.equals(((ClList) psiElement).getHeadText())));
	}

}