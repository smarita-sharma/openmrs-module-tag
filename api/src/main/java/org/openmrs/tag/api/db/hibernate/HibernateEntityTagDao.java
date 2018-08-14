/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.tag.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.tag.api.db.EntityTagDAO;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("entityTagDAO")
public class HibernateEntityTagDao implements EntityTagDAO {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public EntityTag getEntityTagByUuid(String uuid) {
		return (EntityTag) getSession().createCriteria(EntityTag.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	@Override
	public EntityTag getEntityTag(Integer id) {
		return (EntityTag) getSession().get(EntityTag.class, id);
	}
	
	@Override
	public List<EntityTag> getEntityTags(Tag tag) {
		return getSession().createCriteria(EntityTag.class).add(Restrictions.eq("tag", tag)).list();
	}
	
	@Override
	public List<EntityTag> getEntityTags(Tag tag, String objectType) {
		return getSession().createCriteria(EntityTag.class).add(Restrictions.eq("tag", tag))
		        .add(Restrictions.eq("objectType", objectType)).list();
	}
	
	@Override
	public List<EntityTag> getEntityTagsByObjectUuid(String objectUuid) {
		return getSession().createCriteria(EntityTag.class).add(Restrictions.eq("objectUuid", objectUuid)).list();
	}
	
	@Override
	public EntityTag getEntityTag(Tag tag, OpenmrsObject openmrsObject) {
		return null;
	}
	
	@Override
	public EntityTag saveEntityTag(EntityTag entityTag) {
		getSession().saveOrUpdate(entityTag);
		return entityTag;
	}
	
	@Override
	public void deleteEntityTag(EntityTag entityTag) {
		getSession().delete(entityTag);
	}
	
	@Override
	public <T extends OpenmrsObject> T getObject(Class<T> objectType, String objectUuid) {
		Criteria criteria = getSession().createCriteria(objectType);
		return (T) criteria.add(Restrictions.eq("uuid", objectUuid).ignoreCase()).uniqueResult();
	}
}
