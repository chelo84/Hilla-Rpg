package hillaRPG.personagem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import hillaRPG.gui.util.Jogo;
import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;
import hillaRPG.magia.Magia;
import hillaRPG.magia.Magias;

public class Personagens {
    public ArrayList<Personagem> personagens;
    
    public Personagens() {
        this.personagens = new ArrayList<>();
        
        String path = FileSystemStorage.getInstance().getAppHomePath() + "/personagens.json";
        try {
        	InputStream is = FileSystemStorage.getInstance().openInputStream(path);
        	String fileStr = Util.readToString(is);
			JSONArray ja = new JSONArray(fileStr);
			for(int i = 0; i < ja.length(); i++) {
				JSONObject jobj = ja.getJSONObject(i);
				
				String nome = jobj.getString("nome");
				int xpAtual = jobj.getInt("xpAtual");
				int xpTotal = jobj.getInt("xpTotal");
				int xpNecessaria = jobj.getInt("xpNecessaria");
				
				JSONObject jEquipamento = jobj.getJSONObject("equipamento");
				
				HashMap<EquipSlots, Item> equipamento = jsonSetEquipamento(jEquipamento);
				int forca = jobj.getInt("forca");
				int sorte = jobj.getInt("sorte");
				int agilidade = jobj.getInt("agilidade");
				int vitalidade = jobj.getInt("vitalidade");
				int inteligencia = jobj.getInt("inteligencia");
				int pontosStat = jobj.getInt("pontosStat");
				int defesa = jobj.getInt("defesa");
				
				JSONArray jInventario = jobj.getJSONArray("inventario");
				
				Inventario inventario = jsonSetInventario(jInventario);
				int vidaAtual = jobj.getInt("vidaAtual");
				
				JSONArray jArmazem = jobj.getJSONArray("armazem");
				
				Armazem armazem = jsonSetArmazem(jArmazem);
				
				JSONArray jMagias = jobj.getJSONArray("magias");
				
				MagiasPersonagem magias = jsonSetMagias(jMagias);
				int manaAtual = jobj.getInt("manaAtual");
				
				Personagem personagem = new Personagem();
				personagem.setNome(nome);
				personagem.setXpAtual(xpAtual);
				personagem.setXpTotal(xpTotal);
				personagem.setXpNecessaria(xpNecessaria);
				personagem.setLevel();
				personagem.setEquipamento(equipamento);
				personagem.setForca(forca);
				personagem.setSorte(sorte);
				personagem.setAgilidade(agilidade);
				personagem.setVitalidade(vitalidade);
				personagem.setInteligencia(inteligencia);
				personagem.setPontosStat(pontosStat);
				personagem.setPontosStatTotal();
				personagem.setAtaque();
				personagem.setDanoMagico();
				personagem.setDefesa();
				personagem.setInventario(inventario);
				personagem.setChanceCrit();
				personagem.setChanceEvasao();
				personagem.setVidaAtual(vidaAtual);
				personagem.setVidaMax();
				personagem.setArmazem(armazem);
				personagem.setMagias(magias);
				personagem.setManaAtual(manaAtual);
				personagem.setManaMax();
				
				this.personagens.add(personagem);
			}
        } catch (JSONException ex) {
        	
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public MagiasPersonagem jsonSetMagias(JSONArray jMagias) throws IOException, JSONException {
    	Magia[] magias = new Magia[jMagias.length()];
    	
    	Magias magiasJogo = new Magias();
    	for(int i = 0; i < jMagias.length(); i++) {
    		String magiaId = (String) jMagias.get(i);
    		
    		Magia magia = magiasJogo.get(magiaId);
    		magias[i] = magia;
    	}
    	
    	return new MagiasPersonagem(magias);
    }
    
    public Armazem jsonSetArmazem(JSONArray jArmazem) throws IOException, JSONException {
    	Item[] armazem = new Item[jArmazem.length()];
    	Integer[] armazemQtd = new Integer[jArmazem.length()];
    	
    	for(int i = 0; i < jArmazem.length(); i++) {
    		try {
    			String value = (String) jArmazem.get(i);
    			int idx = value.indexOf(':');
    			
    			String itemId = value.substring(0, idx);
    			int qtdItem = Integer.parseInt(value.substring(idx+1));
    			
    			Item item = Jogo.getItensJogo().get(itemId).clone();
    			armazem[i] = item;
    			armazemQtd[i] = qtdItem;
    		} catch (ClassCastException ex) {
    			
    		}
    	}
		return new Armazem(armazem, armazemQtd);
    }
    
    public Inventario jsonSetInventario(JSONArray jInventario) throws IOException, JSONException {
		Item[] inventario = new Item[jInventario.length()];
		Integer[] inventarioQtd = new Integer[jInventario.length()];
		
		for(int i = 0; i < jInventario.length(); i++) {
			try {
				String value= (String) jInventario.get(i);
				int idx = value.indexOf(':');
				
				String itemId = value.substring(0, idx);
				int qtdItem = Integer.parseInt(value.substring(idx+1));
				
				Item item = Jogo.getItensJogo().get(itemId).clone();
				inventario[i] = item;
				inventarioQtd[i] = qtdItem;
			} catch (ClassCastException ex) {
				
			}
		}
		
		return new Inventario(inventario, inventarioQtd);
    }
    
    public HashMap<EquipSlots, Item> jsonSetEquipamento(JSONObject jEquips) throws IOException, JSONException {
		HashMap<EquipSlots, Item> equips = new HashMap<>();
		for(EquipSlots slot : EquipSlots.values()) {
			equips.put(slot, null);
		}
		
		@SuppressWarnings("unchecked")
		Iterator<String> keysItr = jEquips.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			String itemId = (String) jEquips.get(key);
			
			Item item = Jogo.getItensJogo().get(itemId).clone();
			EquipSlots slot = EquipSlots.valueOf(key);
			
			equips.put(slot, item);
		}
		
		return equips;
    }
    
    public boolean add(Personagem personagem) {
        //Verifica se o nome é vazio, se for retorna false e não adiciona a lista de personagens.
        if(personagem.getNome().trim().isEmpty()) {
            return false;
        }
        
        //Verifica se a lista de personagens já não contém um personagem com o mesmo nome.
        if(get(personagem.getNome().trim()) == null) {
            this.personagens.add(personagem);
            
				atualizarInfoPersonagem();
            
            return true;
        }
        
        return false;
    }
    
    public boolean remove(String nome) {
        Personagem personagem = get(nome);
        
        if(personagem != null) {
            this.personagens.remove(personagem);
            return true;
        } else {
            return false;
        }
    }
    
    public Personagem get(int i) {
        return this.personagens.get(i);
    }
    
    public Personagem get(String nome) {
        for(Personagem personagem : this.personagens) {
            if(personagem.getNome().toLowerCase().equals(nome.toLowerCase())) {
                return personagem;
            }
        }
        
        return null;
    }
    
    public int size() {
        return this.personagens.size();
    }
    
    public  void atualizarInfoPersonagem() {
    	
        try {
        	JSONArray ja = new JSONArray();
        	for(Personagem personagem : personagens) {
        		JSONObject jobj = new JSONObject();
        		
        		jobj.put("nome", personagem.getNome());
        		jobj.put("xpAtual", personagem.getXpAtual());
        		jobj.put("xpTotal", personagem.getXpTotal());
        		jobj.put("xpNecessaria", personagem.getXpNecessaria());
        		jobj.put("level", personagem.getLevel());
         		jobj.put("equipamento", personagem.jsonGetEquipamento());
        		jobj.put("forca", personagem.getForca());
        		jobj.put("sorte", personagem.getSorte());
        		jobj.put("agilidade", personagem.getAgilidade());
        		jobj.put("vitalidade", personagem.getVitalidade());
        		jobj.put("inteligencia", personagem.getInteligencia());
        		jobj.put("pontosStat", personagem.getPontosStat());
        		jobj.put("pontosStatTotal", personagem.getPontosStatTotal());
        		jobj.put("ataque", personagem.getAtaque());
        		jobj.put("danoMagico", personagem.getDanoMagico());
        		jobj.put("defesa", personagem.getDefesa());
        		jobj.put("inventario", personagem.jsonGetInventario());
        		jobj.put("chanceCrit", personagem.getChanceCrit());
        		jobj.put("chanceEvasao", personagem.getChanceEvasao());
        		jobj.put("vidaAtual", personagem.getVidaAtual());
        		jobj.put("armazem", personagem.jsonGetArmazem());
        		jobj.put("magias", personagem.jsonGetMagias());
        		jobj.put("manaAtual", personagem.getManaAtual());
        		jobj.put("manaMax", personagem.getManaMax());
        		jobj.put("vidaMax", personagem.getVidaMax());
        		
        		ja.put(jobj);
        	}
        	
        	String path = FileSystemStorage.getInstance().getAppHomePath() + "/personagens.json";
        	OutputStream os = FileSystemStorage.getInstance().openOutputStream(path);
        	
        	String json_string = ja.toString(2);
			os.write(json_string.getBytes("UTF-8"));
        } catch (JSONException ex) {
        	
        } catch (IOException e) {
			e.printStackTrace();
		}
    }    
}
