/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;


/**
 *Vastaa osittain Javan Stack-luokkaa.
 * @author Juho
 */
public class OmaStack <E> {

    private OmaArrayList<E> pino;
    
    
    public OmaStack() {
        pino = new OmaArrayList<>();
    }
    
    /**
     * Lisää pinon päälle uuden alkion.
     * @param lisattava Lisättävä alkio.
     */
    public void push(E lisattava) {
        pino.add(lisattava);
    }
    
    /**
     * Poistaa ja palauttaa pinon päällä
     * olevan alkion.
     * @return Pinon päällä oleva alkio.
     */
    public E pop() {
        E popattu = pino.getLast();
        pino.removeLast();
        return popattu;
    }
    
    /**
     * Kertoo onko pino tyhjä.
     * @return True, jos on tyhjä, muuten false.
     */
    public boolean isEmpty() {
        return pino.size() == 0;
    }
    
    
    /**
     * Palauttaa pinon päällimmäisen
     * alkion, muttei poista sitä.
     * @return Pinon päällimmäinen alkio.
     */
    public E peek() {
        return pino.getLast();
    }
    
    
}
