/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;


/**
 *
 * @author Juho
 */
public class OmaStack <E> {

    private OmaArrayList<E> pino;

    public OmaStack() {
        pino = new OmaArrayList<>();
    }
    
    public void push(E lisattava) {
        pino.add(lisattava);
    }
    
    public E pop() {
        E popattu = pino.getViimeinen();
        pino.poistaViimeinen();
        return popattu;
    }
    
    
}
