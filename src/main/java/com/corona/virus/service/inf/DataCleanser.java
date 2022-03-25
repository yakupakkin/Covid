package com.corona.virus.service.inf;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface DataCleanser<X, Y> {

	default void clean(Map<String, Map<String, X>> xs, Map<String, Map<String, Y>> ys) {
		Set<String> xKeysToRemove = xs.keySet().stream()
				.filter(c -> !ys.containsKey(c) || (c.equalsIgnoreCase("World") || c.equalsIgnoreCase("Global")))
				.collect(Collectors.toSet());

		Set<String> yKeysToRemove = ys.keySet().stream()
				.filter(c -> !xs.containsKey(c) || (c.equalsIgnoreCase("World") || c.equalsIgnoreCase("Global")))
				.collect(Collectors.toSet());

		xKeysToRemove.addAll(yKeysToRemove);

		for (String key : xKeysToRemove) {
			xs.remove(key);
			ys.remove(key);
		}
	}

}
