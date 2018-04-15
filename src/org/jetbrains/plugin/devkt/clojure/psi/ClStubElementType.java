package org.jetbrains.plugin.devkt.clojure.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IndexSink;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;

/**
 * @author ilyas
 */
public abstract class ClStubElementType<S extends StubElement, T extends ClojurePsiElement> extends IStubElementType<S, T> {

	public ClStubElementType(@NonNls @NotNull String debugName) {
		super(debugName, ClojureFileType.CLOJURE_LANGUAGE);
	}

	public abstract PsiElement createElement(final ASTNode node);

	public void indexStub(@NotNull final S stub, @NotNull final IndexSink sink) {
	}

	@NotNull
	public String getExternalId() {
		return "clj." + super.toString();
	}

}
