package org.jetbrains.plugin.devkt.clojure.psi.impl.symbols;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;

/**
 * @author ilyas
 */
public class ClImplicitArgumentImpl extends ClSymbolImpl {
	public ClImplicitArgumentImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClImplicitArgument";
	}

	@NotNull
	public String getNameString() {
		return getText();
	}
}
