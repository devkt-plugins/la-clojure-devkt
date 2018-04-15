package org.jetbrains.plugin.devkt.clojure.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ilyas
 */
public class ClojureSpecialFormTokens {

	public static final String tDEF = "def";
	public static final String tDEFN = "defn";
	public static final String tDEFN_DASH = "defn-";
	public static final String tDEFINLINE = "defninline";
	public static final String tDEFMACRO = "defmacro";
	public static final String tDEFMETHOD = "defmethod";
	public static final String tDEFMULTI = "defmulti";
	public static final String tDEFONCE = "defonce";
	public static final String tDEFSTRUCT = "defstruct";

	public static final Set<String> DEF_TOKENS = new HashSet<>();

	static {
		DEF_TOKENS.addAll(Arrays.asList(tDEF, tDEFN, tDEFN_DASH, tDEFMACRO, tDEFMETHOD, tDEFMULTI, tDEFONCE, tDEFSTRUCT, tDEFINLINE));
	}


}
