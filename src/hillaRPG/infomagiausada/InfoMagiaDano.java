package hillaRPG.infomagiausada;

public class InfoMagiaDano extends InfoMagiaUsada {
    private int dano;
    
    @Override
    public void setDanoMagia(int dano) {
        this.dano = dano;
    }

    @Override
    public int getDanoMagia() {
        return dano;
    }

    @Override
    public void setCuraMagia(int cura) {
    }
}
