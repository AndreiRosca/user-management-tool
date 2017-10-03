package com.endava.user.management.web.mapping;

public class RequestMapping {
	private final String httpMethod;
	private final String urlPattern;

	public RequestMapping(String httpMethod, String urlPattern) {
		this.httpMethod = httpMethod;
		this.urlPattern = urlPattern;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((httpMethod == null) ? 0 : httpMethod.hashCode());
		result = prime * result + ((urlPattern == null) ? 0 : urlPattern.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMapping other = (RequestMapping) obj;
		if (httpMethod == null) {
			if (other.httpMethod != null)
				return false;
		} else if (!httpMethod.equals(other.httpMethod))
			return false;
		if (urlPattern == null) {
			if (other.urlPattern != null)
				return false;
		} else if (!urlPattern.equals(other.urlPattern))
			return false;
		return true;
	}
}
