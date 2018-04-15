package org.jetbrains.plugin.devkt.clojure.psi.stubs.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.EmptyStub;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubInputStream;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.plugin.devkt.clojure.psi.ClStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ClListImpl;

/**
 * @author peter
 */
public class ClListElementType extends ClStubElementType<EmptyStub, ClListImpl> {

	public ClListElementType() {
		super("list");
	}

	public void serialize(@NotNull EmptyStub stub, @NotNull StubOutputStream dataStream) {
	}

	@NotNull
	public EmptyStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) {
		return new EmptyStub(parentStub, this);
	}

	public PsiElement createElement(ASTNode node) {
		return new ClListImpl(node);
	}

	public ClListImpl createPsi(@NotNull EmptyStub stub) {
		return new ClListImpl(stub, this);
	}

	@NotNull
	public EmptyStub createStub(@NotNull ClListImpl psi, StubElement parentStub) {
		return new EmptyStub(parentStub, this);
	}

}
