/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.tag.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.tag.api.db.TagDAO;
import org.openmrs.tag.Tag;
import org.openmrs.tag.EntityTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tagDAO")
public class HibernateTagDao implements TagDAO {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * @see TagDAO#getTagByUuid(String)
	 */
	public EntityTag getTagByUuid(String uuid) {
		return (EntityTag) getSession().createCriteria(EntityTag.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	/**
	 * @see TagDAO#saveTag(EntityTag)
	 */
	public EntityTag saveTag(EntityTag tag) {
		getSession().saveOrUpdate(tag);
		return tag;
	}
	
	/**
	 * @see TagDAO#deleteTag(EntityTag)
	 */
	@Override
	public void deleteTag(EntityTag tag) {
		getSession().delete(tag);
	}
	
	/**
	 * @see TagDAO#getAllTags()
	 */
	@Override
	public List<String> getAllTags() {
		return getSession().createCriteria(Tag.class).setProjection(Projections.distinct(Projections.property("name")))
		        .list();
	}
	
	/**
	 * @see TagDAO#getTag(Integer)
	 */
	@Override
	public EntityTag getTag(Integer id) {
		return (EntityTag) getSession().get(EntityTag.class, id);
	}
	
	/**
	 * @see TagDAO#getTags(String, boolean)
	 */
	@Override
	public List<EntityTag> getTags(String tag, boolean exactMatch) {
		Criteria criteria = getSession().createCriteria(EntityTag.class);
		if (exactMatch) {
			return criteria.add(Restrictions.eq("tag", tag)).list();
		}
		return criteria.add(Restrictions.like("tag", tag, MatchMode.ANYWHERE)).list();
	}
	
	/**
	 * @see TagDAO#getTags(OpenmrsObject)
	 */
	@Override
	public List<EntityTag> getTags(OpenmrsObject openmrsObject) {
		Criteria criteria = getSession().createCriteria(Tag.class);
		criteria.add(Restrictions.eq("objectType", openmrsObject.getClass().getName()));
		criteria.add(Restrictions.eq("objectUuid", (openmrsObject.getUuid())));
		return criteria.list();
	}
	
	/**
	 * @see TagDAO#getTags(String, String)
	 */
	@Override
	public List<EntityTag> getTags(String objectType, String objectUuid) {
		Criteria criteria = getSession().createCriteria(Tag.class);
		criteria.add(Restrictions.eq("objectType", objectType));
		criteria.add(Restrictions.eq("objectUuid", objectUuid));
		return criteria.list();
	}
	
	/**
	 * @see TagDAO#getObject(Class, String)
	 */
	@Override
	public <T extends OpenmrsObject> T getObject(Class<T> objectType, String objectUuid) {
		Criteria criteria = getSession().createCriteria(objectType);
		return (T) criteria.add(Restrictions.eq("uuid", objectUuid).ignoreCase()).uniqueResult();
	}
	
	/**
	 * @see TagDAO#getTags(List, List)
	 */
	@Override
	public List<EntityTag> getTags(List<String> objectTypes, List<String> tags) {
		Criteria criteria = getSession().createCriteria(EntityTag.class);
		if (CollectionUtils.isNotEmpty(objectTypes)) {
			criteria.add(Restrictions.in("objectType", objectTypes));
		}
		criteria.add(Restrictions.in("tag", tags));
		return criteria.list();
	}
	
	/**
	 * @see TagDAO#getTag(String, String, String)
	 */
	@Override
	public EntityTag getTag(String objectType, String objectUuid, String tag) {
		Criteria criteria = getSession().createCriteria(EntityTag.class);
		criteria.add(Restrictions.eq("objectType", objectType));
		criteria.add(Restrictions.eq("objectUuid", objectUuid));
		criteria.add(Restrictions.eq("tag", tag));
		return (EntityTag) criteria.uniqueResult();
	}
}
