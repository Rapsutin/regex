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
    public static void main(String[] args) {
        Tila alkutila = new Tila();
        Tila tila2 = new Tila();
        Automaatti automaatti = new Automaatti(alkutila);
        automaatti.lisaaTila(tila2);
        alkutila.lisaaSiirtofunktio(new Siirtofunktio(tila2, 'c'));
        automaatti.annaSyote('c');
        System.out.println("jee");
    }
}
