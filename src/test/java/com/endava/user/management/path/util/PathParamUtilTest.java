package com.endava.user.management.path.util;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class PathParamUtilTest {

	@Test
	public void canExtractPathParametersWithOneVariable() {
		PathParamUtil paramutil = new PathParamUtil("/users/1/update");
		Map<String, String> pathParams = paramutil.getPathVariables("/users/{userId}/update");
		assertEquals(Collections.singletonMap("userId", "1"), pathParams);
	}
}
