package hillaRPG.gui.telaprincipal.telaaventura.batalha;


import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;

import hillaRPG.gui.telaprincipal.telaaventura.Area;
import hillaRPG.gui.util.Jogo;
import hillaRPG.gui.util.MeuLabel;
import hillaRPG.infomagiausada.InfoMagiaUsada;
import hillaRPG.itens.Item;
import hillaRPG.magia.Magia;
import hillaRPG.monstro.Monstro;
import hillaRPG.personagem.Personagem;
import hillaRPG.personagem.Personagens;

public class Batalha {
    private Personagem personagem;
    private Personagens personagens;
    private Monstro monstro;
    
    Batalha(Area area) {
        this.personagem = Jogo.getPersonagem();
        this.personagens = Jogo.getPersonagens();
        
        Monstro[] monstros = area.getMonstros();
        
        int dadoMonstro = Jogo.getRandom().nextInt(monstros.length-1);
        double dadoChefe = Jogo.getRandom().nextDouble();
        
        if(dadoChefe <= 0.050) {
            for(Monstro m : monstros) {
                if(m.getTipo().trim().toLowerCase().equals("chefe")) {
                    this.monstro = m.clone();
                }
            }
        } else {
            this.monstro = monstros[dadoMonstro].clone();
        }
        
        this.monstro.setDrop(this.monstro.getInvMonstro());
    }
    
    public boolean atacar(Container ctnrLog) {
    	DanoFisico danoCausadoMonstro = this.monstro.causarDanoFisico(this.personagem);
    	DanoFisico danoCausadoPersonagem = this.personagem.causarDanoFisico(this.monstro);
    	
    	String nomePersonagem = this.personagem.getNome();
    	String nomeMonstro = this.monstro.getNome();
    	
    	Container ctnrDanoPersonagem = new Container(BoxLayout.x());
    	TextArea txtDanoPersonagem = new TxtAreaInfoBat();
    	
    	if(danoCausadoPersonagem.getDano() > 0) {
    		
    		if(danoCausadoPersonagem.isCrit()) {
    			txtDanoPersonagem.setText(txtDanoPersonagem.getText() +"[CRITICO]"+ nomePersonagem);
    			txtDanoPersonagem.setText(txtDanoPersonagem.getText() +" causou "+ danoCausadoPersonagem +
    									  " de dano em "+ nomeMonstro);
    		} else {
    			txtDanoPersonagem.setText(txtDanoPersonagem.getText() + nomePersonagem +" causou "+ 
    								      danoCausadoPersonagem +" de dano em "+ nomeMonstro);
    		}
    		
    	} else {
    		txtDanoPersonagem.setText(txtDanoPersonagem.getText() + nomePersonagem +" errou");
    	}
    	ctnrDanoPersonagem.add(txtDanoPersonagem);
    	ctnrLog.add(ctnrDanoPersonagem);
    	
    	Container ctnrDanoMonstro = new Container(BoxLayout.x());
    	TextArea txtDanoMonstro = new TxtAreaInfoBat();
    	if(danoCausadoMonstro.getDano() > 0) {
			txtDanoMonstro.setText(txtDanoMonstro.getText() + nomeMonstro +" causou "+ danoCausadoMonstro +
								   " de dano em "+ nomePersonagem);
			
    	} else {
    		txtDanoMonstro.setText(txtDanoMonstro.getText() + nomeMonstro +" errou");
    	}
    	ctnrDanoMonstro.add(txtDanoMonstro);
    	ctnrLog.add(ctnrDanoMonstro);
    	
    	ctnrLog.add(" ");
    	
    	return danoCausadoPersonagem.isCrit();
    }
    
    public boolean fugir(Container ctnrLog) {
        double dado = Jogo.getRandom().nextDouble();
        boolean tentativa = dado < (0.10 + (this.personagem.getAgilidade() * 0.03));
        String nomePersonagem = this.personagem.getNome();
        String nomeMonstro = this.monstro.getNome();
        
    	TextArea txtFuga = new TxtAreaInfoBat();
        if(tentativa) {
        	txtFuga.setText(txtFuga.getText() + nomePersonagem +" teve êxito em fugir");
        	ctnrLog.add(txtFuga);
        } else {
        	DanoFisico danoCausadoMonstro = this.monstro.causarDanoFisico(this.personagem);
        	
        	txtFuga.setText(txtFuga.getText() + nomePersonagem +" falhou em fugir");
        	TextArea txtDanoMonstro = new TxtAreaInfoBat();
        	txtDanoMonstro.setText(txtDanoMonstro.getText() + nomeMonstro +" causou "+ danoCausadoMonstro +" de dano em "
        						   + nomePersonagem);
        	ctnrLog.addAll(txtFuga, txtDanoMonstro);
        }
        
        return tentativa;
    }
    
    public Monstro getMonstro() {
        return this.monstro;
    }

	public void usarMagia(Container ctnrLog, Magia magia) {
        InfoMagiaUsada info = this.personagem.usarMagia(magia, monstro);
        
        if(info.isSucesso()) {
        	Label lb1 = new Label("");
            String magiaTipo = magia.getTipo().trim().toLowerCase();
            switch(magiaTipo) {
            case "dano" : {
            	lb1 = new MeuLabel(this.personagem.getNome() +" causou "+ info.getDanoMagia() +" em "
            			+this.monstro.getNome() +" com a magia "+ magia.getNome());
            }
            case "cura" : {
            	lb1 = new MeuLabel(this.personagem.getNome() +" se curou "+ info.getCuraMagia() +" com "
            			+magia.getNome());
            }
            }
            
            DanoFisico danoCausadoMonstro = this.monstro.causarDanoFisico(this.personagem);
            
        	Label lb2 = new MeuLabel(this.monstro.getNome() +" causou "+ danoCausadoMonstro +" de dano em "
        						   + this.personagem.getNome());
        	ctnrLog.add(lb1);
        	ctnrLog.add(lb2);
        	ctnrLog.add("  ");
        }
	}
}
