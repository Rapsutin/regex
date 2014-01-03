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
    private boolean aktivoituViimeksi;
    private List<Siirtofunktio> siirtofunktiot;
    
    public Tila() {
        aktiivinen = false;
        aktivoituViimeksi = false;
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
        muutaEpaaktiiviseksi();
        
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
        kutsuMerkittomiaSiirtofunktioita();
    }
    
    /**
     * Muuttaa tilan epäaktiiviseksi.
     */
    public void muutaEpaaktiiviseksi() {
        if(!aktivoituViimeksi) {
            aktiivinen = false;
        }
        
    }

    @Override
    public String toString() {
        return super.toString()+" Aktiivisuus:"+onAktiivinen();
    }

    public List<Siirtofunktio> getSiirtofunktiot() {
        return siirtofunktiot;
    }

    public void setAktivoituViimeksi(boolean aktivoituViimeksi) {
        this.aktivoituViimeksi = aktivoituViimeksi;
    }

    public boolean isAktivoituViimeksi() {
        return aktivoituViimeksi;
    }
    
    
}
