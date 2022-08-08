package swtRefactored.mathOperations;

import java.util.HashMap;
import java.util.Map;

public class OperationUtils {
	public static final String OPERATION_NONE = "NONE";
	public static final String OPERATION_ADDITION = "+";
	public static final String OPERATION_SUBTRACTION = "-";
	public static final String OPERATION_MULTIPLICATION = "\u002A";
	public static final String OPERATION_DIVISION = "\u002F";
	public static final String OPERATION_POWER = "POWER";
	public static final String OPERATION_SQRT = "SQRT";
	
	
	private static Map<String, MathOperation> operations = new HashMap<>();	
	
	
	
	public static MathOperation getOperation(String operation) {
		operations.put(OPERATION_NONE , new WithoutCalculation(OPERATION_NONE));
		operations.put(OPERATION_ADDITION , new Addition(OPERATION_ADDITION));
		operations.put(OPERATION_SUBTRACTION , new Subtraction(OPERATION_SUBTRACTION));
		operations.put(OPERATION_MULTIPLICATION , new Multiplication(OPERATION_MULTIPLICATION));
		operations.put(OPERATION_DIVISION , new Division(OPERATION_DIVISION));
		operations.put(OPERATION_POWER , new Power(OPERATION_POWER));
		operations.put(OPERATION_SQRT , new Sqrt(OPERATION_SQRT));
		if (operations.containsKey(operation)) {
			return operations.get(operation);
		} else 
		return operations.get(OPERATION_NONE);		
	}
}
