package mathOperations;

import java.math.BigDecimal;

public class Power extends MathOperation {

	public Power(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		BigDecimal value1 = null;
		BigDecimal value2 = null;
		try {
		value1 = new BigDecimal(v1);
		value2 = new BigDecimal(v2);
		int i = value2.intValue();
		return trim(value1.pow(i)).toString();
		} catch (ArithmeticException e) {
			return "Exception in: " + value1 + "power " + value2;
		}
		catch (NumberFormatException e) {
			return "Input is invalid";
		}
	}

}
