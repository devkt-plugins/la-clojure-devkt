package org.jetbrains.plugin.devkt.clojure.psi.api.ns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiClass;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClListLike;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

/**
 * @author ilyas
 */
public interface ClNs extends ClList, PsiNamedElement {
	@Nullable
	ClSymbol getNameSymbol();

	String getDefinedName();

	@Nullable
	ClList findImportClause(@Nullable PsiElement place);

	@Nullable
	ClList findImportClause();

	@NotNull
	ClList findOrCreateImportClause(@Nullable PsiElement place);

	@NotNull
	ClList findOrCreateImportClause();

	@Nullable
	ClListLike addImportForClass(PsiElement place, PsiClass clazz);

}
