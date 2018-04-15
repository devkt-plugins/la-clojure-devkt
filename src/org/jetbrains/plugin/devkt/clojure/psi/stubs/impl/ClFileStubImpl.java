package org.jetbrains.plugin.devkt.clojure.psi.stubs.impl;

import org.jetbrains.kotlin.com.intellij.psi.stubs.PsiFileStubImpl;
import org.jetbrains.kotlin.com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.parser.ClojureElementTypes;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClojureFile;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClFileStub;

/**
 * @author ilyas
 */
public class ClFileStubImpl extends PsiFileStubImpl<ClojureFile> implements ClFileStub {
	private final StringRef myPackageName;
	private final StringRef myClassName;
	private final boolean isClassDefinition;

	public ClFileStubImpl(ClojureFile file) {
		super(file);
		myPackageName = StringRef.fromString(file.getPackageName());
		isClassDefinition = file.isClassDefiningFile();
		myClassName = StringRef.fromString(file.getClassName());
	}

	public ClFileStubImpl(StringRef packName, StringRef name, boolean isScript) {
		super(null);
		myPackageName = packName;
		myClassName = name;
		this.isClassDefinition = isScript;
	}

	public IStubFileElementType getType() {
		return ClojureElementTypes.FILE;
	}

	public StringRef getPackageName() {
		return myPackageName;
	}

	public StringRef getClassName() {
		return myClassName;
	}

	public boolean isClassDefinition() {
		return isClassDefinition;
	}
}