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
    
    public void lisaaSiirtofunktio(Siirtofunktio siirtofunktio) {
        siirtofunktiot.add(siirtofunktio);
    }
    
    public List<Siirtofunktio> getSiirtofunktiot() {
        return siirtofunktiot;
    }

    
}
