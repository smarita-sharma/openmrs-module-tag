package org.openmrs.tag.web.rest.resource;

import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.tag.EntityTag;
import org.junit.Before;
import org.openmrs.tag.Tag;
import org.openmrs.tag.api.EntityTagService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.openmrs.tag.web.rest.TagRestTestConstants;

import static org.junit.Assert.assertEquals;

public class EntityTagResourceTest extends BaseDelegatingResourceTest<EntityTagResource, EntityTag> {
	
	private static final String TAG_RESOURCE_DATASET = "TagServiceDataset.xml";
	
	@Before
	public void setup() throws Exception {
		executeDataSet(TAG_RESOURCE_DATASET);
	}
	
	@Override
	public EntityTag newObject() {
		return Context.getService(EntityTagService.class).getEntityTagByUuid(getUuidProperty());
	}
	
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertEquals(getObject().getTag().getUuid(), ((SimpleObject) this.getRepresentation().get("tag")).get("uuid"));
		assertPropEquals("uuid", getObject().getUuid());
		assertPropEquals("objectType", getObject().getObjectType());
		assertPropEquals("objectUuid", getObject().getObjectUuid());
	}
	
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertEquals(getObject().getTag().getUuid(), ((SimpleObject) this.getRepresentation().get("tag")).get("uuid"));
		assertPropEquals("objectType", getObject().getObjectType());
		assertPropEquals("objectUuid", getObject().getObjectUuid());
		assertPropEquals("uuid", getObject().getUuid());
		
		//TODO Fix this
		//		assertPropPresent("auditInfo");
	}
	
	@Override
	public String getDisplayProperty() {
		return getObject().getTag().getName();
	}
	
	@Override
	public String getUuidProperty() {
		return TagRestTestConstants.ENTITY_TAG_UUID;
	}
	
}
