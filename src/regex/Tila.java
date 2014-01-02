/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juho
 */
public class Tila {
    private boolean aktiivinen;
    private List<Siirtofunktio> siirtofunktiot;
    
    public Tila() {
        aktiivinen = false;
        siirtofunktiot = new ArrayList<>();
    }
    /**
     * Kutsuu kaikkia siirtofunktioita annetulla
     * syötteellä. Käsittelee ensin funktiot, jotka
     * eivät tarvitse syötettä.
     * @param syote Funktioille annettava syöte.
     */
    public void kutsuMerkittomiaSiirtofunktioita() {
        if(!aktiivinen) {
            return;
        }
        for(Siirtofunktio s : siirtofunktiot) {
            s.siirra(null);
        }
    }
    
    public void kutsuSiirtofunktioita(Character syote) {
        
        if(!aktiivinen) {
            return;
        }
        aktiivinen = false;
        
        for(Siirtofunktio s : siirtofunktiot) {
            s.siirra(syote);
        }
    }
    
    /**
     * Lisää tilalle uuden siirtofunktion.
     * @param siirtofunktio Lisättävä siirtofunktio.
     */
    public void lisaaSiirtofunktio(Siirtofunktio siirtofunktio) {
        siirtofunktiot.add(siirtofunktio);
    }
    
    /**
     * 
     * @return True, jos tila on aktiivinen, muuten false.
     */
    public boolean onAktiivinen() {
        return aktiivinen;
    }
    
    /**
     * Muuttaa tilan aktiiviseksi.
     */
    public void muutaAktiiviseksi() {
        aktiivinen = true;
    }
    
    /**
     * Muuttaa tilan epäaktiiviseksi.
     */
    public void muutaEpaaktiiviseksi() {
        aktiivinen = false;
    }

    @Override
    public String toString() {
        return super.toString()+" Aktiivisuus:"+onAktiivinen();
    }
    
    
}
