package org.jetbrains.plugin.devkt.clojure.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;

/**
 * Created by IntelliJ IDEA.
 * User: peter
 * Date: Jan 1, 2009
 * Time: 5:44:13 PM
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
public class ClojurePsiElementImpl extends ClojureBaseElementImpl<StubElement> implements ClojurePsiElement {

	final private String myName;

	public ClojurePsiElementImpl(@NotNull ASTNode astNode, @NotNull String name) {
		super(astNode);
		myName = name;
	}

	public ClojurePsiElementImpl(@NotNull ASTNode astNode) {
		super(astNode);
		myName = null;
	}

	@Override
	public String toString() {
		return myName == null ? super.toString() : myName;
	}

}
