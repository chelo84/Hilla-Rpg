package hillaRPG.gui.util;

import java.util.Random;

import com.codename1.ui.Label;

import hillaRPG.gui.telaprincipal.telaaventura.Areas;
import hillaRPG.itens.Itens;
import hillaRPG.loja.Loja;
import hillaRPG.magia.Magias;
import hillaRPG.monstro.Monstros;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class Jogo {
	private static Personagem personagem;
	private static Personagens personagens;
	private static Random random;
	private static Itens itensJogo;
	private static Loja lojaJogo;
	private static Monstros monstrosJogo;
	private static Areas areasJogo;
	private static Magias magiasJogo;
	
	public static boolean setup(Label lbLoading) {
		try {
			lbLoading.setText("Randomizando...");
			random = new Random();
			lbLoading.setText("Forjando itens...");
			itensJogo = new Itens();
			lbLoading.setText("Arrumando a loja...");
			lojaJogo = new Loja();
			lbLoading.setText("Monstros saindo para caçar...");
			monstrosJogo = new Monstros();
			lbLoading.setText("Limpando area de corpos...");
			areasJogo = new Areas();
			lbLoading.setText("Estudando magias...");
			magiasJogo = new Magias();
			lbLoading.setText("Nascendo personagens...");
			personagens = new Personagens();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

			return false;
		}
	}
	
	public static boolean setupPersonagem(String nomePersonagem) {
		try {
			personagem = personagens.get(nomePersonagem);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}

	public static Personagem getPersonagem() {
		return personagem;
	}

	public static Personagens getPersonagens() {
		return personagens;
	}

	public static Random getRandom() {
		return random;
	}

	public static Itens getItensJogo() {
		return itensJogo;
	}

	public static Loja getLojaJogo() {
		return lojaJogo;
	}

	public static Monstros getMonstrosJogo() {
		return monstrosJogo;
	}

	public static Areas getAreasJogo() {
		return areasJogo;
	}

	public static Magias getMagiasJogo() {
		return magiasJogo;
	}
	
	
}

