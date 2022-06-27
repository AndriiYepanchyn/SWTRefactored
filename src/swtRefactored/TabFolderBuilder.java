package swtRefactored;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TabFolderBuilder {
	TabFolder tabFolder;
	TabItem[] tabItems = new TabItem[2];
	Group[] groups = new Group[3];

	public TabFolder build(Shell shell) {
		this.tabFolder = new TabFolder(shell, SWT.BORDER);
		tabFolder.setLayout(new FillLayout(SWT.VERTICAL)); 
		setTabItems();
		return tabFolder;
	}

	private void setTabItems() {
		tabItems[0] = new TabItem(tabFolder, SWT.NONE);
		tabItems[0].setText("Calculator");

		tabItems[1] = new TabItem(tabFolder, SWT.NONE);
		tabItems[1].setText("History");
		
		//Browser browser = BrowserBuilder.build(tabFolder);
		//tabItems[1].setControl(browser);
	}

	public TabItem getTabItem(int index) {
		return this.tabItems[index];
	}

	public Group getGroup(int index) {
		return this.groups[index];
	}
}