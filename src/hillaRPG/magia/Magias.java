package hillaRPG.magia;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public class Magias {
    private final ArrayList<Magia> magias;
    
    public Magias(Magia[] magias) {
        this.magias = new ArrayList<>();
        this.magias.addAll(Arrays.asList(magias));
    }
    
    public Magias() {
    	this.magias = new ArrayList<>();
    	try {
        	InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/magias.json");
        	String fileStr = Util.readToString(is);
        	
			JSONArray ja = new JSONArray(fileStr);
			for(int i = 0; i < ja.length(); i++) {
				JSONObject jobj = ja.getJSONObject(i);
				String id = jobj.getString("id");
				String nome = jobj.getString("nome");
				int custo = jobj.getInt("custo");
				String alvo = jobj.getString("alvo");
				String tipo = jobj.getString("tipo");
				int valorEfeito = jobj.getInt("valorEfeito");
				double valorEfeitoRatio = jobj.getDouble("valorEfeitoRatio");
				int levelNecessario = jobj.getInt("levelNecessario");
				String unicode = jobj.getString("unicode");
				
				Magia magia = new Magia();
				magia.setId(id);
				magia.setNome(nome);
				magia.setCusto(custo);
				magia.setAlvo(alvo);
				magia.setTipo(tipo);
				magia.setValorEfeito(valorEfeito);
				magia.setValorEfeitoRatio(valorEfeitoRatio);
				magia.setLevelNecessario(levelNecessario);
				magia.setUnicode(unicode);
				
				magias.add(magia);
			}
    	} catch (JSONException ex) {
    		
    	} catch (IOException e) {
		}
    }
    
    public boolean add(Magia magia) throws IOException {
        //Verifica se o id é vazio, se for retorna false e não adiciona a lista
        if(magia.getId().trim().isEmpty()) {
            return false;
        }
        
        //Verifica se a lista já não contém um personagem com o mesmo id
        if(get(magia.getId().trim()) == null) {
            this.magias.add(magia);
            
            return true;
        }
        
        return false;
    }
    
    public boolean remove(String magiaId) {
        Magia magia = get(magiaId);
        
        if(magia != null) {
            this.magias.remove(magia);
            return true;
        } else {
            return false;
        }
    }
    
    public Magia get(int i) {
        return this.magias.get(i);
    }
    
    public Magia get(String id) {
        for(Magia magia : this.magias) {
            if(magia.getId().equals(id)) {
                return magia;
            }
        }
        
        return null;
    }
    
    public int size() {
        return this.magias.size();
    }    

    public void remove(int i) {
        this.magias.remove(i);
    }
}
