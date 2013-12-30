/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.Stack;

/**
 *
 * @author Juho
 */
public class KaanteinenNotaatio {
    
    
    /**
     * Muuntaa annetun syötteen käänteiseksi puolalaiseksi
     * notaatioksi, jonka avulla voidaan rakentaa helposti
     * säännöllisiä lausekkeita tulkkaava automaatti.
     * @param infix Tavallisessa muodossa oleva säännöllinen lauseke.
     * @return Käänteisessä muodossa oleva säännöllinen lauseke.
     */
    public static String muunnaKaanteiseksi(String infix) {
        Stack pino = new Stack();
        String infixLiitoksilla = merkitseLiitokset(infix);
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < infixLiitoksilla.length(); i++) {
            char kasiteltavaMerkki = infixLiitoksilla.charAt(i);
            kasitteleMerkki(kasiteltavaMerkki, output, pino);
        }
        
        while(!pino.empty()) {
            output.append(pino.pop());
        }
        return output.toString();
    }
    
    /* Käsittely perustuu shunting-yard -algoritmiin.
     * http://en.wikipedia.org/wiki/Shunting-yard_algorithm
     */
    private static void kasitteleMerkki(char kasiteltavaMerkki, StringBuilder output, Stack pino) {
        if(Character.isLetter(kasiteltavaMerkki)) {
            output.append(kasiteltavaMerkki);
        } else if(Operaattori.valueOf(kasiteltavaMerkki) != null) {
            kasitteleOperaattori(kasiteltavaMerkki, pino, output);
        } else if(kasiteltavaMerkki == '(') {
            pino.push('(');
        } else if(kasiteltavaMerkki == ')') {
            kasitteleOikeaSulku(pino, output);
        }
    }
    
    private static void kasitteleOperaattori(char kasiteltavaMerkki, Stack pino, StringBuilder output) {
        Operaattori kasiteltavaOperaattori = Operaattori.valueOf(kasiteltavaMerkki);
        while(!pino.isEmpty()) {
            
            if(pino.peek().getClass() != Operaattori.class) {
                break;
            }
            Operaattori seuraavaPinonJasen = (Operaattori) pino.peek();
            
            if(seuraavaPinonJasen.getLaskujarjestys() 
                    >= kasiteltavaOperaattori.getLaskujarjestys()) {
                output.append ((Operaattori) pino.pop());
            } else { 
                break; 
            }
        }
        pino.push(Operaattori.valueOf(kasiteltavaMerkki));
    }

    private static void kasitteleOikeaSulku(Stack pino, StringBuilder output) {
        while(pino.peek() != '(') {
            output.append(pino.pop());
        }
        pino.pop();
    }
    
    
    /**
     * Lisää säännölliseen lausekkeeseen liitosmerkit "¤",
     * jotta lauseke olisi helpompi muuttaa käänteiseksi
     * puolalaiseksi notaatioksi.
     * 
     * @return Tavallisessa muodossa oleva säännöllinen lauseke liitosmerkeillä "¤"
     */
    public static String merkitseLiitokset(String infix) {
        
        String infixLiitoksilla = "";
        for (int i = 0; i < infix.length(); i++) {
            if(        onkoKaksiKirjaintaPerakkain(infix, i) 
                    || onkoKirjainJaVasenSulkuPerakkain(infix, i)
                    || onkoOsanPaattavaOperaattoriJaKirjainPerakkain(infix, i)
                    || onkoOsanPaattavaOperaattoriJaVasenSulkuPerakkain(infix, i)) {
                
                infixLiitoksilla += "¤";
            }
            
            infixLiitoksilla += ""+infix.charAt(i);
        }
        return infixLiitoksilla;
    }
    
    private static boolean onkoKaksiKirjaintaPerakkain(String infix, int indeksi) {
        if(indeksi >= 1) { 
            if(Character.isLetter(infix.charAt(indeksi-1)) && Character.isLetter(infix.charAt(indeksi))) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean onkoKirjainJaVasenSulkuPerakkain(String infix, int indeksi) {
        if(indeksi >= 1) { 
            if(Character.isLetter(infix.charAt(indeksi-1)) && infix.charAt(indeksi) == '(') {
                return true;
            }
        }
        return false;
    }
    
    private static boolean onkoOsanPaattavaOperaattoriJaKirjainPerakkain(String infix, int indeksi) {
        
        if(indeksi >= 1) {
            if(onkoKvanttoriIndeksissa(infix, indeksi - 1)) {
                if(Character.isLetter(infix.charAt(indeksi))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean onkoOsanPaattavaOperaattoriJaVasenSulkuPerakkain(String infix, int indeksi) {
        if(indeksi >= 1) {
            if(infix.charAt(indeksi) == '(' && onkoKvanttoriIndeksissa(infix, indeksi - 1)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean onkoKvanttoriIndeksissa(String infix, int indeksi) {
         return onkoKvanttori(infix.charAt(indeksi));
    }
    private static boolean onkoKvanttori(char c) {
        return     c == '*'|| c == '+'|| c == '?';
    }

    

    
}
