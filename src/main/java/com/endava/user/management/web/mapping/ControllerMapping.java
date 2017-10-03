package com.endava.user.management.web.mapping;

import java.util.HashSet;
import java.util.Set;

public class ControllerMapping {
	private Set<RequestMapping> requestMappings = new HashSet<>();
	private String className;

	public ControllerMapping(Set<RequestMapping> requestMappings, String className) {
		this.requestMappings = requestMappings;
		this.className = className;
	}

	public ControllerMapping(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void addRequestMapping(RequestMapping mapping) {
		requestMappings.add(mapping);
	}

	public Set<RequestMapping> getRequestMappings() {
		return requestMappings;
	}

	public boolean hasMappingFor(String httpMethod, String urlInfo) {
		return requestMappings.stream()
				.filter(rm -> rm.getHttpMethod().equals(httpMethod))
				.filter(rm -> urlPatternMatches(rm.getUrlPattern(), urlInfo))
				.count() > 0;
	}
	
	public String getMatchedUrlPattern(String httpMethod, String urlInfo) {
		return requestMappings.stream()
				.filter(rm -> rm.getHttpMethod().equals(httpMethod))
				.filter(rm -> urlPatternMatches(rm.getUrlPattern(), urlInfo))
				.findFirst()
				.get()
				.getUrlPattern();
	}

	private boolean urlPatternMatches(String requestMappingPattern, String urlInfo) {
		String[] urlParts = urlInfo.split("/");
		String[] mappingParts = requestMappingPattern.split("/");
		if (urlParts.length != mappingParts.length)
			return false;
		for (int i = 0; i < urlParts.length; ++i) {
			String part = urlParts[i];
			if (!urlPartIsVariable(mappingParts[i]) && !part.equals(mappingParts[i]))
				return false;
		}
		return true;
	}

	private boolean urlPartIsVariable(String urlPart) {
		return urlPart.matches("\\{.+\\}");
	}
}
