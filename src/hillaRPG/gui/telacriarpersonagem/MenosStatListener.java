package hillaRPG.gui.telacriarpersonagem;

import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class MenosStatListener implements ActionListener {
	private int stat;
	private Label lbValorStat, lbValorPtsStats, lbPtsStats;
	

	public MenosStatListener(Integer stat, Label lbValorStat, Label lbValorPtsStats, Label lbPtsStats) {
		this.stat = stat;
		this.lbValorStat = lbValorStat;
		this.lbValorPtsStats = lbValorPtsStats;
		this.lbPtsStats = lbPtsStats;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		int valorStat = Integer.parseInt(lbValorStat.getText());
		int valorPtsStats = Integer.parseInt(lbValorPtsStats.getText());
		
		if(valorStat-1 >= stat) {
			lbValorStat.setText(""+ --valorStat);
			lbPtsStats.setText("Pontos de Stats : "+ (valorPtsStats+1));
			lbValorPtsStats.setText(""+ (valorPtsStats+1));
			lbValorStat.growShrink(0);
		}
	}

}
