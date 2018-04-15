package org.jetbrains.plugin.devkt.clojure.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;

/**
 * @author ilyas
 */
public interface ClojurePsiElement extends PsiElement {

	@Nullable
	PsiElement getFirstNonLeafElement();

	@Nullable
	PsiElement getLastNonLeafElement();

	@Nullable
	PsiElement getNonLeafElement(int k);

}
