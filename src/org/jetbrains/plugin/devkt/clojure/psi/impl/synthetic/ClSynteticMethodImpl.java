package org.jetbrains.plugin.devkt.clojure.psi.impl.synthetic;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.openapi.diagnostic.Logger;
import org.jetbrains.kotlin.com.intellij.psi.*;
import org.jetbrains.kotlin.com.intellij.psi.impl.PsiSuperMethodImplUtil;
import org.jetbrains.kotlin.com.intellij.psi.impl.light.LightElement;
import org.jetbrains.kotlin.com.intellij.psi.javadoc.PsiDocComment;
import org.jetbrains.kotlin.com.intellij.psi.util.MethodSignature;
import org.jetbrains.kotlin.com.intellij.psi.util.MethodSignatureBackedByPsiMethod;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.plugin.devkt.clojure.file.ClojureFileType;
import org.jetbrains.plugin.devkt.clojure.psi.api.defs.ClDef;
import org.jetbrains.plugin.devkt.clojure.psi.api.synthetic.ClSyntheticClass;
import org.jetbrains.plugin.devkt.clojure.psi.api.synthetic.ClSyntheticMethod;

import java.util.List;

/**
 * @author ilyas
 */
public class ClSynteticMethodImpl extends LightElement implements ClSyntheticMethod {
	public static final Logger LOG = Logger.getInstance("org.jetbrains.plugin.devkt.clojure.psi.impl.synthetic.ClSynteticMethodImpl");

	private final ClDef myDef;
	private final ClSyntheticClass myClass;
	private PsiMethod myCodeBehindMethod;

	protected ClSynteticMethodImpl(ClDef def, ClSyntheticClass clazz) {
		super(def.getManager(), ClojureFileType.CLOJURE_LANGUAGE);
		myDef = def;
		myClass = clazz;
		PsiElementFactory factory = JavaPsiFacade.getInstance(getProject()).getElementFactory();
		try {
			myCodeBehindMethod = factory.createMethodFromText(SynteticUtil.getJavaMethodByDef(myDef), null);
		} catch (IncorrectOperationException e) {
			LOG.error(e);
		}
	}

	@Override
	public String toString() {
		return "ClojureSyntheticMethod[" + getName() + "]";
	}

	public PsiType getReturnTypeNoResolve() {
		return null;
	}

	public String getText() {
		return null;
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
	}

	public PsiElement copy() {
		return null;
	}

	public boolean isExtensionMethod() {
		return false;
	}

	public PsiType getReturnType() {
		return null;
	}

	public PsiTypeElement getReturnTypeElement() {
		return null;
	}

	@NotNull
	public PsiParameterList getParameterList() {
		return null;
	}

	@NotNull
	public PsiReferenceList getThrowsList() {
		return null;
	}

	public PsiCodeBlock getBody() {
		return null;
	}

	public boolean isConstructor() {
		return false;
	}

	public boolean isVarArgs() {
		return false;
	}

	@NotNull
	public MethodSignature getSignature(@NotNull PsiSubstitutor substitutor) {
		return null;
	}

	public PsiIdentifier getNameIdentifier() {
		return null;
	}

	@NotNull
	public PsiMethod[] findSuperMethods() {
		return new PsiMethod[0];
	}

	@NotNull
	public PsiMethod[] findSuperMethods(boolean checkAccess) {
		return new PsiMethod[0];
	}

	@NotNull
	public PsiMethod[] findSuperMethods(PsiClass parentClass) {
		return new PsiMethod[0];
	}

	@NotNull
	public List<MethodSignatureBackedByPsiMethod> findSuperMethodSignaturesIncludingStatic(boolean checkAccess) {
		return null;
	}

	public PsiMethod findDeepestSuperMethod() {
		return null;
	}

	@NotNull
	public PsiMethod[] findDeepestSuperMethods() {
		return new PsiMethod[0];
	}

	@NotNull
	public PsiModifierList getModifierList() {
		return null;
	}

	public boolean hasModifierProperty(@NotNull String name) {
		return false;
	}

	@NotNull
	public String getName() {
		return null;
	}

	public PsiElement setName(@NotNull @NonNls String name) throws IncorrectOperationException {
		return null;
	}

	@NotNull
	public HierarchicalMethodSignature getHierarchicalMethodSignature() {
		return PsiSuperMethodImplUtil.getHierarchicalMethodSignature(this);
	}

	public PsiClass getContainingClass() {
		return myClass;
	}

	public PsiDocComment getDocComment() {
		return null;
	}

	public boolean isDeprecated() {
		return false;
	}

	public boolean hasTypeParameters() {
		return false;
	}

	public PsiTypeParameterList getTypeParameterList() {
		return null;
	}

	@NotNull
	public PsiTypeParameter[] getTypeParameters() {
		return new PsiTypeParameter[0];
	}
}
