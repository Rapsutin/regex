/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author Juho
 */
public class Regex {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static boolean vastaakoSyote(String syote, Automaatti automaatti) {
        automaatti.getAlkutila().muutaAktiiviseksi();
        automaatti.annaSyote(syote);
        if(automaatti.getLopputila().onAktiivinen()) {
            automaatti.muutaKaikkiTilatEpaaktiivisiksi();
            return true;
        }
        automaatti.muutaKaikkiTilatEpaaktiivisiksi();
        return false;
    }
    
    public static boolean vastaakoSyote(String syote, String regex) {
        return vastaakoSyote(syote, Automaatti.luoAutomaattiRegexista(regex));
    }
    
    
    public static void main(String[] args) {
        Automaatti a = Automaatti.luoKirjainautomaatti('a');
        Automaatti b = Automaatti.luoKirjainautomaatti('b');
        Automaatti ab = Automaatti.luoLiitosautomaatti(a, b);
        Automaatti c = Automaatti.luoKirjainautomaatti('c');
        Automaatti abtahti = Automaatti.luoTahtiautomaatti(ab);
        Automaatti supertesti = Automaatti.luoTaiautomaatti(abtahti, c);
        
        
        System.out.println(vastaakoSyote("ababbc", "(bc|ab)*"));
        
        
    }
}
