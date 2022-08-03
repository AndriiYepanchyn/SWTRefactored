package swtRefactored;

import java.util.ArrayList;

public class History {
	ArrayList<String> records;
	public History(){
		records = new ArrayList<String>();
	}
	public void add(String value1, String operation, String value2, String result) {
		records.add(value1 + " " + operation + " " + value2 + " = " + result + ";");
	}
	
	public String getHistoryAsHTML() {
		String beginString = "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>\n"
				+ "<H3>===Operation history ===<p> </p></h3> \n" + "<ol>";
		String finishString = "</ol><h4><p>===End of history records===</P></h4></BODY></HTML>";
		StringBuilder answer = new StringBuilder(beginString);

		for (String a : records) {
			answer.append("<li> Operation:  " + a + "</li>\n");
		}
		answer.append(finishString);

		return answer.toString();
	}
}
