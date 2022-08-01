package swtRefactored.UIElements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ButtonEvaluateBuilder {
	public static Button build(Composite c) {
		Button btnEvaluate;
		btnEvaluate = new Button(c, SWT.PUSH | SWT.LEFT);
		btnEvaluate.setText("E&valuate");
		btnEvaluate.setBounds(10, 200, 150, 50);
		btnEvaluate.setLocation(100,100);
		btnEvaluate.setAlignment(SWT.CENTER);
//		btnEvaluate.addListener(SWT.Selection, new Listener() {
//			@Override
//			public void handleEvent(Event event) {
//				setTemporaryVariables(true);
//			}
//		});
		return btnEvaluate;
	}
}
