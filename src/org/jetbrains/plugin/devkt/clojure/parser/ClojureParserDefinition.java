package org.jetbrains.plugin.devkt.clojure.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.lang.ParserDefinition;
import org.jetbrains.kotlin.com.intellij.lang.PsiParser;
import org.jetbrains.kotlin.com.intellij.lexer.Lexer;
import org.jetbrains.kotlin.com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.com.intellij.psi.FileViewProvider;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.com.intellij.psi.tree.IFileElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureFlexLexer;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.impl.ClojureFileImpl;


/**
 * User: peter
 * Date: Nov 20, 2008
 * Time: 11:10:44 AM
 * Copyright 2007, 2008, 2009 Red Shark Technology
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ClojureParserDefinition implements ParserDefinition {

	@NotNull
	public Lexer createLexer(Project project) {
		return new ClojureFlexLexer();
	}

	public PsiParser createParser(Project project) {
		return new ClojureParser();
	}

	public IFileElementType getFileNodeType() {
		return ClojureElementTypes.FILE;
	}

	@NotNull
	public TokenSet getWhitespaceTokens() {
		return ClojureTokenTypes.WHITESPACE_SET;
	}

	@NotNull
	public TokenSet getCommentTokens() {
		return ClojureTokenTypes.COMMENTS;
	}

	@NotNull
	public TokenSet getStringLiteralElements() {
		return ClojureTokenTypes.STRINGS;
	}

	@NotNull
	public PsiElement createElement(ASTNode node) {
		return ClojurePsiCreator.createElement(node);
	}

	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {

		if (left.getElementType() == ClojureTokenTypes.QUOTE || left.getElementType() == ClojureTokenTypes.SHARP || left.getElementType() == ClojureTokenTypes.SHARPUP) {

			return SpaceRequirements.MUST_NOT;

		} else if (left.getElementType() == ClojureTokenTypes.LEFT_PAREN || right.getElementType() == ClojureTokenTypes.RIGHT_PAREN || left.getElementType() == ClojureTokenTypes.RIGHT_PAREN || right.getElementType() == ClojureTokenTypes.LEFT_PAREN

				|| left.getElementType() == ClojureTokenTypes.LEFT_CURLY || right.getElementType() == ClojureTokenTypes.RIGHT_CURLY || left.getElementType() == ClojureTokenTypes.RIGHT_CURLY || right.getElementType() == ClojureTokenTypes.LEFT_CURLY

				|| left.getElementType() == ClojureTokenTypes.LEFT_SQUARE || right.getElementType() == ClojureTokenTypes.RIGHT_SQUARE || left.getElementType() == ClojureTokenTypes.RIGHT_SQUARE || right.getElementType() == ClojureTokenTypes.LEFT_SQUARE) {

			return SpaceRequirements.MAY;
		}
		return SpaceRequirements.MUST;
	}

	public PsiFile createFile(FileViewProvider viewProvider) {
		return new ClojureFileImpl(viewProvider);
	}
}

