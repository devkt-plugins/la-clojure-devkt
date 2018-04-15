package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;

/**
 * @author ilyas
 */
public interface ClMapEntry extends ClojurePsiElement {

	@Nullable
	ClKeyword getKeywordKey();

	@Nullable
	ClojurePsiElement getKey();

	@Nullable
	ClojurePsiElement getValue();
}
