package mathOperations;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MathOperation {
public String shownAs;

public MathOperation(String shownAs) {
	this.shownAs = shownAs;
}

public abstract String calculate(String v1, String v2);

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
