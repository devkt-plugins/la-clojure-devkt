package org.jetbrains.plugin.devkt.clojure.psi.stubs.elements.ns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubInputStream;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.psi.ClStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;

import java.io.IOException;

/**
 * @author ilyas
 */
public abstract class ClNsElementTypeBase extends ClStubElementType<ClNsStub, ClNs> {

	public ClNsElementTypeBase(String dName) {
		super(dName);
	}

	public void serialize(@NotNull ClNsStub stub, @NotNull StubOutputStream dataStream) throws IOException {
		dataStream.writeName(stub.getName());
		dataStream.writeInt(stub.getTextOffset());
	}

	@NotNull
	public ClNsStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
		StringRef ref = dataStream.readName();
		int textOffset = dataStream.readInt();
		return new ClNsStub(parentStub, ref, this, textOffset);
	}
}