package org.jetbrains.plugin.devkt.clojure.psi.impl.ns;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.navigation.ItemPresentation;
import org.jetbrains.kotlin.com.intellij.openapi.editor.colors.TextAttributesKey;
import org.jetbrains.kotlin.com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.kotlin.com.intellij.psi.*;
import org.jetbrains.kotlin.com.intellij.psi.impl.light.LightElement;
import org.jetbrains.kotlin.com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.ClojureIcons;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;
import org.jetbrains.plugin.devkt.clojure.psi.api.ns.ClNs;

import javax.swing.*;

/**
 * @author ilyas
 */
public class ClSyntheticNamespace extends LightElement implements PsiPackage {
	@NotNull
	private final String myName;
	@NotNull
	private final String myQualifiedName;
	private ClNs myNamespace;

	protected ClSyntheticNamespace(PsiManager manager, @NotNull String name, @NotNull String fqn, ClNs ns) {
		super(manager, ClojureFileType.CLOJURE_LANGUAGE);
		myName = name;
		myQualifiedName = fqn;
		myNamespace = ns;
	}

	@NotNull
	public PsiFile[] getFiles(@NotNull GlobalSearchScope globalSearchScope) {
		return new PsiFile[0];
	}

	public String getText() {
		return "";
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		throw new IncorrectOperationException("Don't ever call it!");
	}

	public PsiElement copy() {
		throw new IncorrectOperationException("cannot copy: nonphysical element");
	}

	@NotNull
	public String getQualifiedName() {
		return myQualifiedName;
	}

	public PsiPackage getParentPackage() {
		return null;
	}

	@NotNull
	public PsiPackage[] getSubPackages() {
		return new PsiPackage[0];
	}

	@NotNull
	public PsiPackage[] getSubPackages(@NotNull GlobalSearchScope scope) {
		return new PsiPackage[0];
	}

	@NotNull
	public PsiClass[] getClasses() {
		return new PsiClass[0];
	}

	@NotNull
	public PsiClass[] getClasses(@NotNull GlobalSearchScope scope) {
		return new PsiClass[0];
	}

	public PsiModifierList getAnnotationList() {
		return null;
	}

	public void handleQualifiedNameChange(@NotNull String newQualifiedName) {
	}

	@NotNull
	public VirtualFile[] occursInPackagePrefixes() {
		return new VirtualFile[0];
	}

	public String getName() {
		return myName;
	}

	public boolean containsClassNamed(@NotNull String name) {
		return false;
	}

	@NotNull
	public PsiClass[] findClassByShortName(@NotNull String name, @NotNull GlobalSearchScope scope) {
		return new PsiClass[0];
	}

	public PsiQualifiedNamedElement getContainer() {
		return null;
	}

	public PsiElement setName(@NotNull @NonNls String name) throws IncorrectOperationException {
		throw new IncorrectOperationException("cannot set name: nonphysical element");
	}

	public void checkSetName(String name) throws IncorrectOperationException {
		throw new IncorrectOperationException("cannot set name: nonphysical element");
	}

	public PsiModifierList getModifierList() {
		return null;
	}

	public boolean hasModifierProperty(@NotNull String name) {
		return false;
	}

	@NotNull
	public PsiDirectory[] getDirectories() {
		return new PsiDirectory[0];
	}

	@NotNull
	public PsiDirectory[] getDirectories(@NotNull GlobalSearchScope scope) {
		return new PsiDirectory[0];
	}

	@Override
	public String toString() {
		return "ClojureSyntheticNamespace:" + getQualifiedName();
	}

	@Override
	public @NotNull
	Icon getIcon(int flags) {
		return ClojureIcons.NAMESPACE;
	}

	@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				final String name = getName();
				return name == null ? "<undefined>" : name;
			}

			public @NotNull
			String getLocationString() {
				String name = getContainingFile().getName();
				//todo show namespace
				return "(in " + name + ")";
			}

			public @NotNull
			Icon getIcon(boolean open) {
				return ClSyntheticNamespace.this.getIcon(0);
			}

			public @Nullable
			TextAttributesKey getTextAttributesKey() {
				return null;
			}
		};
	}

	@NotNull
	@Override
	public PsiElement getNavigationElement() {
		if (myNamespace != null) return myNamespace;
		return super.getNavigationElement();
	}
}
