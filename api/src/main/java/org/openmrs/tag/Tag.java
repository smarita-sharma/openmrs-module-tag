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

import org.openmrs.BaseOpenmrsData;

import java.io.Serializable;

/**
 * Tag is a textual label which can be applied any OpenmrsObject. A Separate tag_tag table is
 * maintained to store information regarding Tags. A Tag Object contains three important properties;
 * <i>objectType</i>, the java class name of the OpenmrsObject, <i>objectUuid</i>, the unique
 * identifier of the object, and <i>tag</i> the text label to be attached to the OpenmrsObject.
 */

public class Tag extends BaseOpenmrsData implements Serializable {
	
	private static final long serialVersionUID = 6713376997114869435L;
	
	private Integer tagId;
	
	private String name;
	
	public Tag() {
	}
	
	public Tag(String name) {
		this.name = name;
	}
	
	@Override
	public Integer getId() {
		return tagId;
	}
	
	@Override
	public void setId(Integer tagId) {
		this.tagId = tagId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
