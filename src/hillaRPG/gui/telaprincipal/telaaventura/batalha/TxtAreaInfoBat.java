package hillaRPG.gui.telaprincipal.telaaventura.batalha;

import hillaRPG.gui.util.MeuTextArea;

public class TxtAreaInfoBat extends MeuTextArea {
	public TxtAreaInfoBat() {
		super();
		
		this.getAllStyles().setAlignment(CENTER);;
		this.setEditable(false);
		this.setFocusable(false);
		this.setScrollVisible(true);
		
	}
	
	public TxtAreaInfoBat(int rows) {
		super(rows);
		
		this.setRows(rows);
	}
	
	public TxtAreaInfoBat(String texto, int rows) {
		super(rows);
		
		this.setText(texto);
	}
	
	public TxtAreaInfoBat(String texto) {
		super();
		
		this.setText(texto);
	}
}
