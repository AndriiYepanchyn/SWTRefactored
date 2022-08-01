package swtRefactored;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import swtRefactored.UIElements.BrowserBuilder;
import swtRefactored.UIElements.ButtonEvaluateBuilder;
import swtRefactored.UIElements.TabFolderBuilder;

public class UI {
	public DTO dto = DTO.getInstance();
	private boolean isCalculateOnFlyEnabled = false;

	private Shell mainWindow;
	Text value1, value2, resultText;
	Combo operationChooser;
	Button evaluateButton, checkButton;
	Text equalSign;
	Browser browser;
	Composite generalCompositeForFirstItem, textAreaComposite, buttonAreaComposite;

	ModifyListener ml = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			setTemporaryVariables(false);
		}
	};

	//Checked
	public UI(Shell window) {
		this.mainWindow = window;
	}

	//Checked
	public void create() {
		TabFolder tabFolder = new TabFolderBuilder().build(mainWindow, 2);
		createCalcItem(tabFolder);
		createHistoryItem(tabFolder);
	}

	//
	private TabItem createCalcItem(TabFolder tabFolder) {
		TabItem answer = tabFolder.getItem(0);
		answer.setText("Calculator");
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
		generalCompositeForFirstItem = new Composite(tabFolder, SWT.NONE);
		generalCompositeForFirstItem.setLayout(generalGridLayout);

		textAreaComposite = new Composite(generalCompositeForFirstItem, SWT.NONE);
		textAreaComposite.setLayout(textAreaLayout);

		buttonAreaComposite = new Composite(generalCompositeForFirstItem, SWT.NONE);
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

		answer.setControl(generalCompositeForFirstItem);
		return answer;
	}

	//Checked
	private Text createTextElement(Composite parent) {
		GridData textGridData = new GridData(80, 17);
		textGridData.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
		textGridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END;

		Text textElement = new Text(parent, SWT.RIGHT | SWT.BORDER);
		textElement.setLayoutData(textGridData);
		textElement.addModifyListener(ml);
		return textElement;
	}

	//Checked
	private Combo createComboElement(Composite parent) {
		Combo comboElement = new Combo(parent, SWT.CENTER | SWT.READ_ONLY);
		comboElement.setItems(Calculations.op);
		comboElement.select(0);
		comboElement.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboElement.setLayoutData(new GridData(80, 15));
		comboElement.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				dto.operationIndex = comboElement.getSelectionIndex();
				if (dto.operationIndex == 6) {
					value2.setEnabled(false);
					value2.setText("");
				} else {
					value2.setEnabled(true);
				}
				setTemporaryVariables(false);
			}
		});
		return comboElement;
	}

	//Checked
	private Text createEqualSignTextElement(Composite parent) {
		Text equalSign = new Text(parent, SWT.NONE);
		equalSign.setText("=");
		equalSign.pack(true);
		equalSign.setEditable(false);
		equalSign.setEnabled(false);
		return equalSign;
	}

	//Checked
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

	//Checked
	private Button createEvaluateButton(Composite parent) {
		Button craeatedButton = new ButtonEvaluateBuilder().build(parent);
		craeatedButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				setTemporaryVariables(true);
			}
		});
		return craeatedButton;
	}

	//Checked
	private Button createCheckButton(Composite parent) {
		Button checkButton = new Button(buttonAreaComposite, SWT.CHECK);
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

	//Checked
	private TabItem createHistoryItem(TabFolder tabFolder) {
		TabItem answer = tabFolder.getItem(1);
		answer.setText("History");
		browser = BrowserBuilder.build(tabFolder);
		answer.setControl(browser);
		return answer;
	}

	//TODO
	private void refreshData(Text label, Browser browser) {
		try {		
			dto.resultString = Calculations.proceed(dto.value1, dto.value2, dto.operationIndex);
			label.setText(dto.resultString);
			label.redraw();
			if (browser != null) {
				browser.setText(Calculations.getHTMLData());
			}
		} catch (NumberFormatException exception) {
			label.setText("Input is invalid");
		}
	}

	//TODO
	private void setTemporaryVariables(boolean immediate) {
		dto.value1 = value1.getText();
		dto.value2 = value2.getText();
		dto.operationIndex = operationChooser.getSelectionIndex();

		if (immediate) {
			refreshData(resultText, browser);
		} else {
			isCalculateOnFlyEnabled = checkButton.getSelection();
			if (isCalculateOnFlyEnabled) {
				refreshData(resultText, browser);
			}
		}
	}
}
