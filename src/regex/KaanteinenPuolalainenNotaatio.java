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
public class KaanteinenPuolalainenNotaatio {
    
    private String infix; //infix == tavallisen muotoinen säännöllinen lauseke
    
    public KaanteinenPuolalainenNotaatio(String infix) {
        this.infix = infix;
    }
    
    public String muunnaKaanteiseksiPuolalaiseksiNotaatioksi() {
        Stack pino = new Stack();
        String infixLiitoksilla = merkitseLiitokset();
        String output = "";
        
        for (int i = 0; i < infixLiitoksilla.length(); i++) {
            char kasiteltavaMerkki = infixLiitoksilla.charAt(i);
            Operaattori kasiteltavaOperaattori = Operaattori.valueOf(kasiteltavaMerkki);
            
            if(Character.isLetter(kasiteltavaMerkki)) {
                
                output += kasiteltavaMerkki;
                
            } else if(kasiteltavaOperaattori != null) {
                
                while(!pino.isEmpty()) {
                    
                    if(pino.peek().getClass() != Operaattori.class) {
                        break;
                    }
                    Operaattori seuraavaPinonJasen = (Operaattori) pino.peek();
                    
                    if(seuraavaPinonJasen.getLaskujarjestys() 
                            >= kasiteltavaOperaattori.getLaskujarjestys()) {
                        output += (Operaattori) pino.pop();
                    } else { break; }
                    
                }
                pino.push(Operaattori.valueOf(kasiteltavaMerkki));
                
            } else if(kasiteltavaMerkki == '(') {
                
                pino.push('(');
            
            } else if(kasiteltavaMerkki == ')') {
            
                while(pino.peek() != '(') {
                    output += pino.pop();
                }
                pino.pop();
            }
        }
        
        while(!pino.empty()) {
            output += pino.pop();
        }
        return output;
    }
    
    
    /**
     * Lisää säännölliseen lausekkeeseen liitosmerkit "¤",
     * jotta lauseke olisi helpompi muuttaa käänteiseksi
     * puolalaiseksi notaatioksi.
     * 
     * @return Tavallisessa muodossa oleva säännöllinen lauseke liitosmerkeillä "¤"
     */
    public String merkitseLiitokset() {
        String infixLiitoksilla = "";
        for (int i = 0; i < infix.length(); i++) {
            if(        onkoKaksiKirjaintaPerakkain(i) 
                    || onkoKirjainJaVasenSulkuPerakkain(i)
                    || onkoOsanPaattavaOperaattoriJaKirjainPerakkain(i)
                    || onkoOsanPaattavaOperaattoriJaVasenSulkuPerakkain(i)) {
                
                infixLiitoksilla += "¤";
            }
            
            infixLiitoksilla += ""+infix.charAt(i);
        }
        return infixLiitoksilla;
    }
    
    private boolean onkoKaksiKirjaintaPerakkain(int indeksi) {
        if(indeksi >= 1) { 
            if(Character.isLetter(infix.charAt(indeksi-1)) && Character.isLetter(infix.charAt(indeksi))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean onkoKirjainJaVasenSulkuPerakkain(int indeksi) {
        if(indeksi >= 1) { 
            if(Character.isLetter(infix.charAt(indeksi-1)) && infix.charAt(indeksi) == '(') {
                return true;
            }
        }
        return false;
    }
    
    private boolean onkoOsanPaattavaOperaattoriJaKirjainPerakkain(int indeksi) {
        
        if(indeksi >= 1) {
            if(onkoKvanttoriIndeksissa(indeksi - 1)) {
                if(Character.isLetter(infix.charAt(indeksi))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean onkoOsanPaattavaOperaattoriJaVasenSulkuPerakkain(int indeksi) {
        
        if(indeksi >= 1) {
            if(infix.charAt(indeksi) == '(' && onkoKvanttoriIndeksissa(indeksi - 1)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean onkoKvanttoriIndeksissa(int indeksi) {
         return onkoKvanttori(infix.charAt(indeksi));
    }
    private boolean onkoKvanttori(char c) {
        return     c == '*'|| c == '+'|| c == '?';
    }
}
