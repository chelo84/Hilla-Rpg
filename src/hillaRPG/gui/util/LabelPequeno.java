package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class LabelPequeno  extends Label {
	
	public LabelPequeno() {
		this.setUIID("LabelPequeno");
        this.getAllStyles().setFgColor(ColorUtil.WHITE);
        
	}
	
	public LabelPequeno(String texto) {
		this();
		this.setText(texto);
	}
}
