package org.jetbrains.plugin.devkt.clojure.psi.stubs.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubInputStream;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.parser.ClojureElementTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;
import org.jetbrains.plugin.devkt.clojure.psi.impl.defs.ClDefImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClDefStub;

import java.io.IOException;

/**
 * @author ilyas
 */
public class ClDefElementType extends ClStubElementType<ClDefStub, ClDef> {

	public ClDefElementType() {
		super("def-element");
	}

	public void serialize(@NotNull ClDefStub stub, @NotNull StubOutputStream dataStream) throws IOException {
		dataStream.writeName(stub.getName());
		dataStream.writeInt(stub.getTextOffset());
	}

	@NotNull
	public ClDefStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
		StringRef ref = dataStream.readName();
		int textOffset = dataStream.readInt();
		return new ClDefStub(parentStub, ref, this, textOffset);
	}

	public PsiElement createElement(ASTNode node) {
		return new ClDefImpl(node);
	}

	public ClDef createPsi(@NotNull ClDefStub stub) {
		return new ClDefImpl(stub, ClojureElementTypes.DEF);
	}

	@NotNull
	public ClDefStub createStub(@NotNull ClDef psi, StubElement parentStub) {
		return new ClDefStub(parentStub, StringRef.fromString(psi.getName()), ClojureElementTypes.DEF, psi.getTextOffset());
	}
}
