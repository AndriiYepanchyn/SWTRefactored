package swtRefactored;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class UserInterface {
	
	//This class replaced with UI class

	private static String v1;
	private static String v2;
	private static int v3;
	boolean isCalculateOnFlyEnabled;
	Shell mainWindow;
	TabFolder tabFolder;
	TabItem tblcalc, tblHistory;
	Browser browser;
	Text value1, value2, equalSign, resultText; //+
	Button btnEvaluate, checkButton; //+
	Combo operationChooser; 
	Group[] firstTabGroups = new Group[3];
	Composite c, c1, c2;

	ModifyListener ml = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			setTemporaryVariables(false);
		}
	};

	
	public UserInterface(Shell window) {
		// Create_main_window
		mainWindow = window;
		tabFolder = new TabFolderBuilder().build(mainWindow);
		tblcalc = tabFolder.getItem(0);
//		tblHistory = tabFolder.getItem(1);
//		browser = BrowserBuilder.build(tabFolder);
	}

	public void run() {
		GridLayout generalGridLayout = new GridLayout(1, true); //+
		GridLayout firstTabGridLayout = new GridLayout(7, false); //+
		GridLayout buttonAreaGridLayout = new GridLayout(2, false);

		firstTabGridLayout.numColumns = 7; //+
		firstTabGridLayout.makeColumnsEqualWidth = false; //+
		firstTabGridLayout.marginHeight = 5; //+
		firstTabGridLayout.marginBottom = 5; //+
		firstTabGridLayout.horizontalSpacing = 3; //+

		GridData gridData = new GridData(80, 17);//+
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END; //+
		GridData gridData2 = new GridData(80, 15); //+

		GridData gridData3 = new GridData(100, 17); //+
		gridData3.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END; //+

// Create_TabFolder

		firstTabGroups[0] = new Group(tabFolder, SWT.NONE); //+
		firstTabGroups[0].setLayout(generalGridLayout); //+

		firstTabGroups[1] = new Group(firstTabGroups[0], SWT.NONE); //+
		firstTabGroups[1].setLayout(firstTabGridLayout); //+

		firstTabGroups[2] = new Group(firstTabGroups[0], SWT.NONE); //+
		firstTabGroups[2].setLayout(buttonAreaGridLayout); //+

// FirstTabItem_content

//		value1 = new Text(firstTabGroups[1], SWT.RIGHT | SWT.BORDER);//+
//		value1.setLayoutData(gridData); //+
//		value1.addModifyListener(ml);

//Combo

//		operationChooser = new Combo(firstTabGroups[1], SWT.CENTER | SWT.READ_ONLY); //+
//		operationChooser.setItems(Calculations.op);//+
//		operationChooser.select(0);//+
//		operationChooser.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));//+
//		operationChooser.setLayoutData(gridData2); //+
//		operationChooser.addListener(SWT.Selection, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				v3 = operationChooser.getSelectionIndex();
//				if (v3 == 6) {
//					value2.setEnabled(false);
//					value2.setText("");
//				} else {
//					value2.setEnabled(true);
//				}
//				setTemporaryVariables(false);
//			}
//		});

//Value2

//		value2 = new Text(firstTabGroups[1], SWT.RIGHT | SWT.BORDER);
//		value2.setLayoutData(gridData);
//		value2.addModifyListener(ml);

//SingEquals	

//		equalSign = new Text(firstTabGroups[1], SWT.NONE);
//		equalSign.setText("=");
//		equalSign.pack(true);
//		equalSign.setEditable(false);
//		equalSign.setEnabled(false);

//Answer	

//		resultText = new Text(firstTabGroups[1], SWT.RIGHT | SWT.READ_ONLY | SWT.BORDER); //+
//		resultText.setText("0.00"); //+
//		resultText.setEnabled(true); //+
//		resultText.setLayoutData(gridData3); //+
//		resultText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE)); //+

//ButtonEvaluate

//		btnEvaluate = generateEvaluateButton(firstTabGroups[2]);
//		btnEvaluate.addListener(SWT.Selection, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				setTemporaryVariables(true);
//			}
//		});

//CheckButton

//		checkButton = generateCheckButton(firstTabGroups[2]);
//		checkButton.addListener(SWT.Selection, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				btnEvaluate.setEnabled(!checkButton.getSelection());
//			}
//		});

		tblcalc.setControl(firstTabGroups[0]);
//		tblHistory.setControl(browser);
	}// End of run()



//	public static Button generateEvaluateButton(Group inputGroup) {
//		Button btnEvaluate = new Button(inputGroup, SWT.PUSH | SWT.LEFT);
//		btnEvaluate.setText("E&valuate");
//		btnEvaluate.setAlignment(SWT.CENTER);
//		return btnEvaluate;
//	}

//	public static Button generateCheckButton(Group parent) {
//		Button checkButton = new Button(parent, SWT.CHECK);
//		checkButton.setText("Calculate on fly");
//		checkButton.setSelection(false);
//		checkButton.setBounds(60, 10, 20, 20);
//		return checkButton;
//	}

	private static void refreshData(Text label, Browser browser) {
		try {
			String answer = Calculations.proceed(v1, v2, v3);
			label.setText(answer);
			label.redraw();
			if (browser != null) {
				browser.setText(Calculations.getHTMLData());
			}
		} catch (NumberFormatException exception) {
			label.setText("Input is invalid");
		}
	}

	public void setTemporaryVariables(boolean immediate) {
		v1 = value1.getText();
		v2 = value2.getText();
		v3 = operationChooser.getSelectionIndex();
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
