package hillaRPG.monstro;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public class Monstros {
    ArrayList<Monstro> monstros;
    
    public Monstros() {
    	monstros = new ArrayList<>();
    	
    	try {
        	InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/monstros.json");
        	String fileStr = Util.readToString(is);
        	
			JSONArray ja = new JSONArray(fileStr);
			for(int i = 0; i < ja.length(); i++) {
				JSONObject jobj = ja.getJSONObject(i);
				
				String id = jobj.getString("id");
				String nome = jobj.getString("nome");
				String tipo = jobj.getString("tipo");
				int vida = jobj.getInt("vida");
				int xp = jobj.getInt("xp");
				int ataque = jobj.getInt("ataque");
				int defesa = jobj.getInt("defesa");
				
				JSONArray jArrDrop = jobj.getJSONArray("drop");
				Drop[] arrDrop = new Drop[jArrDrop.length()];
				for(int j = 0; j < jArrDrop.length(); j++) {
					JSONObject jsonDrop = jArrDrop.getJSONObject(j);
					String item = jsonDrop.getString("item");
					double chance = jsonDrop.getDouble("chance");
					int qtdMin = jsonDrop.getInt("qtdMin");
					int qtdMax = jsonDrop.getInt("qtdMax");
					
					Drop drop = new Drop();
					drop.setItem(item);
					drop.setChance(chance);
					drop.setQtdMin(qtdMin);
					drop.setQtdMax(qtdMax);
					
					arrDrop[j] = drop;
				}
				
				Monstro monstro = new Monstro();
				monstro.setId(id);
				monstro.setNome(nome);
				monstro.setTipo(tipo);
				monstro.setVida(vida);
				monstro.setXp(xp);
				monstro.setAtaque(ataque);
				monstro.setDefesa(defesa);
				monstro.setDrop(arrDrop);
				
				monstros.add(monstro);
			}
    	} catch (JSONException ex) {
    		
    	} catch (IOException e) {
    		
		}
    	
    }
    
    public void add(Monstro monstro) {
        this.monstros.add(monstro);
    }
    
    public boolean remove(String id) {
        Monstro monstro = this.get(id);
        
        if(monstro != null) {
            this.monstros.remove(monstro);
            return true;
        } else {
            return false;
        }
    }
    
    public Monstro get(int i) {
        return this.monstros.get(i);
    }
    
    public Monstro get(String id) {
        for(Monstro monstro : this.monstros) {
            if(monstro.getId().equals(id)) {
                return monstro;
            }
        }
        
        return null;
    }
    
    public int size() {
        return this.monstros.size();
    }
}
