package hillaRPG.gui.telaprincipal.telaloja;

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
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class LojaForm extends MeuForm{
	private Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
	private Font flaticon = Font.createTrueTypeFont("flaticon", "flaticon.ttf");
	private int size = Display.getInstance().convertToPixels(5);
	private FontImage fm, fms;
	private TextArea txtInfo = new TxtAreaInfo(6);
	
	private Personagem personagem;
	private Personagens personagens;
	
	private Container ctnr = new Container(BoxLayout.y());
	private Container ctnrInfo = new Container(BoxLayout.y());
	private Container ctnrTamInvEOuro;
	private Button btnItemSelec = null;
	private Button btnTipoSelec = null;
	
	public LojaForm() {
		this.setLayout(new BorderLayout());
		
		personagem = Jogo.getPersonagem();
		personagens = Jogo.getPersonagens();
		
		createComponents();
	}
	

	public LojaForm(String title) {
		this();
		
		this.setTitle(title);
	}


	private void createComponents() {
		Container ctnrBtnsCV = new Container(new GridLayout(1,2));
		
		Button btnComprar = new BotaoGrande("Comprar");
		Button btnVender = new BotaoGrande("Vender");
		
		btnComprar.addActionListener((ae) -> {
			paintComprar(ctnr);
			limparCtnrInfo();

			btnComprar.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnComprar.getAllStyles().setFgColor(ColorUtil.WHITE);
			
			btnVender.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnVender.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnComprar.repaint();
			btnVender.repaint();
		});
		
		btnVender.addActionListener((ae) -> {
			paintVender(ctnr);
			limparCtnrInfo();
			
			btnComprar.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnComprar.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnVender.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnVender.getAllStyles().setFgColor(ColorUtil.WHITE);
			
			btnComprar.repaint();
			btnVender.repaint();
		});
		
		btnComprar.getAllStyles().setAlignment(TextArea.CENTER);
		btnComprar.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnComprar.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		btnVender.getAllStyles().setAlignment(TextArea.CENTER);
		
		ctnrBtnsCV.add(btnComprar);
		ctnrBtnsCV.add(btnVender);
		
		this.add(BorderLayout.NORTH, ctnrBtnsCV);
		
		ctnr.setScrollableY(true);
		this.add(BorderLayout.CENTER, ctnr);
		
		paintComprar(ctnr);
		
		txtInfo.setEditable(false);
		ctnrInfo.add(txtInfo);
		
		this.add(BorderLayout.SOUTH, ctnrInfo);
	}
	
	private void paintComprar(Container ctnr) {
		ctnr.removeAll();
		btnItemSelec = null;
		btnTipoSelec = null;
		
		Inventario inventario = this.personagem.getInventario();
		ctnrTamInvEOuro = new Container(new BorderLayout());
		ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(inventario.size() +"/"+ inventario.TAMANHOINVENTARIO));
		ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnr.add(ctnrTamInvEOuro);
		
		final int NUM_TIPOS = 3;
		Container ctnrTipoItem = new Container(new GridLayout(1, NUM_TIPOS));
		Container ctnrItens = new Container(BoxLayout.y());
		
		Button btnEquips = new MeuBotao();
		btnEquips.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if(btnTipoSelec == btnEquips) return;
				
				btnEquips.getAllStyles().setBgColor(ColorUtil.BLACK);
				btnEquips.getAllStyles().setFgColor(ColorUtil.WHITE);
				btnEquips.setIcon(btnEquips.getPressedIcon());
				btnEquips.repaint();

				deselecionarItem();

				btnTipoSelec = btnEquips;
				
				paintEquips(ctnrItens);
			}
		});
		ctnrTipoItem.add(btnEquips);
		setIcon(btnEquips, "\uEA1A", "equips");
		
		Button btnMagias = new MeuBotao();
		btnMagias.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if(btnTipoSelec == btnMagias) return;
				
				btnMagias.getAllStyles().setBgColor(ColorUtil.BLACK);
				btnMagias.getAllStyles().setFgColor(ColorUtil.WHITE);
				btnMagias.setIcon(btnMagias.getPressedIcon());
				btnMagias.repaint();

				deselecionarItem();

				btnTipoSelec = btnMagias;
				
				paintMagias(ctnrItens);
			}
		});
		ctnrTipoItem.add(btnMagias);
		setIcon(btnMagias, "\uEA89", "magias");
		
		Button btnUsaveis = new MeuBotao();
		btnUsaveis.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				if(btnTipoSelec == btnUsaveis) return;
				
				btnUsaveis.getAllStyles().setBgColor(ColorUtil.BLACK);
				btnUsaveis.getAllStyles().setFgColor(ColorUtil.WHITE);
				btnUsaveis.setIcon(btnUsaveis.getPressedIcon());
				btnUsaveis.repaint();

				deselecionarItem();

				btnTipoSelec = btnUsaveis;
				
				paintUsaveis(ctnrItens);
			}
		});
		ctnrTipoItem.add(btnUsaveis);
		setIcon(btnUsaveis, "\uEA18", "usaveis");
		
		ctnr.add(ctnrTipoItem);
		ctnr.add(ctnrItens);
	}
	
	private void paintEquips(Container ctnrItens) {
		ctnrItens.removeAll();
		
		paintItensCompra(ctnrItens, 0);

		ctnrItens.repaint();
		ctnrItens.revalidate();
	}
	
	private void paintMagias(Container ctnrItens) {
		ctnrItens.removeAll();
		
		paintItensCompra(ctnrItens, 1);

		ctnrItens.repaint();
		ctnrItens.revalidate();
	}
	
	private void paintUsaveis(Container ctnrItens) {
		ctnrItens.removeAll();
		
		paintItensCompra(ctnrItens, 2);
		
		ctnrItens.repaint();
		ctnrItens.revalidate();
	}

	private void paintItensCompra(Container ctnrItens, int x) {		
		Inventario inventario = personagem.getInventario();
		
		Itens itens = null;
		switch(x) {
		case 0 : itens = Jogo.getLojaJogo().getEquips(); break;
		case 1 : itens = Jogo.getLojaJogo().getMagias(); break;
		case 2 : itens = Jogo.getLojaJogo().getUsaveis(); break;
		}
		
		for(int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			Button btn = new BotaoItem();
			btn.addActionListener((ae) -> {
				if(btnItemSelec == btn) return;
				
				btn.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				btn.getAllStyles().setFgColor(ColorUtil.BLACK);
				btn.setIcon(btn.getPressedIcon());
				btn.repaint();
				
				ctnrInfo.removeAll();
				ctnrInfo.add(txtInfo);
				txtInfo.setText(item.toString() + "\n Preço: "+ item.getPrecoCompra() +" ouro(s)");
				
				ArrayList<Button> btnsInfoItem = new ArrayList<>();
				Button btnComprar = new BotaoInfoItem("Comprar");
				btnComprar.addActionListener((actEv) -> {
					if(inventario.remove("ouro", item.getPrecoCompra())) {
						if (inventario.add(item.getId(), 1)) {
							
						} else {
							inventario.add("ouro", item.getPrecoCompra());
						}
						
						personagens.atualizarInfoPersonagem();
						
						limparCtnrInfo();
						ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(inventario.size() +"/"+ inventario.TAMANHOINVENTARIO));
						ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
					}
				});
				
				ctnrInfo.add(btnComprar);
				ctnrInfo.repaint();
				ctnrInfo.revalidate();
				
				try {
					btnItemSelec.getAllStyles().setBgColor(ColorUtil.BLACK);
					btnItemSelec.getAllStyles().setFgColor(ColorUtil.WHITE);
					btnItemSelec.setIcon(btnItemSelec.getDisabledIcon());
					btnItemSelec.repaint();
				} catch (NullPointerException ex) {
					System.err.println("Nenhum item selecionado.");
				}
				
				btnItemSelec = btn;
			});
			
			btn.setText(item.getNome());
			
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
		}
		
	}

	private void paintVender(Container ctnrItens) {
		ctnrItens.removeAll();
		
		Inventario inventario = personagem.getInventario();
		ctnrTamInvEOuro = new Container(new BorderLayout());
		ctnrTamInvEOuro.add(BorderLayout.WEST, new LabelPequeno(inventario.size() +"/"+ inventario.TAMANHOINVENTARIO));
		ctnrTamInvEOuro.add(BorderLayout.EAST, new LabelPequeno("Ouro: "+ inventario.getOuro()));
		ctnr.add(ctnrTamInvEOuro);
		
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
				txtInfo.setText(item.toString() + "\n Preço: "+ item.getPrecoVenda() +" ouro(s)");
				
				ArrayList<Button> btnsInfoItem = new ArrayList<>();
				Button btnComprar = new BotaoInfoItem("Vender");
				btnComprar.addActionListener((actEv) -> {
					if (inventario.remove(item.getId(), 1)) {
						if(inventario.add("ouro", item.getPrecoVenda())) {
							
						} else {
							inventario.add(item.getId(), 1);
						}
						
						personagens.atualizarInfoPersonagem();
						
						btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));
						
						limparCtnrInfo();
						paintVender(ctnrItens);
					}
				});
				
				ctnrInfo.add(btnComprar);
				ctnrInfo.repaint();
				ctnrInfo.revalidate();
				
				try {
					btnItemSelec.getAllStyles().setBgColor(ColorUtil.BLACK);
					btnItemSelec.getAllStyles().setFgColor(ColorUtil.WHITE);
					btnItemSelec.setIcon(btnItemSelec.getDisabledIcon());
					btnItemSelec.repaint();
				} catch (NullPointerException ex) {
					System.err.println("Nenhum item selecionado.");
				}
				
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
		}
		ctnrItens.repaint();
		ctnrItens.revalidate();
	}
	
	public void setIcon(Button btn, String unicode, String tipo) {
		fm = FontImage.createFixed(unicode, fnt, 0x000000, size, size);
		fms = FontImage.createFixed(unicode, fnt, 0xffffff, size, size);
		
		btn.setIcon(fm);
		btn.setDisabledIcon(fm);
		btn.setPressedIcon(fms);
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
			btnTipoSelec.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnTipoSelec.getAllStyles().setFgColor(ColorUtil.BLACK);
			btnTipoSelec.setIcon(btnTipoSelec.getDisabledIcon());
			btnTipoSelec.repaint();
		} catch (NullPointerException ex) {
			System.err.println("Nenhum item selecionado.");
		}
	}
}
