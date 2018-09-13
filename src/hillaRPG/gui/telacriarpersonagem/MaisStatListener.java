package hillaRPG.gui.telacriarpersonagem;

import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class MaisStatListener implements ActionListener {
	private Label lbValorStat, lbValorPtsStats, lbPtsStats;
	

	public MaisStatListener(Label lbValorStat, Label lbValorPtsStats, Label lbPtsStats) {
		this.lbValorStat = lbValorStat;
		this.lbValorPtsStats = lbValorPtsStats;
		this.lbPtsStats = lbPtsStats;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int valorStat = Integer.parseInt(lbValorStat.getText());
		int valorPtsStats = Integer.parseInt(lbValorPtsStats.getText());
		
		if(valorPtsStats-1 >= 0) {
			lbValorStat.setText(""+ (valorStat+1));
			lbValorPtsStats.setText(""+ (valorPtsStats-1));
			lbPtsStats.setText("Pontos de Stats : "+ lbValorPtsStats.getText());
			lbValorStat.growShrink(0);
			lbPtsStats.growShrink(0);
		}
	}

}
