package hillaRPG.infomagiausada;

public class InfoMagiaCura extends InfoMagiaUsada {
    private int cura;
    
    @Override
    public void setDanoMagia(int dano) {
    	
    }

    @Override
    public void setCuraMagia(int cura) {
        this.cura = cura;
    }
    
    @Override
    public int getCuraMagia() {
        return this.cura;
    }
    
}
