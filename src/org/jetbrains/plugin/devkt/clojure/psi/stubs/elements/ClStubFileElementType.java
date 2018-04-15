package org.jetbrains.plugin.devkt.clojure.psi.stubs.elements;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.psi.stubs.*;
import org.jetbrains.kotlin.com.intellij.psi.tree.IStubFileElementType;
import org.jetbrains.kotlin.com.intellij.util.io.StringRef;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClFileStub;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.impl.ClFileStubImpl;

import java.io.IOException;

/**
 * @author ilyas
 */
public class ClStubFileElementType extends IStubFileElementType<ClFileStub> {
	private static final int CACHES_VERSION = 14;

	public ClStubFileElementType() {
		super(ClojureFileType.CLOJURE_LANGUAGE);
	}

	@Override
	public int getStubVersion() {
		return super.getStubVersion() + CACHES_VERSION;
	}

	@NotNull
	public String getExternalId() {
		return "clojure.FILE";
	}

	@Override
	public void indexStub(@NotNull PsiFileStub stub, @NotNull IndexSink sink) {
		super.indexStub(stub, sink);
	}

	@Override
	public void serialize(@NotNull final ClFileStub stub, @NotNull final StubOutputStream dataStream) throws IOException {
		dataStream.writeName(stub.getPackageName().toString());
		dataStream.writeName(stub.getClassName().toString());
		dataStream.writeBoolean(stub.isClassDefinition());
	}

	@Override
	public @NotNull
	ClFileStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
		StringRef packName = dataStream.readName();
		StringRef name = dataStream.readName();
		boolean isScript = dataStream.readBoolean();
		return new ClFileStubImpl(packName, name, isScript);
	}

	public void indexStub(@NotNull ClFileStub stub, @NotNull IndexSink sink) {
	}
}