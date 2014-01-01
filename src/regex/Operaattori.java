/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

/**
 *
 * @author Juho
 */
public enum Operaattori {
    /*
     * Lähde: Compilers: Principles, Techniques, and Tools 
     * Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman
     * (Sivut 121-122)
     */
    TAHTI(4, '*'),
    PLUS(4, '+'),
    KYSYMYSMERKKI(4, '?'),
    LIITOSMERKKI(3, '¤'),
    TAIMERKKI(2, '|');
    
    private int laskujarjestys;
    private char merkki;

    private Operaattori(int laskujarjestys, char merkki) {
        this.laskujarjestys = laskujarjestys;
        this.merkki = merkki;
    }
    
    public static Operaattori valueOf(char c) {
        switch (c) {
            case '*': return TAHTI;
            case '+': return PLUS;
            case '?': return KYSYMYSMERKKI;
            case '¤': return LIITOSMERKKI;
            case '|': return TAIMERKKI;
        }
        return null;
    }

    public int getLaskujarjestys() {
        return laskujarjestys;
    }
    
    public static boolean onkoOperaattori(char c) {
        if(valueOf(c) == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return ""+merkki;
    }
    
    
    
    
    
}
