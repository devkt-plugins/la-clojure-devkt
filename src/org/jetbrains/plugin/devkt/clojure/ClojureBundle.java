package org.jetbrains.plugin.devkt.clojure;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;
import org.jetbrains.kotlin.com.intellij.CommonBundle;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.ResourceBundle;

/**
 * @author ilyas
 */
public class ClojureBundle {

	private static final @NotNull
	@NonNls
	String BUNDLE = "org.jetbrains.plugin.devkt.clojure.ClojureBundle";
	private static Reference<ResourceBundle> ourBundle;

	public static @NotNull
	String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
		return CommonBundle.message(getBundle(), key, params);
	}

	private static ResourceBundle getBundle() {
		ResourceBundle bundle = null;

		if (ourBundle != null) bundle = ourBundle.get();

		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BUNDLE);
			ourBundle = new SoftReference<>(bundle);
		}
		return bundle;
	}
}
