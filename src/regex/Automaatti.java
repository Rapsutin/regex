/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;

/**
 * Epädeterministinen äärellinen automaatti.
 * @author Juho
 */
public class Automaatti {
    
    private List<Tila> tilat;
    private Tila alkutila;
    private Tila lopputila;
    
    
    public Automaatti() {
        tilat = new ArrayList<>();
        alkutila = new Tila();
        lopputila = new Tila();
        tilat.add(alkutila);
        tilat.add(lopputila);
    }
    
    public Automaatti(Character hyvaksyttySyote) {
        this();
        alkutila.lisaaSiirtofunktio(new Siirtofunktio(lopputila, hyvaksyttySyote));
    }
    
    public Automaatti (String infix) {
        this(infix.charAt(0));
        String postfix = KaanteinenNotaatio.muunnaKaanteiseksi(infix);
        List<Automaatti> aliautomaatit = new ArrayList<>();
        aliautomaatit.add(this);
        for (int i = 1; i < postfix.length(); i++) {
            char kasiteltava = postfix.charAt(i);
            
            if(Operaattori.onkoOperaattori(kasiteltava) || kasiteltava == ')' || kasiteltava == '(') {
                kasitteleOperaattori(kasiteltava, aliautomaatit);
            } else {
                aliautomaatit.add(new Automaatti(kasiteltava));
            }
        }
               
    }
    
    private void kasitteleOperaattori(char operaattori, List<Automaatti> aliAutomaatit) {
        Automaatti vasen = aliAutomaatit.get(aliAutomaatit.size() - 2);
        Automaatti oikea = aliAutomaatit.get(aliAutomaatit.size() - 1);
        switch(operaattori) {
            case '|':
                vasen.alkutila.lisaaSiirtofunktio(new Siirtofunktio(oikea.getAlkutila(), null));
                vasen.getTilat().addAll(oikea.getTilat());
                aliAutomaatit.remove(oikea);
            case '¤':
                vasen.lopputila.lisaaSiirtofunktio(new Siirtofunktio(oikea.getAlkutila(), null));
                vasen.setLopputila(oikea.lopputila);
                vasen.getTilat().addAll(oikea.getTilat());
                aliAutomaatit.remove(oikea);
        }
    }

   
    
    
    
    /**
     * Antaa automaatille syötteen
     * ja käsittelee aktiivisten tilojen
     * siirtofunktiot.
     * @param syote Annettava syöte.
     */
    public void annaSyote(Character syote) {
        List<Tila> aktiivisetTilat = new ArrayList<>();
        
        for(Tila t : tilat) {
            if(t.onAktiivinen()) {
                aktiivisetTilat.add(t);
            }
        }
        
        for(Tila t : aktiivisetTilat) {
            t.kutsuSiirtofunktioita(syote);
        }
    }
    
    /**
     * Lisää automaattiin tilan.
     * @param lisattava Lisättävä tila.
     */
    public void lisaaTila(Tila lisattava) {
        tilat.add(lisattava);
    }
    
    /**
     * 
     * @return Kaikki automaatin tilat.
     */
    public List<Tila> getTilat() {
        return tilat;
    }
    
    /**
     * 
     * @return Alkutila.
     */
    public Tila getAlkutila() {
        return alkutila;
    }

    public Tila getLopputila() {
        return lopputila;
    }
    
     public void setAlkutila(Tila alkutila) {
        this.alkutila = alkutila;
    }

    public void setLopputila(Tila lopputila) {
        this.lopputila = lopputila;
    }
    
    
    
    
    
    
}
