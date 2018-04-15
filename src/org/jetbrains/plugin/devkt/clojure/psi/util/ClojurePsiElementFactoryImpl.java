package org.jetbrains.plugin.devkt.clojure.psi.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.com.intellij.psi.PsiClass;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiErrorElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFileFactory;
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClList;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClListLike;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClVector;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClojureFile;

/**
 * @author ilyas
 */
public class ClojurePsiElementFactoryImpl extends ClojurePsiFactory {

	private static final String DUMMY = "DUMMY.";
	private final Project myProject;

	public ClojurePsiElementFactoryImpl(Project project) {
		myProject = project;
	}

	private static String getErrorMessageInner(PsiElement element) {
		if (element instanceof PsiErrorElement) {
			return ((PsiErrorElement) element).getErrorDescription();
		}
		for (PsiElement child : element.getChildren()) {
			final String msg = getErrorMessageInner(child);
			if (msg != null) return msg;
		}
		return null;
	}

	private static boolean hasErrorElement(PsiElement element) {
		if (element instanceof PsiErrorElement) return true;
		for (PsiElement child : element.getChildren()) {
			if (hasErrorElement(child)) return true;
		}
		return false;
	}

	public ASTNode createSymbolNodeFromText(@NotNull String newName) {
		final String text = "(" + newName + ")";
		final ClojureFile dummyFile = createClojureFileFromText(text);
		return dummyFile.getFirstChild().getFirstChild().getNextSibling().getNode();
	}

	@Override
	public boolean hasSyntacticalErrors(@NotNull String text) {
		final ClojureFile clojureFile = (ClojureFile) PsiFileFactory.getInstance(getProject()).createFileFromText(DUMMY + ClojureFileType.CLOJURE_FILE_TYPE.getDefaultExtension(), text);
		return hasErrorElement(clojureFile);
	}

	public String getErrorMessage(@NotNull String text) {
		if (!hasSyntacticalErrors(text)) return null;
		final ClojureFile clojureFile = (ClojureFile) PsiFileFactory.getInstance(getProject()).createFileFromText(DUMMY + ClojureFileType.CLOJURE_FILE_TYPE.getDefaultExtension(), text);
		return getErrorMessageInner(clojureFile);
	}

	@NotNull
	public ClojureFile createClojureFileFromText(@NotNull String text) {
		return (ClojureFile) PsiFileFactory.getInstance(getProject()).createFileFromText(DUMMY + ClojureFileType.CLOJURE_FILE_TYPE.getDefaultExtension(), text);
	}

	@Override
	public ClList createListFromText(@NotNull String text) {
		return (ClList) createSymbolNodeFromText("(" + text + ")").getPsi();

	}

	@Override
	public ClVector createVectorFromText(@NotNull String text) {
		return (ClVector) createSymbolNodeFromText("[" + text + "]").getPsi();
	}

	@Override
	@Nullable
	public ClListLike findOrCreateJavaImportForClass(PsiClass clazz, ClList importClause) {
		final String name = clazz.getQualifiedName();
		if (name == null) return null;
		final int lastDot = name.lastIndexOf('.');
		if (lastDot <= 0) return null;

		final String prefix = name.substring(0, lastDot);
		final String suffix = name.substring(lastDot + 1);

		final ClListLike[] imported = PsiTreeUtil.getChildrenOfType(importClause, ClListLike.class);

		// Find or create an import member
		ClListLike importMember = null;
		if (imported == null) {
			importMember = addFreshImportToMember(importClause, prefix);
		} else {
			importMember = ContainerUtil.find(imported, elem -> prefix.equals(elem.getHeadText()));
			if (importMember == null) {
				importMember = addFreshImportToMember(importClause, prefix);
			}
		}

		assert importMember != null;

		// Insert a new class into it
		final PsiElement lastChild = importMember.getLastChild();
		final PsiElement newClass = createSymbolNodeFromText(suffix).getPsi();
		assert newClass != null;
		if (lastChild instanceof LeafPsiElement) {
			importMember.addBefore(newClass, lastChild);
		} else {
			importMember.add(newClass);
		}

		return importMember;
	}

	private ClListLike addFreshImportToMember(ClList importClause, String prefix) {
		final ClListLike vector = createVectorFromText(prefix);
		final PsiElement lastChild = importClause.getLastChild();
		if (lastChild instanceof LeafPsiElement) {
			return (ClListLike) importClause.addBefore(vector, lastChild);
		} else {
			return (ClListLike) importClause.add(vector);
		}
	}

	public Project getProject() {
		return myProject;
	}
}