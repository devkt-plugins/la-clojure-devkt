package org.jetbrains.plugin.devkt.clojure.psi.stubs.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.psi.stubs.NamedStub;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;

/**
 * @author ilyas
 */
public interface ClKeywordStub extends NamedStub<ClKeyword> {
	@NotNull
	String getName();
}
