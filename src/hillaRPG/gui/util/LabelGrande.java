package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class LabelGrande extends Label {
	
	public LabelGrande() {
		this.setUIID("LabelGrande");
        this.getAllStyles().setFgColor(ColorUtil.WHITE);
        
	}
	
	public LabelGrande(String texto) {
		this();
		this.setText(texto);
	}

}
