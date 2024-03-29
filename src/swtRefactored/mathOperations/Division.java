package swtRefactored.mathOperations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division extends MathOperation {

	public Division(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		try {
			BigDecimal value1 = new BigDecimal(v1);
			BigDecimal value2 = new BigDecimal(v2);
			if (value2.compareTo(BigDecimal.ZERO) == 0) {
				return "Dividing to zero";
			} else {
				return trim(value1.divide(value2)).toString();
			}
		} catch (NumberFormatException exception) {
			return "Input is invalid";
		}
	}

}
