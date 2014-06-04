/**************************************************************************
 * copyright file="RuleOperationError.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RuleOperationError.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Iterator;

/***
 * Defines the RuleOperationError class. 
 */
public final class RuleOperationError extends 
ComplexProperty implements Iterable<RuleError> {
	/**
	 * Index of the operation mapping to the error.
	 */
	private int operationIndex;

	/**
	 * RuleOperation object mapping to the error.
	 */
	private RuleOperation operation;

	/**
	 * RuleError Collection.
	 */
	private RuleErrorCollection ruleErrors;

	/**
	 * Initializes a new instance of the RuleOperationError class.
	 */
	protected RuleOperationError() {
		super();
	}

	/**
	 * Gets the operation that resulted in an error.
	 * @return operation
	 */
	public RuleOperation getOperation() {
		return this.operation; 
	}

	/**
	 * Gets the number of rule errors in the list.
	 * @return count
	 */
	public int getCount() {
		return this.ruleErrors.getCount(); 
	}

	/**
	 * Gets the rule error at the specified index.
	 * @return Index
	 * @throws ArgumentOutOfRangeException 
	 */
	public RuleError getRuleError(int index) 
	throws ArgumentOutOfRangeException {
		if (index < 0 || index >= this.getCount()) {
			throw new ArgumentOutOfRangeException("index");
		}

		return this.ruleErrors.getPropertyAtIndex(index);
		
	}


	/**
	 * Tries to read element from XML.
	 * @return true
	 * @throws Exception 
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
	throws Exception {
		if(reader.getLocalName().equals(XmlElementNames.OperationIndex)) {
			this.operationIndex = reader.readElementValue(Integer.class);
			return true;
		}
		else if(reader.getLocalName().equals(XmlElementNames.ValidationErrors)) {
			this.ruleErrors = new RuleErrorCollection();
			this.ruleErrors.loadFromXml(reader, reader.getLocalName());
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Set operation property by the index of a given opeation enumerator.
	 */
	protected void setOperationByIndex(Iterator<RuleOperation> operations) {
		for (int i = 0; i <= this.operationIndex; i++) {
			operations.next();
		}
		this.operation = operations.next();
	}
	
	/***
	 * Gets an iterator that iterates through the elements of the collection.
	 * 
	 * @return An Iterator for the collection.
	 */	
	public Iterator<RuleError> iterator() {
		return this.ruleErrors.iterator();
	}
}
