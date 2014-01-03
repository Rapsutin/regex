/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.List;

/**
 *
 * @author Juho
 */
public class Siirtofunktio {

    private Tila lopputila;
    private Character hyvaksyttySyote;
    
    /**
     * Luo siirtofunktion.
     * @param lopputila Siirtofunktion lopputila.
     * @param hyvaksyttySyote Hyväksytty syöte.
     */
    public Siirtofunktio(Tila lopputila, Character hyvaksyttySyote) {
        this.lopputila = lopputila;
        this.hyvaksyttySyote = hyvaksyttySyote;
    }

    public Character getHyvaksyttySyote() {
        return hyvaksyttySyote;
    }
    
    
    
    /**
     * Metodi muuttaa lopputilan aktiiviseksi, jos
     * annettu syöte on oikea.
     * @param annettuSyote Annettu syöte.
     */
    public void siirra(Character annettuSyote) {
        if (annettuSyote != hyvaksyttySyote) {
        } else {
            lopputila.muutaAktiiviseksi();
            lopputila.setAktivoituViimeksi(true);
        }
        

    }

    public Tila getLopputila() {
        return lopputila;
    }
    
    
}
