package hillaRPG.gui.telaprincipal.telaaventura.batalha;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.telaprincipal.telainventario.BotaoInfoItem;
import hillaRPG.gui.util.AddBackCmd;
import hillaRPG.gui.util.BotaoItem;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.itens.Item;
import hillaRPG.monstro.InventarioMonstro;
import hillaRPG.monstro.Monstro;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class DropForm extends MeuForm {
	private Personagem personagem;
	private Personagens personagens;
	private Monstro monstro;

	private Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
	private Font flaticon = Font.createTrueTypeFont("flaticon", "flaticon.ttf");
	private int size = Display.getInstance().convertToPixels(5);
	private FontImage fm, fms;
	private TextArea txtInfo = new TxtAreaInfo(6);

	private Container ctnrInfo = new Container(BoxLayout.y());
	private Container ctnrCentro = new Container(BoxLayout.y());;
	private Button btnItemSelec = null;

	public DropForm(Monstro monstro) {
		this.personagem = Jogo.getPersonagem();
		this.personagens = Jogo.getPersonagens();
		this.monstro = monstro;
		txtInfo.setEditable(false);

		AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), this);
		createComponents();
	}

	public void createComponents() {
		this.setLayout(new BorderLayout());

		this.add(BorderLayout.CENTER, ctnrCentro);
		paintDrop(ctnrCentro);


		this.add(BorderLayout.SOUTH, ctnrInfo);
	}

	public void paintDrop(Container ctnr) {
		ctnr.removeAll();
		btnItemSelec = null;

		InventarioMonstro drop = monstro.getDrop();
		Inventario inventarioPersonagem = this.personagem.getInventario();

		ArrayList<Button> btns = new ArrayList<>();
		for(int i = 0; i < drop.size(); i++) {
			Item item = drop.getItem(i);
			Button btn = new BotaoItem();
			btn.addActionListener((ae) -> {
				if(btnItemSelec == btn) return;
				btn.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				btn.getAllStyles().setFgColor(ColorUtil.BLACK);
				btn.setIcon(btn.getPressedIcon());
				btn.repaint();

				ctnrInfo.removeAll();
				ctnrInfo.add(txtInfo);
				txtInfo.setText(item.toString());

				Button btnEquipar = new BotaoInfoItem("Pegar");
				btnEquipar.addActionListener((actEv) -> {
					int qtd = drop.getQtd(item.getId());
					if(inventarioPersonagem.add(item.getId(), qtd)) {
						drop.remove(item.getId(), qtd);

						this.personagens.atualizarInfoPersonagem();
						limparCtnrInfo();
						paintDrop(ctnr);
					} else {
						Dialog.show("Inventário cheio",
								"Seu inventário está cheio.",
								"Ok", null);
					}
				});
				ctnrInfo.add(btnEquipar);

				ctnrInfo.repaint();
				ctnrInfo.revalidate();

				deselecionarItem();

				btnItemSelec = btn;
			});

			btn.setText(item.getNome() +" x"+ drop.getQtd(item.getId()));

			if(item.getTipo().equals("calcas")) {
				fm = FontImage.createFixed(item.getUnicode(), flaticon, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), flaticon, 0x000000, size, size);

			} else {
				fm = FontImage.createFixed(item.getUnicode(), fnt, 0xffffff, size, size);

				fms = FontImage.createFixed(item.getUnicode(), fnt, 0x000000, size, size);
			}

			btn.setIcon(fm);
			btn.setDisabledIcon(fm);
			btn.setPressedIcon(fms);

			btn.getAllStyles().setBgColor(ColorUtil.BLACK);
			btn.getAllStyles().setFgColor(ColorUtil.WHITE);
			btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));

			ctnr.add(btn);
			btns.add(btn);
		}

		ctnr.repaint();
		ctnr.revalidate();
	}

	public void limparCtnrInfo() {
		ctnrInfo.removeAll();

		txtInfo.setText("");
		ctnrInfo.add(txtInfo);
		ctnrInfo.repaint();
		ctnrInfo.revalidate();
	}
	
	public void deselecionarItem() {
		try {
			btnItemSelec.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnItemSelec.getAllStyles().setFgColor(ColorUtil.WHITE);
			btnItemSelec.setIcon(btnItemSelec.getDisabledIcon());
			btnItemSelec.repaint();
		} catch (NullPointerException ex) {
			System.err.println("Nenhum item selecionado.");
		}
	}
}
