package hillaRPG.itens;

public class Outros extends Item {
	
    @Override
    public Item clone() {
    	Item item = new Outros();
    	
    	item.setId(this.getId());
    	item.setNome(this.getNome());
    	item.setTipo(this.getTipo());
    	item.setDescricao(this.getDescricao());
    	item.setQtdMax(this.getQtdMax());
    	item.setUsavel(this.isUsavel());
    	item.setPrecoVenda(this.getPrecoVenda());
    	item.setPrecoCompra(this.getPrecoCompra());
    	item.setUnicode(this.getUnicode());
    	
    	return item;
    }
	
    @Override
    public String getIdClasse() {
    	return "Outros";
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
    public String getValorEfeito() {
    	return null;
    }
    
}
