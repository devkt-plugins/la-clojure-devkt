package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;

/**
 * @author ilyas
 */
public class ClMapEntryImpl extends ClojurePsiElementImpl implements ClMapEntry {
	public ClMapEntryImpl(@NotNull ASTNode astNode) {
		super(astNode, "ClMapEntry");
	}


	public ClKeyword getKeywordKey() {
		return findChildByClass(ClKeyword.class);
	}

	public ClojurePsiElement getKey() {
		final PsiElement child = getFirstChild();
		if (child instanceof ClojurePsiElement) {
			return (ClojurePsiElement) child;
		}
		return null;
	}

	public ClojurePsiElement getValue() {
		final PsiElement child = getLastChild();
		if (child instanceof ClojurePsiElement) {
			return (ClojurePsiElement) child;
		}
		return null;
	}
}
