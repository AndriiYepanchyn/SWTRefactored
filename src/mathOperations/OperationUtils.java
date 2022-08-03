package mathOperations;

import java.util.HashMap;
import java.util.Map;



public class OperationUtils {
	private final String OPERATION_NONE = "NONE";
	private final String OPERATION_ADDITION = "+";
	private final String OPERATION_SUBTRACTION = "-";
	private final String OPERATION_MULTIPLICATION = "\u002A";
	private final String OPERATION_DIVISION = "\u002F";
	private final String OPERATION_POWER = "POWER";
	private final String OPERATION_SQRT = "SQRT";
	
	
	private Map<String, MathOperation> operations = new HashMap<>();
	
	private static OperationUtils instance;
	
	private OperationUtils() {
		operations.put(OPERATION_NONE , new WithoutCalculation(OPERATION_NONE));
		operations.put(OPERATION_ADDITION , new Addition(OPERATION_ADDITION));
		operations.put(OPERATION_SUBTRACTION , new Subtraction(OPERATION_SUBTRACTION));
		operations.put(OPERATION_MULTIPLICATION , new Multiplication(OPERATION_MULTIPLICATION));
		operations.put(OPERATION_DIVISION , new Division(OPERATION_DIVISION));
		operations.put(OPERATION_POWER , new Power(OPERATION_POWER));
		operations.put(OPERATION_SQRT , new Sqrt(OPERATION_SQRT));
	}
	
	public static OperationUtils getInstance() {
		if (instance == null) {
			instance = new OperationUtils();
		}
		return instance;
	}
	
	public MathOperation getOperation(String operation) {
		if (!operations.isEmpty()) {
			return operations.get(operation);
		}
		return null;
	}
}
