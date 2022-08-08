package mathOperations;

import java.math.BigDecimal;

public class Sqrt extends MathOperation {

	public Sqrt(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		checkInput(v1,v2);
		BigDecimal value1 = new BigDecimal(v1);
		if (value1.compareTo(BigDecimal.ZERO) < 0) {
			return "Illegal operation";
		} else {
			return trim(BigDecimal.valueOf(Math.sqrt(value1.doubleValue()))).toString();
		}
	}
}
