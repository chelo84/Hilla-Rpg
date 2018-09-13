package hillaRPG.itens;

import hillaRPG.personagem.Stats;


class Armadura extends Item {
    private int defesa;
    private int levelNecessario;
    private int forcaNecessaria;
    private int vitalidadeNecessaria;
    private int sorteNecessaria;
    private int agilidadeNecessaria;
    private int inteligenciaNecessaria;
    
    @Override
    public Item clone() {
    	Item item = new Armadura();
    	
    	item.setId(this.getId());
    	item.setNome(this.getNome());
    	item.setTipo(this.getTipo());
    	item.setDescricao(this.getDescricao());
    	item.setQtdMax(this.getQtdMax());
    	item.setUsavel(this.isUsavel());
    	item.setPrecoVenda(this.getPrecoVenda());
    	item.setPrecoCompra(this.getPrecoCompra());
    	item.setUnicode(this.getUnicode());
    	
    	item.setDefesa(this.getDefesa());
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
    	return "Armadura";
    }
    
    @Override
    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    @Override
    public int getDefesa() {
        return this.defesa;
    }
    
    @Override
    public String toString() {
        String str = " Nome: "+ this.getNome();
        
        if(this.levelNecessario > 0)  {
            str += "\n Level: "+ this.getLevelNecessario();
        } 
        if(this.forcaNecessaria > 0)  {
            str += "\n Força: "+ this.getForcaNecessaria();
        } 
        if(this.inteligenciaNecessaria > 0)  {
            str += "\n Inteligência: "+ this.getInteligenciaNecessaria();
        } 
        if(this.vitalidadeNecessaria > 0)  {
            str += "\n Vitalidade: "+ this.getVitalidadeNecessaria();
        } 
        if(this.sorteNecessaria > 0)  {
            str += "\n Sorte: "+ this.getSorteNecessaria();
        } 
        if(this.agilidadeNecessaria > 0)  {
            str += "\n Agilidade: "+ this.getAgilidadeNecessaria();
        } 
        
        str += "\n Tipo: "+ this.getTipo()+
                "\n Descrição: "+ this.getDescricao()+
                "\n Defesa: "+ this.getDefesa();
            
        return str;
    }
    
    @Override
    public void setAtaque(int ataque) {
    }

    @Override
    public int getAtaque() {
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
    public void setAtaqueMagico(int ataque) {
    }

    @Override
    public int getAtaqueMagico() {
    	return 0;
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
