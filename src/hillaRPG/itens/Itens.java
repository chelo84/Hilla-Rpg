package hillaRPG.itens;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Display;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;

public class Itens {
    private ArrayList<Item> itens;
    
    public Itens(Item[] itens) {
        this.itens = new ArrayList<>();
        this.itens.addAll(Arrays.asList(itens));
    }
    
    public Itens() {
    	List<Item> its = new ArrayList<>();
    	
    	try {
    		this.itens = new ArrayList<>();
        
        	InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/itens.json");
        	String fileStr = Util.readToString(is);
        	
			JSONArray ja = new JSONArray(fileStr);
			for(int i = 0; i < ja.length(); i++) {
				JSONObject obj = ja.getJSONObject(i);
				String type = obj.getString("@type");
				String id = obj.getString("id");
				String nome = obj.getString("nome");
				String tipo = obj.getString("tipo");
				String descricao = obj.getString("descricao");
				int qtdMax = obj.getInt("qtdMax");
				boolean usavel = obj.getBoolean("usavel");
				int precoVenda = obj.getInt("precoVenda");
				int precoCompra = obj.getInt("precoCompra");
				String unicode = obj.getString("unicode");
				
				int ataque, ataqueMagico, defesa, levelNecessario, forcaNecessaria, vitalidadeNecessaria,
					sorteNecessaria, agilidadeNecessaria, inteligenciaNecessaria;
				ataque = ataqueMagico = defesa = levelNecessario = forcaNecessaria = vitalidadeNecessaria =
				sorteNecessaria = agilidadeNecessaria = inteligenciaNecessaria = 0;
				
				String efeito = null;
				
				try {
					ataque = obj.getInt("ataque");
				} catch (JSONException ex) {}
				try {
					ataqueMagico = obj.getInt("ataqueMagico");
				} catch (JSONException ex) {}
				try {
					defesa = obj.getInt("defesa");
				} catch (JSONException ex) {}
				try {
					efeito = obj.getString("efeito");
				} catch (JSONException ex) {}
				try {
					levelNecessario = obj.getInt("levelNecessario");
				} catch (JSONException ex) {}
				try {
					forcaNecessaria = obj.getInt("forcaNecessaria");
				} catch (JSONException ex) {}
				try {
					vitalidadeNecessaria = obj.getInt("vitalidadeNecessaria");
				} catch (JSONException ex) {}
				try {
					sorteNecessaria = obj.getInt("sorteNecessaria");
				} catch (JSONException ex) {}
				try {
					agilidadeNecessaria = obj.getInt("agilidadeNecessaria");
				} catch (JSONException ex) {}
				try {
					inteligenciaNecessaria = obj.getInt("inteligenciaNecessaria");
				} catch (JSONException ex) {}
				
				Item item = null;
				if(type.toLowerCase().trim().equals("outros")) {
					item = new Outros();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setUsavel(usavel);
					item.setQtdMax(qtdMax);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else if(type.toLowerCase().trim().equals("usavel")) {
					item = new ItemUsavel();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setUsavel(usavel);
					item.setEfeito(efeito);
					item.setQtdMax(qtdMax);
					item.setPrecoCompra(precoCompra);
					item.setPrecoVenda(precoVenda);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else if(type.toLowerCase().trim().equals("magia")) {
					item = new ItemMagia();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setLevelNecessario(levelNecessario);
					item.setInteligenciaNecessaria(inteligenciaNecessaria);
					item.setForcaNecessaria(forcaNecessaria);
					item.setAgilidadeNecessaria(agilidadeNecessaria);
					item.setSorteNecessaria(sorteNecessaria);
					item.setVitalidadeNecessaria(vitalidadeNecessaria);
					item.setUsavel(usavel);
					item.setEfeito(efeito);
					item.setQtdMax(qtdMax);
					item.setPrecoVenda(precoVenda);
					item.setPrecoCompra(precoCompra);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else if(type.toLowerCase().trim().equals("armadura")) {
					item = new Armadura();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setLevelNecessario(levelNecessario);
					item.setInteligenciaNecessaria(inteligenciaNecessaria);
					item.setForcaNecessaria(forcaNecessaria);
					item.setAgilidadeNecessaria(agilidadeNecessaria);
					item.setSorteNecessaria(sorteNecessaria);
					item.setVitalidadeNecessaria(vitalidadeNecessaria);
					item.setDefesa(defesa);
					item.setUsavel(usavel);
					item.setQtdMax(qtdMax);
					item.setPrecoVenda(precoVenda);
					item.setPrecoCompra(precoCompra);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else if(type.toLowerCase().trim().equals("armafisica")) {
					item = new ArmaFisica();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setLevelNecessario(levelNecessario);
					item.setInteligenciaNecessaria(inteligenciaNecessaria);
					item.setForcaNecessaria(forcaNecessaria);
					item.setAgilidadeNecessaria(agilidadeNecessaria);
					item.setSorteNecessaria(sorteNecessaria);
					item.setVitalidadeNecessaria(vitalidadeNecessaria);
					item.setAtaque(ataque);
					item.setUsavel(usavel);
					item.setQtdMax(qtdMax);
					item.setPrecoVenda(precoVenda);
					item.setPrecoCompra(precoCompra);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else if(type.toLowerCase().trim().equals("armamagica")) {
					item = new ArmaMagica();
					item.setId(id);
					item.setNome(nome);
					item.setTipo(tipo);
					item.setLevelNecessario(levelNecessario);
					item.setInteligenciaNecessaria(inteligenciaNecessaria);
					item.setForcaNecessaria(forcaNecessaria);
					item.setAgilidadeNecessaria(agilidadeNecessaria);
					item.setSorteNecessaria(sorteNecessaria);
					item.setVitalidadeNecessaria(vitalidadeNecessaria);
					item.setAtaque(ataque);
					item.setAtaqueMagico(ataqueMagico);
					item.setUsavel(usavel);
					item.setQtdMax(qtdMax);
					item.setPrecoCompra(precoCompra);
					item.setPrecoVenda(precoVenda);
					item.setDescricao(descricao);
					item.setUnicode(unicode);
					
				} else {
					Log.p("Algum coisa aconteceu");
				}
				
				its.add(item);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.itens.addAll(its);
    }
    
    public boolean add(Item item) throws IOException {
        //Verifica se o nome é vazio, se for retorna false e não adiciona a lista de itens.
        if(item.getId().trim().isEmpty()) {
            return false;
        }
        
        //Verifica se a lista de itens já não contém um personagem com o mesmo nome.
        if(get(item.getId().trim()) == null) {
            this.itens.add(item);
            
            return true;
        }
        
        return false;
    }
    
    public boolean remove(String nome) {
        Item item = get(nome);
        
        if(item != null) {
            this.itens.remove(item);
            return true;
        } else {
            return false;
        }
    }
    
    public Item get(int i) {
        return this.itens.get(i).clone();
    }
    
    public Item get(String id) {
        for(Item item : this.itens) {
            if(item.getId().equals(id)) {
                return item.clone();
            }
        }
        
        System.out.println("null");
        return null;
    }
    
    public int size() {
        return this.itens.size();
    }    

    public void remove(int i) {
        this.itens.remove(i);
    }
    
    public String getIdClasse() {
    	return "Itens";
    }
}
