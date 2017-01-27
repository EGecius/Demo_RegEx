package com.egecius.demo_regex;

import java.util.ArrayList;
import java.util.List;

/**
 * Single responsibility:
 * //todo
 */
final class RegexChecker {


	boolean doesMatchEveryOs(String regEx, List<String> osVersions) {
		for (final String osVersion : osVersions) {
			boolean matches = osVersion.matches(regEx);
			if (!matches) {
				return false;
			}
		}

		return true;
	}


	List<String> getunmatchingAppVersions(final String regEx, final List<String> appVersion) {

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
