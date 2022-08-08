package mathOperations;

import java.math.BigDecimal;

public class Subtraction extends MathOperation {

	public Subtraction(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		try {
			BigDecimal value1 = new BigDecimal(v1);
			BigDecimal value2 = new BigDecimal(v2);
			return trim(value1.subtract(value2)).toString();
		} catch (NumberFormatException exception) {
			return "Input is invalid";
		}
	}
}
