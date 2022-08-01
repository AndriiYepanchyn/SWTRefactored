package swtRefactored.UIElements;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class HistoryTabItemBuilderDONTUSED {
	TabItem answer;
	
	public TabItem build(TabFolder tabFolder) {
		this.answer = tabFolder.getItem(1);
		answer.setText("History");
		Browser browser = BrowserBuilder.build(tabFolder);
		answer.setControl(browser);
		return this.answer;
	}
}
