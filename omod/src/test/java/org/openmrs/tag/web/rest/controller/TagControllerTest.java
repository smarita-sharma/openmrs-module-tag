package org.openmrs.tag.web.rest.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceControllerTest;
import org.openmrs.tag.web.controller.TagController;
import org.openmrs.tag.web.rest.TagRestTestConstants;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TagControllerTest extends MainResourceControllerTest {
	
	@Autowired
	private TagController tagController;
	
	private static final String TAG_RESOURCE_DATASET = "TagServiceDataset.xml";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup() throws Exception {
		executeDataSet(INITIAL_XML_DATASET_PACKAGE_PATH);
		executeDataSet(TAG_RESOURCE_DATASET);
	}
	
	@Override
	@Test
	public void shouldGetAll() throws Exception {
		thrown.expect(ResourceDoesNotSupportOperationException.class);
		super.shouldGetAll();
	}
	
	@Test
	public void shouldRetrieveTags() {
		assertEquals(tagController.allTags().size(), 6);
	}
	
	@Test
	@Ignore
	public void shouldRetrieveTagsForSpecifiedUrl() throws Exception {
		String response = handle(newGetRequest("/rest/v1/tag/unique")).getContentAsString();
		assertTrue(response.contains("NewTag"));
		assertTrue(response.contains("Vip-User"));
		assertTrue(response.contains("Initial"));
	}
	
	@Override
	public String getURI() {
		return TagRestTestConstants.TAG_URI;
	}
	
	@Override
	public String getUuid() {
		return TagRestTestConstants.TAG_UUID;
	}
	
	@Override
	public long getAllCount() {
		return 0;
	}
}
