package hillaRPG.monstro;

import com.codename1.io.Log;

import hillaRPG.gui.util.Jogo;
import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;

public class Drop {
    private Item item;
    private double chance;
    private int qtdMin;
    private int qtdMax;

    public Item getItem() {
        return item;
    }

    public void setItem(String itemId) {
        Item item = new Itens().get(itemId);
        
        this.item = item;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public int getQtdMin() {
        return qtdMin;
    }

    public void setQtdMin(int qtdMin) {
        this.qtdMin = qtdMin;
    }

    public int getQtdMax() {
        return qtdMax;
    }

    public void setQtdMax(int qtdMax) {
        this.qtdMax = qtdMax;
    }
}
