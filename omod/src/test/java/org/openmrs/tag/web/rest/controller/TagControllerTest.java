package org.openmrs.tag.web.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.tag.web.controller.TagController;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@org.springframework.test.context.ContextConfiguration(locations = { "classpath:TestingApplicationContext.xml" }, inheritLocations = true)
public class TagControllerTest extends BaseWebControllerTest {
	
	@Autowired
	private TagController tagController;
	
	private static final String TAG_RESOURCE_DATASET = "TagServiceDataset.xml";
	
	@Before
	public void setup() throws Exception {
		executeDataSet(INITIAL_XML_DATASET_PACKAGE_PATH);
		executeDataSet(TAG_RESOURCE_DATASET);
	}
	
	//	@Test
	//	public void shouldRetrieveTags() {
	//		assertEquals(tagController.allTags().size(), 6);
	//	}
	
	//	@Test
	//	public void shouldRetrieveTagsForSpecifiedUrl() throws Exception {
	//		String response = handle(newGetRequest("/rest/v1/tag/unique")).getContentAsString();
	//		assertTrue(response.contains("NewTag"));
	//		assertTrue(response.contains("Vip-User"));
	//		assertTrue(response.contains("Initial"));
	//	}
	
}
