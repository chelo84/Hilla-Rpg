package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.Layout;

public class MeuForm extends Form {
	
	public MeuForm() {
		this.setTitle("Hilla RPG");
        this.getToolbar().getTitleComponent().getAllStyles().setFgColor(ColorUtil.BLACK);
        this.getToolbar().getTitleComponent().getAllStyles().setAlignment(TextArea.CENTER);
        this.getAllStyles().setBgColor(ColorUtil.BLACK);
	}
	
	public MeuForm(String title) {
		this();
	}
	

	public MeuForm(Layout layout) {
		this();
		this.setLayout(layout);
	}
}
