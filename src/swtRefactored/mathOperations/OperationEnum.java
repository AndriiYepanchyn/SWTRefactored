package swtRefactored.mathOperations;

public enum OperationEnum {

	NONE("NONE") {
		public MathOperation generateObject() {
			return new WithoutCalculation("NONE");
		}
	},

	ADDITION("+") {
		public MathOperation generateObject() {
			return new Addition("+");
		}
	},

	SUBTRACTION("-") {
		public MathOperation generateObject() {
			return new Subtraction("-");
		}
	},

	MULTIPLICATION("\u002A") {
		public MathOperation generateObject() {
			return new Multiplication("\u002A");
		}
	},

	DIVISION("\u002F") {
		public MathOperation generateObject() {
			return new Division("\u002F");
		}
	},

	POWER("POWER") {
		public MathOperation generateObject() {
			return new Power("POWER");
		}
	},

	SQRT("SQRT") {
		public MathOperation generateObject() {
			return new Sqrt("SQRT");
		}
	};

	private String showAs;

	public abstract MathOperation generateObject();

	OperationEnum(String showAs) {
		this.showAs = showAs;
	}

	public static String[] getAllSurrogates() {
		OperationEnum[] values = OperationEnum.values();
		int len = OperationEnum.values().length;
		String[] answer = new String[len];
		for (int i = 0; i < len; i++) {
			answer[i] = values[i].showAs;
		}
		return answer;
	}
}
