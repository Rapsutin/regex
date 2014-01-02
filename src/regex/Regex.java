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
    
    
    public static void main(String[] args) {
        Automaatti a = Automaatti.luoKirjainautomaatti('a');
        Automaatti b = Automaatti.luoKirjainautomaatti('b');
        Automaatti ab = Automaatti.luoLiitosautomaatti(a, b);
        Automaatti c = Automaatti.luoKirjainautomaatti('c');
        Automaatti abtaic = Automaatti.luoTaiautomaatti(ab, c);
        abtaic.getAlkutila().muutaAktiiviseksi();
        abtaic.annaSyote("ab");
        System.out.println("jee");
    }
}
