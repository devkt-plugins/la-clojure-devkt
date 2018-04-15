package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClQuotedForm;

/**
 * @author ilyas
 */
public class ClQuotedFormImpl extends ClojurePsiElementImpl implements ClQuotedForm {
	public ClQuotedFormImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClQuotedForm";
	}

	@Nullable
	public ClojurePsiElement getQuotedElement() {
		return findChildByClass(ClojurePsiElement.class);
	}


}
