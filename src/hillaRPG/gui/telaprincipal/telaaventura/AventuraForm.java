package hillaRPG.gui.telaprincipal.telaaventura;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;

import hillaRPG.gui.telaprincipal.telaaventura.batalha.BatalhaForm;
import hillaRPG.gui.util.AddBackCmd;
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;

public class AventuraForm extends MeuForm {
	public AventuraForm() {
		this.setLayout(BoxLayout.y());
		createComponents();
	}
	
	public AventuraForm(String title) {
		super(title);
		
		createComponents();
	}
	
	private void createComponents() {
		Button btnBueiro = new MeuBotao("Bueiro");
		btnBueiro.getAllStyles().setMarginTop(3);
		btnBueiro.addActionListener((ae) -> {
			String area = getArea(btnBueiro);
			
			Form form = new BatalhaForm(area);
			
			form.show();
		});
		this.add(btnBueiro);
		
		Button btnFortTroll = new MeuBotao("Fortaleza dos Trolls");
		btnFortTroll.getAllStyles().setMarginTop(3);
		btnFortTroll.addActionListener((ae) -> {
			String area = getArea(btnFortTroll);
			
			Form form = new BatalhaForm(area);
			
			form.show();
		});
		this.add(btnFortTroll);
		
		Button btnFortOrc = new MeuBotao("Fortaleza dos Ogros");
		btnFortOrc.getAllStyles().setMarginTop(3);
		btnFortOrc.addActionListener((ae) -> {
			String area = getArea(btnFortOrc);
			
			Form form = new BatalhaForm(area);
			
			form.show();
		});
		this.add(btnFortOrc);
		
		Button btnFloresta = new MeuBotao("Floresta");
		btnFloresta.getAllStyles().setMarginTop(3);
		btnFloresta.addActionListener((ae) -> {
			String area = getArea(btnFloresta);
			
			Form form = new BatalhaForm(area);
			
			form.show();
		});
		this.add(btnFloresta);
	}
	
	public String getArea(Button btn) {
		return btn.getText().trim().toLowerCase().replace(' ', '_');
	}
}
