package org.jetbrains.plugin.devkt.clojure.psi.stubs.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubBase;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClKeywordStub;

/**
 * @author ilyas
 */
public class ClKeywordStubImpl extends StubBase<ClKeyword> implements ClKeywordStub {

	private final StringRef myName;

	public ClKeywordStubImpl(StubElement parent, StringRef name, IStubElementType elementType) {
		super(parent, elementType);
		myName = name;
	}

	public @NotNull
	String getName() {
		return StringRef.toString(myName);
	}
}
