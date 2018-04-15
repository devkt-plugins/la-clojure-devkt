package org.jetbrains.plugin.devkt.clojure.psi.impl.ns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClQuotedForm;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;
import org.jetbrains.plugin.devkt.clojure.psi.stubs.api.ClNsStub;

/**
 * @author ilyas
 */
public class ClInNsImpl extends ClNsImpl {

	public ClInNsImpl(ClNsStub stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClInNsImpl(ASTNode node) {
		super(node);
	}

	@Override
	@Nullable
	public ClSymbol getNameSymbol() {
		final PsiElement element = getSecondNonLeafElement();
		if (element instanceof ClQuotedForm) {
			final ClQuotedForm form = (ClQuotedForm) element;
			final ClojurePsiElement elt = form.getQuotedElement();
			if (elt instanceof ClSymbol) {
				return (ClSymbol) elt;
			}
			return null;
		}
		return null;
	}


}
