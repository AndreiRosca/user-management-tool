package com.endava.user.management.web.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

	public <T> T tryGetRequestParametersAs(Class<T> formClass) throws IOException, ServletException {
		T form = createFormInstance(formClass);
		Map<String, String[]> parameterMap = httpRequest.getParameterMap();
		populateFormParameters(form, parameterMap, buildPartsMap());
		return form;
	}

	private Map<String, Part> buildPartsMap() throws IOException, ServletException {
		Map<String, Part> partsMap = new HashMap<>();
		for (Part part : httpRequest.getParts()) {
			partsMap.put(part.getName(), part);
		}
		return partsMap;
	}

	public <T> T getRequestParametersAs(Class<T> formClass) {
		try {
			return tryGetRequestParametersAs(formClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private <T> void populateFormParameters(T form, Map<String, String[]> parameterMap, Map<String, Part> partsMap) {
		Field[] fields = form.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String[] paramValues = parameterMap.get(fieldName);
			if (paramValues != null) {
				String value = paramValues[0];
				setFieldValue(field, form, value);				
			} else if (partsMap.containsKey(fieldName)) {
				setFieldValue(field, form, partsMap.get(fieldName));
			}
		}
	}

	private void setFieldValue(Field field, Object target, Object value) {
		try {
			field.setAccessible(true);
			field.set(target, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private <T> T createFormInstance(Class<T> formClass) {
		try {
			return formClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
