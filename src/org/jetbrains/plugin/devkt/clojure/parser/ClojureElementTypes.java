package org.jetbrains.plugin.devkt.clojure.parser;

import org.jetbrains.kotlin.com.intellij.psi.stubs.EmptyStub;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;
import org.jetbrains.plugin.devkt.clojure.lexer.ClojureTokenTypes;
import org.jetbrains.plugin.devkt.clojure.psi.ClStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;
import org.jetbrains.plugin.devkt.clojure.psi.impl.list.ClListImpl;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClDefStub;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClKeywordStub;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.*;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.ns.ClCreateNsElementType;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.ns.ClInNsElementType;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.ns.ClNsElementType;

/**
 * User: peter
 * Date: Nov 21, 2008
 * Time: 9:46:12 AM
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
public interface ClojureElementTypes extends ClojureTokenTypes {
	IStubFileElementType FILE = new ClStubFileElementType();

	IElementType TOPLIST = new ClojureElementType("toplist");
	ClStubElementType<EmptyStub, ClListImpl> LIST = new ClListElementType();
	IElementType VECTOR = new ClojureElementType("vector");
	IElementType MAP = new ClojureElementType("map");
	IElementType SET = new ClojureElementType("map");

	ClStubElementType<ClDefStub, ClDef> DEF = new ClDefElementType();
	ClStubElementType<ClDefStub, ClDef> DEFMETHOD = new ClDefMethodElementType();
	ClStubElementType<ClKeywordStub, ClKeyword> KEYWORD = new ClKeywordElementType();

	ClStubElementType<ClNsStub, ClNs> NS = new ClNsElementType();
	ClStubElementType<ClNsStub, ClNs> IN_NS = new ClInNsElementType();
	ClStubElementType<ClNsStub, ClNs> CREATE_NS = new ClCreateNsElementType();

	IElementType MAP_ENTRY = new ClojureElementType("map");
	IElementType LITERAL = new ClojureElementType("literal");
	IElementType SYMBOL = new ClojureElementType("symbol");
	IElementType IMPLICIT_ARG = new ClojureElementType("function argument");

	IElementType BINDINGS = new ClojureElementType("bindings");
	IElementType REST = new ClojureElementType("rest");
	IElementType AS = new ClojureElementType("as");

	IElementType EXPRESSION = new ClojureElementType("expression");
	IElementType QUOTED_FORM = new ClojureElementType("quoted expression");
	IElementType BACKQUOTED_EXPRESSION = new ClojureElementType("backquoted expression");

	IElementType SHARP_EXPRESSION = new ClojureElementType("pound expression");
	IElementType META_FORM = new ClojureElementType("up expression");
	IElementType METADATA = new ClojureElementType("poundup expression");
	IElementType TILDA_EXPRESSION = new ClojureElementType("tilda expression");
	IElementType AT_EXPRESSION = new ClojureElementType("at expression");
	IElementType TILDAAT_EXPRESSION = new ClojureElementType("tildaat expression");


	TokenSet LIST_LIKE_FORMS = TokenSet.create(LIST, VECTOR, MAP, SET, DEF, DEFMETHOD, NS, IN_NS, CREATE_NS);

	TokenSet BRACES = TokenSet.create(LEFT_CURLY, LEFT_PAREN, LEFT_SQUARE, RIGHT_CURLY, RIGHT_PAREN, RIGHT_SQUARE);

	TokenSet MODIFIERS = TokenSet.create(SHARP, UP, SHARPUP, TILDA, AT, TILDAAT, QUOTE, BACKQUOTE);
}
