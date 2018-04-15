package org.jetbrains.plugin.devkt.clojure;

import org.ice1000.devkt.openapi.AnnotationHolder;
import org.ice1000.devkt.openapi.ColorScheme;
import org.ice1000.devkt.openapi.ExtendedDevKtLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.parser.ClojureParserDefinition;

/**
 * DevKt implementation for la-clojure
 *
 * @author ice1000
 */
public class Clojure<TextAttributes> extends ExtendedDevKtLanguage<TextAttributes> {
	private static final TokenSet NUMBERS = TokenSet.create(ClojureTokenTypes.LONG_LITERAL,
			ClojureTokenTypes.BIG_INT_LITERAL,
			ClojureTokenTypes.DOUBLE_LITERAL,
			ClojureTokenTypes.BIG_DECIMAL_LITERAL,
			ClojureTokenTypes.RATIO);

	public Clojure() {
		super(ClojureLanguage.getInstance(), new ClojureParserDefinition());
	}

	@Override
	public @NotNull
	String getLineCommentStart() {
		return "; ";
	}

	@Override
	public boolean satisfies(String fileName) {
		return fileName.endsWith(".clj") || fileName.endsWith(".cljs") || fileName.endsWith(".cljc");
	}

	@Override
	public void annotate(PsiElement psiElement, AnnotationHolder<? super TextAttributes> annotationHolder, ColorScheme<? extends TextAttributes> colorScheme) {
	}

	@Override
	public @Nullable
	TextAttributes attributesOf(IElementType iElementType, ColorScheme<? extends TextAttributes> colorScheme) {
		if (iElementType == ClojureTokenTypes.COMMA) return colorScheme.getComma();
		else if (iElementType == ClojureTokenTypes.BAD_CHARACTER) return colorScheme.getUnknown();
		else if (NUMBERS.contains(iElementType)) return colorScheme.getNumbers();
		else if (ClojureTokenTypes.STRINGS.contains(iElementType)) return colorScheme.getString();
		else if (ClojureTokenTypes.COMMENTS.contains(iElementType)) return colorScheme.getLineComments();
		else if (ClojureTokenTypes.KEYWORDS.contains(iElementType)) return colorScheme.getKeywords();
		return null;
	}
}
