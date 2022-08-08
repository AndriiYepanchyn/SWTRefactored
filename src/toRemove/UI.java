package toRemove;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import swtRefactored.UIElements.CalculatorAppComposite;
import swtRefactored.UIElements.CalculatorComposite;
import swtRefactored.UIElements.HistoryComposite;

public class UI {
	private Shell mainWindow;

	CalculatorAppComposite instance;
	
	// Checked
	public UI(Shell window) {
		this.mainWindow = window;	
	}
	
	public void run() {	
		instance = CalculatorAppComposite.getInstance();
		instance.createContent(mainWindow);
	
	}
}
