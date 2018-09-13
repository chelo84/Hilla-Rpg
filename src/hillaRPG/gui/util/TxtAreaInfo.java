package hillaRPG.gui.util;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.TextArea;
import com.codename1.ui.plaf.Border;

public class TxtAreaInfo extends TextArea {
	
	public TxtAreaInfo() {
		this.getAllStyles().setBgColor(ColorUtil.BLACK);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
	}
	
	public TxtAreaInfo(int rows) {
		this();
		
		this.setRows(rows);
	}
	
	public TxtAreaInfo(String texto, int rows) {
		this(rows);
		
		this.setText(texto);
	}
}
