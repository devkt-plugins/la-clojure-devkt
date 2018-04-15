package org.jetbrains.plugin.devkt.clojure.psi.impl.symbols;

import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.plugin.devkt.clojure.ClojureIcons;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ListDeclarations;

import javax.swing.*;

/**
 * @author nik
 * @since 02.11.12
 */
public class SymbolUtils {
	public static Icon getIcon(ClSymbol symbol, int flags) {
		ClList list = PsiTreeUtil.getParentOfType(symbol, ClList.class);
		if (list == null) return null;
		if (!symbol.equals(list.getSecondNonLeafElement())) return null;
		PsiElement firstNonLeafElement = list.getFirstNonLeafElement();
		if (firstNonLeafElement instanceof ClSymbol) {
			String nameString = ((ClSymbol) firstNonLeafElement).getNameString();
			if (nameString.equals(ListDeclarations.FN)) return ClojureIcons.FUNCTION;
			else if (nameString.equals(ListDeclarations.DEFN) || nameString.equals(ListDeclarations.DEFN_))
				return ClojureIcons.FUNCTION;
			else return null;
		} else return null;
	}
}
