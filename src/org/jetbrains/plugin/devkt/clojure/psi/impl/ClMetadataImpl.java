package org.jetbrains.plugin.devkt.clojure.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.util.containers.ContainerUtil;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElement;
import org.jetbrains.plugin.devkt.clojure.psi.ClojurePsiElementImpl;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClKeyword;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClMap;
import org.jetbrains.plugin.devkt.clojure.psi.api.ClMetadata;

import java.util.Collections;
import java.util.List;

/**
 * @author ilyas
 */
public class ClMetadataImpl extends ClojurePsiElementImpl implements ClMetadata {
	public ClMetadataImpl(ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "ClMetadata";
	}

	@NotNull
	public List<ClKeyword> getKeys() {
		ClMap map = getUnderlyingMap();
		if (map == null) return Collections.emptyList();
		return ContainerUtil.map(map.getEntries(), ClMapEntry::getKeywordKey);
	}

	private ClMap getUnderlyingMap() {
		final ClMap map = findChildByClass(ClMap.class);
		if (map == null) return null;
		return map;
	}

	public ClojurePsiElement getValue(String key) {
		final ClMap map = getUnderlyingMap();
		if (map == null) return null;
		return map.getValue(key);
	}
}
