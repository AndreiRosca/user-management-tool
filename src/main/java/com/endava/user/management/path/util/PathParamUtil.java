package com.endava.user.management.path.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathParamUtil {
	private final String[] parts;
	private final Map<String, String> params = new HashMap<>();

	public PathParamUtil(String pathUri) {
		parts = pathUri.split("/");
	}

	public Map<String, String> getPathVariables(String pathMapping) {
		Pattern pattern = Pattern.compile("\\{(.+)\\}");
		String[] mappingParts = pathMapping.split("/");
		if (mappingParts.length != parts.length)
			throw new RuntimeException(String.format("Can't extract pathparams from: %s. Doesn't match the uri: %s",
					Arrays.toString(mappingParts), Arrays.toString(parts)));
		for (int i = 0; i < mappingParts.length; ++i) {
			String mapping = mappingParts[i];
			Matcher matcher = pattern.matcher(mapping);
			if (matcher.find()) {
				String paramName = matcher.group(1);
				params.put(paramName, parts[i]);
			}
		}
		return params;
	}
}
