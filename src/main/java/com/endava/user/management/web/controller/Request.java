package com.endava.user.management.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.endava.user.management.path.util.PathParamUtil;

public class Request {
	private HttpServletRequest httpRequest;
	private HttpServletResponse httpResponse;
	private String httpMethod;
	private Map<String, String> parameters;
	private String urlMapping;
	private String urlInfo;

	private Request() {
	}

	public HttpServletRequest getHttpRequest() {
		return httpRequest;
	}

	public HttpServletResponse getHttpResponse() {
		return httpResponse;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getUrlMapping() {
		return urlMapping;
	}
	
	public String getPathParameter(String parameterName) {
		return parameters.get(parameterName);
	}

	public static RequestBuilder newBuilder() {
		return new RequestBuilder();
	}

	public static class RequestBuilder {
		private Request request = new Request();

		public RequestBuilder setHttpRequest(HttpServletRequest httpRequest) {
			request.httpRequest = httpRequest;
			return this;
		}

		public RequestBuilder setHttpResponse(HttpServletResponse httpResponse) {
			request.httpResponse = httpResponse;
			return this;
		}

		public RequestBuilder setHttpMethod(String httpMethod) {
			request.httpMethod = httpMethod;
			return this;
		}

		public RequestBuilder setUrlMapping(String urlMapping) {
			request.urlMapping = urlMapping;
			return this;
		}

		public RequestBuilder setUrlInfo(String urlInfo) {
			request.urlInfo = urlInfo;
			return this;
		}

		public Request build() {
			request.parameters = new PathParamUtil(request.urlInfo).getPathVariables(request.urlMapping);
			return request;
		}
	}
}
