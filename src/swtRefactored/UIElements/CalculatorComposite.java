package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import mathOperations.OperationEnum;
import mathOperations.OperationUtils;
import swtRefactored.DataTransferObject;

public class CalculatorComposite extends Composite {
	public DataTransferObject dto;
	private boolean isCalculateOnFlyEnabled;

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

		// Prepare layouts
		GridLayout generalGridLayout = new GridLayout(1, true);
		
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
		textElement.addModifyListener(modifyListener);
		return textElement;
	}

	private Combo createComboElement(Composite parent) {
		Combo comboElement = new Combo(parent, SWT.CENTER | SWT.READ_ONLY);
		comboElement.setItems(OperationEnum.getAllSurrogates());
		comboElement.select(0);
		comboElement.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboElement.setLayoutData(new GridData(80, 15));
		comboElement.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				int index = comboElement.getSelectionIndex();
				String operationName = comboElement.getItem(index);
				dto.operation = OperationUtils.getInstance().getOperation(operationName);
				if (index == 6) {
					value2.setEnabled(false);
					value2.setText("");
				} else {
					value2.setEnabled(true);
				}
				proceedEvoluationProcedure(false);
			}
		});
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
		Button craeatedButton = ButtonEvaluateBuilder.build(parent);
		craeatedButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				proceedEvoluationProcedure(true);
			}
		});
		return craeatedButton;
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

	public void evaluate() {
		dto.value1 = value1.getText();
		dto.value2 = value2.getText();
		// dto.operation changing on handleEvent in ComboBox
		dto.resultString = dto.operation.calculate(dto.value1, dto.value2);
		dto.records.add(dto.value1, dto.operation.shownAs, dto.value2, dto.resultString);
	}

	public void refreshAll() {
		try {
			value1.setText(dto.value1);
			value2.setText(dto.value2);
			resultText.setText(dto.resultString);
			value1.redraw();
			value2.redraw();
			resultText.redraw();

			Browser browser = CalculatorAppComposite.getInstance().getHistoryComposite().browser;	
			if (browser != null) {
				browser.setText(dto.records.getConvertedToHTML());
			}
			System.out.println(dto.records.getConvertedToHTML());
			CalculatorAppComposite.getInstance().getHistoryComposite().refresh();
			System.out.println("======================");
			System.out.println(browser.getText());
		} catch (NumberFormatException exception) {
			resultText.setText("Input is invalid");
		}
	}

	public void proceedEvoluationProcedure(boolean isEvoluationImmediate) {
		isCalculateOnFlyEnabled = checkButton.getSelection();
		if (isEvoluationImmediate || isCalculateOnFlyEnabled) {
			evaluate();
			refreshAll();
		}
	}

	ModifyListener modifyListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			proceedEvoluationProcedure(false);
		}
	};
}
