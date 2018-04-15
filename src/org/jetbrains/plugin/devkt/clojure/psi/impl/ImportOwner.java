package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClVector;
import org.jetbrains.plugin.devkt.clojure.psi.util.ClojureKeywords;

import java.util.Objects;

/**
 * @author ilya
 */
public abstract class ImportOwner {
	public static boolean isSpecialVector(ClVector vector) {
		return isSpecialVector(vector, ClojureKeywords.AS) || isSpecialVector(vector, ClojureKeywords.ONLY) || isSpecialVector(vector, ClojureKeywords.RENAME) || isSpecialVector(vector, ClojureKeywords.EXCLUDE);
	}

	public static boolean isSpecialVector(ClVector vector, String keyword) {
		for (PsiElement child : vector.getChildren()) {
			if (child instanceof ClKeyword && Objects.equals(((ClKeyword) child).getName(), keyword)) {
				return true;
			}
		}
		return false;
	}
}
