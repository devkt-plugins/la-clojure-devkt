package org.jetbrains.plugin.devkt.clojure.psi.stubs.api;

import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.psi.stubs.NamedStub;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubBase;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;

/**
 * @author ilyas
 */
public class ClDefStub extends StubBase<ClDef> implements NamedStub<ClDef> {
	private final StringRef myName;
	private final int myTextOffset;

	public ClDefStub(StubElement parent, StringRef name, final IStubElementType elementType, int textOffset) {
		super(parent, elementType);
		myName = name;
		myTextOffset = textOffset;
	}

	public int getTextOffset() {
		return myTextOffset;
	}

	public String getName() {
		return StringRef.toString(myName);
	}

}