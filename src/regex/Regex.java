/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 *
 * @author Juho
 */
public class Regex {

    /**
     * @param args the command line arguments
     */
    
    /**
     * Kertoo vastaako annettu syöte
     * säännöllisessä lausekkeessa määriteltyä muotoilua.
     * @param syote Testattava merkkijono.
     * @param regex Säännöllinen lauseke.
     * @return True, jos syöte vastaa regexiä, muuten false.
     */
    public static boolean vastaakoSyote(String syote, String regex) {
        return vastaakoSyote(syote, Automaatti.luoAutomaattiRegexista(regex));
    }
    
    /**
     * Kertoo hyväksyykö automaatti
     * syötteen.
     * @param syote Testattava merkkijono.
     * @param automaatti Epädeterministinen äärellinen automaatti.
     * @return True, jos automaatti hyväksyy syötteen, muuten false.
     */
    public static boolean vastaakoSyote(String syote, Automaatti automaatti) {
        return automaatti.annaSyote(syote);
    }
    
    
    
}
