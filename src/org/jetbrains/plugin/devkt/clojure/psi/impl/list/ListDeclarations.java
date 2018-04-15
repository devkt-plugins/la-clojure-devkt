package org.jetbrains.plugin.devkt.clojure.psi.impl.list;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.ResolveState;
import org.jetbrains.kotlin.com.intellij.psi.scope.PsiScopeProcessor;
import org.jetbrains.kotlin.com.intellij.util.containers.HashSet;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;

import java.util.Arrays;
import java.util.Set;

/**
 * @author ilyas
 */
public class ListDeclarations {

	public static final String LET = "let";
	public static final String WITH_OPEN = "with-open";
	public static final String WITH_LOCAL_VARS = "with-local-vars";
	public static final String WHEN_LET = "when-let";
	public static final String WHEN_FIRST = "when-let";
	public static final String FOR = "for";
	public static final String IF_LET = "if-let";
	public static final String LOOP = "loop";
	public static final String DOSEQ = "doseq";
	public static final String DECLARE = "declare";
	public static final String FN = "fn";

	public static final String NS = "ns";

	public static final String DEFN = "defn";
	public static final String DEFN_ = "defn-";
	public static final String IMPORT = "import";
	public static final String USE = "use";
	public static final String REFER = "refer";
	public static final String REQUIRE = "require";
	private static final String MEMFN = "memfn";
	private static final String DOT = ".";

	private static final Set<String> LOCAL_BINDINGS = new HashSet<>(Arrays.asList(LET, WITH_OPEN, WITH_LOCAL_VARS, WHEN_LET, WHEN_FIRST, FOR, IF_LET, LOOP, FN, DOSEQ));

	public static boolean get(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place, ClList list, @Nullable String headText) {
		return false;
	}
}
