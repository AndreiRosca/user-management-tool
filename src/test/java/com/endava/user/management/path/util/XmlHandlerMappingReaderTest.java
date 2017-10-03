package com.endava.user.management.path.util;

import org.junit.Test;

import com.endava.user.management.web.mapping.XmlHandlerMappingReader;

public class XmlHandlerMappingReaderTest {

	@Test
	public void canParseHandlerMappings() {
		XmlHandlerMappingReader reader = new XmlHandlerMappingReader("/controller-mapping.xml");
		reader.parseMappingRegistry();
	}
}
