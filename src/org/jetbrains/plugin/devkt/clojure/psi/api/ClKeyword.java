package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiReference;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;

/**
 * @author ilyas
 */
public interface ClKeyword extends ClojurePsiElement, PsiNamedElement, PsiReference {
}
