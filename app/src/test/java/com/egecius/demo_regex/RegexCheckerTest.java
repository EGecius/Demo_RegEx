package com.egecius.demo_regex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link RegexChecker}
 */
@RunWith (JUnit4.class)
public class RegexCheckerTest {

	public static final String REGEX_FOR_4_3_AND_ABOVE_OS_VERSION = "regex";
	public static final String REGEX_FOR_5_APP_VERSION = "^[0-5]\\.\\d\\.\\d$";
	public static final String REGEX_FOR_6_0_0_APP_VERSION = "^6\\.0\\.0$";

	RegexChecker checker = new RegexChecker();

	List<String> os4_3_andAbove = new ArrayList<>();
	List<String> appVersions_5 = new ArrayList<>();
	List<String> appVersion_6_0_0 = new ArrayList<>();


	List<String> appVersions_5_and_6_1_0 = new ArrayList<>();




	@Before
	public void setup() {
		populateOsVersions();
		populateAppVersions();
	}

	private void populateAppVersions() {
		appVersions_5.add("5.4.0");
		appVersions_5.add("5.4.1");
		appVersions_5.add("5.4.2");

		appVersion_6_0_0.add("6.0.0");

		appVersions_5_and_6_1_0.addAll(appVersions_5);
		appVersions_5_and_6_1_0.add("6.1.0");
	}

	private void populateOsVersions() {
		os4_3_andAbove.add("4.3");
		os4_3_andAbove.add("4.3.1");
		os4_3_andAbove.add("4.4");
		os4_3_andAbove.add("4.4.1");
		os4_3_andAbove.add("4.4.2");
		os4_3_andAbove.add("4.4.3");
		os4_3_andAbove.add("4.4.4");

		os4_3_andAbove.add("5.0");
		os4_3_andAbove.add("5.1");
		os4_3_andAbove.add("5.1.1");

		os4_3_andAbove.add("6.0");
		os4_3_andAbove.add("6.1");

		os4_3_andAbove.add("7.0");
		os4_3_andAbove.add("7.1");
		os4_3_andAbove.add("7.1.1");
	}

	@Test
	public void checksOSVersionsCorrectly() {
		//WHEN
		boolean matches = checker.doesMatchEveryOs(REGEX_FOR_4_3_AND_ABOVE_OS_VERSION, os4_3_andAbove);
		assertThat(matches).isTrue();
	}
	
	@Test
	public void matchesAppVersion_6_0_0() {
		List<String> unmatching = checker.getunmatchingAppVersions(REGEX_FOR_6_0_0_APP_VERSION, appVersion_6_0_0);
		assertThat(unmatching).isEmpty();
	}


	@Test
	public void matchesAppVersion_5() {
		List<String> unmatching = checker.getunmatchingAppVersions(REGEX_FOR_5_APP_VERSION, appVersions_5);
		assertThat(unmatching).isEmpty();
	}
	
	@Test
	public void failsMatchingWhenVersions5And6ArePutTogether() {
		List<String> unmatching = checker.getunmatchingAppVersions(REGEX_FOR_5_APP_VERSION, appVersions_5_and_6_1_0);
		assertThat(unmatching.size()).isEqualTo(1);
		assertThat(unmatching.get(0)).isEqualTo("6.1.0");
	}


}