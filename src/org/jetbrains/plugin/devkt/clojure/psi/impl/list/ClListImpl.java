package org.jetbrains.plugin.devkt.clojure.psi.impl.list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.ResolveState;
import org.jetbrains.kotlin.com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.kotlin.com.intellij.psi.stubs.EmptyStub;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;

/**
 * @author ilyas
 */
public class ClListImpl extends ClListBaseImpl<EmptyStub> {

	public ClListImpl(ASTNode node) {
		super(node);
	}

	public ClListImpl(EmptyStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	@Override
	public String toString() {
		return "ClList";
	}

	@Override
	public boolean processDeclarations(@NotNull PsiScopeProcessor processor, @NotNull ResolveState state, PsiElement lastParent, @NotNull PsiElement place) {
		return ListDeclarations.get(processor, state, lastParent, place, this, getHeadText());
	}
}
