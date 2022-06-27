package swtRefactored;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import swtRefactored.UIElements.WindowBuilder;

public class Main {

	public static void main(String[] args) {
		Display display = new Display();
		Shell mainWindow = new WindowBuilder().build(display);
		UI calc = new UI(mainWindow);
		calc.create();

		// Proceed_window
		mainWindow.open();
		while (!mainWindow.isDisposed()) {
		    if (!display.readAndDispatch())
			display.sleep();
		}
		display.dispose();
	}

}
