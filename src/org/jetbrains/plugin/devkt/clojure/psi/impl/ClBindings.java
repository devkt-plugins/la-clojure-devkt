package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;

/**
 * @author ilyas
 */
public class ClBindings extends ClojurePsiElementImpl {
	public ClBindings(ASTNode node) {
		super(node);
	}
}
