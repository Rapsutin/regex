/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author Juho
 */
public class AutomaatinRakentaja {
    private String infix; //infix == tavallisen muotoinen säännöllinen lauseke

    public AutomaatinRakentaja(String infix) {
        this.infix = infix;
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
            if(onkoOperaattori(indeksi - 1)) {
                if(Character.isLetter(infix.charAt(indeksi))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean onkoOsanPaattavaOperaattoriJaVasenSulkuPerakkain(int indeksi) {
        
        if(indeksi >= 1) {
            if(infix.charAt(indeksi) == '(' && onkoOperaattori(indeksi - 1)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean onkoOperaattori(int indeksi) {
         return     infix.charAt(indeksi) == '*' 
                 || infix.charAt(indeksi) == '+'
                 || infix.charAt(indeksi) == '?';
    }
    
    
}
