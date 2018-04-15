package org.jetbrains.plugin.devkt.clojure;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
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
public interface ClojureIcons {
	@NotNull
	Icon CLOJURE_ICON_16x16 = IconLoader.getIcon("/icons/clojure_icon_16x16.png");

	@NotNull
	Icon FUNCTION = IconLoader.getIcon("/icons/def_tmp.png");
	@NotNull
	Icon METHOD = IconLoader.getIcon("/icons/meth_tmp.png");
	@NotNull
	Icon JAVA_METHOD = IconLoader.getIcon("/icons/method.png");
	@NotNull
	Icon JAVA_FIELD = IconLoader.getIcon("/icons/field.png");
	@NotNull
	Icon SYMBOL = IconLoader.getIcon("/icons/symbol.png");
	@NotNull
	Icon NAMESPACE = IconLoader.getIcon("/icons/namespace.png");

	@NotNull
	Icon REPL_CONSOLE = IconLoader.getIcon("/icons/repl_console.png");
	@NotNull
	Icon REPL_ADD = IconLoader.getIcon("/icons/repl_add.png");
	@NotNull
	Icon REPL_CLOSE = IconLoader.getIcon("/icons/repl_close.png");
	@NotNull
	Icon REPL_LOAD = IconLoader.getIcon("/icons/repl_run.png");
	@NotNull
	Icon REPL_GO = IconLoader.getIcon("/icons/repl_go.png");
	@NotNull
	Icon REPL_EVAL = IconLoader.getIcon("/icons/repl_eval.png");
}