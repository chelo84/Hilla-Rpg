package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class BotaoGrande extends Button {
	
	public BotaoGrande() {
		this.setUIID("BotaoGrande");
		
		this.getAllStyles().setBorder(Border.createEmpty());
        this.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getSelectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.getPressedStyle().setBgColor(ColorUtil.GRAY);
        
        this.getAllStyles().setPadding(1, 1, 0, 0);


	}
	
	public BotaoGrande(String texto) {
		this();
		this.setText(texto);
	}
}
