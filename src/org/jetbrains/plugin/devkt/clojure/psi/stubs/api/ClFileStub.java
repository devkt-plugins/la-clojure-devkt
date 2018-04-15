package org.jetbrains.plugin.devkt.clojure.psi.stubs.api;

import org.jetbrains.kotlin.com.intellij.psi.stubs.PsiFileStub;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClojureFile;

/**
 * @author ilyas
 */
public interface ClFileStub extends PsiFileStub<ClojureFile> {
	StringRef getPackageName();

	StringRef getClassName();

	boolean isClassDefinition();
}

