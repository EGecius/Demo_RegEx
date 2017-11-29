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

	public static final String REGEX_FOR_5_APP_VERSION = "^[0-5]\\.\\d\\.\\d$";
	public static final String REGEX_FOR_6_0_0_APP_VERSION = "^6\\.0\\.0$";
	public static final String REGEX_FOR_5_AND_6_0_APP_VERSION = "(^[0-5]\\.\\d\\.\\d$|^6\\.0\\.0$)";

	public static final String REGEX_FOR_4_3_AND_4_4 = "4\\.[3-4].*";
	public static final String REGEX_FOR_5 = "5.*";
	public static final String REGEX_FOR_6 = "6.*";
	public static final String REGEX_FOR_7 = "7.*";

	public static final String REGEX_FOR_4_3_AND_ABOVE_OS_VERSION = "(4\\.[3-4].*|[5-7].*)";

	RegexChecker checker = new RegexChecker();

	List<String> os4_0_andAbove = new ArrayList();
	List<String> os4_3_andAbove = new ArrayList<>();
	List<String> appVersions_5 = new ArrayList<>();
	List<String> appVersion_6_0_0 = new ArrayList<>();

	List<String> appVersions_5_and_6_0_0 = new ArrayList<>();
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

		appVersions_5_and_6_0_0.addAll(appVersions_5);
		appVersions_5_and_6_0_0.addAll(appVersion_6_0_0);

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

		os4_0_andAbove.add(("4.0"));
		os4_0_andAbove.add(("4.1"));
		os4_0_andAbove.add(("4.2"));
		os4_0_andAbove.add("4.2.2");
		os4_0_andAbove.addAll(os4_3_andAbove);
	}

	@Test
	public void checksOSVersionsCorrectly() {
		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_4_3_AND_ABOVE_OS_VERSION, os4_3_andAbove);
		assertThat(unmatching).isEmpty();
	}

	@Test
	public void checksOSVersionsFailsForVersion_4_2_andAbove() {
		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_4_3_AND_ABOVE_OS_VERSION, os4_0_andAbove);
		assertThat(unmatching.size()).isEqualTo(4);
		assertThat(unmatching.get(0)).isEqualTo("4.0");
		assertThat(unmatching.get(1)).isEqualTo("4.1");
		assertThat(unmatching.get(2)).isEqualTo("4.2");
		assertThat(unmatching.get(3)).isEqualTo("4.2.2");
	}

	@Test
	public void matchesAppVersion_6_0_0() {
		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_6_0_0_APP_VERSION, appVersion_6_0_0);
		assertThat(unmatching).isEmpty();
	}


	@Test
	public void matchesAppVersion_5() {
		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_5_APP_VERSION, appVersions_5);
		assertThat(unmatching).isEmpty();
	}
	
	@Test
	public void failsMatchingWhenVersions5And6ArePutTogether() {
		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_5_APP_VERSION, appVersions_5_and_6_1_0);
		assertThat(unmatching.size()).isEqualTo(1);
		assertThat(unmatching.get(0)).isEqualTo("6.1.0");
	}
	
	@Test
	public void matchesVersions_5_and_6_0_0() {
		//WHEN


		List<String> unmatching = checker.getMismatchingVersions(REGEX_FOR_5_AND_6_0_APP_VERSION, appVersions_5_and_6_0_0);
		assertThat(unmatching).isEmpty();


		//THEN	
	}


}