package hillaRPG.gui.telaprincipal;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

import hillaRPG.gui.telaprincipal.telaarmazem.ArmazemForm;
import hillaRPG.gui.telaprincipal.telaaventura.AventuraForm;
import hillaRPG.gui.telaprincipal.telainventario.InventarioForm;
import hillaRPG.gui.telaprincipal.telaloja.LojaForm;
import hillaRPG.gui.telaprincipal.telamagias.MagiasForm;
import hillaRPG.gui.telaprincipal.telapersonagem.PersonagemForm;
import hillaRPG.gui.util.AddBackCmd;
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;

public class PrincipalForm extends MeuForm {
	
	public PrincipalForm() {
		this.setLayout(BoxLayout.y());
		createComponents();
	}
	
	public PrincipalForm(String title) {
		super(title);
		
		createComponents();
	}
	
	public void createComponents() {
		Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
		Font flaticon = Font.createTrueTypeFont("flaticon", "flaticon.ttf");
		int size = Display.getInstance().convertToPixels(5);
		FontImage fm;
		
		Button btnAventura = new MeuBotao("Aventura");
		fm = FontImage.createFixed("\uf101", flaticon, 0x000000, size, size);
		btnAventura.setIcon(fm);
		btnAventura.getAllStyles().setMarginTop(3);
		btnAventura.addActionListener((ae) -> {
			Form form = new AventuraForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			
			form.show();
		}); 
		this.add(btnAventura);
		
		Button btnPersonagem = new MeuBotao("Personagem");
		fm = FontImage.createFixed("\ueA6F", fnt, 0x000000, size, size);
		btnPersonagem.setIcon(fm);
		btnPersonagem.getAllStyles().setMarginTop(4);
		btnPersonagem.addActionListener((ae) -> {
			Form form = new PersonagemForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			
			form.show();
		});
		this.add(btnPersonagem);
		
		Button btnMagias = new MeuBotao("Magias");
		fm = FontImage.createFixed("\uEA89", fnt, 0x000000, size, size);
		btnMagias.setIcon(fm);
		btnMagias.getAllStyles().setMarginTop(4);
		btnMagias.addActionListener((ae) -> {
			Form form = new MagiasForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			
			form.show();
		});
		this.add(btnMagias);
		
		Button btnInventario = new MeuBotao("Inventário");
		fm = FontImage.createFixed("\ue918", fnt, 0x000000, size, size);
		btnInventario.setIcon(fm);
		btnInventario.getAllStyles().setMarginTop(4);
		btnInventario.addActionListener((ae) -> {
			Form form = new InventarioForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			form.show();
		});
		this.add(btnInventario);
		
		Button btnLoja = new MeuBotao("Loja");
		btnLoja.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, btnInventario.getStyle(), 5));
		btnLoja.getAllStyles().setMarginTop(4);
		btnLoja.addActionListener((ae) -> {
			Form form = new LojaForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			form.show();
		});
		this.add(btnLoja);
		
		Button btnArmazem = new MeuBotao("Armazém");
		fm = FontImage.createFixed("\uE95A", fnt, 0x000000, size, size);
		btnArmazem.setIcon(fm);
		btnArmazem.getAllStyles().setMarginTop(4);
		btnArmazem.addActionListener((ae) -> {
			Form form = new ArmazemForm();
			
			AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), form);
			form.show();
		});
		this.add(btnArmazem);
	}
}
