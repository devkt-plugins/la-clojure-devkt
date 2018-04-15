package org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.ns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.parser.ClojureElementTypes;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;
import org.jetbrains.plugin.devkt.clojure.psi.impl.ns.ClNsImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;

/**
 * @author ilyas
 */
public class ClNsElementType extends ClNsElementTypeBase {
	public ClNsElementType() {
		super("ns");
	}

	public PsiElement createElement(ASTNode node) {
		return new ClNsImpl(node);
	}

	public ClNs createPsi(@NotNull ClNsStub stub) {
		return new ClNsImpl(stub, ClojureElementTypes.NS);
	}

	public @NotNull
	ClNsStub createStub(@NotNull ClNs psi, StubElement parentStub) {
		return new ClNsStub(parentStub, StringRef.fromString(psi.getDefinedName()), ClojureElementTypes.NS, psi.getTextOffset());
	}
}