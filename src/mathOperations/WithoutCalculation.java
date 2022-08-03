package mathOperations;

public class WithoutCalculation extends MathOperation {

	public WithoutCalculation(String shownAs) {
		super(shownAs);
	}

	@Override
	public String calculate(String v1, String v2) {
		return "0";
	}

}
