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
    
    /**
     * Luo siirtofunktion lopputilan indeksin perusteella.
     * @param lopputilanIndeksi Lopputilan indeksi.
     * @param hyvaksyttySyote Hyväksytty syöte. 
     * Huom! null, jos funktio hyväksyy tyhjän merkkijonon.
     * @param automaatinTilat Kaikki automaatin tilat.
     */
    public Siirtofunktio(int lopputilanIndeksi, Character hyvaksyttySyote, List<Tila> automaatinTilat) {
        this(automaatinTilat.get(lopputilanIndeksi), hyvaksyttySyote);
    }
    
    /**
     * Metodi muuttaa lopputilan aktiiviseksi, jos
     * annettu syöte on oikea.
     * @param annettuSyote Annettu syöte.
     */
    public void siirra(Character annettuSyote) {
        if (annettuSyote != hyvaksyttySyote && hyvaksyttySyote != null) {
        } else {
            lopputila.muutaAktiiviseksi();
        }
        

    }
}
