package swtRefactored.UIElements;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import swtRefactored.model.DataTransferObject;

public class HistoryComposite extends Composite {
	Browser browser;

	public HistoryComposite(Composite parent) {
		super(parent, SWT.BORDER);
		createContent(parent);
	}

	private void createContent(Composite parent) {
		super.dispose();
		browser = createBrowser(parent);
	}
	
	private Browser createBrowser(Composite parent) {	
		Browser innerBrowser;
		try {
			innerBrowser = new Browser(parent, SWT.BORDER);
			innerBrowser.setLayoutData(new GridData(GridData.FILL_BOTH));
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			return null;
		}
		String records = getConvertedToHTML(DataTransferObject.getInstance().records.get());
		innerBrowser.setText(records);
		return innerBrowser;
	}
	
	private String getConvertedToHTML(ArrayList<String> records) {
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
	
	public void refresh() {
		String records = getConvertedToHTML(DataTransferObject.getInstance().records.get());
		browser.setText(records);
		browser.redraw();
	}
}
