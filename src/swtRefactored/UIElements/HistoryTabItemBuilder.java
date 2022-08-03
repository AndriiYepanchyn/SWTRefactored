package swtRefactored.UIElements;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class HistoryTabItemBuilder {
	public static TabItem create(TabFolder tabFolder, int index) {
		TabItem answer = tabFolder.getItem(index);
		answer.setText("History");
		Browser browser = BrowserBuilder.build(tabFolder);
		answer.setControl(browser);
		return answer;
	}
}
