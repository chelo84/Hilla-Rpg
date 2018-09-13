package hillaRPG.personagem;


import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import ca.weblite.codename1.json.JSONArray;
import ca.weblite.codename1.json.JSONException;
import ca.weblite.codename1.json.JSONObject;
import hillaRPG.gui.telaprincipal.telaaventura.batalha.DanoFisico;
import hillaRPG.infomagiausada.InfoMagiaCura;
import hillaRPG.infomagiausada.InfoMagiaDano;
import hillaRPG.infomagiausada.InfoMagiaUsada;
import hillaRPG.itens.Item;
import hillaRPG.magia.Magia;
import hillaRPG.magia.Magias;
import hillaRPG.monstro.Monstro;

public class Personagem {
    private String nome;
    private int xpAtual;
    private int xpTotal;
    private int xpNecessaria;
    private int level;
    private HashMap<EquipSlots, Item> equipamento;
    private int forca;
    private int sorte;
    private int agilidade;
    private int vitalidade;
    private int inteligencia;
    private int pontosStat;
    private int pontosStatTotal;
    private Integer ataque;
    private Integer danoMagico;
    private int defesa;
    private Inventario inventario;
    private double chanceCrit;
    private double chanceEvasao;
    private int vidaAtual;
    private int vidaMax;
    private Armazem armazem;
    private MagiasPersonagem magias;
    private int manaAtual;
    private int manaMax;
    
    private final int VIDAMAXPADRAO = 40;
    private final int VIDALEVELRATIO = 10;
    private final int VIDAVITRATIO = 3;
    private final int MANAMAXPADRAO = 48;
    private final int MANALEVELRATIO = 2;
    private final int MANAINTELIGENCIARATIO = 5;
    private final double CRITSORTERATIO = 0.0250;
    private final double EVASAOAGIRATIO = 100;
    private final int ATAQUESEMARMA = 5;

    public int getManaAtual() {
        return manaAtual;
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = manaAtual;
    }

    public int getManaMax() {
        return manaMax;
    }

    public void setManaMax() {
        this.manaMax = MANAMAXPADRAO + (MANALEVELRATIO*level) + (MANAINTELIGENCIARATIO*inteligencia);
    }

    public MagiasPersonagem getMagias() {
        return magias;
    }
    
    //JSON
    public JSONArray jsonGetMagias() {
        String[] magias = new String[this.magias.size()];
        
        for(int i = 0; i < this.magias.size(); i++) {
            Magia magia = this.magias.get(i);
            String str = magia.getId();

            magias[i] = str;
        }
        
        JSONArray jMagias = new JSONArray();
        for(int i = 0; i < magias.length; i++) {
        	jMagias.put(magias[i]);
        }
        
        return jMagias;
    }
    
    public void setMagias(MagiasPersonagem magias) throws IOException {
        this.magias = magias;
    }

    public int getPontosStatTotal() {
        return this.pontosStatTotal;
    }

    public void setPontosStatTotal() {
        this.pontosStatTotal = forca+inteligencia+sorte+agilidade+vitalidade+pontosStat;
    }
    
    public int getPontosStat() {
        return pontosStat;
    }

    public void setPontosStat(int pontosStat) {
        this.pontosStat = pontosStat;
    }
    
    public HashMap<EquipSlots, Item> getEquipamento() {
        return this.equipamento;
    }
    
    public void setEquipamento(HashMap<EquipSlots, Item> equipamento) {
        this.equipamento = equipamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(int xpAtual) {
        this.xpAtual = xpAtual;
    }

    public void setXpNecessaria(int xpNecessaria) {
        this.xpNecessaria = xpNecessaria;
    }
    
    public int getXpNecessaria() {
        return xpNecessaria;
    }

    public void addXp(int xp) {
        int xpAtual = this.getXpAtual();
        int xpNecessaria = this.getXpNecessaria();
        int xpDepois = xpAtual + xp;
        
        if(xpDepois < xpNecessaria) {
            this.xpAtual = xpDepois;
        } else {
            this.xpAtual = xpDepois - xpNecessaria;
            
            this.levelUp();
            
        }
        
        this.xpTotal = this.xpTotal + xp;
    }

    public int getXpTotal() {
        return xpTotal;
    }

    public void setXpTotal(int xpTotal) {
        this.xpTotal = xpTotal;
    }
    
    public void levelUp() {
        this.level++;
        this.xpNecessaria = XpTable.XPTABLE[this.level+1];
        this.pontosStat++;
        this.pontosStatTotal++;
        this.setVidaMax();
        this.curarVida(this.getVidaMax());
        this.setManaMax();
        this.setManaAtual(this.getManaMax());
        
        Stats.level = this.getLevel();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        int level1 = 0;
        int xpTotal = this.xpTotal;
        for(int i = 0; i < XpTable.XPTABLE.length; i++) {
            if(xpTotal >= XpTable.XPTABLE[i]) {
                level1 = i;
                xpTotal -= XpTable.XPTABLE[i];
            }
        }
        
        this.level = level1;
        
        Stats.level = this.getLevel();
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public int getVidaMax() {
        return vidaMax;
    }
    
    public void setVidaMax() {
        this.vidaMax = VIDAMAXPADRAO + (VIDALEVELRATIO*level) + (VIDAVITRATIO*vitalidade);
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    public Inventario getInventario() {
        return inventario;
    }
    
    //JSON
    public JSONArray jsonGetInventario() throws IOException {
        String[] itens = new String[inventario.size()+1];
        
        
        for(int i = 0; i < inventario.size(); i++) {
        	Item item = inventario.getItem(i);
        	int qtd = inventario.getQtd(i);
        	String str = item.getId() +":"+ qtd;

        	itens[i] = str;
        }
        
        int ouro = inventario.getOuro();
        itens[inventario.size()] = "ouro:"+ ouro;
        
		JSONArray jInventario = new JSONArray();
        for(int i = 0; i < itens.length; i++) {
        	jInventario.put(itens[i]);
        }
        
        return jInventario;
    }
    
    public Integer getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
        
        Stats.forca = getForca();
    }

    public Integer getSorte() {
        return sorte;
        
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(int agilidade) {
        this.agilidade = agilidade;
    }

    public Integer getVitalidade() {
        return vitalidade;
    }

    public void setVitalidade(int vitalidade) {
        this.vitalidade = vitalidade;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public double getChanceCrit() {
        return chanceCrit;
    }

    public void setChanceCrit() {
        this.chanceCrit = (double) this.getSorte() * CRITSORTERATIO;
    }
    
    public int getDefesa() {
        return defesa;
    }

    public void setDefesa() {
        int defesaElmo = 0;
        int defesaArmadura = 0;
        int defesaEscudo = 0;
        int defesaCalcas = 0;
        int defesaBotas = 0;
        
        try { defesaElmo = this.equipamento.get(EquipSlots.ELMO).getDefesa(); } catch (NullPointerException ex) { defesaElmo = 0; }
        try { defesaArmadura = this.equipamento.get(EquipSlots.ARMADURA).getDefesa(); } catch (NullPointerException ex) { defesaArmadura = 0; }
        try { defesaEscudo = this.equipamento.get(EquipSlots.ESCUDO).getDefesa(); } catch (NullPointerException ex) { defesaEscudo = 0; }
        try { defesaCalcas = this.equipamento.get(EquipSlots.CALCAS).getDefesa(); } catch (NullPointerException ex) { defesaCalcas = 0; }
        try { defesaBotas = this.equipamento.get(EquipSlots.BOTAS).getDefesa(); } catch (NullPointerException ex) { defesaBotas = 0; }
        
        this.defesa = defesaElmo + defesaArmadura + defesaEscudo + defesaCalcas + defesaBotas;
        
        Stats.defesa = this.getDefesa();
    }
    
    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque() {
        try {
            this.ataque = (this.equipamento.get(EquipSlots.ARMA).getAtaque() + this.getForca());
        } catch (NullPointerException ex) {
            this.ataque = ATAQUESEMARMA+this.getForca();
        }
        
        Stats.ataque = this.getAtaque();
    }
    
    public Integer getDanoMagico() {
        return danoMagico;
    }

    public void setDanoMagico() {
        try {
            this.danoMagico = (this.equipamento.get(EquipSlots.ARMA).getAtaqueMagico() + this.getInteligencia());
        } catch (NullPointerException ex) {
            this.danoMagico = ATAQUESEMARMA+this.getInteligencia();
        }
        
        Stats.danoMagico = this.getDanoMagico();
    }

    public double getChanceEvasao() {
        return chanceEvasao;
    }

    public void setChanceEvasao() {
        this.chanceEvasao = (double) (this.agilidade / 2) / EVASAOAGIRATIO;
    }
    
    @Override
    public String toString() {
        return "Nome: "+ this.getNome() +
               "\nLevel: "+ this.getLevel() +
               "\nXpAtual: "+ this.getXpAtual() +"/"+ this.getXpNecessaria() +
               "\nVida: "+ this.getVidaAtual() +"/"+ this.getVidaMax() +
               "\nForca: "+ this.getForca() +
               "\nSorte: "+ this.getSorte() +
               "\nAgilidade: "+ this.getAgilidade() +
               "\nVitalidade: "+ this.getVitalidade() +
               "\nAtaque: "+ this.getAtaque() +
               "\nDano Mágico: "+ this.getDanoMagico() +
               "\nDefesa: "+ this.getDefesa() +
               "\nChance Critica: "+ this.getChanceCrit() +
               "\nChance de Evasão: "+ this.getChanceEvasao() +
               "\n\n----- Equipamentos -----\n\n"+ this.equipamento.get(EquipSlots.ELMO) +
               "\n\n"+ this.equipamento.get(EquipSlots.ARMA) +
               "\n\n"+ this.equipamento.get(EquipSlots.ARMADURA) +
               "\n\n"+ this.equipamento.get(EquipSlots.ESCUDO) +
               "\n\n"+ this.equipamento.get(EquipSlots.CALCAS) +
               "\n\n"+ this.equipamento.get(EquipSlots.BOTAS);
    }

    public void setArmazem(Armazem armazem) {
        this.armazem = armazem;
    }
    
    public Armazem getArmazem() {
        return armazem;
    }
    
    //JSON
    public JSONArray jsonGetArmazem() throws IOException {
        String[] itens = new String[armazem.size()];
        
        for(int i = 0; i < armazem.size(); i++) {
        		Item item = armazem.getItem(i);
        		int qtd = armazem.getQtd(i);
                String str = item.getId() +":"+ qtd;

                itens[i] = str;
        }
        
		JSONArray jArmazem = new JSONArray();
        for(int i = 0; i < itens.length; i++) {
        	jArmazem.put(itens[i]);
        }
        
        return jArmazem;
    }
    
    public JSONObject jsonGetEquipamento() {
        HashMap<EquipSlots, String> equips = new HashMap<>();
        for(EquipSlots slot : equipamento.keySet()) {
            String idItem;
            try {
                idItem = equipamento.get(slot).getId();
            } catch (NullPointerException ex) {
                idItem = null;
            }
            
            equips.put(slot, idItem);
        }
        
        JSONObject jEquips = new JSONObject();
		try {
			for(EquipSlots slot : EquipSlots.values()) {
				jEquips.put(slot.name(), equips.get(slot));
			}
		} catch (JSONException ex) {
		}
        
        return jEquips;
    }

    public void addForca(int forca) {
        this.forca += forca;
        Stats.forca += forca;
        
        this.setAtaque();
        Stats.ataque = this.getAtaque();
    }

    public void addVitalidade(int vitalidade) {
        this.vitalidade += vitalidade;
        Stats.vitalidade += vitalidade;
        
        this.setVidaMax();
    }

    public void addAgilidade(int agilidade) {
        this.agilidade += agilidade;
        Stats.agilidade += agilidade;
        
        this.setChanceEvasao();
    }

    public void addSorte(int sorte) {
        this.sorte += sorte;
        Stats.sorte += sorte;
        
        this.setChanceCrit();
    }
    
    public void addInteligencia(int inteligencia) {
        this.inteligencia += inteligencia;
        Stats.inteligencia += inteligencia;
        
        this.setDanoMagico();
        Stats.danoMagico = this.getDanoMagico();
    }

    public DanoFisico causarDanoFisico(Monstro monstro) {
        DanoFisico dano = new DanoFisico();
        
        Random random = new Random();
        double dado = random.nextDouble();
        boolean crit = (dado < this.getChanceCrit());
        dano.setCrit(crit);
        int danoTomado = (crit)? (this.getAtaque() - monstro.getDefesa())*2 : this.getAtaque() - monstro.getDefesa();
        if (danoTomado < 0) danoTomado = 0;
        
        if(monstro.getVidaAtual() - danoTomado > 0) {
            monstro.setVidaAtual(monstro.getVidaAtual() - danoTomado);
            
            dano.setDano(danoTomado);
            
           return dano;
        } else {
            int vida = monstro.getVidaAtual();
            
            monstro.setVidaAtual(0);
            dano.setDano(vida);
            
            return dano;
        }
    }
    
    public void curarVida(int valor) {
        int vidaAtual = this.getVidaAtual();
        int vidaDepois = vidaAtual + valor;
        int vidaMax = this.getVidaMax();
        
        if(vidaDepois <= vidaMax) {
            this.setVidaAtual(vidaDepois);
        } else {
            this.setVidaAtual(vidaMax);
        }
    }

    public boolean usarItem(Item item) {
        try {
            String nomeEfeito = item.getNomeEfeito();
            String valorEfeito = item.getValorEfeito();
        
            switch(nomeEfeito) {
                case "curar" : this.curarVida(Integer.parseInt(valorEfeito)); return true;
                case "aprender": {
                    Magias magias = new Magias();
                    if(this.getLevel() < item.getLevelNecessario()
                       || this.getForca() < item.getForcaNecessaria()
                       || this.getVitalidade() < item.getVitalidadeNecessaria()
                       || this.getAgilidade() < item.getAgilidadeNecessaria()
                       || this.getSorte() < item.getSorteNecessaria()
                       || this.getInteligencia() < item.getInteligenciaNecessaria()) {
                
                        return false;
                    } else {
                        return (this.magias.add(magias.get(valorEfeito)));
                    }
                }
                
            }
        } catch (IOException ex) {
        	
        }
        
        return true;
    }
    
    public void atualizarPersonagem() {
        this.setAtaque();
        this.setDanoMagico();
        this.setChanceCrit();
        this.setChanceEvasao();
        this.setDefesa();
        this.setVidaMax();
    }

    public boolean equiparItem(Item item) {
        if(this.getLevel() < item.getLevelNecessario()
           || this.getForca() < item.getForcaNecessaria()
           || this.getVitalidade() < item.getVitalidadeNecessaria()
           || this.getAgilidade() < item.getAgilidadeNecessaria()
           || this.getSorte() < item.getSorteNecessaria()
           || this.getInteligencia() < item.getInteligenciaNecessaria()) {
            
            atualizarPersonagem();
        	
            return false;
        }
        
        EquipSlots slot = EquipSlots.valueOf(item.getTipo().toUpperCase());
        
        if(this.equipamento.get(slot) == null) {
            this.equipamento.put(slot, item);
            this.inventario.remove(item.getId());
            
            atualizarPersonagem();
            
            return true;
        } else {
            Item itemEquipado = this.equipamento.get(slot);
            int indexItemInv = this.inventario.getIndex(item.getId());
            
            this.equipamento.put(slot, item);
            this.inventario.remove(item.getId());
            this.inventario.add(itemEquipado, 1, indexItemInv);
            
            atualizarPersonagem();
            
            return true;
        }
    }
    
    public boolean desequiparItem(EquipSlots slot) {
        Item item = equipamento.get(slot);
        
        if(this.inventario.add(item, 1)) {
            equipamento.remove(slot);
            
            return true;
        } else {
            
            return false;
        }
    }

    public InfoMagiaUsada usarMagia(Magia magia, Monstro monstro) {
        InfoMagiaUsada info = null;
        String tipoMagia = magia.getTipo().trim().toLowerCase();
        if(this.getManaAtual() - magia.getCusto() < 0) {
            info = new InfoMagiaDano();
            
            info.setSucesso(false);
            return info;
        }
        this.setManaAtual(this.getManaAtual()-magia.getCusto());
        
        switch(tipoMagia) {
            case "dano" : {
                info = new InfoMagiaDano();
                
                int dano = (int) (magia.getValorEfeito() + this.getDanoMagico()*magia.getValorEfeitoRatio());
                double err = this.getDanoMagico()*magia.getValorEfeitoRatio();
                
                if(monstro.getVidaAtual() - dano >= 0) {
                    monstro.setVidaAtual(monstro.getVidaAtual() - dano);
                    
                    info.setDanoMagia(dano);
                } else {
                    dano = monstro.getVidaAtual();
                    monstro.setVidaAtual(0);
                    
                    info.setDanoMagia(dano);
                }
                break;
            }
            
            case "cura": {
                info = new InfoMagiaCura();
                
                double err = this.getDanoMagico()*magia.getValorEfeitoRatio();
                
                int cura = (int) (magia.getValorEfeito() + this.getDanoMagico()*magia.getValorEfeitoRatio());
                this.curarVida(cura);
                
                info.setCuraMagia(cura);
            }
        }
        
        info.setSucesso(true);
        
        return info;
    }
}
