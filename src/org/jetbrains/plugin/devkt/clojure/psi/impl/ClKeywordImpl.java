package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.util.TextRange;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiReference;
import org.jetbrains.kotlin.com.intellij.psi.StubBasedPsiElement;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.psi.ClStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.ClojureBaseElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClKeywordStub;

/**
 * @author ilyas
 */
public class ClKeywordImpl extends ClojureBaseElementImpl<ClKeywordStub> implements ClKeyword,
		StubBasedPsiElement<ClKeywordStub> {
	public ClKeywordImpl(ASTNode node) {
		super(node);
	}

	public ClKeywordImpl(ClKeywordStub stub, @NotNull ClStubElementType nodeType) {
		super(stub, nodeType);
	}

	@Override
	public String toString() {
		return "ClKeyword";
	}

	@Override
	@NotNull
	public String getName() {
		ClKeywordStub stub = getStub();
		if (stub != null) {
			return stub.getName();
		}

		return getText();
	}

	public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
		throw new IncorrectOperationException("Name changing for the keyword");
	}

	@Override
	public PsiReference getReference() {
		return this;
	}

	public PsiElement getElement() {
		return this;
	}

	public TextRange getRangeInElement() {
		return new TextRange(0, getTextLength());
	}

	public PsiElement resolve() {
		return null;
	}

	@NotNull
	public String getCanonicalText() {
		return getText();
	}

	public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
		return null;
	}

	public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
		return null;
	}

	public boolean isReferenceTo(PsiElement element) {
		return false;
	}

	public boolean isSoft() {
		return false;
	}

	@NotNull
	public Object[] getVariants() {
		return PsiElement.EMPTY_ARRAY;
	}
}
