package swtRefactored.model;

import java.util.ArrayList;

public class History {
	private ArrayList<String> records;

	public History() {
		records = new ArrayList<String>();
	}

	public void add(String value1, String operation, String value2, String result) {
		records.add(value1 + " " + operation + " " + value2 + " = " + result + ";");
	}

	public ArrayList<String> get() {
		return records;
	}
}
