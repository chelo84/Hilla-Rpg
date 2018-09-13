package hillaRPG.gui.telaprincipal.telaaventura.batalha;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.telaprincipal.telaaventura.Area;
import hillaRPG.gui.util.AddBackCmd;
import hillaRPG.gui.util.BotaoGrande;
import hillaRPG.gui.util.BotaoItem;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.LabelGrande;
import hillaRPG.gui.util.LabelPequeno;
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.MeuLabel;
import hillaRPG.gui.util.SliderMana;
import hillaRPG.gui.util.SliderVida;
import hillaRPG.gui.util.SliderXp;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.itens.Item;
import hillaRPG.magia.Magia;
import hillaRPG.monstro.Monstro;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.MagiasPersonagem;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class BatalhaForm extends MeuForm {
	private Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
	private int size = Display.getInstance().convertToPixels(5);
	private FontImage fm, fms;
	private Command backCmd;
	
	private Personagem personagem;
	private Personagens personagens;
	private Monstro monstro;
	private Area area;
	private Batalha batalha;
	private Slider barraVidaPersonagem, barraManaPersonagem, barraXpPersonagem, barraVidaMonstro;
	private Container ctnrNorte, ctnrCentro, ctnrSul;
	private Container ctnrBatalha, ctnrDiv, ctnrInvMagias, ctnrBtns;
	private Container ctnrLog = new Container(BoxLayout.y());
	private Button btnDrop;
	private TextArea txtInfoInvMagias;
	private ArrayList<Button> btns;
	private Button btnMagias = new MeuBotao(), btnInventario = new MeuBotao(), btnUsarItem = new MeuBotao(), btnMagia = new MeuBotao();
	
	public BatalhaForm(String area) {
		this.area = Jogo.getAreasJogo().get(area);
		this.personagem = Jogo.getPersonagem();
		this.personagens = Jogo.getPersonagens();
		
		this.setBatalha();
		
		this.monstro = batalha.getMonstro();
		
		AddBackCmd.addBackCmd(Display.getInstance().getCurrent(), this);
		
		this.createComponents();
	}


	private void createComponents() {
		this.setLayout(new BorderLayout());
		
		backCmd = this.getBackCommand();
		this.removeCommand(backCmd);
		
		ctnrNorte = new Container(new GridLayout(1,2));
		ctnrLog.setScrollableX(true);
		ctnrLog.setScrollableY(true);
		
		Button btnBatalha = new BotaoGrande("Batalha");
		btnBatalha.getAllStyles().setAlignment(TextArea.CENTER);
		btnBatalha.getAllStyles().setFgColor(ColorUtil.WHITE);
		btnBatalha.getAllStyles().setBgColor(ColorUtil.BLACK);
		Button btnLog = new BotaoGrande("Log");
		
		btnBatalha.addActionListener((ae) -> {
			btnBatalha.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnBatalha.getAllStyles().setFgColor(ColorUtil.WHITE);
			
			btnLog.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnLog.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnBatalha.repaint();
			btnLog.repaint();
			
			paintBatalha();
			ctnrSul.setEnabled(true);
			ctnrSul.setVisible(true);
			
			btnMagia.setEnabled(btnMagias.getUnselectedStyle().getBgColor() == ColorUtil.BLACK);
			btnUsarItem.setEnabled(btnInventario.getUnselectedStyle().getBgColor() == ColorUtil.BLACK);
			btnDrop.setEnabled(monstro.getVidaAtual() <= 0);
			
			ctnrSul.repaint();
			ctnrSul.revalidate();
		});
		
		ctnrNorte.add(btnBatalha);
		
		btnLog.addActionListener((ae) -> {
			btnLog.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnLog.getAllStyles().setFgColor(ColorUtil.WHITE);
		
			btnBatalha.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnBatalha.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnLog.repaint();
			btnBatalha.repaint();
			
			paintLog();
			ctnrSul.setEnabled(false);
			ctnrSul.setVisible(false);
			ctnrSul.repaint();
			ctnrSul.revalidate();
		});
		
		btnLog.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrNorte.add(btnLog);
		
		this.add(BorderLayout.NORTH, ctnrNorte);
		ctnrNorte = new Container(new GridLayout(1,2));

		
		ctnrCentro = new Container(BoxLayout.y());
		this.add(BorderLayout.CENTER, ctnrCentro);
		
		ctnrSul = new Container(new FlowLayout());
		this.add(BorderLayout.SOUTH, ctnrSul);
		
		paintBatalha();
		paintControles();
	}
	
	private void paintBatalha() {
		ctnrCentro.removeAll();
		
		ctnrBatalha = new Container(new GridLayout(1, 3));
		ctnrCentro.add(ctnrBatalha);
		Container ctnrPersonagem = new Container(BoxLayout.y());
		
		Label lbNomePersonagem = new LabelPequeno(this.personagem.getNome());
		lbNomePersonagem.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrPersonagem.add(lbNomePersonagem);
		
		barraVidaPersonagem = new SliderVida();
		barraVidaPersonagem.setEditable(false);
		
		barraVidaPersonagem.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		barraVidaPersonagem.getAllStyles().setPaddingRight(0);
		barraVidaPersonagem.setMinValue(0);
		barraVidaPersonagem.setMaxValue(this.personagem.getVidaMax());
		barraVidaPersonagem.setProgress(this.personagem.getVidaAtual());
		barraVidaPersonagem.setText(barraVidaPersonagem.getProgress() +"/"+ barraVidaPersonagem.getMaxValue());
		ctnrPersonagem.add(barraVidaPersonagem);
		
		barraManaPersonagem = new SliderMana();
		barraManaPersonagem.setEditable(false);
		
		barraManaPersonagem.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		barraManaPersonagem.getAllStyles().setPaddingRight(0);
		barraManaPersonagem.setMinValue(0);
		barraManaPersonagem.setMaxValue(this.personagem.getManaMax());
		barraManaPersonagem.setProgress(this.personagem.getManaAtual());
		barraManaPersonagem.setText(barraManaPersonagem.getProgress() +"/"+ barraManaPersonagem.getMaxValue());
		ctnrPersonagem.add(barraManaPersonagem);
		
		barraXpPersonagem = new SliderXp();
		barraXpPersonagem.setEditable(false);
		
		barraXpPersonagem.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		barraXpPersonagem.getAllStyles().setPaddingRight(0);
		barraXpPersonagem.setMinValue(0);
		barraXpPersonagem.setMaxValue(this.personagem.getXpNecessaria());
		barraXpPersonagem.setProgress(this.personagem.getXpAtual());
		barraXpPersonagem.setText(barraXpPersonagem.getProgress() +"/"+ barraXpPersonagem.getMaxValue());
		ctnrPersonagem.add(barraXpPersonagem);
		
		ctnrBatalha.add(ctnrPersonagem);
		
		Label lbVs = new LabelGrande("VS");
		lbVs.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrBatalha.add(lbVs);
		
		Container ctnrMonstro = new Container(BoxLayout.y());
		ctnrBatalha.add(ctnrMonstro);
		
		Label lbNomeMonstro = new LabelPequeno(monstro.getNome());
		lbNomeMonstro.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrMonstro.add(lbNomeMonstro);
		
		barraVidaMonstro = new SliderVida();
		barraVidaMonstro.setEditable(false);
		
		barraVidaMonstro.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		barraVidaMonstro.getAllStyles().setPaddingRight(0);
		barraVidaMonstro.setMinValue(0);
		barraVidaMonstro.setMaxValue(monstro.getVida());
		barraVidaMonstro.setProgress(monstro.getVidaAtual());
		barraVidaMonstro.setText(barraVidaMonstro.getProgress() +"/"+ barraVidaMonstro.getMaxValue());
		ctnrMonstro.add(barraVidaMonstro);
		
		ctnrBatalha.repaint();
		ctnrDiv = new Container(new GridLayout(1, 2));
		ctnrDiv.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(3));
		
		ctnrInvMagias = new Container(BoxLayout.y());

		ctnrInvMagias.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
		
		ctnrDiv.add(ctnrInvMagias);
		ctnrCentro.add(ctnrDiv);
		
		txtInfoInvMagias = new TxtAreaInfo(6);
		txtInfoInvMagias.setEditable(false);
		ctnrDiv.add(txtInfoInvMagias);
		
		paintInvMagias(ctnrInvMagias);
		
		Container.setSameSize(txtInfoInvMagias,ctnrInvMagias);
		
		ctnrCentro.repaint();
		ctnrCentro.revalidate();
	}
	
	public void paintControles() {
		Button btnFugir = new MeuBotao("Fugir");
		btnFugir.addActionListener((ae) -> {
			if(batalha.fugir(ctnrLog)) {
				this.getToolbar().setBackCommand(backCmd);
				
				this.personagem.curarVida(this.personagem.getVidaMax());
				this.personagens.atualizarInfoPersonagem();
				
				this.removeComponent(ctnrSul);
				this.repaint();
				this.revalidate();
			}
			
			atualizarInfoBatalha();
		});
		btnFugir.getAllStyles().setPaddingLeft(1);
		btnFugir.getAllStyles().setPaddingRight(1);
		
		btnUsarItem = new MeuBotao("Usar Item");
		btnUsarItem.addActionListener((ae) -> {
			try {
				Item item = null;
				Button button = null;
				for(Button btn : btns) {
					if(btn.getUnselectedStyle().getBgColor() == ColorUtil.LTGRAY) {
						String idItem = btn.getText().trim().toLowerCase().replace(' ', '_');
						idItem = idItem.substring(0, (idItem.length()-3));
						item = Jogo.getItensJogo().get(idItem).clone();
						
						button = btn;
					}
				}

				if(!personagem.usarItem(item)) {
					Dialog.show("Não foi possivel usar",
							"Não foi possível usar o item",
							"Ok", null);
				} else {
					personagem.getInventario().remove(item.getId());
					personagens.atualizarInfoPersonagem();

					button.setText(item.getNome() +" x"+ personagem.getInventario().getQtd(item.getId()));
				}
			} catch (NullPointerException ex) {
				Dialog.show("Nenhum item selecionado", "Nenhum item foi selecionado.", "Ok", null);

			} catch (ArrayIndexOutOfBoundsException ex) {
				limparCtnrInfo();
				paintInventario(ctnrInvMagias);
			}
			
			atualizarInfoBatalha();
		});
		btnUsarItem.setEnabled(false);
		btnUsarItem.getAllStyles().setPaddingLeft(1);
		btnUsarItem.getAllStyles().setPaddingRight(1);
		
		btnMagia = new MeuBotao("Magia");
		btnMagia.addActionListener((ae) -> {
			try {
				Magia magia = null;
				for(Button btn : btns) {
					if(btn.getUnselectedStyle().getBgColor() == ColorUtil.LTGRAY) {
						String idMagia = btn.getText().trim().toLowerCase().replace(' ', '_');
						magia = Jogo.getMagiasJogo().get(idMagia);
					}
				}

				batalha.usarMagia(ctnrLog, magia);
			} catch (NullPointerException ex) {
				Dialog.show("Nenhuma magia selecionada", "Nenhuma magia foi selecionada.", "Ok", null);
			}
			atualizarInfoBatalha();
		});
		btnMagia.getAllStyles().setPaddingLeft(1);
		btnMagia.getAllStyles().setPaddingRight(1);
		
		Button btnAtacar = new MeuBotao("Atacar");
		btnAtacar.addActionListener((ae) -> {
			batalha.atacar(ctnrLog);
			
			atualizarInfoBatalha();
		});
		btnAtacar.getAllStyles().setPaddingLeft(1);
		btnAtacar.getAllStyles().setPaddingRight(1);
		
		btnDrop = new MeuBotao("Drop");
		btnDrop.addActionListener((ae) -> {
			Form form = new DropForm(this.monstro);
			
			form.show();
		});
		
		ctnrSul = FlowLayout.encloseCenter(btnFugir, btnUsarItem, btnMagia, btnAtacar);
		ctnrSul.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
		this.add(BorderLayout.SOUTH, ctnrSul);
	}
	
	private void atualizarInfoBatalha() {
		if(this.personagem.getVidaAtual() <= 0) {
			this.personagem.curarVida(this.personagem.getVidaMax());
            this.personagem.addXp(-(this.personagem.getXpAtual()/10));
            
            this.personagens.atualizarInfoPersonagem();
            
			this.getToolbar().setBackCommand(backCmd);
			this.removeComponent(ctnrSul);
			this.repaint();
			this.revalidate();
			
			Dialog.show("Você morreu", "Você morreu e perdeu 10% de experiência, volte a tela anterior", "Ok", null);
		} else if(this.monstro.getVidaAtual() <= 0) {
			btnDrop.setEnabled(true);
			this.getToolbar().setBackCommand(backCmd);
			
			this.personagem.curarVida(this.personagem.getVidaMax());
			this.personagem.setManaAtual(this.personagem.getManaMax());
			ctnrLog.add(new MeuLabel(this.personagem.getNome() +" venceu e recebeu "+ this.monstro.getXp() +" de XP"));
			this.personagem.addXp(this.monstro.getXp());
            this.personagens.atualizarInfoPersonagem();
            
            ctnrSul.removeAll();
            ctnrSul.setLayout(BoxLayout.y());
            
            ctnrSul.add(btnDrop);
			ctnrSul.repaint();
			ctnrSul.revalidate();
			
			this.repaint();
			this.revalidate();
		}
		
		barraVidaPersonagem.setProgress(this.personagem.getVidaAtual());
		barraVidaPersonagem.setText(barraVidaPersonagem.getProgress() +"/"+ barraVidaPersonagem.getMaxValue());
		
		barraManaPersonagem.setProgress(this.personagem.getManaAtual());
		barraManaPersonagem.setText(barraManaPersonagem.getProgress() +"/"+ barraManaPersonagem.getMaxValue());
		
		barraXpPersonagem.setProgress(this.personagem.getXpAtual());
		barraXpPersonagem.setText(barraXpPersonagem.getProgress() +"/"+ barraXpPersonagem.getMaxValue());
		
		barraVidaMonstro.setProgress(monstro.getVidaAtual());
		barraVidaMonstro.setText(barraVidaMonstro.getProgress() +"/"+ barraVidaMonstro.getMaxValue());
	}


	private void paintInvMagias(Container ctnrInvMagias) {
		ctnrInvMagias.removeAll();
		
		btnMagias = new BotaoItem("Magias");
		btnMagias.getAllStyles().setMarginTop(1);
		btnMagias.getAllStyles().setAlignment(TextArea.CENTER);
		
		btnInventario = new BotaoItem("Inventário");
		btnInventario.getAllStyles().setMarginTop(1);
		btnInventario.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrBtns = new Container(new GridLayout(1,2));
		ctnrBtns.addAll(btnMagias, btnInventario);
		
		btnMagias.addActionListener((ae) -> {
			ctnrInvMagias.removeAll();
			ctnrInvMagias.add(ctnrBtns);
			paintMagias(ctnrInvMagias);
			btnMagia.setEnabled(true);
			btnUsarItem.setEnabled(false);
			limparCtnrInfo();

			btnMagias.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnMagias.getAllStyles().setFgColor(ColorUtil.WHITE);
			
			btnInventario.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnInventario.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnMagias.repaint();
			btnInventario.repaint();
		});
		
		btnInventario.addActionListener((ae) -> {
			ctnrInvMagias.removeAll();
			ctnrInvMagias.add(ctnrBtns);
			paintInventario(ctnrInvMagias);
			btnMagia.setEnabled(false);
			btnUsarItem.setEnabled(true);
			limparCtnrInfo();
			
			btnMagias.getAllStyles().setBgColor(ColorUtil.LTGRAY);
			btnMagias.getAllStyles().setFgColor(ColorUtil.BLACK);
			
			btnInventario.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnInventario.getAllStyles().setFgColor(ColorUtil.WHITE);
			
			btnMagias.repaint();
			btnInventario.repaint();
		});
		
		btnMagias.getAllStyles().setAlignment(TextArea.CENTER);
		btnMagias.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnMagias.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		ctnrInvMagias.add(ctnrBtns);
		
		paintMagias(ctnrInvMagias);
	}
	
	private void paintInventario(Container ctnrInvMagias) {
		ctnrInvMagias.removeAll();
		ctnrInvMagias.add(ctnrBtns);
		
		Inventario inventario = this.personagem.getInventario();
		
		btns = new ArrayList<>();
		for(int i = 0; i < inventario.size(); i++) {
			Item item = inventario.getItem(i);
			if(item.isUsavel()) {
				Button btn = new BotaoItem();
				btn.addActionListener((ae) -> {
					for(Button button : btns) {
						if(button.equals(btn)) {
							button.getAllStyles().setBgColor(ColorUtil.LTGRAY);
							button.getAllStyles().setFgColor(ColorUtil.BLACK);
							button.setIcon(button.getPressedIcon());
							button.repaint();

							limparCtnrInfo();
							txtInfoInvMagias.setText(item.toString());
						} else {
							button.getAllStyles().setBgColor(ColorUtil.BLACK);
							button.getAllStyles().setFgColor(ColorUtil.WHITE);
							button.setIcon(button.getDisabledIcon());
							button.repaint();
						}
					}
				});

				btn.setText(item.getNome() +" x"+ inventario.getQtd(item.getId()));

				fm = FontImage.createFixed(item.getUnicode(), fnt, 0xffffff, size, size);
				fms = FontImage.createFixed(item.getUnicode(), fnt, 0x000000, size, size);

				btn.setIcon(fm);
				btn.setDisabledIcon(fm);
				btn.setPressedIcon(fms);

				btn.getAllStyles().setBgColor(ColorUtil.BLACK);
				btn.getAllStyles().setFgColor(ColorUtil.WHITE);
				btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));

				ctnrInvMagias.add(btn);
				btns.add(btn);
			}
		}
		
		ctnrInvMagias.repaint();
		ctnrInvMagias.revalidate();
	}


	private void paintMagias(Container ctnrInvMagias) {
		MagiasPersonagem magias = this.personagem.getMagias();
		
		btns = new ArrayList<>();
		for(int i = 0; i < magias.size(); i++) {
			Magia magia = magias.get(i);
			Button btn = new BotaoItem(magia.getNome());
			btn.addActionListener((ae) -> {
				for(Button button : btns) {
					if(button.equals(btn)) {
						button.getAllStyles().setBgColor(ColorUtil.LTGRAY);
						button.getAllStyles().setFgColor(ColorUtil.BLACK);
						button.setIcon(button.getPressedIcon());
						button.repaint();	
						
						txtInfoInvMagias.setText(magia.toString());
					} else {
						button.getAllStyles().setBgColor(ColorUtil.BLACK);
						button.getAllStyles().setFgColor(ColorUtil.WHITE);
						button.setIcon(button.getDisabledIcon());
						button.repaint();
					}
				}
			});
			
			fm = FontImage.createFixed(magia.getUnicode(), fnt, 0xffffff, size, size);
			fms = FontImage.createFixed(magia.getUnicode(), fnt, 0x000000, size, size);
			
			btn.setIcon(fm);
			btn.setDisabledIcon(fm);
			btn.setPressedIcon(fms);
			
			btn.getAllStyles().setBgColor(ColorUtil.BLACK);
			btn.getAllStyles().setFgColor(ColorUtil.WHITE);
			btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
			
			ctnrInvMagias.add(btn);
			btns.add(btn);
		}
		
		ctnrInvMagias.repaint();
		ctnrInvMagias.revalidate();
	}
	
	private void paintLog() {
		ctnrCentro.removeAll();
		
		ctnrCentro.add(ctnrLog);
		
		ctnrCentro.repaint();
		ctnrCentro.revalidate();
	}
	
	private void setBatalha() {
        this.batalha = new Batalha(this.area);
	}
	
	public void limparCtnrInfo() {
		txtInfoInvMagias.setText("");
	}
}
