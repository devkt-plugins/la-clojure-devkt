package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClMap;

import java.util.Arrays;
import java.util.List;

/**
 * @author ilyas
 */
public class ClMapImpl extends ClojurePsiElementImpl implements ClMap {
	public ClMapImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClMap";
	}

	@NotNull
	public PsiElement getFirstBrace() {
		PsiElement element = findChildByType(ClojureTokenTypes.LEFT_CURLY);
		assert element != null;
		return element;
	}

	public PsiElement getLastBrace() {
		return findChildByType(ClojureTokenTypes.RIGHT_CURLY);
	}


	public List<ClMapEntry> getEntries() {
		return Arrays.asList(findChildrenByClass(ClMapEntry.class));
	}

	public ClojurePsiElement getValue(final String key) {
		final ClMapEntry entry = ContainerUtil.find(getEntries(), clMapEntry -> {
			final ClKeyword clKeyword = clMapEntry.getKeywordKey();
			if (clKeyword == null) return false;
			final String text = StringUtil.trimStart(clKeyword.getText(), ":");
			return text.equals(key);
		});
		if (entry == null) return null;
		return entry.getValue();
	}
}
