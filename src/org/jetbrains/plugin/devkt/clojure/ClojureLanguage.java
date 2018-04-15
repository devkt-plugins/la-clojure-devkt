package org.jetbrains.plugin.devkt.clojure;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.Language;
import org.jetbrains.kotlin.com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;

/**
 * Created by IntelliJ IDEA.
 * User: merlyn
 * Date: 16-Nov-2008
 * Time: 11:09:48 PM
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
public class ClojureLanguage extends Language {
	private static final String ID = "Clojure";

	public ClojureLanguage() {
		super(ID);
	}

	@Contract(pure = true)
	public static @NotNull
	Language getInstance() {
		return ClojureFileType.CLOJURE_LANGUAGE;
	}

	@Nullable
	@Override
	public LanguageFileType getAssociatedFileType() {
		return ClojureFileType.CLOJURE_FILE_TYPE;
	}
}
