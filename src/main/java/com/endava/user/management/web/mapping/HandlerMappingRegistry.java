package com.endava.user.management.web.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.endava.user.management.web.controller.Controller;

public class HandlerMappingRegistry {
	private final List<ControllerMapping> controllerMapppings = new ArrayList<>();

	public void addControllerMapping(ControllerMapping mapping) {
		controllerMapppings.add(mapping);
	}

	public Optional<Controller> getController(String httpMethod, String urlInfo) {
		for (ControllerMapping mapping : controllerMapppings) {
			if (mapping.hasMappingFor(httpMethod, urlInfo)) {
				Controller controller = createControllerInstance(mapping);
				controller.setUrlMapping(mapping.getMatchedUrlPattern(httpMethod, urlInfo));
				return Optional.of(controller);
			}
		}
		return Optional.empty();
	}

	private Controller createControllerInstance(ControllerMapping mapping) {
		try {
			return tryCreateControllerInstance(mapping);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Controller tryCreateControllerInstance(ControllerMapping mapping) throws Exception {
		String className = mapping.getClassName();
		Class<?> controllerClass = Class.forName(className);
		return (Controller) controllerClass.newInstance();
	}
}
