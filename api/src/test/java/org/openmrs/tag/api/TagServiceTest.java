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
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.tag.EntityTag;
import org.openmrs.tag.Tag;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a unit test, which verifies logic in EntityTagService.
 */
public class TagServiceTest extends BaseModuleContextSensitiveTest {
	
	private static final String TAG_INITIAL_XML = "TagServiceDataset.xml";
	
	TagService tagService;
	
	@Before
	public void runBeforeTests() throws Exception {
		executeDataSet(INITIAL_XML_DATASET_PACKAGE_PATH);
		executeDataSet(TAG_INITIAL_XML);
		
		tagService = Context.getService(TagService.class);
	}
	
	@Test
	public void getAllTags_shouldFetchAllUniqueStringTags() throws Exception {
		List<Tag> list = tagService.getAllTags();
		assertEquals(6, list.size());
	}
	
	@Test
	public void getTag_shouldFetchUniqueMatchingTag() throws Exception {
		Tag tag = tagService.getTag(3);
		assertEquals("b87c9a48-4712-435a-ab50-7a6e7dd4b88d", tag.getUuid());
	}
	
	@Test
	public void getTags_shouldFetchListOfMatchingTags() throws Exception {
		List<Tag> tagList = tagService.getTags("Vip", false);
		assertEquals(2, tagList.size());
	}
	
	@Test
	public void getTags_shouldFetchListOfExactMatchingTags() throws Exception {
		List<Tag> tagList = tagService.getTags("Vip-Encounters", true);
		assertEquals(1, tagList.size());
	}
}
