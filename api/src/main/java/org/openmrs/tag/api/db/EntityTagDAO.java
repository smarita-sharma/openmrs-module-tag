/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.tag.api.db;

import org.openmrs.OpenmrsObject;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.openmrs.api.APIException;

import java.util.List;

public interface EntityTagDAO {
	
	EntityTag getEntityTagByUuid(String uuid);
	
	EntityTag getEntityTag(Integer uuid);
	
	List<EntityTag> getEntityTags(Tag tag);
	
	List<EntityTag> getEntityTags(Tag tag, String objectType);
	
	List<EntityTag> getEntityTagsByObjectUuid(String objectUuid);
	
	EntityTag getEntityTag(Tag tag, OpenmrsObject openmrsObject);
	
	EntityTag saveEntityTag(EntityTag entityTag);
	
	void deleteEntityTag(EntityTag entityTag);
	
	<T extends OpenmrsObject> T getObject(Class<T> objectType, String objectUuid);
}
