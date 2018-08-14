package org.openmrs.tag.web.rest.resource;

import org.openmrs.tag.EntityTag;
import org.junit.Before;
import org.openmrs.tag.api.EntityTagService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.openmrs.tag.web.rest.TagRestTestConstants;

public class EntityTagResourceTest extends BaseDelegatingResourceTest<EntityTagResource, EntityTag> {
	
	private static final String TAG_RESOURCE_DATASET = "TagServiceDataset.xml";
	
	@Before
	public void setup() throws Exception {
		executeDataSet(TAG_RESOURCE_DATASET);
	}
	
	@Override
	public EntityTag newObject() {
		return Context.getService(EntityTagService.class).getTagByUuid(getUuidProperty());
	}
	
	@Override
	public void validateDefaultRepresentation() throws Exception {
		super.validateDefaultRepresentation();
		assertPropEquals("tag", getObject().getTag());
		assertPropEquals("uuid", getObject().getUuid());
		assertPropEquals("objectType", getObject().getObjectType());
		assertPropEquals("objectUuid", getObject().getObjectUuid());
	}
	
	@Override
	public void validateFullRepresentation() throws Exception {
		super.validateFullRepresentation();
		assertPropEquals("tag", getObject().getTag());
		assertPropEquals("objectType", getObject().getObjectType());
		assertPropEquals("objectUuid", getObject().getObjectUuid());
		assertPropEquals("uuid", getObject().getUuid());
		assertPropPresent("auditInfo");
	}
	
	@Override
	public String getDisplayProperty() {
		return getObject().getTag().getName();
	}
	
	@Override
	public String getUuidProperty() {
		return TagRestTestConstants.TAG_UUID;
	}
	
}
