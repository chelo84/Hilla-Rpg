package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.TextArea;
import com.codename1.ui.plaf.Border;

public class MeuTextArea extends TextArea{
	public MeuTextArea() {
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		this.getUnselectedStyle().setBorder(Border.createEmpty());
	}
	
	public MeuTextArea(int rows) {
		this();
		
		this.setRows(rows);
	}
	
	public MeuTextArea(String texto, int rows) {
		this(rows);
		
		this.setText(texto);
	}
	
	public MeuTextArea(String texto) {
		this();
		
		this.setText(texto);
	}
}
