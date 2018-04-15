package org.jetbrains.plugin.devkt.clojure.psi.impl.synthetic;

import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;

/**
 * @author ilyas
 */
public abstract class SynteticUtil {
	public static String getJavaMethodByDef(ClDef def) {
		return "public static void main";
	}
}
