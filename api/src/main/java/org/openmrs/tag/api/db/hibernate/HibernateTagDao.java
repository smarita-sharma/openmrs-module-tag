package org.openmrs.tag.api.db.hibernate;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.tag.Tag;
import org.openmrs.tag.api.db.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tagDAO")
public class HibernateTagDao implements TagDAO {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Tag getTagByUuid(String uuid) {
		return (Tag) getSession().createCriteria(Tag.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	@Override
	public Tag getTag(Integer id) {
		return (Tag) this.sessionFactory.getCurrentSession().get(Tag.class, id);
	}
	
	@Override
	public List<Tag> getAllTags() {
		return getSession().createCriteria(Tag.class).list();
	}
	
	@Override
	public Tag getTag(String tagName) {
		return (Tag) getSession().createCriteria(Tag.class).add(Restrictions.eq("name", tagName)).uniqueResult();
	}
	
	@Override
	public Tag saveTag(Tag tag) {
		getSession().saveOrUpdate(tag);
		return tag;
	}
	
	@Override
	public void deleteTag(Tag tag) {
		getSession().delete(tag);
	}
	
	@Override
	public List<Tag> getTags(String searchPhrase, boolean exactMatch) {
		return exactMatch ? getSession().createCriteria(Tag.class).add(Restrictions.eq("name", searchPhrase)).list()
		        : getSession().createCriteria(Tag.class).add(Restrictions.ilike("name", "%" + searchPhrase + "%")).list();
	}
}
