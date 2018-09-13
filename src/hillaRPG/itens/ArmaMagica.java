package hillaRPG.itens;

import hillaRPG.personagem.Stats;

public class ArmaMagica extends Item {
    private int ataqueMagico;
    private int ataque;
    private int levelNecessario;
    private int forcaNecessaria;
    private int vitalidadeNecessaria;
    private int sorteNecessaria;
    private int agilidadeNecessaria;
    private int inteligenciaNecessaria;
    
    @Override
    public Item clone() {
    	Item item = new ArmaMagica();
    	
    	item.setId(this.getId());
    	item.setNome(this.getNome());
    	item.setTipo(this.getTipo());
    	item.setDescricao(this.getDescricao());
    	item.setQtdMax(this.getQtdMax());
    	item.setUsavel(this.isUsavel());
    	item.setPrecoVenda(this.getPrecoVenda());
    	item.setPrecoCompra(this.getPrecoCompra());
    	item.setUnicode(this.getUnicode());
    	
    	item.setAtaque(this.getAtaque());
    	item.setAtaqueMagico(this.getAtaqueMagico());
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
    	return "ArmaMagica";
    }
    
    @Override
    public int getAtaque() {
        return ataque;
    }

    @Override
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    
    @Override
    public int getAtaqueMagico() {
        return ataqueMagico;
    }

    @Override
    public void setAtaqueMagico(int ataqueMagico) {
        this.ataqueMagico = ataqueMagico;
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
        
        str += "\nTipo: "+ this.getTipo()+
               "\nDescrição: "+ this.getDescricao()+
               "\nAtaque mágico: "+ this.getAtaqueMagico() +" + ( "+ Stats.inteligencia +" )";  
            
        return str;
    }

    @Override
    public void setDefesa(int defesa) {
    }

    @Override
    public int getDefesa() {
    	return 0;
    }

    @Override
    public void setEfeito(String efeito) {
    }

    @Override
    public String getNomeEfeito() {
    	return null;
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
    public String getValorEfeito() {
    	return null;
    }
}
