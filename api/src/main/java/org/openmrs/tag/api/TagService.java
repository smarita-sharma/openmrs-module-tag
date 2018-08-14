package org.openmrs.tag.api;

import org.openmrs.tag.Tag;

import java.util.List;

public interface TagService {
	
	Tag getTagByUuid(String uuid);
	
	Tag saveTag(Tag tag);
	
	void deleteTag(Tag tag);
	
	Tag getTag(Integer tagId);
	
	List<Tag> getTags(String searchPhrase, boolean exactMatch);
	
	List<Tag> getAllTags();
}
