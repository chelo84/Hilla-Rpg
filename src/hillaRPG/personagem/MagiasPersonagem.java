package hillaRPG.personagem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import hillaRPG.magia.Magia;
import hillaRPG.magia.Magias;

public final class MagiasPersonagem {
    private final ArrayList<Magia> magias;
    
    public void ordenarPorLevel() {
        Comparator<Magia> comparator = new Comparator<Magia>() {

        	@Override
        	public int compare(Magia o1, Magia o2) {
        		return o1.compareTo(o2);
        	}
        };
        Collections.sort(magias, comparator);
    }
    
    public MagiasPersonagem(Magia[] magias) {
        this.magias = new ArrayList<>();
        this.magias.addAll(Arrays.asList(magias));
        
        ordenarPorLevel();
    }
    
    public MagiasPersonagem() throws IOException {
        this.magias = new ArrayList<>();
        
        ordenarPorLevel();
    }
    
    public boolean add(Magia magia) throws IOException {
        //Verifica se o id é vazio, se for retorna false e não adiciona a lista
        if(magia.getId().trim().isEmpty()) {
            return false;
        }
        
        //Verifica se a lista já não contém um personagem com o mesmo id
        if(get(magia.getId().trim()) == null) {
            this.magias.add(magia);
            
            ordenarPorLevel();
            
            return true;
        }
        
        return false;
    }
    
    public boolean add(String magiaId) throws IOException {
    	Magias magiasJogo = new Magias();
        Magia magia = magiasJogo.get(magiaId);
        
        if(this.get(magia.getId()) == null) {
            this.magias.add(magia);

            ordenarPorLevel();
            return true;
        }
        
        return false;
    }
    
    public boolean remove(String magiaId) {
        Magia magia = get(magiaId);
        
        if(magia != null) {
            this.magias.remove(magia);
            
            ordenarPorLevel();
            
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
        
        ordenarPorLevel();
    }

    public class LevelComparator implements Comparator<Magia> {
        @Override
        public int compare(Magia primeiraMagia, Magia segundaMagia) {
           return (primeiraMagia.getLevelNecessario() - segundaMagia.getLevelNecessario());
        }
    }
}
