package hillaRPG.gui.telaprincipal.telamagias;

import java.util.ArrayList;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

import hillaRPG.gui.util.BotaoItem;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.LabelGrande;
import hillaRPG.gui.util.LabelPequeno;
import hillaRPG.gui.util.MeuForm;
import hillaRPG.gui.util.TxtAreaInfo;
import hillaRPG.magia.Magia;
import hillaRPG.personagem.MagiasPersonagem;

public class MagiasForm extends MeuForm {
	private Font fnt = Font.createTrueTypeFont("rpgawesome", "rpgawesome.ttf");
	private Font flaticon = Font.createTrueTypeFont("flaticon", "flaticon.ttf");
	private int size = Display.getInstance().convertToPixels(5);
	private FontImage fm, fms;
	private TextArea txtInfo = new TxtAreaInfo(6);

	private Container ctnrNorte = new Container(BoxLayout.y());
	private Container ctnrCentro = new Container(BoxLayout.y());
	private Container ctnrSul = new Container(BoxLayout.y());
	private Button btnMagiaSelec = null;

	public MagiasForm() {
		this.setLayout(new BorderLayout());
		createComponents();
	}


	public MagiasForm(String title) {
		super(title);

		createComponents();
	}

	private void createComponents() {
		Label lbMagias = new LabelGrande("Magias");
		lbMagias.getAllStyles().setAlignment(TextArea.CENTER);
		ctnrNorte.add(lbMagias);

		this.add(BorderLayout.NORTH, ctnrNorte);

		this.add(BorderLayout.CENTER, ctnrCentro);
		paintMagias(ctnrCentro);

		this.add(BorderLayout.SOUTH, ctnrSul);

		txtInfo.setEditable(false);
		ctnrSul.add(txtInfo);
		ctnrSul.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));
	}

	private void paintMagias(Container ctnrCentro) {
		ctnrCentro.removeAll();
		btnMagiaSelec = null;

		MagiasPersonagem magias = Jogo.getPersonagem().getMagias();

		Container ctnrNumMagias = new Container(new BorderLayout());
		Label lbNumMagias = new LabelPequeno(""+ magias.size());

		ctnrNumMagias.add(BorderLayout.WEST, lbNumMagias);
		ctnrCentro.add(ctnrNumMagias);

		ArrayList<Button> btns = new ArrayList<>();
		for(int i = 0; i < magias.size(); i++) {
			Magia magia = magias.get(i);
			Button btn = new BotaoItem(magia.getNome());
			btn.addActionListener((ae) -> {
				if(btnMagiaSelec == btn) return;
				btn.getAllStyles().setBgColor(ColorUtil.LTGRAY);
				btn.getAllStyles().setFgColor(ColorUtil.BLACK);
				btn.setIcon(btn.getPressedIcon());
				btn.repaint();	

				txtInfo.setText(magia.toString());

				deselecionarItem();

				btnMagiaSelec = btn;
			});

			fm = FontImage.createFixed(magia.getUnicode(), fnt, 0xffffff, size, size);
			fms = FontImage.createFixed(magia.getUnicode(), fnt, 0x000000, size, size);

			btn.setIcon(fm);
			btn.setDisabledIcon(fm);
			btn.setPressedIcon(fms);

			btn.getAllStyles().setBgColor(ColorUtil.BLACK);
			btn.getAllStyles().setFgColor(ColorUtil.WHITE);
			btn.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.WHITE));

			ctnrCentro.add(btn);
			btns.add(btn);
		}

		ctnrCentro.repaint();
		ctnrCentro.revalidate();
	}
	
	public void deselecionarItem() {
		try {
			btnMagiaSelec.getAllStyles().setBgColor(ColorUtil.BLACK);
			btnMagiaSelec.getAllStyles().setFgColor(ColorUtil.WHITE);
			btnMagiaSelec.setIcon(btnMagiaSelec.getDisabledIcon());
			btnMagiaSelec.repaint();
		} catch (NullPointerException ex) {
			System.err.println("Nenhum item selecionado.");
		}
	}
}
