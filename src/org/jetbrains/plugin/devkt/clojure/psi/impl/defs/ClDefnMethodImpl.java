package org.jetbrains.plugin.devkt.clojure.psi.impl.defs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.navigation.ItemPresentation;
import org.jetbrains.kotlin.com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.plugin.devkt.clojure.ClojureIcons;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDefMethod;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClDefStub;

import javax.swing.*;

/**
 * @author ilyas
 */
public class ClDefnMethodImpl extends ClDefImpl implements ClDefMethod {

	public ClDefnMethodImpl(ClDefStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClDefnMethodImpl(ASTNode node) {
		super(node);
	}

	protected String getPrefix() {
		return "defmethod";
	}

	@Override
	public String toString() {
		return "ClDefn";
	}

	@Override
	public Icon getIcon(int flags) {
		return ClojureIcons.METHOD;
	}

	public String getPresentationText() {
		final StringBuilder buffer = new StringBuilder();
		final String name = getName();
		if (name == null) return "<undefined>";
		buffer.append(name).append(" ");
		buffer.append(getMethodInfo()).append(" ");
		buffer.append(getParameterString());

		return buffer.toString();
	}


	@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return getPresentationText();
			}

			@Nullable
			public String getLocationString() {
				String name = getContainingFile().getName();
				return "(in " + name + ")";
			}

			@Nullable
			public Icon getIcon(boolean open) {
				return ClDefnMethodImpl.this.getIcon(0);
			}

			@Nullable
			public TextAttributesKey getTextAttributesKey() {
				return null;
			}
		};
	}
}
