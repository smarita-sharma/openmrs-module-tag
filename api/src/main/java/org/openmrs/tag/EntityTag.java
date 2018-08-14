package org.openmrs.tag;

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

import org.openmrs.Auditable;
import org.openmrs.BaseOpenmrsData;

import java.io.Serializable;

/**
 * EntityTag is a textual label which can be applied any OpenmrsObject. A Separate tag_tag table is
 * maintained to store information regarding Tags. A Tag Object contains three important properties;
 * <i>objectType</i>, the java class name of the OpenmrsObject, <i>objectUuid</i>, the unique
 * identifier of the object, and <i>tag</i> the text label to be attached to the OpenmrsObject.
 */

public class EntityTag extends BaseOpenmrsData implements Serializable, Auditable {
	
	private static final long serialVersionUID = 6713376997114869435L;
	
	public Integer getEntityTagId() {
		return entityTagId;
	}
	
	public void setEntityTagId(Integer entityTagId) {
		this.entityTagId = entityTagId;
	}
	
	private Integer entityTagId;
	
	private Tag tag;
	
	private String objectUuid;
	
	private String objectType;
	
	public EntityTag() {
	}
	
	public EntityTag(Tag tag, String objectUuid, String objectType) {
		this.tag = tag;
		this.objectUuid = objectUuid;
		this.objectType = objectType;
	}
	
	@Override
	public Integer getId() {
		return this.getEntityTagId();
	}
	
	@Override
	public void setId(Integer tagId) {
		this.setEntityTagId(tagId);
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public String getObjectUuid() {
		return objectUuid;
	}
	
	public void setObjectUuid(String objectUuid) {
		this.objectUuid = objectUuid;
	}
	
	public String getObjectType() {
		return objectType;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
}
