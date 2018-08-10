package org.openmrs.tag.api;

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

import org.junit.Before;
import org.junit.Test;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.OpenmrsObject;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a unit test, which verifies logic in TagService.
 */
public class UniqueTagServiceTest extends BaseModuleContextSensitiveTest {
	
	private static final String UNIQUETAG_INITIAL_XML = "UniqueTagServiceDataset.xml";
	
	TagService tagService;
	
	@Before
	public void runBeforeTests() throws Exception {
		executeDataSet(UNIQUETAG_INITIAL_XML);
		
		tagService = Context.getService(TagService.class);
	}
	
	@Test
	public void getAllTags_shouldFetchAllUniqueStringTags() throws Exception {
		List<String> list = tagService.getAllTags();
		assertEquals(6, list.size());
	}
	
}
