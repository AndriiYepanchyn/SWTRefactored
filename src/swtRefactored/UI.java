package swtRefactored;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import swtRefactored.UIElements.TabFolderBuilder;

public class UI {
	public DTO dto = DTO.getInstance();
	private Shell mainWindow;

	// Checked
	public UI(Shell window) {
		this.mainWindow = window;
		TabFolder tabFolder = new TabFolderBuilder().build(mainWindow, 2);
	}
}
