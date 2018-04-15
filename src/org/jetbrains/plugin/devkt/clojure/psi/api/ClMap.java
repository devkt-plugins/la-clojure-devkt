package org.jetbrains.plugin.devkt.clojure.psi.api;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.impl.ClMapEntry;

import java.util.List;

/**
 * @author ilyas
 */
public interface ClMap extends ClojurePsiElement, ClBraced {
	List<ClMapEntry> getEntries();

	@Nullable
	ClojurePsiElement getValue(final String key);
}
