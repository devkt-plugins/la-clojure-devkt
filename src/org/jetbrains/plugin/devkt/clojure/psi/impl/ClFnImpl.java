package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClFn;

/**
 * @author ilyas
 */
public class ClFnImpl extends ClojurePsiElementImpl implements ClFn {
	public ClFnImpl(ASTNode node) {
		super(node);
	}
}
