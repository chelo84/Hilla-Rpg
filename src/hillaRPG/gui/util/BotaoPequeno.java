package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class BotaoPequeno extends Button {
	
	public BotaoPequeno() {
		this.setUIID("BotaoPequeno");
		
        this.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getSelectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.getPressedStyle().setBgColor(ColorUtil.GRAY);
        
        this.getAllStyles().setPadding(0, 0, 0, 0);
        this.getAllStyles().setMarginTop(0);

	}
	
	public BotaoPequeno(String texto) {
		this();
		this.setText(texto);
	}
}
