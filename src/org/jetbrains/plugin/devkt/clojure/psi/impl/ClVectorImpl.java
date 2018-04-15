package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClVector;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

import java.util.ArrayList;

/**
 * @author ilyas
 */
public class ClVectorImpl extends ClojurePsiElementImpl implements ClVector {
	public ClVectorImpl(ASTNode node) {
		super(node, "ClVector");
	}

	@NotNull
	public PsiElement getFirstBrace() {
		PsiElement element = findChildByType(ClojureTokenTypes.LEFT_SQUARE);
		assert element != null;
		return element;
	}

	public PsiElement getLastBrace() {
		return findChildByType(ClojureTokenTypes.RIGHT_SQUARE);
	}

	@Nullable
	public String getHeadText() {
		PsiElement first = getFirstNonLeafElement();
		if (first == null) return null;
		return first.getText();
	}

	public ClSymbol[] getOddSymbols() {
		final ClojurePsiElement[] elems = findChildrenByClass(ClojurePsiElement.class);
		final ArrayList<ClSymbol> res = new ArrayList<>();
		for (int i = 0; i < elems.length; i++) {
			ClojurePsiElement elem = elems[i];
			if (i % 2 == 0 && elem instanceof ClSymbol) {
				res.add((ClSymbol) elem);
			}
		}
		return res.toArray(new ClSymbol[res.size()]);
	}
}
