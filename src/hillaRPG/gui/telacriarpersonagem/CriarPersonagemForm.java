package hillaRPG.gui.telacriarpersonagem;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.MeuBotao;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.MeuLabel;
import hillaRPG.gui.util.MeuStatsLabel;
import hillaRPG.personagem.EquipSlots;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class CriarPersonagemForm extends MeuForm {
	
	public CriarPersonagemForm() {
		createComponents();
	}
	
	public CriarPersonagemForm(String title) {
		super(title);
		
		createComponents();
	}
	

	public CriarPersonagemForm(Layout layout) {
		super(layout);
		
		createComponents();
	}
	
	private void createComponents() {
		Personagens personagens = new Personagens();
		Personagem personagem = NovoPersonagem.criarPersonagem();
		
		TextField nomeField = new TextField("", "Nome do personagem");
		nomeField.getAllStyles().setBgColor(ColorUtil.WHITE);
		nomeField.getAllStyles().setMarginBottom(5);
		this.add(nomeField);
		
		Label lbValorPtsStats = new MeuLabel(""+ personagem.getPontosStat());
		Label lbPtsStats = new MeuLabel("Pontos de Stats : "+ lbValorPtsStats.getText());
		lbPtsStats.getAllStyles().setAlignment(TextArea.CENTER);
		lbPtsStats.getAllStyles().setBorder(Border.createCompoundBorder(Border.createLineBorder(1, ColorUtil.WHITE),
											null, null, null));
		
		this.add(lbPtsStats);
		
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
		this.add(ctnrFor);
		
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
		this.add(ctnrInt);
		
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
		this.add(ctnrSor);
		
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
		this.add(ctnrAgi);
		
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
		this.add(ctnrVit);
		
		Button btnCriarPersonagem = new MeuBotao("Criar personagem");
		btnCriarPersonagem.addActionListener((ae) -> {
			String nome = nomeField.getText();
			if(nome.isEmpty() || nome == null || personagens.get(nome) != null) {
				Dialog.show("Nome inválido", "Nome inválido, tente outro.", "Ok", null);
			} else {
			
				int forca = Integer.parseInt(lbValorFor.getText());
				int inteligencia = Integer.parseInt(lbValorInt.getText());
				int sorte = Integer.parseInt(lbValorSor.getText());
				int agilidade = Integer.parseInt(lbValorAgi.getText());
				int vitalidade = Integer.parseInt(lbValorVit.getText());
				int ptsStat = Integer.parseInt(lbValorPtsStats.getText());
				
				personagem.setNome(nome);
				personagem.setForca(forca);
				personagem.setInteligencia(inteligencia);
				personagem.setSorte(sorte);
				personagem.setAgilidade(agilidade);
				personagem.setVitalidade(vitalidade);
				personagem.setPontosStat(ptsStat);
				personagem.getEquipamento().put(EquipSlots.ARMA, Jogo.getItensJogo().get("adaga").clone());
				
				personagens.add(personagem);
				
				Dialog.show("Personagem criado", "Personagem criado com sucesso!\nVolte a tela anterior.", "Ok", null);
			}
		});
		btnCriarPersonagem.getAllStyles().setMargin(5,0,0,0);
		this.add(btnCriarPersonagem);
	}
}
