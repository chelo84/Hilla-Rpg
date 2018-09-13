package hillaRPG.gui.telaprincipal.telainventario;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.util.MeuBotao;

public class BotaoInfoItem extends MeuBotao {
	public BotaoInfoItem() {
		super();
		
		//this.getAllStyles().setMarginLeft(Display.getInstance().convertToPixels(1));
		this.getAllStyles().setPadding(1, 1, 2,2);
	}
	
	public BotaoInfoItem(String texto) {
		this();
		
		this.setText(texto);
	}
}
