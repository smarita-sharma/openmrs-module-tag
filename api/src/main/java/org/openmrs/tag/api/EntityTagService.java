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

import org.openmrs.OpenmrsObject;
import org.openmrs.tag.TagConstants;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.OpenmrsService;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.openmrs.api.context.Context;

import java.util.List;

public interface EntityTagService extends OpenmrsService {
	
	EntityTag getEntityTagByUuid(String uuid);
	
	EntityTag getEntityTag(Integer tagId);
	
	EntityTag saveEntityTag(EntityTag tag);
	
	void deleteEntityTag(EntityTag tag);
	
	EntityTag addTag(OpenmrsObject openmrsObject, Tag tag);
	
	List<EntityTag> findEntityTags(String tagName, String objectType);
	
	List<EntityTag> getEntityTagsByObject(OpenmrsObject openmrsObject);
	
	List<EntityTag> getEntityTagsByObject(String objectUuid);
}
