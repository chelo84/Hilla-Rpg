package hillaRPG.magia;

import hillaRPG.personagem.Stats;

public class Magia {
    private String id;
    private String nome;
    private int custo;
    private String alvo;
    private String tipo;
    private int valorEfeito;
    private double valorEfeitoRatio;
    private int levelNecessario;
    private String unicode;

    public int getLevelNecessario() {
        return levelNecessario;
    }

    public void setLevelNecessario(int levelNecessario) {
        this.levelNecessario = levelNecessario;
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

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public String getAlvo() {
        return alvo;
    }

    public void setAlvo(String alvo) {
        this.alvo = alvo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValorEfeito() {
        return valorEfeito;
    }

    public void setValorEfeito(int valorEfeito) {
        this.valorEfeito = valorEfeito;
    }

    public double getValorEfeitoRatio() {
        return valorEfeitoRatio;
    }

    public void setValorEfeitoRatio(double valorEfeitoRatio) {
        this.valorEfeitoRatio = valorEfeitoRatio;
    }

    @Override
    public String toString() {
        String str = "Nome: "+ this.getNome() +
               "\nLevel: "+ this.getLevelNecessario() +
               "\nTipo: "+ this.getTipo() +
               "\nAlvo: "+ this.getAlvo() +
               "\nCusto: "+ this.getCusto() +" mana";
        
            switch(this.getTipo()) {
                case "dano" : str += "\nDano: "+ this.getValorEfeito()
                                   + " + ( "+ (int) (Stats.danoMagico*getValorEfeitoRatio()) +" )";
                break;
                            
                case "curar" : str += "\nCura: "+ this.getValorEfeito() +
                                     "( "+ (int) (Stats.danoMagico*getValorEfeitoRatio()) +" )";
                break;
            }
        
        return str;
    }
    
    public int compareTo(Magia outraMagia) {
        return (this.getLevelNecessario() - outraMagia.getLevelNecessario());
    }

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}
}
