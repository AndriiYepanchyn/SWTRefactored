package swtRefactored.UIElements;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

	public static void main(String[] args) {
		Display display = new Display();
		Shell mainWindow = new WindowBuilder().build(display);
		UI calc = new UI(mainWindow);
		calc.run();

		// Proceed_window
		mainWindow.open();
		while (!mainWindow.isDisposed()) {
		    if (!display.readAndDispatch())
			display.sleep();
		}
		display.dispose();
	}

}
