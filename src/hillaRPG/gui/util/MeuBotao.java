package hillaRPG.gui.util;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;

public class MeuBotao extends Button {
	
	public MeuBotao() {
		this.getAllStyles().setBorder(Border.createEmpty());
        this.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getSelectedStyle().setBgColor(ColorUtil.LTGRAY);
        this.getAllStyles().setFgColor(ColorUtil.BLACK);
        this.getPressedStyle().setBgColor(ColorUtil.GRAY);
		this.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		
        this.getAllStyles().setPadding(1, 1, 0, 0);

	}
	
	public MeuBotao(String texto) {
		this();
		this.setText(texto);
	}
}
