package hillaRPG.gui.telaprincipal.telaarmazem;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.telaprincipal.telainventario.BotaoInfoItem;
import hillaRPG.gui.util.BotaoGrande;
import hillaRPG.gui.util.BotaoItem;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.LabelPequeno;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.itens.Item;
import hillaRPG.personagem.Armazem;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class ArmazemForm extends MeuForm {
	private Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
	private Font flaticon = Font.createTrueTypeFont("flaticon", "flaticon.ttf");
	private int size = Display.getInstance().convertToPixels(5);
	private FontImage fm, fms;
	private TextArea txtInfo = new TxtAreaInfo(6);

	private Personagem personagem;
	private Personagens personagens;

	private Container ctnrItens = new Container(BoxLayout.y());
	private Container ctnrInfo = new Container(BoxLayout.y());
	private Button btnItemSelec = null;

	public ArmazemForm() {
		this.setLayout(new BorderLayout());

		personagem = Jogo.getPersonagem();
		personagens = Jogo.getPersonagens();

		createComponents();

		ctnrInfo.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
	}

	public ArmazemForm(String title) {
		super(title);

		createComponents();
	}

	private void createComponents() {
		Container ctnrBtnsArmInv = new Container(new GridLayout(1,2));

		Button btnArmazem = new BotaoGrande("Armazém");
		Button btnInventario = new BotaoGrande("Inventário");

		btnArmazem.addActionListener((ae) -> {
			paintArmazem(ctnrItens);
			limparCtnrInfo();

			btnArmazem.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnArmazem.getAllStyles().setFgColor(ColorUtil.WHITE);

			btnInventario.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnInventario.getAllStyles().setFgColor(ColorUtil.BLACK);

			btnArmazem.repaint();
			btnInventario.repaint();
		});

		btnInventario.addActionListener((ae) -> {
			paintInventario(ctnrItens);
			limparCtnrInfo();

			btnArmazem.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnArmazem.getAllStyles().setFgColor(ColorUtil.BLACK);

			btnInventario.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnInventario.getAllStyles().setFgColor(ColorUtil.WHITE);

			btnArmazem.repaint();
			btnInventario.repaint();
		});

		btnArmazem.getAllStyles().setAlignment(TextArea.CENTER);
		btnArmazem.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnArmazem.getAllStyles().setFgColor(ColorUtil.WHITE);

		btnInventario.getAllStyles().setAlignment(TextArea.CENTER);

		ctnrBtnsArmInv.add(btnArmazem);
		ctnrBtnsArmInv.add(btnInventario);

		this.add(BorderLayout.NORTH, ctnrBtnsArmInv);

		ctnrItens.setScrollableY(true);
		this.add(BorderLayout.CENTER, ctnrItens);

		paintArmazem(ctnrItens);

		txtInfo.setEditable(false);
		ctnrInfo.add(txtInfo);

		this.add(BorderLayout.SOUTH, ctnrInfo);
	}

	public void paintArmazem(Container ctnrItens) {
		ctnrItens.removeAll();
		btnItemSelec = null;

		Inventario inventario = this.personagem.getInventario();
		Armazem armazem = this.personagem.getArmazem();
		Container ctnrTamInvEOuro = new Container(new BorderLayout());
		ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(armazem.size() +"/"+ armazem.TAMANHOARMAZEM));
		ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnrItens.add(ctnrTamInvEOuro);

		ArrayList<Button> btns = new ArrayList<>();
		for(int i = 0; i < armazem.size(); i++) {
			Item item = armazem.getItem(i);
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

				ArrayList<Button> btnsInfoItem = new ArrayList<>();
				Button btnRetirar = new BotaoInfoItem("Retirar");
				btnRetirar.addActionListener((actEv) -> {
					if(inventario.add(item, armazem.getQtd(item.getId()))) {
						armazem.remove(item.getId(), armazem.getQtd(item.getId()));
						this.personagens.atualizarInfoPersonagem();

						limparCtnrInfo();

						paintArmazem(ctnrItens);

					} else {
						Dialog.show("Não foi possivel retirar",
								"Não foi possível retirar o item do armazém, verifique se o inventário não está cheio.",
								"Ok", null);
					}
				});

				ctnrInfo.add(btnRetirar);
				ctnrInfo.repaint();
				ctnrInfo.revalidate();

				deselecionarItem();

				btnItemSelec = btn;
			});

			btn.setText(item.getNome() +" x"+ armazem.getQtd(item.getId()));

			if(item.getTipo().equals("calcas")) {
				fm = FontImage.createFixed(item.getUnicode(), flaticon, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), flaticon, 0x000000, size, size);

			}else {
				fm = FontImage.createFixed(item.getUnicode(), fnt, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), fnt, 0x000000, size, size);
			}
			btn.setIcon(fm);
			btn.setDisabledIcon(fm);
			btn.setPressedIcon(fms);

			btn.getAllStyles().setBgColor(ColorUtil.BLACK);
			btn.getAllStyles().setFgColor(ColorUtil.WHITE);
			btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));

			ctnrItens.add(btn);
			btns.add(btn);
		}

		ctnrItens.repaint();
		ctnrItens.revalidate();
	}

	public void paintInventario(Container ctnrItens) {
		ctnrItens.removeAll();
		btnItemSelec = null;

		Inventario inventario = this.personagem.getInventario();
		Armazem armazem = this.personagem.getArmazem();
		Container ctnrTamInvEOuro = new Container(new BorderLayout());
		ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(inventario.size() +"/"+ inventario.TAMANHOINVENTARIO));
		ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnrItens.add(ctnrTamInvEOuro);

		ArrayList<Button> btns = new ArrayList<>();
		for(int i = 0; i < inventario.size(); i++) {
			Item item = inventario.getItem(i);
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

				Button btnDepositar = new BotaoInfoItem("Depositar");
				btnDepositar.addActionListener((actEv) -> {
					if(armazem.add(item, inventario.getQtd(item.getId()))) {
						inventario.remove(item.getId(), inventario.getQtd(item.getId()));

						this.personagens.atualizarInfoPersonagem();

						limparCtnrInfo();

						paintInventario(ctnrItens);

					} else {
						Dialog.show("Não foi possivel depositar",
								"Não foi possível depositar o item, verifique se o armazém não está cheio.",
								"Ok", null);
					}
				});
				ctnrInfo.add(btnDepositar);

				ctnrInfo.repaint();
				ctnrInfo.revalidate();

				deselecionarItem();

				btnItemSelec = btn;
			});

			btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));

			if(item.getTipo().equals("calcas")) {
				fm = FontImage.createFixed(item.getUnicode(), flaticon, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), flaticon, 0x000000, size, size);

			}else {
				fm = FontImage.createFixed(item.getUnicode(), fnt, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), fnt, 0x000000, size, size);
			}
			btn.setIcon(fm);
			btn.setDisabledIcon(fm);
			btn.setPressedIcon(fms);

			btn.getAllStyles().setBgColor(ColorUtil.BLACK);
			btn.getAllStyles().setFgColor(ColorUtil.WHITE);
			btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));

			ctnrItens.add(btn);
			btns.add(btn);
		}

		ctnrItens.repaint();
		ctnrItens.revalidate();
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
