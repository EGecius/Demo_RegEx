package com.egecius.demo_regex;

import java.util.ArrayList;
import java.util.List;

/**
 * Single responsibility:
 * //todo
 */
final class RegexChecker {

	List<String> getUnmatchingVersions(final String regEx, final List<String> appVersion) {

		List<String> unmatchingVersions = new ArrayList<>();

		for (final String osVersion : appVersion) {
			boolean matches = osVersion.matches(regEx);
			if (!matches) {
				unmatchingVersions.add(osVersion);
			}
		}

		return unmatchingVersions;
	}
}
