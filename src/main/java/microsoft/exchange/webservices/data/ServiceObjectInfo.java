/**************************************************************************
 * copyright file="ServiceObjectInfo.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObjectInfo.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * ServiceObjectInfo contains metadata on how to map from an element name to a
 * ServiceObject type as well as how to map from a ServiceObject type to
 * appropriate constructors.
 */
class ServiceObjectInfo {

	/** The service object constructors with attachment param. */
	private Map<Class<?>, ICreateServiceObjectWithAttachmentParam> 
	serviceObjectConstructorsWithAttachmentParam;

	/** The service object constructors with service param. */
	private Map<Class<?>, ICreateServiceObjectWithServiceParam> 
	serviceObjectConstructorsWithServiceParam;

	/** The xml element name to service object class map. */
	private Map<String, Class<?>> xmlElementNameToServiceObjectClassMap;

	/**
	 * * Default constructor.
	 */
	protected ServiceObjectInfo() {
		this.xmlElementNameToServiceObjectClassMap =
			new HashMap<String, Class<?>>();
		this.serviceObjectConstructorsWithServiceParam = 
			new HashMap<Class<?>, ICreateServiceObjectWithServiceParam>();
		this.serviceObjectConstructorsWithAttachmentParam = 
			new HashMap<Class<?>, ICreateServiceObjectWithAttachmentParam>();

		this.initializeServiceObjectClassMap();
	}

	/***
	 * Initializes the service object class map. If you add a new ServiceObject
	 * subclass that can be returned by the Server, add the type to the class
	 * map as well as associated delegate(s) to call the constructor(s).
	 */
	private void initializeServiceObjectClassMap() {
		// Appointment
		this.addServiceObjectType(XmlElementNames.CalendarItem,
				Appointment.class, new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Appointment(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new Appointment(itemAttachment, isNew);
					}
				});

		// CalendarFolder
		this.addServiceObjectType(XmlElementNames.CalendarFolder,
				CalendarFolder.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new CalendarFolder(srv);
					}
				}, null);

		// Contact
		this.addServiceObjectType(XmlElementNames.Contact, Contact.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Contact(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new Contact(itemAttachment);
					}
				});

		// ContactsFolder
		this.addServiceObjectType(XmlElementNames.ContactsFolder,
				ContactsFolder.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new ContactsFolder(srv);
					}
				}, null);

		// ContactGroup
		this.addServiceObjectType(XmlElementNames.DistributionList,
				ContactGroup.class, new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new ContactGroup(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new ContactGroup(itemAttachment);
					}
				});
		
		// Conversation
		this.addServiceObjectType(XmlElementNames.Conversation,
				 Conversation.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Conversation(srv);
					}
				}, null);

		// EmailMessage
		this.addServiceObjectType(XmlElementNames.Message, EmailMessage.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new EmailMessage(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new EmailMessage(itemAttachment);
					}
				});

		// Folder
		this.addServiceObjectType(XmlElementNames.Folder, Folder.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Folder(srv);
					}
				}, null);

		// Item
		this.addServiceObjectType(XmlElementNames.Item, Item.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Item(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new Item(itemAttachment);
					}
				});

		// MeetingCancellation
		this.addServiceObjectType(XmlElementNames.MeetingCancellation,
				MeetingCancellation.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new MeetingCancellation(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new MeetingCancellation(itemAttachment);
					}
				});

		// MeetingMessage
		this.addServiceObjectType(XmlElementNames.MeetingMessage,
				MeetingMessage.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new MeetingMessage(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new MeetingMessage(itemAttachment);
					}
				});

		// MeetingRequest
		this.addServiceObjectType(XmlElementNames.MeetingRequest,
				MeetingRequest.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new MeetingRequest(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new MeetingRequest(itemAttachment);
					}
				});

		// MeetingResponse
		this.addServiceObjectType(XmlElementNames.MeetingResponse,
				MeetingResponse.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new MeetingResponse(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new MeetingResponse(itemAttachment);
					}
				});

		// PostItem
		this.addServiceObjectType(XmlElementNames.PostItem, PostItem.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new PostItem(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new PostItem(itemAttachment);
					}
				});

		// SearchFolder
		this.addServiceObjectType(XmlElementNames.SearchFolder,
				SearchFolder.class, new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new SearchFolder(srv);
					}
				}, null);

		// Task
		this.addServiceObjectType(XmlElementNames.Task, Task.class,
				new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new Task(srv);
					}
				}, new ICreateServiceObjectWithAttachmentParam() {
					public Object createServiceObjectWithAttachmentParam(
							ItemAttachment itemAttachment, boolean isNew)
							throws Exception {
						return new Task(itemAttachment);
					}
				});

		// TasksFolder
		this.addServiceObjectType(XmlElementNames.TasksFolder,
				TasksFolder.class, new ICreateServiceObjectWithServiceParam() {
					public Object createServiceObjectWithServiceParam(
							ExchangeService srv) throws Exception {
						return new TasksFolder(srv);
					}
				}, null);
	}

	/**
	 * * Adds specified type of service object to map.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param cls
	 *            the cls
	 * @param createServiceObjectWithServiceParam
	 *            the create service object with service param
	 * @param createServiceObjectWithAttachmentParam
	 *            the create service object with attachment param
	 */
	private void addServiceObjectType(
			String xmlElementName,
			Class<?> cls,
			ICreateServiceObjectWithServiceParam createServiceObjectWithServiceParam,
			ICreateServiceObjectWithAttachmentParam createServiceObjectWithAttachmentParam) {
		this.xmlElementNameToServiceObjectClassMap.put(xmlElementName, cls);
		this.serviceObjectConstructorsWithServiceParam.put(cls,
				createServiceObjectWithServiceParam);
		if (createServiceObjectWithAttachmentParam != null) {
			this.serviceObjectConstructorsWithAttachmentParam.put(cls,
					createServiceObjectWithAttachmentParam);
		}
	}

	/**
	 * * Return Dictionary that maps from element name to ServiceObject Type.
	 * 
	 * @return the xml element name to service object class map
	 */
	protected Map<String, Class<?>> getXmlElementNameToServiceObjectClassMap() {
		return this.xmlElementNameToServiceObjectClassMap;
	}

	/**
	 * * Return Dictionary that maps from ServiceObject Type to
	 * CreateServiceObjectWithServiceParam delegate with ExchangeService
	 * parameter.
	 * 
	 * @return the service object constructors with service param
	 */
	protected Map<Class<?>, ICreateServiceObjectWithServiceParam> 
			getServiceObjectConstructorsWithServiceParam() {
		return this.serviceObjectConstructorsWithServiceParam;
	}

	/**
	 * * Return Dictionary that maps from ServiceObject Type to
	 * CreateServiceObjectWithAttachmentParam delegate with ItemAttachment
	 * parameter.
	 * 
	 * @return the service object constructors with attachment param
	 */
	protected Map<Class<?>, ICreateServiceObjectWithAttachmentParam> 
			getServiceObjectConstructorsWithAttachmentParam() {
		return this.serviceObjectConstructorsWithAttachmentParam;
	}

	/***
	 * Set event to happen when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void addOnChangeEvent(
			ICreateServiceObjectWithAttachmentParam change) {
		onChangeList.add(change);
	}

	/***
	 * Remove the event from happening when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void removeChangeEvent(
			ICreateServiceObjectWithAttachmentParam change) {
		onChangeList.remove(change);
	}

	/** The on change list. */
	private List<ICreateServiceObjectWithAttachmentParam> onChangeList = 
		new ArrayList<ICreateServiceObjectWithAttachmentParam>();

	/** The on change list1. */
	private List<ICreateServiceObjectWithServiceParam> onChangeList1 = 
		new ArrayList<ICreateServiceObjectWithServiceParam>();

	/***
	 * Set event to happen when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void addOnChangeEvent(
			ICreateServiceObjectWithServiceParam change) {
		onChangeList1.add(change);
	}

	/***
	 * Remove the event from happening when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void removeChangeEvent(
			ICreateServiceObjectWithServiceParam change) {
		onChangeList1.remove(change);
	}

}
