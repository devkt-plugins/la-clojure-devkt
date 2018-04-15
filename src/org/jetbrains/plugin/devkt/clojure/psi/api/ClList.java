package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

/**
 * @author ilyas
 */
public interface ClList extends ClListLike {
	@Nullable
	String getPresentableText();

	@Nullable
	ClSymbol getFirstSymbol();

	@Nullable
	PsiElement getSecondNonLeafElement();

}
