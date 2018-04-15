package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClLiteral;

/**
 * @author ilyas
 */
public class ClLiteralImpl extends ClojurePsiElementImpl implements ClLiteral {
	public ClLiteralImpl(ASTNode node) {
		super(node, "ClLiteral");
	}
}
