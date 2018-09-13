package hillaRPG.itens;

public abstract class Item {
    private String id;
    private String nome;
    private String tipo;
    private String descricao;
    private int qtdMax;
    private boolean usavel;
    private int precoVenda;
    private int precoCompra;
    private String unicode;
    
    public abstract void setAtaque(int ataque);
    public abstract int getAtaque();
    public abstract void setAtaqueMagico(int ataque);
    public abstract int getAtaqueMagico();
    public abstract void setDefesa(int defesa);
    public abstract int getDefesa();
    public abstract void setEfeito(String efeito);
    public abstract String getNomeEfeito();
    public abstract String getValorEfeito();
    public abstract void setLevelNecessario(int levelNecessario);
    public abstract int getLevelNecessario();
    public abstract void setForcaNecessaria(int forcaNecessaria);
    public abstract int getForcaNecessaria();
    public abstract void setVitalidadeNecessaria(int vitalidadeNecessaria);
    public abstract int getVitalidadeNecessaria();
    public abstract void setSorteNecessaria(int sorteNecessaria);
    public abstract int getSorteNecessaria();
    public abstract void setAgilidadeNecessaria(int agilidadeNecessaria);
    public abstract int getAgilidadeNecessaria();
    public abstract void setInteligenciaNecessaria(int inteligenciaNecessaria);
    public abstract int getInteligenciaNecessaria();
    public abstract Item clone();
    
    public void setPrecoVenda(int precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    public int getPrecoVenda() {
        return this.precoVenda;
    }
    
    public int getPrecoCompra() {
        return precoCompra;
    }
    
    public void setPrecoCompra(int precoCompra) {
        this.precoCompra = precoCompra;
    }
    
    public int getQtdMax() {
        return qtdMax;
    }

    public void setQtdMax(int qtdMax) {
        this.qtdMax = qtdMax;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isUsavel() {
        return usavel;
    }

    public void setUsavel(boolean usavel) {
        if(this.getTipo().trim().toLowerCase().equals("usavel")) this.usavel = true;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        String str = "Nome: "+ this.getNome()+
               "\nTipo: "+ this.getTipo()+
               "\nDescrição: "+ this.getDescricao();
        return str;
    }

    public boolean isEquipavel() {
        return getIdClasse().equals("ArmaFisica") ||
               getIdClasse().equals("ArmaMagica") ||
               getIdClasse().equals("Armadura");
    }
    
    @Override
    public boolean equals(Object other) {
        return this.hashCode() == other.hashCode();
    }    
    
    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public String getIdClasse() {
    	return "Item";
    }
    
	public String getUnicode() {
		return unicode;
	}
	
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
}
