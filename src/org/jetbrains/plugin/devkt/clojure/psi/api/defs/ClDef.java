package org.jetbrains.plugin.devkt.clojure.psi.api.defs;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.navigation.NavigationItem;
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClMetadata;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

/**
 * @author ilyas
 */
public interface ClDef extends ClList, PsiNamedElement, NavigationItem {
	@Nullable
	ClSymbol getNameSymbol();

	String getDefinedName();

	String getPresentationText();

	@Nullable
	String getDocString();

	String getParameterString();

	@Nullable
	ClMetadata getMeta();
}
