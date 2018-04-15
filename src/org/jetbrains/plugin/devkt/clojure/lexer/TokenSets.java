package org.jetbrains.plugin.devkt.clojure.lexer;

import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;

/**
 * @author ilyas
 */
public class TokenSets implements ClojureTokenTypes {

	public static final TokenSet REFERENCE_NAMES = TokenSet.create(symATOM);

	public static final TokenSet DOTS = TokenSet.create(symDOT, symNS_SEP);

	public static final TokenSet RIGHT_PARENTHESES = TokenSet.create(RIGHT_CURLY, RIGHT_PAREN, RIGHT_SQUARE);
}
