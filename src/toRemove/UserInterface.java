package toRemove;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;


public class UserInterface {

	private static String v1;
	private static String v2;
	private static int v3;
	boolean isCalculateOnFlyEnabled;
	Shell mainWindow;
	TabFolder tabFolder;
	TabItem tblcalc, tblHistory;
	Browser browser;
	Text value1, value2, equal, answer;
	Button btnEvaluate, checkButton;
	Combo combo;
	Group[] firstTabGroups = new Group[3];
	Composite generalComposite, c1, c2;

	ModifyListener ml = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
				setTemporaryVariables(false);
		}
	};

	public UserInterface(Shell window) {

// Create_main_window
		mainWindow = window;
		tabFolder = new TabFolderBuilder().build(mainWindow,2);
		tblcalc = tabFolder.getItem(0);
		tblHistory = tabFolder.getItem(1);
		browser = generateBrowser(tabFolder);
	}

	public void run() {
		GridLayout generalGrigLayout = new GridLayout(1, true);
		GridLayout grid2 = new GridLayout(7, false);
		GridLayout grid3 = new GridLayout(2, false);

		grid2.numColumns = 7;
		grid2.makeColumnsEqualWidth = false;
		grid2.marginHeight = 5;
		grid2.marginBottom = 5;
		grid2.marginHeight = 5;
		grid2.marginTop = 5;
		grid2.horizontalSpacing = 3;

		GridData gridData = new GridData(80, 17);
		gridData.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END;
		GridData gridData2 = new GridData(80, 15);

		GridData gridData3 = new GridData(100, 17);
		gridData3.horizontalAlignment = GridData.HORIZONTAL_ALIGN_END;

// Create_TabFolder

		generalComposite = new Composite(tabFolder, SWT.NONE);
		generalComposite.setLayout(generalGrigLayout);

		c1 = new Composite(generalComposite, SWT.NONE);
		c1.setLayout(grid2);

		c2 = new Group(generalComposite, SWT.NONE);
		c2.setLayout(grid3);

// FirstTabItem_content

		value1 = new Text(c1, SWT.RIGHT | SWT.BORDER);
		value1.setLayoutData(gridData);
		value1.addModifyListener(ml);

//Combo

		combo = new Combo(c1, SWT.CENTER | SWT.READ_ONLY);
//		combo.setItems(Calculations.op);
		combo.select(0);
		combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		combo.setLayoutData(gridData2);
		combo.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				v3 = combo.getSelectionIndex();
				if (v3 == 6) {
					value2.setEnabled(false);
					value2.setText("");
				} else {
					value2.setEnabled(true);
				}
				setTemporaryVariables(false);
			}
		});

//Value2

		value2 = new Text(c1, SWT.RIGHT | SWT.BORDER);
		value2.setLayoutData(gridData);
		value2.addModifyListener(ml);

//SingEquals	

		equal = new Text(c1, SWT.NONE);
		equal.setText("=");
		equal.pack(true);
		equal.setEditable(false);
		equal.setEnabled(false);

//Answer	

		answer = new Text(c1, SWT.RIGHT | SWT.READ_ONLY | SWT.BORDER);
		answer.setText("0.00");
		answer.setEnabled(true);
		answer.setLayoutData(gridData3);
		answer.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

//ButtonEvaluate

		btnEvaluate = generateEvaluateButton(c2);
		btnEvaluate.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				setTemporaryVariables(true);
			}
		});

//CheckButton

		checkButton = generateCheckButton(c2);
		checkButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				btnEvaluate.setEnabled(!checkButton.getSelection());
			}
		});

		tblcalc.setControl(generalComposite);
		tblHistory.setControl(browser);
	}// End of run()

	public static Browser generateBrowser(TabFolder tabFolder) {
		Browser innerBrowser;
		try {
			innerBrowser = new Browser(tabFolder, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return null;
		}
//		innerBrowser.setText(Calculations.getHTMLData());
//		tabFolder.getItem(1).setControl(innerBrowser);
		return innerBrowser;
	}

	public static Button generateEvaluateButton(Composite inputGroup) {
		Button btnEvaluate = new Button(inputGroup, SWT.PUSH | SWT.LEFT);
		btnEvaluate.setText("E&valuate");
		btnEvaluate.setAlignment(SWT.CENTER);
		return btnEvaluate;
	}

	public static Button generateCheckButton(Composite parent) {
		Button checkButton = new Button(parent, SWT.CHECK);
		checkButton.setText("Calculate on fly");
		checkButton.setSelection(false);
		checkButton.setBounds(60, 10, 20, 20);
		return checkButton;
	}

	private static void refreshData(Text label, Browser browser) {
		try {
//			String answer = Calculations.proceed(v1, v2, v3);
//			label.setText(answer);
			label.redraw();
			if (browser != null) {
//				browser.setText(Calculations.getHTMLData());
			}
		} catch (NumberFormatException exception) {
			label.setText("Input is invalid");
		}
	}

	public void setTemporaryVariables(boolean immediate) {
		v1 = value1.getText();
		v2 = value2.getText();
		v3 = combo.getSelectionIndex();
		if (immediate) {
			refreshData(answer, browser);
		} else {
			isCalculateOnFlyEnabled = checkButton.getSelection();
			
			if (isCalculateOnFlyEnabled) {
				refreshData(answer, browser);
			}
		}
	}
}
