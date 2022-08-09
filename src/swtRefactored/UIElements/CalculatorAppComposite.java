package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class CalculatorAppComposite{
	public static CalculatorAppComposite instance;
	private static final String TITLE_CALCULATOR = "Calculator";
	private static final String TITLE_HISTORY = "History";
		
	private TabFolder tabFolder;
	private TabItem calcTabItem, historyTabItem;
	
	private CalculatorComposite calcPanel;
	private HistoryComposite historyPanel;
	
	private CalculatorAppComposite() {
		
	}

	public static CalculatorAppComposite getInstance() {
		 if (instance == null) {
	            instance = new CalculatorAppComposite();
	        }
	        return instance;
	}
	
	public void createContent(Shell window) {

		this.tabFolder = new TabFolder(window, SWT.BORDER);
		this.tabFolder.setLayout(new FillLayout());
		this.tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		this.calcPanel = new CalculatorComposite(tabFolder);
		this.calcPanel.setLayout(new GridLayout(1,true));
		this.historyPanel = new HistoryComposite(tabFolder);
		this.historyPanel.setLayout(new GridLayout(1,true));
		
		this.calcTabItem = new TabItem(tabFolder, SWT.BORDER);
		this.calcTabItem.setText(TITLE_CALCULATOR);
				
		this.historyTabItem = new TabItem(tabFolder, SWT.BORDER);
		this.historyTabItem.setText(TITLE_HISTORY);
	
		this.calcTabItem.setControl(calcPanel);
		this.historyTabItem.setControl(historyPanel);
	}

	public HistoryComposite getHistoryComposite() {
		return historyPanel;
	}
	
	public CalculatorComposite getCalculatorComposite() {
		return calcPanel;
	}
	
	public Text getValue1() {
		return getCalculatorComposite().value1;
	}

	public Text getValue2() {
		return getCalculatorComposite().value2;
	}
	
	public boolean isOnFlyMode() {
		return getCalculatorComposite().checkButton.getSelection();
	}
	
	public Text getResult() {
		return getCalculatorComposite().resultText;
	}
	
	
}
