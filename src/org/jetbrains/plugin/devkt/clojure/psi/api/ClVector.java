package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

/**
 * @author ilyas
 */
public interface ClVector extends ClListLike {

	ClSymbol[] getOddSymbols();
}
