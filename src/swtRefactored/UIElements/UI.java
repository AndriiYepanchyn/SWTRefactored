package swtRefactored.UIElements;

import org.eclipse.swt.widgets.Shell;

public class UI {
	private Shell mainWindow;
	CalculatorAppComposite instance;

	public UI(Shell window) {
		this.mainWindow = window;	
	}
	
	public void run() {	
		instance = CalculatorAppComposite.getInstance();
		instance.createContent(mainWindow);	
	}
}
