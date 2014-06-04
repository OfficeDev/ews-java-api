/**************************************************************************
 * copyright file="SyncResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SyncResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * * Represents the base response class for synchronuization operations.
 * <typeparam name="TServiceObject">ServiceObject type.</typeparam> <typeparam
 * name="TChange">Change type.</typeparam>
 * 
 * @param <TServiceObject>
 *            the generic type
 * @param <TChange>
 *            the generic type
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class SyncResponse<TServiceObject extends ServiceObject, 
		TChange extends Change> extends
		 ServiceResponse {

	/** The changes. */
	private ChangeCollection<TChange> changes = new ChangeCollection<TChange>();

	/** The property set. */
	private PropertySet propertySet;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param propertySet
	 *            the property set
	 */
	protected SyncResponse(PropertySet propertySet) {
		super();
		this.propertySet = propertySet;
		EwsUtilities.EwsAssert(this.propertySet != null, "SyncResponse.ctor",
				"PropertySet should not be null");
	}

	/***
	 * Gets the name of the includes last in range XML element.
	 * 
	 * @return XML element name.
	 */
	protected abstract String getIncludesLastInRangeXmlElementName();

	/***
	 * Creates the change instance.
	 * 
	 * @return TChange instance
	 */
	protected abstract TChange createChangeInstance();

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws ServiceLocalException, Exception {
		this.changes.setSyncState(reader.readElementValue(
				XmlNamespace.Messages, XmlElementNames.SyncState));
		this.changes.setMoreChangesAvailable(!reader.readElementValue(
				Boolean.class, XmlNamespace.Messages, this
						.getIncludesLastInRangeXmlElementName()));

		reader.readStartElement(XmlNamespace.Messages, XmlElementNames.Changes);
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.isStartElement()) {
					TChange change = this.createChangeInstance();

					if (reader.getLocalName().equals(XmlElementNames.Create)) {
						change.setChangeType(ChangeType.Create);
					} else if (reader.getLocalName().equals(
							XmlElementNames.Update)) {
						change.setChangeType(ChangeType.Update);
					} else if (reader.getLocalName().equals(
							XmlElementNames.Delete)) {
						change.setChangeType(ChangeType.Delete);
					} else if (reader.getLocalName().equals(
							XmlElementNames.ReadFlagChange)) {
						change.setChangeType(ChangeType.ReadFlagChange);
					} else {
						reader.skipCurrentElement();
					}

					if (change != null) {
						reader.read();
						reader.ensureCurrentNodeIsStartElement();

						if (change.getChangeType().equals(ChangeType.Delete)
								|| change.getChangeType().equals(
										ChangeType.ReadFlagChange)) {
							change.setId(change.createId());
							change.getId().loadFromXml(reader,
									change.getId().getXmlElementName());

							if (change.getChangeType().equals(
									ChangeType.ReadFlagChange)) {
								reader.read();
								reader.ensureCurrentNodeIsStartElement();
								ItemChange itemChange = null;
								if(change instanceof ItemChange){
									itemChange = (ItemChange) change;
								}
								EwsUtilities
										.EwsAssert(
												itemChange != null,
												"SyncResponse." +
												"ReadElementsFromXml",
												"ReadFlagChange is only " +
												"valid on ItemChange");

								itemChange.setIsRead(reader.readElementValue(
										Boolean.class, XmlNamespace.Types,
										XmlElementNames.IsRead));
							}
						} else {

							change.setServiceObject(EwsUtilities
									.createEwsObjectFromXmlElementName(null,
											reader.getService(), reader
													.getLocalName()));

							change.getServiceObject().loadFromXml(reader, 
									true, /* clearPropertyBag */
							this.propertySet, this.getSummaryPropertiesOnly());
						}

						reader.readEndElementIfNecessary(XmlNamespace.Types,
								change.getChangeType().toString());

						this.changes.add(change);
					}
				}
			} while (!reader.isEndElement(XmlNamespace.Messages,
					XmlElementNames.Changes));
		} else {
			reader.read();
		}
	}

	/**
	 * * Gets a list of changes that occurred on the synchronized folder.
	 * 
	 * @return the changes
	 */
	public ChangeCollection<TChange> getChanges() {
		return this.changes;
	}

	/**
	 * * Gets a value indicating whether this request returns full or summary
	 * properties.
	 * 
	 * @return the summary properties only
	 */
	protected abstract boolean getSummaryPropertiesOnly();

}
