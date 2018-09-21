/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * <p>
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * <p>
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.tag.web.rest.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.RepHandler;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.annotation.SubResource;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.*;
import org.openmrs.module.webservices.rest.web.response.ConversionException;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.tag.EntityTag;
import org.openmrs.tag.Tag;
import org.openmrs.tag.api.EntityTagService;
import org.openmrs.tag.api.TagService;

@Resource(name = RestConstants.VERSION_1 + "/tag", supportedClass = Tag.class, supportedOpenmrsVersions = { "1.11.*",
        "1.12.*", "2.0.*", "2.1.*" })
public class TagResource extends DataDelegatingCrudResource<Tag> {
	
	/**
	 * @see DelegatingResourceHandler#newDelegate()
	 */
	@Override
	public Tag newDelegate() {
		return new Tag();
	}
	
	/**
	 * @see BaseDelegatingResource#getByUniqueId(String)
	 */
	@Override
	public Tag getByUniqueId(String uuid) {
		return Context.getService(TagService.class).getTagByUuid(uuid);
	}
	
	/**
	 * @see DelegatingResourceHandler#save(Object)
	 */
	@Override
	public Tag save(Tag tag) {
		return getService().saveTag(tag);
	}
	
	/**
	 * @see BaseDelegatingResource#delete(Object, String, RequestContext)
	 */
	@Override
	protected void delete(Tag tag, String reason, RequestContext context) {
		throw new ResourceDoesNotSupportOperationException("delete not allowed on tag");
	}
	
	/**
	 * @see BaseDelegatingResource#purge(Object, RequestContext)
	 */
	@Override
	public void purge(Tag tag, RequestContext context) {
		getService().deleteTag(tag);
	}
	
	/**
	 * Returns the DefaultRepresentation
	 */
	@RepHandler(DefaultRepresentation.class)
	public SimpleObject asDef(Tag delegate) throws ConversionException {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("name");
		description.addProperty("uuid");
		
		description.addSelfLink();
		description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
		return convertDelegateToRepresentation(delegate, description);
	}
	
	/**
	 * Returns the FullRepresentation
	 */
	@RepHandler(FullRepresentation.class)
	public SimpleObject asFull(Tag delegate) throws ConversionException {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addProperty("name");
		//TODO Fix this
		//		description.addProperty("auditInfo");
		return convertDelegateToRepresentation(delegate, description);
	}
	
	/**
	 * @see BaseDelegatingResource#getRepresentationDescription(Representation)
	 */
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof RefRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("display");
			description.addProperty("uuid");
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("name");
			//TODO Fix this
			//			description.addProperty("auditInfo");
			description.addSelfLink();
			return description;
		}
		return null;
	}
	
	/**
	 * Property getter for 'display'
	 */
	@PropertyGetter("display")
	public String getDisplay(Tag instance) {
		return instance.getName();
	}
	
	/**
	 * @return the TagService
	 */
	private TagService getService() {
		return Context.getService(TagService.class);
	}
	
	/**
	 * @see BaseDelegatingResource#getCreatableProperties()
	 */
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		
		description.addRequiredProperty("name");
		
		return description;
	}
	
	/**
	 * @see DelegatingCrudResource#doGetAll(RequestContext)
	 */
	@Override
	protected NeedsPaging<EntityTag> doGetAll(RequestContext context) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	/**
	 * Method to obtain the Uri of any OpenrmrsObject
	 * 
	 * @param objectType the Java class name of the object
	 * @param objectUuid the Uuid of the object
	 * @return the Uri of the given object
	 */
	private String getUriOfObject(String objectType, String objectUuid) {
		Class<?> className = null;
		try {
			className = Context.loadClass(objectType);
		}
		catch (ClassNotFoundException e) {}
		BaseDelegatingResource resource = (BaseDelegatingResource) Context.getService(RestService.class)
		        .getResourceBySupportedClass(className);
		return resource.getUri(resource.getByUniqueId(objectUuid));
	}
	
	/**
	 * Extracts resource name from a resource handler
	 * 
	 * @return resource name
	 */
	private String getResourceName(String objectType) {
		
		Class<?> className = null;
		try {
			className = Context.loadClass(objectType);
		}
		catch (ClassNotFoundException e) {}
		DelegatingResourceHandler resourceHandler = (DelegatingResourceHandler<?>) Context.getService(RestService.class)
		        .getResourceBySupportedClass(className);
		Resource annotation = resourceHandler.getClass().getAnnotation(Resource.class);
		
		String resourceName = null;
		if (annotation != null) {
			resourceName = annotation.name();
		} else {
			SubResource subResourceAnnotation = resourceHandler.getClass().getAnnotation(SubResource.class);
			if (subResourceAnnotation != null) {
				resourceName = subResourceAnnotation.path();
			}
		}
		return resourceName;
	}
	
	/**
	 * @see DelegatingCrudResource#doSearch(RequestContext)
	 */
	//	@Override
	//	protected PageableResult doSearch(RequestContext context) {
	//		String tagName = context.getRequest().getParameter("tag");
	//		String objectType = context.getRequest().getParameter("objectType");
	//		return new AlreadyPaged<EntityTag>(context, getService().getTags(tagName, objectType), false);
	//	}
	
}
