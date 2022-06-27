package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class WindowBuilder {
	Shell shell;

	public Shell build(Display d) {

		int xSize = 500;
		int ySize = 250;
		Rectangle sizes = d.getBounds();
		int x = sizes.width;
		Rectangle minSize = new Rectangle((x - xSize) - 30, 110, xSize, ySize);
		this.shell = new Shell(d);
		shell.setBounds(minSize);
		shell.setMinimumSize(xSize, ySize);
		shell.setMaximumSize(xSize, ySize + 300);
		shell.setText("Manual Calculator v. 2.0");

		FillLayout layout = new FillLayout(SWT.VERTICAL );
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.spacing = 5;
		

		shell.setLayout(layout);

		return shell;
	}
}
