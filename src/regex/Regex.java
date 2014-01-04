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
public class Regex {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static boolean vastaakoSyote(String syote, Automaatti automaatti) {
        return automaatti.annaSyote(syote);
    }
    
    public static boolean vastaakoSyote(String syote, String regex) {
        return vastaakoSyote(syote, Automaatti.luoAutomaattiRegexista(regex));
    }
    
    
    public static void main(String[] args) {
        
        List<Tila> tilat = new ArrayList<>();
        
        
        System.out.println(vastaakoSyote("abbbbbbbbb", "(a|(a)*b)*"));
    }
}
