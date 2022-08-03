package swtRefactored;

import mathOperations.MathOperation;

public class DTO {
	private static DTO instance;
	public String value1, value2, resultString;
	public MathOperation operation;
	public History records;

	private DTO() {
		value1 = "";
		value2 = "";
		resultString = "";
		operation = null;
		records = new History();
	}

	public static synchronized DTO getInstance() {
		if (instance == null) {
			instance = new DTO();
		}
		return instance;
	}
}
