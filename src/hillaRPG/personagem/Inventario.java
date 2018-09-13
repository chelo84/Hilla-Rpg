package hillaRPG.personagem;

import java.util.ArrayList;
import java.util.Arrays;

import hillaRPG.gui.util.Jogo;
import hillaRPG.itens.Item;
import hillaRPG.itens.Itens;

public class Inventario {
    public static int TAMANHOINVENTARIO = 15;
    private int ouro;
    private ArrayList<Item> itens;
    private ArrayList<Integer> itensQtd;
    
    public Inventario(Item[] itens, Integer[] qtds) {
        this.itens = new ArrayList<>(Arrays.asList(itens));
        this.itensQtd = new ArrayList<>(Arrays.asList(qtds));
        
        if(getItem("ouro") != null) {
        	setOuro(getQtd("ouro"));
        	
        	int idx = this.getIndex("ouro");
        	this.itens.remove(idx);
        	this.itensQtd.remove(idx);
        }
    }
    
    public Inventario() {
        this.itens = new ArrayList<>();
        this.itensQtd = new ArrayList<>();
        setOuro(0);
    }
    
    public boolean add(Item item, int quantidade) {
        if(this.itens.size() >= TAMANHOINVENTARIO || this.itensQtd.size() >= TAMANHOINVENTARIO) {
            if(this.itens.indexOf(item) == -1) {
                return false;
            }
            
        } else if(item.getId().equals("ouro")) {
        	ouro += quantidade;
        	
        	return true;
        }
        
        try {
            int i = this.itens.indexOf(item);
            if(itensQtd.get(i) < item.getQtdMax()) {
                this.itensQtd.set(i, this.itensQtd.get(i) + quantidade);
            } else {
                throw new Exception("excecao");
            }
                return true;
        } catch(Exception ex) {
            if(this.itens.size() >= TAMANHOINVENTARIO || this.itensQtd.size() >= TAMANHOINVENTARIO) {
                return false;
            } else {
                this.itens.add(item);
                this.itensQtd.add(quantidade);
            
                return true;
            }
        }
    }   
    
    public boolean add(String itemId, int quantidade) {
        Item item = Jogo.getItensJogo().get(itemId).clone();
        
        if(this.itens.size() >= TAMANHOINVENTARIO || this.itensQtd.size() >= TAMANHOINVENTARIO) {
            if(this.itens.indexOf(item) == -1) {
                return false;
            }
        } else if(item.getId().equals("ouro")) {
        	ouro += quantidade;
        	
        	return true;
        }
        
        try {
            int i = this.itens.indexOf(item);
            if(itensQtd.get(i) < item.getQtdMax()) {
                this.itensQtd.set(i, this.itensQtd.get(i) + quantidade);
            } else {
                throw new Exception("excecao");
            }
                return true;
        } catch(Exception ex) {
            if(this.itens.size() >= TAMANHOINVENTARIO || this.itensQtd.size() >= TAMANHOINVENTARIO) {
                return false;
            } else {
                this.itens.add(item);
                this.itensQtd.add(quantidade);
            
                return true;
            }
        }
    }  
    
    public boolean add(Item item, int quantidade, int index) {
        if(this.itens.size() >= TAMANHOINVENTARIO || this.itensQtd.size() >= TAMANHOINVENTARIO) {
            return false;
        } else if(item.getId().equals("ouro")) {
        	ouro += quantidade;
        	
        	return true;
        }
        try {
            int i = this.itens.indexOf(item);
            if(itensQtd.get(i) < item.getQtdMax()) {
                this.itensQtd.set(i, this.itensQtd.get(i) + quantidade);
            } else {
                throw new Exception("excecao");
            }
                return true;
        } catch(Exception ex) {
            this.itens.add(index, item);
            this.itensQtd.add(index, quantidade);
            
            return true;
        }
    }   
    
    public boolean remove(String id) {
        if(Inventario.this.getItem(id) != null) {
            Item item = Inventario.this.getItem(id);
            
            int i = this.itens.indexOf(item);
            
            this.itensQtd.set(i, this.itensQtd.get(i) - 1);
            if(this.itensQtd.get(i) < 1) {
                this.itens.remove(i);
                this.itensQtd.remove(i);
            }
            return true;
        }
        
        return false;
    }
    
    public boolean remove(String id, int quantidade) {
    	if(id.equals("ouro")) {
    		ouro -= quantidade;
         	
         	return true;
        }
    	
        if(this.getItem(id) != null) {
            Item item = this.getItem(id);
            
            int i = this.itens.indexOf(item);
            
            if(this.itensQtd.get(i) - quantidade < 0) {
                return false;
            }
            this.itensQtd.set(i, this.itensQtd.get(i) - quantidade);
            if(this.itensQtd.get(i) < 1) {
                this.itens.remove(i);
                this.itensQtd.remove(i);
            }
            return true;
        }
        
        return false;
    }
    
    public Item getItem(String id) {
    	if(id.equals("ouro")) {
    		return Jogo.getItensJogo().get("ouro").clone();
    	}
    	
        for(Item item : this.itens) {
            if(item.getId().equals(id)) {
                return item;
            }
        }
        
        return null;
    }
    
    public Item getItem(int index) {
        return this.itens.get(index);
    }
    
    public int getQtd(String id) {
    	Item item = Inventario.this.getItem(id);
       
    	int i = this.itens.indexOf(item);
       
    	return this.itensQtd.get(i);
    }
    
    public int getQtd(int index) {
       return this.itensQtd.get(index);
    }
    
    public int getIndex(String id) {
        Item item = Inventario.this.getItem(id);
        
        int i = this.itens.indexOf(item);
        
        return i;
    }
    
    public int getOuro() {
    	return this.ouro;
    }
    
    public void setOuro(int ouro) {
    	this.ouro = ouro;
    }
    
    public int size() {
        return this.itens.size();
    }
    
}
