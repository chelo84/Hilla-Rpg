package hillaRPG.gui.telaprincipal.telainventario;

import java.util.ArrayList;
import java.util.HashMap;

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

import hillaRPG.gui.util.BotaoGrande;
import hillaRPG.gui.util.BotaoItem;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.LabelPequeno;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.itens.Item;
import hillaRPG.personagem.EquipSlots;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class InventarioForm extends MeuForm {
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

	public InventarioForm() {
		this.setLayout(new BorderLayout());

		personagem = Jogo.getPersonagem();
		personagens = Jogo.getPersonagens();

		createComponents();

		ctnrInfo.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
	}

	public InventarioForm(String title) {
		this();

		this.setTitle(title);
	}

	private void createComponents() {
		Container ctnrBtnsInvEquip = new Container(new GridLayout(1,2));

		Button btnEquipamento = new BotaoGrande("Equipamento");
		Button btnInventario = new BotaoGrande("Inventário");

		btnEquipamento.addActionListener((ae) -> {
			paintEquipamento(ctnrItens);
			limparCtnrInfo();

			btnEquipamento.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnEquipamento.getAllStyles().setFgColor(ColorUtil.WHITE);

			btnInventario.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnInventario.getAllStyles().setFgColor(ColorUtil.BLACK);

			btnEquipamento.repaint();
			btnInventario.repaint();
		});

		btnInventario.addActionListener((ae) -> {
			paintInventario(ctnrItens);
			limparCtnrInfo();

			btnEquipamento.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnEquipamento.getAllStyles().setFgColor(ColorUtil.BLACK);

			btnInventario.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnInventario.getAllStyles().setFgColor(ColorUtil.WHITE);

			btnEquipamento.repaint();
			btnInventario.repaint();
		});

		btnEquipamento.getAllStyles().setAlignment(TextArea.CENTER);
		btnEquipamento.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnEquipamento.getAllStyles().setFgColor(ColorUtil.WHITE);

		btnInventario.getAllStyles().setAlignment(TextArea.CENTER);

		ctnrBtnsInvEquip.add(btnEquipamento);
		ctnrBtnsInvEquip.add(btnInventario);

		this.add(BorderLayout.NORTH, ctnrBtnsInvEquip);

		ctnrItens.setScrollableY(true);
		this.add(BorderLayout.CENTER, ctnrItens);

		paintEquipamento(ctnrItens);

		txtInfo.setEditable(false);
		ctnrInfo.add(txtInfo);

		this.add(BorderLayout.SOUTH, ctnrInfo);
	}

	private void paintEquipamento(Container ctnrItens) {
		ctnrItens.removeAll();
		btnItemSelec = null;

		Inventario inventario = this.personagem.getInventario();
		Container ctnrOuro = new Container(new BorderLayout());
		ctnrOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnrItens.add(ctnrOuro);

		HashMap<EquipSlots, String> unicodes = new HashMap<>();
		unicodes.put(EquipSlots.ARMADURA, "\uEAE0");
		unicodes.put(EquipSlots.BOTAS, "\uE93C");
		unicodes.put(EquipSlots.CALCAS, "\uF100");
		unicodes.put(EquipSlots.ELMO, "\uEA1A");
		unicodes.put(EquipSlots.ESCUDO, "\uEA87");
		if(this.personagem.getEquipamento().get(EquipSlots.ARMA) != null) {
			unicodes.put(EquipSlots.ARMA, this.personagem.getEquipamento().get(EquipSlots.ARMA).getUnicode());
		} else {
			unicodes.put(EquipSlots.ARMA, "\uEA7E");
		}

		HashMap<EquipSlots, Item> equipamento = this.personagem.getEquipamento();
		ArrayList<Button> btns = new ArrayList<>();
		for(EquipSlots slot : EquipSlots.values()) {
			Item item = equipamento.get(slot);
			Button btn = new BotaoItem();
			btn.addActionListener((ae) -> {
				if(btnItemSelec == btn) return;

				btn.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				btn.getAllStyles().setFgColor(ColorUtil.BLACK);
				btn.setIcon(btn.getPressedIcon());
				btn.repaint();

				if(item != null) {
					ctnrInfo.removeAll();
					ctnrInfo.add(txtInfo);
					txtInfo.setText(item.toString());

					Button btnDesequipar = new BotaoInfoItem("Desequipar");
					btnDesequipar.addActionListener((actEv) -> {
						if(!this.personagem.desequiparItem(slot)) {
							Dialog.show("Inventário cheio",
									"Seu inventário está cheio, arrume um lugar para o item",
									"Ok", null);
						} else {
							this.personagens.atualizarInfoPersonagem();
							limparCtnrInfo();

							paintEquipamento(ctnrItens);
						}
					});

					Container ctnrInfoBtns = FlowLayout.encloseCenter(btnDesequipar);
					ctnrInfo.add(ctnrInfoBtns);
					ctnrInfo.repaint();
					ctnrInfo.revalidate();
				} else {
					limparCtnrInfo();
				}

				deselecionarItem();

				btnItemSelec = btn;
			});

			if(item != null) {
				btn.setText(equipamento.get(slot).getNome());
			} else {
				btn.setText("[vazio]");
			}

			if(slot.equals(EquipSlots.CALCAS)) {
				fm = FontImage.createFixed(unicodes.get(slot), flaticon, 0xffffff, size, size);

				fms = FontImage.createFixed(unicodes.get(slot), flaticon, 0x000000, size, size);
			} else {
				fm = FontImage.createFixed(unicodes.get(slot), fnt, 0xffffff, size, size);

				fms = FontImage.createFixed(unicodes.get(slot), fnt, 0x000000, size, size);
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

		Inventario inventario = this.personagem.getInventario();
		Container ctnrTamInvEOuro = new Container(new BorderLayout());
		ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(inventario.size() +"/"+ inventario.TAMANHOINVENTARIO));
		ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnrItens.add(ctnrTamInvEOuro);

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

				ArrayList<Button> btnsInfoItem = new ArrayList<>();
				Button btnEquipar = new BotaoInfoItem("Equipar");
				btnEquipar.addActionListener((actEv) -> {
					if(!personagem.equiparItem(item)) {
						Dialog.show("Não foi possivel equipar",
								"Não foi possível equipar o item, você não possui os requisitos"
										+ " necessários.",
										"Ok", null);
					} else {
						personagens.atualizarInfoPersonagem();

						limparCtnrInfo();

						paintInventario(ctnrItens);
					}
				});
				if(item.isEquipavel()) btnsInfoItem.add(btnEquipar);

				Button btnUsar = new BotaoInfoItem("Usar");
				btnUsar.addActionListener(new ActionListener() {
					private Item item;

					public void actionPerformed(ActionEvent ae) {
						if(!personagem.usarItem(item)) {
							Dialog.show("Não foi possivel usar",
									"Não foi possível usar o item",
									"Ok", null);
						} else {
							inventario.remove(item.getId());
							personagens.atualizarInfoPersonagem();

							try {
								btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));

							} catch (ArrayIndexOutOfBoundsException ex) {
								limparCtnrInfo();
								paintInventario(ctnrItens);
							}
						}
					}

				});
				if(item.isUsavel()) btnsInfoItem.add(btnUsar);

				Button btnAprender = new BotaoInfoItem("Aprender");
				btnAprender.addActionListener((actEv) -> {
					if(!personagem.usarItem(item)) {
						Dialog.show("Não foi possivel aprender",
								"Não foi possível aprender a magia, você não possui os requisitos"
										+ " necessários",
										"Ok", null);
					} else {
						inventario.remove(item.getId());
						personagens.atualizarInfoPersonagem();

						try {
							btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));

						} catch (ArrayIndexOutOfBoundsException ex) {
							limparCtnrInfo();
							paintInventario(ctnrItens);
						}
					}
				});
				if(item.getTipo().trim().toLowerCase().equals("magia")) btnsInfoItem.add(btnAprender);

				Button btnRemover = new BotaoInfoItem("Remover");
				btnRemover.addActionListener((actEv) -> {
					if(Dialog.show("Certeza?", "Deseja realmente remover o item?",
							"Sim", "Não")) {
						inventario.remove(item.getId(), inventario.getQtd(item.getId()));
						personagens.atualizarInfoPersonagem();
						limparCtnrInfo();
					}

					paintInventario(ctnrItens);
				});
				btnsInfoItem.add(btnRemover);

				Container ctnrInfoBtns = FlowLayout.encloseCenter(btnsInfoItem
						.toArray(new Button[btnsInfoItem.size()]));
				ctnrInfo.add(ctnrInfoBtns);
				ctnrInfo.repaint();
				ctnrInfo.revalidate();

				deselecionarItem();

				btnItemSelec = btn;
			});

			btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));

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

			ctnrItens.add(btn);
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
