package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;

/**
 * @author ilyas
 */
public class ClMetaForm extends ClojurePsiElementImpl {
	public ClMetaForm(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "MetaForm";
	}

}
