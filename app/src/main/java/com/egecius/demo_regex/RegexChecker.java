package com.egecius.demo_regex;

import java.util.ArrayList;
import java.util.List;

final class RegexChecker {

	List<String> getMismatchingVersions(final String regEx, final List<String> appVersion) {

		List<String> mismatchingVersions = new ArrayList<>();

		for (final String osVersion : appVersion) {
			boolean matches = osVersion.matches(regEx);
			if (!matches) {
				mismatchingVersions.add(osVersion);
			}
		}

		return mismatchingVersions;
	}
}
