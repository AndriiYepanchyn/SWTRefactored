package swtRefactored;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.TabFolder;

public class BrowserBuilder {
	public static Browser build(TabFolder tabFolder) {
		Browser innerBrowser;
		try {
			innerBrowser = new Browser(tabFolder, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return null;
		}
		innerBrowser.setText(Calculations.getHTMLData());
		tabFolder.getItem(1).setControl(innerBrowser);
		return innerBrowser;
	}
}
