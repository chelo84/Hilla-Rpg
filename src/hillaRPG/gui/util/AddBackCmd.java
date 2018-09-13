package hillaRPG.gui.util;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;

public class AddBackCmd {
	
	public static void addBackCmd(Form formVoltar, Form formIr) {
		Command cmd = new Command("Voltar") {
			@Override
			public void actionPerformed(ActionEvent ae) {
				formVoltar.showBack();
			}
		};
		formIr.getToolbar().setBackCommand(cmd);
	}
}
