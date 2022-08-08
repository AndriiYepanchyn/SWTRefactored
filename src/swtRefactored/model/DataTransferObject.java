package swtRefactored.model;

import swtRefactored.mathOperations.MathOperation;

public class DataTransferObject {
	private static DataTransferObject instance;
	public String value1, value2, resultString;
	public MathOperation operation;
	public History records;

	private DataTransferObject() {
		value1 = "";
		value2 = "";
		resultString = "";
		operation = null;
		records = new History();
	}

	public static synchronized DataTransferObject getInstance() {
		if (instance == null) {
			instance = new DataTransferObject();
		}
		return instance;
	}
}
