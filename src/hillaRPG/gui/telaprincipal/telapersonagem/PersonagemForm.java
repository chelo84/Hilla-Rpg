package hillaRPG.gui.telaprincipal.telapersonagem;

import com.codename1.charts.util.ColorUtil;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.telacriarpersonagem.MaisStatListener;
import hillaRPG.gui.telacriarpersonagem.MenosStatListener;
import hillaRPG.gui.util.BotaoGrande;
import hillaRPG.gui.util.BotaoPequeno;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.LabelGrande;
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.MeuLabel;
import hillaRPG.gui.util.MeuStatsLabel;
import hillaRPG.gui.util.SliderMana;
import hillaRPG.gui.util.SliderVida;
import hillaRPG.gui.util.SliderXp;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class PersonagemForm extends MeuForm {
	private final L10NManager lnm = L10NManager.getInstance();
	private Personagem personagem = Jogo.getPersonagem();
	private Personagens personagens = Jogo.getPersonagens();
	
	public PersonagemForm() {
		this.setLayout(BoxLayout.y());
		
		createComponents();
	}
	

	public PersonagemForm(String title) {
		super(title);
		
		createComponents();
	}

	private void createComponents() {
		this.removeAll();
		personagem = this.personagem;
		personagens = this.personagens;
		Button btnMagias = new BotaoGrande("Magias");
		Button btnInfo = new BotaoGrande("Info");
		btnInfo.addActionListener((ae) -> {
			
		});
		btnInfo.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnInfo.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		btnMagias.addActionListener((ae) -> {
			
		});
		
		Label lbNomePersonagem = new LabelGrande(this.personagem.getNome());
		lbNomePersonagem.getAllStyles().setAlignment(TextArea.CENTER);
		this.add(lbNomePersonagem);
		
		paintBarras(this.getContentPane());
		
		paintInfoPersonagem(this.getContentPane());
		
		paintStats(this.getContentPane());
		
		this.repaint();
		this.revalidate();
	}

	public void paintBarras(Container ctnr) {
		Slider sliderVida = new SliderVida();
		sliderVida.setEditable(false);
		
		sliderVida.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(2));
		sliderVida.setMinValue(0);
		sliderVida.setMaxValue(personagem.getVidaMax());
		sliderVida.setProgress(personagem.getVidaAtual());
		sliderVida.setText(sliderVida.getProgress() +"/"+ sliderVida.getMaxValue());
		
		Slider sliderMana = new SliderMana();
		sliderMana.setEditable(false);
		
		sliderMana.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		sliderMana.setMinValue(0);
		sliderMana.setMaxValue(personagem.getManaMax());
		sliderMana.setProgress(personagem.getManaAtual());
		sliderMana.setText(sliderMana.getProgress() +"/"+ sliderMana.getMaxValue());
		
		Slider sliderXp = new SliderXp();
		sliderXp.setEditable(false);
		
		sliderXp.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(1));
		sliderXp.setMinValue(0);
		sliderXp.setMaxValue(personagem.getXpNecessaria());
		sliderXp.setProgress(personagem.getXpAtual());
		sliderXp.setText(sliderXp.getProgress() +"/"+ sliderXp.getMaxValue());
		
		
		
		Button btnRestaurar = new MeuBotao("Restaurar");
		btnRestaurar.addActionListener((ae) -> {
			personagem.setVidaAtual(personagem.getVidaMax());
			personagem.setManaAtual(personagem.getManaMax());
			
			sliderVida.setMinValue(0);
			sliderVida.setMaxValue(personagem.getVidaMax());
			sliderVida.setProgress(personagem.getVidaAtual());
			sliderVida.setText(sliderVida.getProgress() +"/"+ sliderVida.getMaxValue());
			
			sliderMana.setMinValue(0);
			sliderMana.setMaxValue(personagem.getManaMax());
			sliderMana.setProgress(personagem.getManaAtual());
			sliderMana.setText(sliderMana.getProgress() +"/"+ sliderMana.getMaxValue());
			
			ctnr.repaint();
			ctnr.revalidate();
		});
		btnRestaurar.getUnselectedStyle().setBgColor(0x00ff00);
		btnRestaurar.getSelectedStyle().setBgColor(0x009933);
		btnRestaurar.getPressedStyle().setBgColor(0x009933);
		btnRestaurar.getAllStyles().setMarginBottom(2);
		
		Container ctnrBarras = FlowLayout.encloseCenter(sliderVida, sliderMana, sliderXp, btnRestaurar);
		ctnrBarras.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(1, ColorUtil.WHITE),
																		null, null, null));
		ctnr.add(ctnrBarras);
		
		Component.setSameWidth(sliderVida, sliderMana, sliderXp, btnRestaurar);
	}
	
	private void paintInfoPersonagem(Container ctnr) {
		Label lbLevel = new MeuLabel("Level: ");
		lbLevel.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(1, ColorUtil.WHITE),
			      null, null, null));
		Label lbValorLevel = new MeuLabel(""+ personagem.getLevel());
		
		Container container = FlowLayout.encloseIn(lbLevel, lbValorLevel);
		container.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(1, ColorUtil.WHITE),
			      null, null, null));
		
		ctnr.add(container);
		
		Label lbAtaque = new MeuLabel("Ataque: ");
		Label lbValorAtaque = new MeuLabel(""+ personagem.getAtaque());
		
		ctnr.add(FlowLayout.encloseIn(lbAtaque, lbValorAtaque));
		
		Label lbDanoMagico = new MeuLabel("Dano mágico: ");
		Label lbValorDanoMagico = new MeuLabel(""+ personagem.getDanoMagico());
		
		ctnr.add(FlowLayout.encloseIn(lbDanoMagico, lbValorDanoMagico));
		
		Label lbDefesa = new MeuLabel("Defesa: ");
		Label lbValorDefesa = new MeuLabel(""+ personagem.getDefesa());
		
		ctnr.add(FlowLayout.encloseIn(lbDefesa, lbValorDefesa));
		
		Label lbCritico = new MeuLabel("Critico: ");
		Label lbValorCritico = new MeuLabel(lnm.format((personagem.getChanceCrit()*100), 2) +"%");
		
		ctnr.add(FlowLayout.encloseIn(lbCritico, lbValorCritico));
		
		Label lbEvasao = new MeuLabel("Evasão: ");
		Label lbValorEvasao = new MeuLabel(lnm.format((personagem.getChanceEvasao()*100), 2) +"%");
		
		ctnr.add(FlowLayout.encloseIn(lbEvasao, lbValorEvasao));
	}
	
	private void paintStats(Container ctnr) {
		Container ctnrPtsStats = new Container(new BorderLayout());
		Label lbValorPtsStats = new MeuLabel(""+ personagem.getPontosStat());
		Label lbPtsStats = new MeuLabel("Pontos de Stats : "+ lbValorPtsStats.getText());
		lbPtsStats.getAllStyles().setAlignment(TextArea.CENTER);
		lbPtsStats.getAllStyles().setMarginTop(Display.getInstance().convertToPixels(2));
		
		ctnrPtsStats.add(BorderLayout.CENTER, lbPtsStats);
		Button btnInfo = new BotaoPequeno();
		btnInfo.addActionListener((ae) -> {
			Dialog.show("Informações", "1pt Força = +1 Ataque" +
									   "\n1pt Inteligência = +1 Dano mágico" +
									   "\n1pt Sorte = +2.50% Crítico" +
									   "\n1pt Agilidade = +1.00% Evasão" +
									   "\n1pt Vitalidade = +3 Vida", "Ok", null);
		});
		btnInfo.getAllStyles().setBgColor(ColorUtil.BLACK);
		btnInfo.getAllStyles().setFgColor(ColorUtil.WHITE);
		btnInfo.setIcon(FontImage.createMaterial(FontImage.MATERIAL_INFO, btnInfo.getStyle()));
		ctnrPtsStats.add(BorderLayout.EAST, btnInfo);
		
		ctnr.add(ctnrPtsStats);
		
		Container ctnrFor = new Container();
		
		Label lbFor = new MeuStatsLabel("Força: ");
		ctnrFor.add(lbFor);
		
		Label lbValorFor = new MeuStatsLabel(""+ personagem.getForca());
		
		Button btnMenosFor = new MeuBotao("<");
		btnMenosFor.addActionListener(new MenosStatListener(personagem.getForca(), lbValorFor, lbValorPtsStats,
															lbPtsStats));
		btnMenosFor.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrFor.add(btnMenosFor);
		
		ctnrFor.add(lbValorFor);
		
		Button btnMaisFor = new MeuBotao(">");
		btnMaisFor.addActionListener(new MaisStatListener(lbValorFor, lbValorPtsStats, lbPtsStats));
		btnMaisFor.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrFor.add(btnMaisFor);
		ctnr.add(ctnrFor);
		
		Container ctnrInt = new Container();
		
		Label lbInt = new MeuStatsLabel("Inteligência: ");
		ctnrInt.add(lbInt);
		
		Label lbValorInt = new MeuStatsLabel(""+ personagem.getInteligencia());
		
		Button btnMenosInt = new MeuBotao("<");
		btnMenosInt.addActionListener(new MenosStatListener(personagem.getInteligencia(), lbValorInt, lbValorPtsStats,
															lbPtsStats));
		btnMenosInt.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrInt.add(btnMenosInt);
		
		ctnrInt.add(lbValorInt);
		
		Button btnMaisInt = new MeuBotao(">");
		btnMaisInt.addActionListener(new MaisStatListener(lbValorInt, lbValorPtsStats, lbPtsStats));
		btnMaisInt.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrInt.add(btnMaisInt);
		ctnr.add(ctnrInt);
		
		Container ctnrSor = new Container();
		
		Label lbSor = new MeuStatsLabel("Sorte: ");
		ctnrSor.add(lbSor);
		
		Label lbValorSor = new MeuStatsLabel(""+ personagem.getSorte());
		
		Button btnMenosSor = new MeuBotao("<");
		btnMenosSor.addActionListener(new MenosStatListener(personagem.getSorte(), lbValorSor, lbValorPtsStats,
															lbPtsStats));
		btnMenosSor.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrSor.add(btnMenosSor);
		
		ctnrSor.add(lbValorSor);
		
		Button btnMaisSor = new MeuBotao(">");
		btnMaisSor.addActionListener(new MaisStatListener(lbValorSor, lbValorPtsStats, lbPtsStats));
		btnMaisSor.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrSor.add(btnMaisSor);
		ctnr.add(ctnrSor);
		
		Container ctnrAgi = new Container();
		
		Label lbAgi = new MeuStatsLabel("Agilidade: ");
		ctnrAgi.add(lbAgi);
		
		Label lbValorAgi = new MeuStatsLabel(""+ personagem.getAgilidade());
		
		Button btnMenosAgi = new MeuBotao("<");
		btnMenosAgi.addActionListener(new MenosStatListener(personagem.getAgilidade(), lbValorAgi, lbValorPtsStats,
															lbPtsStats));
		btnMenosAgi.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrAgi.add(btnMenosAgi);
		
		ctnrAgi.add(lbValorAgi);
		
		Button btnMaisAgi = new MeuBotao(">");
		btnMaisAgi.addActionListener(new MaisStatListener(lbValorAgi, lbValorPtsStats, lbPtsStats));
		btnMaisAgi.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrAgi.add(btnMaisAgi);
		ctnr.add(ctnrAgi);
		
		Container ctnrVit = new Container();
		
		Label lbVit = new MeuStatsLabel("Vitalidade: ");
		ctnrVit.add(lbVit);
		
		Label lbValorVit = new MeuStatsLabel(""+ personagem.getVitalidade());
		
		Button btnMenosVit = new MeuBotao("<");
		btnMenosVit.addActionListener(new MenosStatListener(personagem.getVitalidade(), lbValorVit, lbValorPtsStats,
															lbPtsStats));
		btnMenosVit.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrVit.add(btnMenosVit);
		
		ctnrVit.add(lbValorVit);
		
		Button btnMaisVit = new MeuBotao(">");
		btnMaisVit.addActionListener(new MaisStatListener(lbValorVit, lbValorPtsStats, lbPtsStats));
		btnMaisVit.getAllStyles().setPadding(0, 0, 1, 1);
		ctnrVit.add(btnMaisVit);
		ctnr.add(ctnrVit);
		
		Button btnConfirmar = new MeuBotao("Confirmar");
		btnConfirmar.addActionListener((ae) -> {
			int forca = Integer.parseInt(lbValorFor.getText());
			int inteligencia = Integer.parseInt(lbValorInt.getText());
			int sorte = Integer.parseInt(lbValorSor.getText());
			int agilidade = Integer.parseInt(lbValorAgi.getText());
			int vitalidade = Integer.parseInt(lbValorVit.getText());
			int ptsStat = Integer.parseInt(lbValorPtsStats.getText());
			
			personagem.setForca(forca);
			personagem.setInteligencia(inteligencia);
			personagem.setSorte(sorte);
			personagem.setAgilidade(agilidade);
			personagem.setVitalidade(vitalidade);
			personagem.setPontosStat(ptsStat);
			
			personagem.atualizarPersonagem();
			
			personagens.atualizarInfoPersonagem();
			
			createComponents();
			
			ctnr.setSmoothScrolling(false);
			ctnr.scrollComponentToVisible(ctnr.getComponentAt(ctnr.getComponentCount()-1));
			ctnr.setSmoothScrolling(true);
		});
		
		ctnr.add(BorderLayout.west(btnConfirmar));
	}
}
