/**************************************************************************
 * copyright file="ComplexProperty.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ComplexProperty.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/***
 * Represents a property that can be sent to or retrieved from EWS.
 * 
 * 
 */
@SuppressWarnings("unchecked")
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class ComplexProperty implements ISelfValidate,ComplexFunctionDelegate {

	/** The xml namespace. */
	private XmlNamespace xmlNamespace = XmlNamespace.Types;

	/**
	 * * Initializes a new instance.
	 */
	protected ComplexProperty() {

	}

	/***
	 * Gets the namespace.
	 * 
	 * @return the namespace.
	 */
	protected XmlNamespace getNamespace() {
		return xmlNamespace;
	}

	/***
	 * Sets the namespace.
	 * 
	 * @param xmlNamespace
	 *            the namespace.
	 */
	protected void setNamespace(XmlNamespace xmlNamespace) {
		this.xmlNamespace = xmlNamespace;
	}

	/***
	 * Instance was changed.
	 */
	protected void changed() {
		if (!onChangeList.isEmpty()) {
			for (IComplexPropertyChangedDelegate change : onChangeList) {
				change.complexPropertyChanged(this);
			}
		}
	}

	/**
	 * * Sets value of field.
	 * 
	 * @param <T>
	 *            Field type.
	 * @param field
	 *            The field.
	 * @param value
	 *            The value.
	 * @return true, if successful
	 */
	protected <T> boolean canSetFieldValue(T field, T value) {
		boolean applyChange;
		if (field == null) {
			applyChange = value != null;
		} else {
			if (field instanceof Comparable<?>) {
				Comparable<T> c = (Comparable<T>)field;
				if(value != null){
				applyChange = c.compareTo(value) != 0;
				} else {
					applyChange = false;
				}
			} else {
				applyChange = true;
			}
		}
		return applyChange;
	}

	/***
	 * Clears the change log.
	 */
	protected void clearChangeLog() {
	}

	/**
	 * * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
	}

	/**
	 * * Reads the text value from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws Exception {
	}

	/**
	 * * Tries to read element from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		return false;
	}

	/** 
     *  Tries to read element from XML to patch this property.
     * 
     * @param reader The reader. 
     *  True if element was read.
     * 
     * */
     
    protected boolean tryReadElementFromXmlToPatch(EwsServiceXmlReader reader) throws Exception
    {
        return false;
    }
	
	/**
	 * * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
	}

	/**
	 * * Writes elements to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param xmlNamespace
	 *            the xml namespace
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader,
			XmlNamespace xmlNamespace, String xmlElementName) throws Exception {

		/*reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);
		this.readAttributesFromXml(reader);

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				switch (reader.getNodeType().nodeType) {
				case XMLNodeType.START_ELEMENT:
					if (!this.tryReadElementFromXml(reader)) {
						reader.skipCurrentElement();
					}
					break;
				case XMLNodeType.CHARACTERS:
					this.readTextValueFromXml(reader);
					break;
				}
			} while (!reader.isEndElement(xmlNamespace, xmlElementName));
		} else {
			// Adding this code to skip the END_ELEMENT of an Empty Element.
			reader.read();
			reader.isEndElement(xmlNamespace, xmlElementName);
		} */
		
		this.internalLoadFromXml(reader, xmlNamespace, xmlElementName, false);
                   
        		
	}
	
	 /**  
      * Loads from XML to update this property.
      * 
      *@param reader The reader. 
      *@param xmlElementName Name of the XML element. 
	 * @throws Exception 
      */
	
	protected  void updateFromXml(EwsServiceXmlReader reader, String xmlElementName) throws Exception
 {
		this.updateFromXml(reader, this.getNamespace(), xmlElementName);

	}
	
	 /** 
      * Loads from XML to update itself.
      * 
      *@param reader The reader. 
      *@param xmlNamespace The XML namespace. 
      *@param xmlElementName Name of the XML element. 
	 * @param complexFunctionDelegate 
      */
    protected  void updateFromXml(
        EwsServiceXmlReader reader,
        XmlNamespace xmlNamespace,
        String xmlElementName) throws Exception
 {
		this.internalupdateLoadFromXml(reader, xmlNamespace, xmlElementName,
				false);
	}

    /**
     * 
     *  Loads from XML
     *@param reader The Reader.
     *@param xmlNamespace The Xml NameSpace.
     *@param xmlElementName  The Xml ElementName
     *@param readAction   The Reade Action.
     */
    private void internalLoadFromXml(
        EwsServiceXmlReader reader,
        XmlNamespace xmlNamespace,
        String xmlElementName,          
        boolean readValue)throws Exception
 {
		reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);

		this.readAttributesFromXml(reader);

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				switch (reader.getNodeType().nodeType) {
				case XMLNodeType.START_ELEMENT:
					if (!this.tryReadElementFromXml(reader)) {
						reader.skipCurrentElement();
					}
					break;
				case XMLNodeType.CHARACTERS:
					this.readTextValueFromXml(reader);
					break;
				}
			} while (!reader.isEndElement(xmlNamespace, xmlElementName));
		} else {
			// Adding this code to skip the END_ELEMENT of an Empty Element.
			reader.read();
			reader.isEndElement(xmlNamespace, xmlElementName);
		}
	} 
        
        
    
    
    
    
    private void internalupdateLoadFromXml(
            EwsServiceXmlReader reader,
            XmlNamespace xmlNamespace,
            String xmlElementName,          
            boolean readValue)throws Exception
 {
		reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);

		this.readAttributesFromXml(reader);

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				switch (reader.getNodeType().nodeType) {
				case XMLNodeType.START_ELEMENT:
					if (!this.tryReadElementFromXmlToPatch(reader)) {
						reader.skipCurrentElement();
					}
					break;
				case XMLNodeType.CHARACTERS:
					this.readTextValueFromXml(reader);
					break;
				}
			} while (!reader.isEndElement(xmlNamespace, xmlElementName));
		}
	}
   
    
	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader,
			String xmlElementName)
			throws Exception {
		this.loadFromXml(reader, this.getNamespace(), xmlElementName);
	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param xmlNamespace
	 *            The XML namespace.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer,
			XmlNamespace xmlNamespace, String xmlElementName) throws Exception {
		writer.writeStartElement(xmlNamespace, xmlElementName);
		this.writeAttributesToXml(writer);
		this.writeElementsToXml(writer);
		writer.writeEndElement();
	}

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
			throws Exception {
		this.writeToXml(writer, this.getNamespace(), xmlElementName);
	}

	/***
	 * Change events occur when property changed.
	 */
	private List<IComplexPropertyChangedDelegate> onChangeList = 
		new ArrayList<IComplexPropertyChangedDelegate>();

	/***
	 * Set event to happen when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void addOnChangeEvent(
			IComplexPropertyChangedDelegate change) {
		onChangeList.add(change);
	}

	/***
	 * Remove the event from happening when property changed.
	 * 
	 * @param change
	 *            change event
	 */
	protected void removeChangeEvent(
			IComplexPropertyChangedDelegate change) {
		onChangeList.remove(change);
	}

	/**
	 * * Clears change events list.
	 */
	protected void clearChangeEvents() {
		onChangeList.clear();
	}

	/**
	 * * Implements ISelfValidate.validate. Validates this instance.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception
	 *             the exception
	 */
	public void validate() throws ServiceValidationException, Exception {
		this.internalValidate();

	}

	/**
	 * * Validates this instance.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception 
	 */
	protected void internalValidate() 
	throws ServiceValidationException, Exception {
	}
	
	
	public Boolean func(EwsServiceXmlReader reader)
	 throws Exception {
		if (!this.tryReadElementFromXml(reader)) 
			return true;
		else return false;
	
	}
		
}
