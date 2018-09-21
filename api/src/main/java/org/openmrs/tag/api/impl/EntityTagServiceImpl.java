/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.tag.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.openmrs.tag.api.EntityTagService;
import org.openmrs.tag.api.TagService;
import org.openmrs.tag.api.db.EntityTagDAO;
import org.openmrs.util.OpenmrsClassLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional(readOnly = true)
public class EntityTagServiceImpl extends BaseOpenmrsService implements EntityTagService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private EntityTagDAO entityTagDAO;
	
	private TagService tagService;
	
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	public void setEntityTagDAO(EntityTagDAO entityTagDAO) {
		this.entityTagDAO = entityTagDAO;
	}
	
	@Override
	public EntityTag getEntityTagByUuid(String uuid) {
		return entityTagDAO.getEntityTagByUuid(uuid);
	}
	
	@Override
	public EntityTag getEntityTag(Integer tagId) {
		return entityTagDAO.getEntityTag(tagId);
	}
	
	@Override
	@Transactional(readOnly = false)
	public EntityTag saveEntityTag(EntityTag tag) {
		if (getObject(toClass(tag.getObjectType()), tag.getObjectUuid()) == null) {
			log.warn("Object does not exist in the the database");
		}
		return entityTagDAO.saveEntityTag(tag);
	}
	
	@Override
	public void deleteEntityTag(EntityTag tag) {
		entityTagDAO.deleteEntityTag(tag);
	}
	
	private <T extends OpenmrsObject> T getObject(Class<T> objectType, String objectUuid) {
		return entityTagDAO.getObject(objectType, objectUuid);
	}
	
	@Override
	@Transactional(readOnly = false)
	public EntityTag addTag(OpenmrsObject openmrsObject, Tag tag) {
		if (hasTag(openmrsObject, tag)) {
			log.warn("duplicate Tag for " + openmrsObject);
		} else {
			return saveEntityTag(new EntityTag(tag, openmrsObject.getUuid(), openmrsObject.getClass().getName()));
		}
		return null;
	}
	
	@Override
	public List<EntityTag> findEntityTags(String tagName, String objectType) {
		List<Tag> tags = tagService.getTags(tagName, true);
		if (tags.size() != 1) {
			return Collections.emptyList();
		}
		return entityTagDAO.getEntityTags(tags.get(0), objectType);
	}
	
	public boolean hasTag(OpenmrsObject openmrsObject, Tag tag) {
		return entityTagDAO.getEntityTag(tag, openmrsObject) != null;
	}
	
	@Override
	public List<EntityTag> getEntityTagsByObject(OpenmrsObject openmrsObject) {
		return entityTagDAO.getEntityTagsByObjectUuid(openmrsObject.getUuid());
	}
	
	@Override
	public List<EntityTag> getEntityTagsByObject(String objectUuid) {
		return entityTagDAO.getEntityTagsByObjectUuid(objectUuid);
	}
	
	private <T extends OpenmrsObject> Class<T> toClass(String className) {
		try {
			return (Class<T>) OpenmrsClassLoader.getInstance().loadClass(className);
		}
		catch (ClassNotFoundException e) {
			throw new APIException(e);
		}
	}
}
