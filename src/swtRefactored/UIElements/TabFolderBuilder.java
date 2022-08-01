package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TabFolderBuilder {
	TabFolder tabFolder;
	TabItem[] tabItems;


	public TabFolder build(Shell shell, int n) {
		this.tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setLayout(new FillLayout(SWT.VERTICAL));
		if (n > 0) {
			TabItem[] tabItems = new TabItem[n];
			for (int i = 0; i < n; i++) {
				tabItems[i] = new TabItem(tabFolder, SWT.NONE);
				tabItems[i].setText("TabItem " + (i+1));
			}
		}
		return tabFolder;
	}
}