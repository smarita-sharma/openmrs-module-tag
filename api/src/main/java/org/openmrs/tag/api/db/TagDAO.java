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

public interface TagDAO {
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTagByUuid(String)
	 */
	EntityTag getTagByUuid(String uuid);
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTag(Integer)
	 */
	EntityTag getTag(Integer id);
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTags(String, boolean)
	 */
	List<EntityTag> getTags(String tag, boolean exactMatch);
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTags(OpenmrsObject)
	 */
	List<EntityTag> getTags(OpenmrsObject openmrsObject);
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTags(String, String)
	 */
	List<EntityTag> getTags(String objectType, String objectUuid);
	
	/**
	 * @see org.openmrs.tag.api.TagService#saveTag(EntityTag)
	 */
	EntityTag saveTag(EntityTag tag);
	
	/**
	 * @see org.openmrs.tag.api.TagService#purgeTag(EntityTag)
	 */
	void deleteTag(EntityTag tag);
	
	/**
	 * @see org.openmrs.tag.api.TagService#getAllTags()
	 */
	List<String> getAllTags();
	
	/**
	 * @see org.openmrs.tag.api.TagService#getTags(List, List)
	 */
	List<EntityTag> getTags(List<String> objectType, List<String> tags);
	
	/**
	 * @see org.openmrs.tag.api.impl.TagServiceImpl#getObject(Class, String)
	 */
	<T extends OpenmrsObject> T getObject(Class<T> objectType, String objectUuid);
	
	/**
	 * Gets a unique tag object from the database having matching type, object uuid, and tag
	 * 
	 * @param objectType the java class name of the Object
	 * @param objectUuid the Uuid of the Object
	 * @param tag the textual label (tag) on that object
	 * @return the Tag object with matching parameters
	 */
	EntityTag getTag(String objectType, String objectUuid, String tag);
}
