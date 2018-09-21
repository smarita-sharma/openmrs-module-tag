/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.tag.api;

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
 * This is a unit test, which verifies logic in EntityTagService.
 */
public class EntityTagServiceTest extends BaseModuleContextSensitiveTest {
	
	private static final String TAG_INITIAL_XML = "TagServiceDataset.xml";
	
	EntityTagService entityTagService;
	
	TagService tagService;
	
	@Before
	public void runBeforeTests() throws Exception {
		executeDataSet(INITIAL_XML_DATASET_PACKAGE_PATH);
		executeDataSet(TAG_INITIAL_XML);
		
		entityTagService = Context.getService(EntityTagService.class);
		tagService = Context.getService(TagService.class);
	}
	
	@Test
	public void getTags_shouldReturnListOfTagsOnAnOpenmrsObject() throws Exception {
		Encounter encounter = Context.getEncounterService().getEncounterByUuid("e403fafb-e5e4-42d0-9d11-4f52e89d148c");
		List<EntityTag> tags = entityTagService.getEntityTagsByObject(encounter);
		assertEquals(3, tags.size());
	}
	
	//	@Test
	//	public void getTags_shouldReturnTagsWhichMatchObjectTypeAndHaveAnyTag() throws Exception {
	//		List<Class<? extends OpenmrsObject>> types = new ArrayList<Class<? extends OpenmrsObject>>();
	//		types.add(Encounter.class);
	//		types.add(Obs.class);
	//		List<String> tags = new ArrayList<String>();
	//		tags.add("Initial");
	//		tags.add("FollowUp");
	//		List<EntityTag> tagList = entityTagService.getTags(types, tags);
	//		assertEquals(7, tagList.size());
	//	}
	
	//	@Test
	//	public void getTags_shouldIgnoreMatchingByTypeIfObjectTypesListIsEmpty() {
	//		List<Class<? extends OpenmrsObject>> types = new ArrayList<Class<? extends OpenmrsObject>>();
	//		List<String> tags = new ArrayList<String>();
	//		tags.add("Initial");
	//		tags.add("FollowUp");
	//		List<EntityTag> tagList = entityTagService.getTags(types, tags);
	//		assertEquals(9, tagList.size());
	//	}
	
	//	@Test
	//	public void removeTag_shouldRemoveTagFromObject() {
	//		Obs obs = Context.getObsService().getObsByUuid("2f616900-5e7c-4667-9a7f-dcb260abf1de");
	//		List<EntityTag> list = entityTagService.getTags(obs);
	//		assertEquals(2, list.size());
	//		assertTrue(entityTagService.deleteEntityTag(obs, tagService.getEntityTag(1)));
	//		List<EntityTag> list1 = entityTagService.getTags(obs);
	//		assertEquals(1, list1.size());
	//	}
	
	//	@Test
	//	public void addTag_shouldAddTagToObject() {
	//		Obs obs = Context.getObsService().getObsByUuid("2f616900-5e7c-4667-9a7f-dcb260abf1de");
	//		List<EntityTag> list = tagService.getTags(obs);
	//		assertEquals(2, list.size());
	//		entityTagService.addTag(obs, new Tag("Important"));
	//		List<EntityTag> list1 = entityTagService.getTags(obs);
	//		assertEquals(3, list1.size());
	//	}
	
}
