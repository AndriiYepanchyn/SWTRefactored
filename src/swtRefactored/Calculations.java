package swtRefactored;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculations {
	private static ArrayList<String> strHistory = new ArrayList<>();
	protected static String[] op = { "NONE", "+", "-", "\u002A", "\u002F", "POWER", "SQRT" };

	public static String proceed(String v1, String v2, int operation) throws IllegalArgumentException {
		BigDecimal result = new BigDecimal(0);
		if (operation == 0) {
			result = BigDecimal.valueOf(0);
			return result.toString();
		}
		if (operation == 6) {
			BigDecimal value1 = new BigDecimal(v1);
			if (value1.compareTo(BigDecimal.ZERO) < 0) {
				addHistoryRecord("", "(" + value1.toString() + ")", operation, "Illegal operation");
				return "Illegal operation";
			} else {
				result = trim(BigDecimal.valueOf(Math.sqrt(value1.doubleValue())));
				addHistoryRecord("", "(" + value1.toString() + ")", operation, result.toString());
			}
			return result.toString();
		}

		BigDecimal value1 = new BigDecimal(v1);
		BigDecimal value2 = new BigDecimal(v2);
		switch (operation) {
		case 1: { // ADD
			result = trim(value1.add(value2));
			addHistoryRecord(value1.toString(), value2.toString(), operation, result.toString());
			break;
		}

		case 2: { // SUBTRACT
			result = trim(value1.subtract(value2));
			addHistoryRecord(value1.toString(), value2.toString(), operation, result.toString());
			break;
		}

		case 3: {// MULTIPLY
			result = trim(value1.multiply(value2));
			addHistoryRecord(value1.toString(), value2.toString(), operation, result.toString());
			break;
		}

		case 4: { // DIVIDE
			if (value2.compareTo(BigDecimal.ZERO) == 0) {
				addHistoryRecord(value1.toString(), value2.toString(), operation, "Dividing to zero");
				return "Dividing to zero";
			} else {
				result = trim(value1.divide(value2, 30, RoundingMode.CEILING));
				addHistoryRecord(value1.toString(), value2.toString(), operation, result.toString());
			}
			break;
		}

		case 5: {// POWER
			int i = value2.intValue();
			try {
				result = trim(value1.pow(i));
				addHistoryRecord(value1.toString(), value2.toString(), operation, result.toString());
			} catch (ArithmeticException e) {
				System.out.println("Exception in: " + value1 + "power " + value2);
			}
			break;
		}
		}

		return result.toString();
	}

	public static void addHistoryRecord(String value1, String value2, int i, String result) {
		strHistory.add(value1 + " " + op[i] + " " + value2 + " = " + result + ";");
	}

	public static String getHTMLData() {
		String beginString = "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>\n"
				+ "<H3>===Operation history ===<p> </p></h3> \n" + "<ol>";
		String finishString = "</ol><h4><p>===End of history records===</P></h4></BODY></HTML>";
		StringBuilder answer = new StringBuilder(beginString);

		for (String a : strHistory) {
			answer.append("<li> Operation:  " + a + "</li>\n");
		}
		answer.append(finishString);

		return answer.toString();
	}

	public static BigDecimal trim(BigDecimal input) {
		String answer = input.toString();

		while (answer.contains(".") && answer.endsWith("0")) {
			answer = answer.substring(0, answer.lastIndexOf("0"));
			if (answer.endsWith(".0")) {
				answer = answer.substring(0, answer.lastIndexOf("."));
			}
		}
		Pattern p = Pattern.compile("0E-");
		Matcher m = p.matcher(answer);
		if (m.find()) {
			answer = "0";
		}
		return new BigDecimal(answer);
	}
}