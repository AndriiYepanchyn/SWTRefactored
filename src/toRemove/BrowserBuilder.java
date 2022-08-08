package toRemove;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.TabFolder;

import swtRefactored.DataTransferObject;

public class BrowserBuilder {
	public static Browser build(TabFolder tabFolder) {
		Browser innerBrowser;
		try {
			innerBrowser = new Browser(tabFolder, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return null;
		}
		String records = getConvertedToHTML(DataTransferObject.getInstance().records.get());
		innerBrowser.setText(records);
		return innerBrowser;
	}
	
	public static String getConvertedToHTML(ArrayList<String> records) {
		String beginString = "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>\n"
				+ "<H3>===Operation history ===<p> </p></h3> \n" + "<ol>";
		String finishString = "</ol><h4><p>===End of history records===</P></h4></BODY></HTML>";
		StringBuilder answer = new StringBuilder(beginString);

		for (String a : records) {
			answer.append("<li> Operation:  " + a + "</li>\n");
		}
		answer.append(finishString);

		return answer.toString();
	}
}
