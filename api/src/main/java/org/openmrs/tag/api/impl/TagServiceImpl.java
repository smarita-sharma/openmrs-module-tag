package org.openmrs.tag.api.impl;

import org.openmrs.tag.Tag;
import org.openmrs.tag.api.TagService;
import org.openmrs.tag.api.db.TagDAO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {
	
	private TagDAO tagDAO;
	
	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	
	@Override
	public Tag getTagByUuid(String uuid) {
		return tagDAO.getTagByUuid(uuid);
	}
	
	@Override
	public Tag saveTag(Tag tag) {
		return tagDAO.saveTag(tag);
	}
	
	@Override
	public void deleteTag(Tag tag) {
		tagDAO.deleteTag(tag);
	}
	
	@Override
	public Tag getTag(Integer tagId) {
		return tagDAO.getTag(tagId);
	}
	
	@Override
	public List<Tag> getTags(String searchPhrase, boolean exactMatch) {
		return tagDAO.getTags(searchPhrase, exactMatch);
	}
	
	@Override
	public List<Tag> getAllTags() {
		return tagDAO.getAllTags();
	}
}
