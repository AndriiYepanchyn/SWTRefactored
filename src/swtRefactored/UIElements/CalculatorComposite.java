package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import swtRefactored.listeners.MyModifyListener;
import swtRefactored.mathOperations.OperationEnum;
import swtRefactored.mathOperations.OperationUtils;
import swtRefactored.model.DataTransferObject;

public class CalculatorComposite extends Composite {
	public DataTransferObject dto;
	Text value1, value2, resultText, equalSign;
	Combo operationChooser;
	Button evaluateButton, checkButton;

	public CalculatorComposite(Composite parent) {
		super(parent, SWT.BORDER);
		createContent(parent);
	}

	private void createContent(Composite parent) {
		super.dispose();
		Composite textAreaComposite, buttonAreaComposite;
		dto = DataTransferObject.getInstance();

		new GridLayout(1, true);

		GridLayout textAreaLayout = new GridLayout(7, false);
		textAreaLayout.numColumns = 7;
		textAreaLayout.makeColumnsEqualWidth = false;
		textAreaLayout.marginHeight = 5;
		textAreaLayout.marginBottom = 5;
		textAreaLayout.horizontalSpacing = 3;

		GridLayout buttonAreaLayout = new GridLayout(2, false);

		// prepare composites
		textAreaComposite = new Composite(parent, SWT.BORDER);
		textAreaComposite.setLayout(textAreaLayout);

		buttonAreaComposite = new Composite(parent, SWT.BORDER);
		buttonAreaComposite.setLayout(buttonAreaLayout);

		// Create textArea elements
		value1 = createTextElement(textAreaComposite);
		operationChooser = createComboElement(textAreaComposite);
		value2 = createTextElement(textAreaComposite);
		equalSign = createEqualSignTextElement(textAreaComposite);
		resultText = createResultTextElement(textAreaComposite);

		// Create Evaluate button
		evaluateButton = createEvaluateButton(buttonAreaComposite);
		checkButton = createCheckButton(buttonAreaComposite);
	}

	private Text createTextElement(Composite parent) {
		GridData textGridData = new GridData(80, 17);
		textGridData.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
		textGridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END;

		Text textElement = new Text(parent, SWT.RIGHT | SWT.BORDER);
		textElement.setLayoutData(textGridData);
		textElement.addModifyListener(new MyModifyListener());
		return textElement;
	}

	private Combo createComboElement(Composite parent) {
		Combo comboElement = new Combo(parent, SWT.CENTER | SWT.READ_ONLY);
		comboElement.setItems(OperationEnum.getAllSurrogates());
		comboElement.select(0);
		dto.operation = OperationUtils.getOperation(OperationUtils.OPERATION_NONE);
		comboElement.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboElement.setLayoutData(new GridData(80, 15));	
		comboElement.addListener(SWT.Selection, new MyModifyListener());
		return comboElement;
	}

	private Text createEqualSignTextElement(Composite parent) {
		Text equalSign = new Text(parent, SWT.NONE);
		equalSign.setText("=");
		equalSign.pack(true);
		equalSign.setEditable(false);
		equalSign.setEnabled(false);
		return equalSign;
	}

	private Text createResultTextElement(Composite parent) {
		Text resultText = new Text(parent, SWT.RIGHT | SWT.READ_ONLY | SWT.BORDER);
		resultText.setText("0.00");
		resultText.setEnabled(true);

		GridData resultTextGridData = new GridData(100, 17);
		resultTextGridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END;

		resultText.setLayoutData(resultTextGridData);
		resultText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		return resultText;
	}

	private Button createEvaluateButton(Composite parent) {
		Button btnEvaluate = new Button(parent, SWT.PUSH | SWT.LEFT);
		btnEvaluate.setText("E&valuate");
		btnEvaluate.setBounds(10, 200, 150, 50);
		btnEvaluate.setLocation(100,100);
		btnEvaluate.setAlignment(SWT.CENTER);
		btnEvaluate.addListener(SWT.Selection, new MyModifyListener());
		return btnEvaluate;
	}

	private Button createCheckButton(Composite parent) {
		Button checkButton = new Button(parent, SWT.CHECK);
		checkButton.setText("Calculate on fly");
		checkButton.setSelection(false);
		checkButton.setBounds(60, 10, 20, 20);
		checkButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				evaluateButton.setEnabled(!checkButton.getSelection());
			}
		});
		return checkButton;
	}

	public Text getValue1() {
		return value1;
	}
	
	public Text getValue2() {
		return value2;
	}
	
	public Text getResult() {
		return resultText;
	}
	
	public Button getCheckButton() {
		return checkButton;
	}
	
}
