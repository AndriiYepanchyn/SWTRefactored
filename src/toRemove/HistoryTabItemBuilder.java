package toRemove;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class HistoryTabItemBuilder {
	public static TabItem create(TabFolder tabFolder) {
		TabItem answer = new TabItem(tabFolder, SWT.NONE);
		answer.setText("History");
		Browser browser = BrowserBuilder.build(tabFolder);
		answer.setControl(browser);
		return answer;
	}
}
