package swtRefactored;

public class DTO {
	private static DTO instance;
	public String value1, value2, resultString;
	public int operationIndex;

	private DTO() {
		value1 = "";
		value2 = "";
		resultString = "";
		operationIndex = 0;
	}

	public static synchronized DTO getInstance() {
		if (instance == null) {
			instance = new DTO();
		}
		return instance;
	}
}
