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
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.impl.ClKeywordImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClKeywordStub;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.impl.ClKeywordStubImpl;

import java.io.IOException;

/**
 * @author ilyas
 */
public class ClKeywordElementType extends ClStubElementType<ClKeywordStub, ClKeyword> {

	public ClKeywordElementType() {
		super("key definition");
	}

	public void serialize(@NotNull ClKeywordStub stub, @NotNull StubOutputStream dataStream) throws IOException {
		dataStream.writeName(stub.getName());
	}

	@NotNull
	public ClKeywordStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
		StringRef ref = dataStream.readName();
		return new ClKeywordStubImpl(parentStub, ref, this);
	}

	public PsiElement createElement(ASTNode node) {
		return new ClKeywordImpl(node);
	}

	public ClKeyword createPsi(@NotNull ClKeywordStub stub) {
		return new ClKeywordImpl(stub, ClojureElementTypes.KEYWORD);
	}

	@NotNull
	public ClKeywordStub createStub(@NotNull ClKeyword psi, StubElement parentStub) {
		return new ClKeywordStubImpl(parentStub, StringRef.fromString(psi.getName()), ClojureElementTypes.KEYWORD);
	}
}