package hillaRPG.quest;

import java.io.IOException;

import hillaRPG.gui.util.Jogo;
import hillaRPG.itens.Item;

public class Quest {
    private Item item;
    private int itemQtd;
    private int xpRecompensa;
    
    public void setItem(String itemId) throws IOException {
        
        this.item = Jogo.getItensJogo().get(itemId).clone();
    }
    
    public Item getItem() {
        return this.item;
    }
    
    public void setItemQtd(int itemQtd) {
        this.itemQtd = itemQtd;
    }
    
    public int getItemQtd() {
        return this.itemQtd;
    }
    
    public void setXpRecompensa(int xpRecompensa) {
        this.xpRecompensa = xpRecompensa;
    }
    
    public int getXpRecompensa() {
        return this.xpRecompensa;
    }
}
