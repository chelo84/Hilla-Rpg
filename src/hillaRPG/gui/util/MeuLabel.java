package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;

public class MeuLabel extends Label {
	
	public MeuLabel() {
        this.getAllStyles().setFgColor(ColorUtil.WHITE);
        
	}
	
	public MeuLabel(String texto) {
		this();
		this.setText(texto);
	}
	
	public Label clone() {
		Label lb = new Label();
		
		lb.setText(this.getText());
		lb.setUnselectedStyle(this.getUnselectedStyle());
		lb.setSelectedStyle(this.getSelectedStyle());
		lb.setPressedStyle(this.getPressedStyle());
		lb.setDisabledStyle(this.getDisabledStyle());
		
		return lb;
	}
}
