package hillaRPG.gui.telacriarpersonagem;

import java.io.IOException;
import java.util.HashMap;

import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;
import hillaRPG.personagem.Armazem;
import hillaRPG.personagem.EquipSlots;
import hillaRPG.personagem.Inventario;
import hillaRPG.personagem.MagiasPersonagem;
import hillaRPG.personagem.Personagem;

public class NovoPersonagem {
	
	public static Personagem criarPersonagem(){
		Personagem personagem = new Personagem();
		
		try {
			personagem.setXpAtual(0);
			personagem.setXpTotal(0);
			personagem.setXpNecessaria(100);
			personagem.setLevel();

			HashMap<EquipSlots, Item> equipamento = new HashMap<>();
			for(EquipSlots slot : EquipSlots.values()) {
				equipamento.put(slot, null);
			}
			personagem.setEquipamento(equipamento);

			personagem.setForca(0);
			personagem.setSorte(0);
			personagem.setAgilidade(0);
			personagem.setVitalidade(0);
			personagem.setInteligencia(0);
			personagem.setPontosStat(1);
			personagem.setPontosStatTotal();
			personagem.setAtaque();
			personagem.setDanoMagico();
			personagem.setDefesa();
			personagem.setInventario(new Inventario());
			personagem.setChanceCrit();
			personagem.setChanceEvasao();
			personagem.setVidaMax();
			personagem.setVidaAtual(personagem.getVidaMax());
			personagem.setArmazem(new Armazem());
			personagem.setMagias(new MagiasPersonagem());
			personagem.setManaMax();
			personagem.setManaAtual(personagem.getManaMax());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return personagem;
	}
}
