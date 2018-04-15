package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;

/**
 * @author ilyas
 */
public interface ClBraced extends ClojurePsiElement {
	@NotNull
	PsiElement getFirstBrace();

	@Nullable
	PsiElement getLastBrace();
}
