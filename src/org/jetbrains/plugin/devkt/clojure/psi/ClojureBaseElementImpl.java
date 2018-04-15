package org.jetbrains.plugin.devkt.clojure.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.extapi.psi.StubBasedPsiElementBase;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.editor.Document;
import org.jetbrains.kotlin.com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.com.intellij.psi.PsiComment;
import org.jetbrains.kotlin.com.intellij.psi.PsiDocumentManager;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.kotlin.com.intellij.psi.stubs.IStubElementType;
import org.jetbrains.kotlin.com.intellij.psi.stubs.StubElement;
import org.jetbrains.kotlin.com.intellij.util.Function;
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClQuotedForm;
import org.jetbrains.plugin.devkt.clojure.psi.api.symbols.ClSymbol;

import java.util.List;

/**
 * @author ilyas
 */
public abstract class ClojureBaseElementImpl<T extends StubElement> extends StubBasedPsiElementBase<T> implements
		ClojurePsiElement {

	public ClojureBaseElementImpl(T stub, @NotNull IStubElementType nodeType) {
		super(stub, nodeType);
	}

	public ClojureBaseElementImpl(ASTNode node) {
		super(node);
	}

	protected boolean isWrongElement(PsiElement element) {
		return element == null || (element instanceof LeafPsiElement || element instanceof PsiWhiteSpace || element instanceof PsiComment);
	}

	public PsiElement getFirstNonLeafElement() {
		PsiElement first = getFirstChild();
		while (first != null && isWrongElement(first)) {
			first = first.getNextSibling();
		}
		return first;
	}

	public PsiElement getNonLeafElement(int k) {
		final List<PsiElement> elements = ContainerUtil.filter(getChildren(), psiElement -> !isWrongElement(psiElement));
		if (k - 1 >= elements.size()) return null;
		return elements.get(k - 1);
	}

	public PsiElement getLastNonLeafElement() {
		PsiElement lastChild = getLastChild();
		while (lastChild != null && isWrongElement(lastChild)) {
			lastChild = lastChild.getPrevSibling();
		}
		return lastChild;
	}

	public <T> T findFirstChildByClass(Class<T> aClass) {
		PsiElement element = getFirstChild();
		while (element != null && !aClass.isInstance(element)) {
			element = element.getNextSibling();
		}
		return (T) element;
	}

	protected void commitDocument() {
		final Project project = getProject();
		final Document document = PsiDocumentManager.getInstance(project).getDocument(getContainingFile());
		if (document != null) {
			PsiDocumentManager.getInstance(project).commitDocument(document);
		}
	}

	public ClSymbol[] getAllSymbols() {
		return findChildrenByClass(ClSymbol.class);
	}

	public ClSymbol[] getAllQuotedSymbols() {
		final ClQuotedForm[] quoteds = findChildrenByClass(ClQuotedForm.class);
		final List<ClQuotedForm> quotedSymbols = ContainerUtil.filter(quoteds, clQuotedForm -> {
			final ClojurePsiElement element = clQuotedForm.getQuotedElement();
			return element instanceof ClSymbol;
		});

		return ContainerUtil.map(quotedSymbols, (Function<ClQuotedForm, Object>) ClQuotedForm::getQuotedElement).toArray(ClSymbol.EMPTY_ARRAY);
	}

}
