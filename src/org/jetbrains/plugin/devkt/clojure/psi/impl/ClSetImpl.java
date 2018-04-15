package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClSet;

/**
 * @author ilyas
 */
public class ClSetImpl extends ClojurePsiElementImpl implements ClSet {

	public ClSetImpl(ASTNode node) {
		super(node, "ClSet");
	}

	@NotNull
	public PsiElement getFirstBrace() {
		// XXX: there must be a cleaner way of doing this...
		ASTNode sharp;
		while ((sharp = getNode().findChildByType(ClojureTokenTypes.SHARP)) != null) {
			ASTNode next = sharp.getTreeNext();
			if (ClojureTokenTypes.LEFT_CURLY.equals(next.getElementType())) {
				return sharp.getPsi();
			}
		}
		throw new AssertionError();
	}

	public PsiElement getLastBrace() {
		return findChildByType(ClojureTokenTypes.RIGHT_CURLY);
	}

}
