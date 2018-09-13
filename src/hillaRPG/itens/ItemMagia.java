package hillaRPG.itens;

import com.codename1.io.Log;

import hillaRPG.personagem.Stats;

public class ItemMagia extends Item {
    private String nomeEfeito;
    private String valorEfeito;
    private int levelNecessario;
    private int forcaNecessaria;
    private int inteligenciaNecessaria;
    private int vitalidadeNecessaria;
    private int sorteNecessaria;
    private int agilidadeNecessaria;
    
    @Override
    public Item clone() {
    	Item item = new ItemMagia();
    	
    	item.setId(this.getId());
    	item.setNome(this.getNome());
    	item.setTipo(this.getTipo());
    	item.setDescricao(this.getDescricao());
    	item.setQtdMax(this.getQtdMax());
    	item.setUsavel(this.isUsavel());
    	item.setPrecoVenda(this.getPrecoVenda());
    	item.setPrecoCompra(this.getPrecoCompra());
    	item.setUnicode(this.getUnicode());
    	
    	item.setEfeito(this.getNomeEfeito()+":"+this.getValorEfeito());
    	item.setLevelNecessario(this.getLevelNecessario());
    	item.setForcaNecessaria(this.getForcaNecessaria());
    	item.setVitalidadeNecessaria(this.getVitalidadeNecessaria());
    	item.setSorteNecessaria(this.getSorteNecessaria());
    	item.setAgilidadeNecessaria(this.getAgilidadeNecessaria());
    	item.setInteligenciaNecessaria(this.getInteligenciaNecessaria());
    	
    	return item;
    }
    
    @Override
    public String getIdClasse() {
    	return "ItemMagia";
    }
    
    @Override
    public void setEfeito(String efeito) {
    	String[] strs = new String[2];
    	int idx = efeito.indexOf(':');
    	strs[0] = efeito.substring(0, idx);
    	strs[1] = efeito.substring(idx+1);
    	
        String nomeEfeito = strs[0];
        String valorEfeito = strs[1];
        
        this.nomeEfeito = nomeEfeito;
        this.valorEfeito = valorEfeito;
    }

    @Override
    public String getNomeEfeito() {
        return this.nomeEfeito;
    }

    @Override
    public String getValorEfeito() {
        return this.valorEfeito;
    }
    
    public String JsonGetEfeito() {
        return nomeEfeito +":"+ valorEfeito;
    }
    
    @Override
    public void setLevelNecessario(int levelNecessario) {
        this.levelNecessario = levelNecessario;
    }

    @Override
    public int getLevelNecessario() {
        return this.levelNecessario;
    }
    @Override
    public void setAtaque(int ataque) {
    }

    @Override
    public int getAtaque() {
    	return 0;
    }

    @Override
    public void setAtaqueMagico(int ataque) {
    }

    @Override
    public int getAtaqueMagico() {
    	return 0;
    }

    @Override
    public void setDefesa(int defesa) {
    }

    @Override
    public int getDefesa() {
    	return 0;
    }


    @Override
    public void setForcaNecessaria(int forcaNecessaria) {
        this.forcaNecessaria = forcaNecessaria;
    }

    @Override
    public int getForcaNecessaria() {
        return this.forcaNecessaria;
    }

    @Override
    public void setVitalidadeNecessaria(int vitalidadeNecessaria) {
        this.vitalidadeNecessaria = vitalidadeNecessaria;
    }

    @Override
    public int getVitalidadeNecessaria() {
        return this.vitalidadeNecessaria;
    }

    @Override
    public void setSorteNecessaria(int sorteNecessaria) {
        this.sorteNecessaria = sorteNecessaria;
    }

    @Override
    public int getSorteNecessaria() {
        return this.sorteNecessaria;
    }

    @Override
    public void setAgilidadeNecessaria(int agilidadeNecessaria) {
        this.agilidadeNecessaria = agilidadeNecessaria;
    }

    @Override
    public int getAgilidadeNecessaria() {
        return this.agilidadeNecessaria;
    }

    @Override
    public void setInteligenciaNecessaria(int inteligenciaNecessaria) {
        this.inteligenciaNecessaria = inteligenciaNecessaria;
    }

    @Override
    public int getInteligenciaNecessaria() {
        return this.inteligenciaNecessaria;
    }
    
    @Override
    public String toString() {
        String str = "Nome: "+ this.getNome();
        
        if(this.levelNecessario > 0)  {
            str += "\nLevel: "+ this.getLevelNecessario();
        } 
        if(this.forcaNecessaria > 0)  {
            str += "\nForça: "+ this.getForcaNecessaria();
        } 
        if(this.inteligenciaNecessaria > 0)  {
            str += "\nInteligência: "+ this.getInteligenciaNecessaria();
        } 
        if(this.vitalidadeNecessaria > 0)  {
            str += "\nVitalidade: "+ this.getVitalidadeNecessaria();
        } 
        if(this.sorteNecessaria > 0)  {
            str += "\nSorte: "+ this.getSorteNecessaria();
        } 
        if(this.agilidadeNecessaria > 0)  {
            str += "\nAgilidade: "+ this.getAgilidadeNecessaria();
        } 
        
        str += "\nTipo: "+ this.getTipo() +
               "\nDescrição: "+ this.getDescricao();
        
        return str;
    }
    
}
