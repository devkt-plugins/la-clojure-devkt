package org.jetbrains.plugin.devkt.clojure.psi.api.symbols;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiPolyVariantReference;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;

/**
 * @author ilyas
 */
public interface ClSymbol extends ClojurePsiElement, PsiPolyVariantReference, PsiNamedElement {

	ClSymbol[] EMPTY_ARRAY = new ClSymbol[0];

	@NotNull
	String getNameString();

	@Nullable
	PsiElement getReferenceNameElement();

	@Nullable
	String getReferenceName();

	@Nullable
	ClSymbol getRawQualifierSymbol();

	/**
	 * Raw qualifier or in case of it's empty it can be symbol from import list
	 * (import '(java.util Date))
	 *
	 * @return real qualifier of the symbol
	 */
	@Nullable
	ClSymbol getQualifierSymbol();

	boolean isQualified();

	@Nullable
	PsiElement getSeparatorToken();
}
