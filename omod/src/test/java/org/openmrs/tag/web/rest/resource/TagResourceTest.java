package org.openmrs.tag.web.rest.resource;

import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.tag.api.TagService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResourceTest;
import org.openmrs.tag.web.rest.TagRestTestConstants;

public class TagResourceTest extends BaseDelegatingResourceTest<TagResource, EntityTag> {
	
	private static final String TAG_RESOURCE_DATASET = "TagServiceDataset.xml";
	
	@Before
	public void setup() throws Exception {
		executeDataSet(TAG_RESOURCE_DATASET);
	}
	
	@Override
	public EntityTag newObject() {
		return Context.getService(TagService.class).getTagByUuid(getUuidProperty());
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
		return getObject().getTag();
	}
	
	@Override
	public String getUuidProperty() {
		return TagRestTestConstants.TAG_UUID;
	}
	
}
