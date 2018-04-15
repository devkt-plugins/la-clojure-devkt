package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.PsiClass;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.com.intellij.psi.impl.source.PsiFileWithStubSupport;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;

import java.util.List;

/**
 * @author ilyas
 */
public interface ClojureFile extends PsiFile, ClojurePsiElement, PsiFileWithStubSupport {

	List<ClDef> getFileDefinitions();

	boolean isClassDefiningFile();

	@Nullable
	String getNamespace();

	void setNamespace(String newNs);

	@Nullable
	ClNs getNamespaceElement();

	@NotNull
	ClNs findOrCreateNamespaceElement() throws IncorrectOperationException;

	@NotNull
	String getPackageName();

	@Nullable
	String getClassName();

	PsiElement setClassName(@NonNls String s);

	@Nullable
	PsiClass getDefinedClass();

	@Nullable
	String getNamespacePrefix();

	@Nullable
	String getNamespaceSuffix();
}
