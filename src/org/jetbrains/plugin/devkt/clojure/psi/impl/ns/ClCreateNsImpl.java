package org.jetbrains.plugin.devkt.clojure.psi.impl.ns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;

/**
 * @author ilyas
 */
public class ClCreateNsImpl extends ClNsImpl {
	public ClCreateNsImpl(ClNsStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClCreateNsImpl(ASTNode node) {
		super(node);
	}
}
