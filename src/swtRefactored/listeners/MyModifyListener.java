package swtRefactored.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import swtRefactored.UIElements.CalculatorAppComposite;
import swtRefactored.mathOperations.OperationUtils;
import swtRefactored.mathOperations.WithoutCalculation;
import swtRefactored.model.DataTransferObject;

public class MyModifyListener implements Listener, ModifyListener {
	CalculatorAppComposite calc = CalculatorAppComposite.getInstance();
	DataTransferObject dto = DataTransferObject.getInstance();
	
	public void modifyText(ModifyEvent e) {
		//If widget is Text
		 proceedEvoluationProcedure(false);
	}

	@Override
	public void handleEvent(Event e) {
		//If widget is button
		if(e.widget instanceof Button) {
			 proceedEvoluationProcedure(true);
		 }
		// If widget is Combo
		if(e.widget instanceof Combo) {			
			Combo comboElement = (Combo) e.widget;
			int index = comboElement.getSelectionIndex();
			String operationName = comboElement.getItem(index);
			dto.operation = OperationUtils.getOperation(operationName);
			if (index == 6) {
				calc.getValue2().setEnabled(false);
				calc.getValue2().setText("");
			} else {
				calc.getValue2().setEnabled(true);
			}
			proceedEvoluationProcedure(false);
		}
		
	}
	
	public void proceedEvoluationProcedure(boolean isEvoluationImmediate) {
		boolean isCalculateOnFlyEnabled = calc.isOnFlyMode();
		if (isEvoluationImmediate || isCalculateOnFlyEnabled) {
			evaluate();
			refreshAll();
		}
	}
	
	public void evaluate() {
		dto.value1 = calc.getValue1().getText();
		dto.value2 = calc.getValue2().getText();
		if (dto.operation != null && !(dto.operation instanceof WithoutCalculation)) {
			dto.resultString = dto.operation.calculate(dto.value1, dto.value2);
			dto.records.add(dto.value1, dto.operation.shownAs, dto.value2, dto.resultString);
		}
	}
	
	public void refreshAll() {	
		try {
			calc.getResult().setText(dto.resultString);
			calc.getValue1().redraw();
			calc.getValue2().redraw();
			calc.getResult().redraw();
			calc.getHistoryComposite().refresh();
		} catch (NumberFormatException exception) {
			calc.getResult().setText("Input is invalid");
		}
	}	
}
