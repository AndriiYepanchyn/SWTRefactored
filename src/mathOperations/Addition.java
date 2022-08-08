package mathOperations;

import java.math.BigDecimal;

public class Addition extends MathOperation {

	public Addition(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		try {
		BigDecimal value1 = v1 == null? new BigDecimal(0): new BigDecimal(v1);
		BigDecimal value2 = v2 == null? new BigDecimal(0): new BigDecimal(v2);
		return trim(value1.add(value2)).toString();
		} catch (NumberFormatException exception) {
			return "Input is invalid";
		}
	}

}
