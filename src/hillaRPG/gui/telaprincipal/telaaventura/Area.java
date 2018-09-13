package hillaRPG.gui.telaprincipal.telaaventura;

import java.io.IOException;

import hillaRPG.gui.util.Jogo;
import hillaRPG.monstro.Monstro;

public class Area {
    private String nome;
    private Monstro[] monstros;
    
    //Constrói a área com o seu nome e os monstros que vivem dentro
    public void setMonstros(MonstroArea[] monstrosArea) throws IOException {
        Monstro[] monstros = new Monstro[monstrosArea.length];
        
        int i = 0;
        for(MonstroArea monstroArea : monstrosArea) {
            monstros[i] = Jogo.getMonstrosJogo().get(monstroArea.getId()).clone();
            
            i++;
        }
        
        this.monstros = monstros;
    }
    
    //Retorna um array com os monstros da área
    public Monstro[] getMonstros() {
        return monstros;
    }
    
    //Insere o nome da área
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Retorna o nome da área
    public String getNome() {
        return this.nome;
    }
}
