package com.endava.user.management.web.mapping;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;

import com.endava.user.management.web.controller.Controller;

public class HandlerMappingRegistryTest {

	@Test
	public void canGetControllers() {
		HandlerMappingRegistry registry = new HandlerMappingRegistry();
		ControllerMapping mapping = new ControllerMapping("com.endava.user.management.web.controller.impl.ListUsersController");
		mapping.addRequestMapping(new RequestMapping("get", "/welcome"));
		registry.addControllerMapping(mapping);
		Optional<Controller> controller = registry.getController("get", "/welcome");
		assertTrue(controller.isPresent());
	}
}
