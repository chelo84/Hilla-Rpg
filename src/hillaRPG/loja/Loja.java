package hillaRPG.loja;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import hillaRPG.gui.util.Jogo;
import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;

public class Loja {
    private String[] itensId;
    private Itens equips;
    private Itens usaveis;
    private Itens magias;
    
    public Loja() {
    	try {
        	InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/loja.json");
        	String fileStr = Util.readToString(is);
        	
			JSONArray ja = new JSONArray(fileStr);
			itensId = new String[ja.length()];
			for(int i = 0; i < ja.length(); i++) {
				itensId[i] = (String) ja.get(i);
			}
    	} catch (JSONException ex) {
    		
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        this.setTipos(this.itensId);
    }
    
    public void setTipos(String[] itensId) {
        ArrayList<Item> equipsList = new ArrayList<>();
        ArrayList<Item> usaveisList = new ArrayList<>();
        ArrayList<Item> magiasList = new ArrayList<>();
        
        for(String itemId : itensId) {
            Item item = Jogo.getItensJogo().get(itemId);
            
            if(item.isEquipavel()) equipsList.add(item.clone());
            if(item.getTipo().equals("usavel")) usaveisList.add(item.clone());
            if(item.getTipo().equals("magia")) magiasList.add(item.clone());
        }
        
        this.equips = new Itens(equipsList.toArray(new Item[equipsList.size()]));
        this.usaveis = new Itens(usaveisList.toArray(new Item[usaveisList.size()]));
        this.magias = new Itens(magiasList.toArray(new Item[magiasList.size()]));
    }
    
    public Itens getEquips() {
        return this.equips;
    }
    
    public Itens getUsaveis() {
        return this.usaveis;
    }
    
    public Itens getMagias() {
        return this.magias;
    }
}
