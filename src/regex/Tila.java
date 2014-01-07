/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;
import tietorakenteet.OmaArrayList;

/**
 *
 * @author Juho
 */
public class Tila {
    
    private OmaArrayList<Siirtofunktio> siirtofunktiot;
    
    public Tila() {
        siirtofunktiot = new OmaArrayList<>();
    }
    
    public void lisaaSiirtofunktio(Siirtofunktio siirtofunktio) {
        siirtofunktiot.add(siirtofunktio);
    }
    
    public OmaArrayList<Siirtofunktio> getSiirtofunktiot() {
        return siirtofunktiot;
    }

    
}
