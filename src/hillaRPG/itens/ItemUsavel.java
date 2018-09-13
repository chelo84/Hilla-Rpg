package hillaRPG.itens;

import com.codename1.io.Log;

public class ItemUsavel extends Item {
	private String nomeEfeito;
	private String valorEfeito;
	
    @Override
    public Item clone() {
    	Item item = new ItemUsavel();
    	
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
    	
    	return item;
    }
	
    @Override
    public String getIdClasse() {
    	return "ItemUsavel";
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

	public String getValorEfeito() {
		return this.valorEfeito;
	}

	public String JsonGetEfeito() {
		return nomeEfeito +":"+ valorEfeito;
	}
	@Override
	public void setAtaque(int ataque) {

	}

	@Override
	public int getAtaque() {
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
	public void setAtaqueMagico(int ataque) {
	}

	@Override
	public int getAtaqueMagico() {
		return 0;
	}

	@Override
	public void setLevelNecessario(int levelNecessario) {
	}

	@Override
	public int getLevelNecessario() {
		return 0;
	}

	@Override
	public void setForcaNecessaria(int forcaNecessaria) {
	}

	@Override
	public int getForcaNecessaria() {
		return 0;
	}

	@Override
	public void setVitalidadeNecessaria(int vitalidadeNecessaria) {
	}

	@Override
	public int getVitalidadeNecessaria() {
		return 0;
	}

	@Override
	public void setSorteNecessaria(int sorteNecessaria) {
	}

	@Override
	public int getSorteNecessaria() {
		return 0;
	}

	@Override
	public void setAgilidadeNecessaria(int agilidadeNecessaria) {
	}

	@Override
	public int getAgilidadeNecessaria() {
		return 0;
	}

	@Override
	public void setInteligenciaNecessaria(int inteligenciaNecessaria) {
	}

	@Override
	public int getInteligenciaNecessaria() {
		return 0;
	}
	
	@Override
	public String toString() {
		String str = "Nome: "+ this.getNome()+
					 "\nTipo: "+ this.getTipo()+
					 "\nDescrição: "+ this.getDescricao();
				switch(this.getNomeEfeito()) {
						case "dano" : str += "\nDano: "+ this.getValorEfeito();
		                break;
		                            
		                case "curar" : str += "\nCura: "+ this.getValorEfeito();
		                break;
		        }
		
		return str;
	}
}
