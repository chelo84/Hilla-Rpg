package hillaRPG.gui.telaprincipal.telaaventura;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public class Areas {
    ArrayList<Area> areas;
    
    public Areas() {
    	areas = new ArrayList<>();
    	
    	try {
    		InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/areas.json");
    		String fileStr = Util.readToString(is);
    		
    		JSONArray ja = new JSONArray(fileStr);
    		for(int i = 0; i < ja.length(); i++) {
    			JSONObject jobj = ja.getJSONObject(i);
    			
    			String nome = jobj.getString("nome");
    			JSONArray jaMonstros = jobj.getJSONArray("monstros");
    			ArrayList<MonstroArea> list = new ArrayList<>();
    			for(int j = 0; j < jaMonstros.length(); j++) {
    				JSONObject monstro = jaMonstros.getJSONObject(j);
    				String id = monstro.getString("id");
    				MonstroArea monstroArea = new MonstroArea();
    				monstroArea.setId(id);
    				
    				list.add(monstroArea);
    			}
    			
    			Area area = new Area();
    			area.setNome(nome);
    			area.setMonstros(list.toArray(new MonstroArea[list.size()]));
    			
    			areas.add(area);
    		}
    	} catch (JSONException ex) {
    		
    	} catch (IOException e) {
    		
		}
    }
    
    //Adiciona uma área
    public void add(Area area) {
        this.areas.add(area);
    }
    
    //Remove uma área
    public boolean remove(String nome) {
        Area area = this.get(nome);
        
        if(area != null) {
            this.areas.remove(area);
            return true;
        } else {
            return false;
        }
    }
    
    //Retorna a área em certo índice
    public Area get(int i) {
        return this.areas.get(i);
    }
    
    //Retorna a área com certo nome
    public Area get(String nome) {
        for(Area area : this.areas) {
            if(area.getNome().equals(nome)) {
                return area;
            }
        }
        
        return null;
    }
    
    //Retorna o tamanho da lista
    public int size() {
        return this.areas.size();
    } 
}
