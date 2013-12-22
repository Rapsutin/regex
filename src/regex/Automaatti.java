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
    
    public Automaatti(Tila alkutila) {
        this.alkutila = alkutila;
        alkutila.muutaAktiiviseksi();
        tilat = new ArrayList<>();
        tilat.add(alkutila);
        "jee".matches(null);
    }
    
    /**
     * Antaa automaatille syötteen
     * ja käsittelee aktiivisten tilojen
     * siirtofunktiot.
     * @param syote Annettava syöte.
     */
    public void annaSyote(Character syote) {
        ArrayList<Tila> aktiivisetTilat = new ArrayList<>();
        
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
    
    
    
}
