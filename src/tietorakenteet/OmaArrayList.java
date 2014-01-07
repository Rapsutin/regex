/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import java.util.Iterator;

/**
 *
 * @author Juho
 */
public class OmaArrayList <E> {
    
    private int seuraavaIndeksi;
    private Object[] taulukko;
  
    
  
    public OmaArrayList() {
        seuraavaIndeksi = 0;
        taulukko = new Object[10];
    }
    
    /**
     * Lisää taulukon loppuun annetun
     * alkion.
     * @param lisattava Lisättava alkio.
     */
    public void add(E lisattava) {
        muutaTaulukonKokoaTarvittaessa();
        taulukko[seuraavaIndeksi] = lisattava;
        seuraavaIndeksi++;
    }
    
    /**
     * Poistaa alkion annetusta
     * indeksistä.
     * @param indeksi 
     */
    public void remove(int indeksi) {
        taulukko[indeksi] = null;
        for (int i = indeksi + 1; i < seuraavaIndeksi; i++) {
            taulukko[i-1] = taulukko[i];
        }
        taulukko[seuraavaIndeksi - 1] = null;
        seuraavaIndeksi--;
    }
    
    public boolean contains(E elementti) {
        for (int i = 0; i < size(); i++) {
            if(elementti.equals(get(i))) {
                return true;
            }
        }
        return false;
    }
    
    public void removeLast() {
        remove(seuraavaIndeksi - 1);
    }
    
    /**
     * Palauttaa listan alkion,
     * joka on annetussa indeksissä.
     * @param indeksi Listan indeksi.
     * @return Listan alkio.
     */
    public E get(int indeksi) {
        if(indeksi >= seuraavaIndeksi || indeksi < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) taulukko[indeksi];
    }
    
    public E getLast() {
        return get(seuraavaIndeksi - 1);
    }
    
    public E getFirst() {
        return get(0);
    }
    
    /**
     * Kaksinkertaistaa taulukon koon, kun
     * se tulee täyteen.
     */
    private void muutaTaulukonKokoaTarvittaessa() {
        if(size() == taulukko.length) {
            Object[] uusiTaulukko = new Object[taulukko.length * 2];
            kopioiAlkiotUuteenTaulukkoon(taulukko, uusiTaulukko);
            taulukko = uusiTaulukko;
        }
    }
    
    /*Tähän on olemassa Javan omakin metodi, mutta pyrin tekemään
     * mahdollisimman paljon itse.
     */
    private void kopioiAlkiotUuteenTaulukkoon(Object[] kopioitava, Object[] uusi) {
        for (int i = 0; i < kopioitava.length; i++) {
            uusi[i] = kopioitava[i];
        }
    }
    
    
    
    public int size() {
        return seuraavaIndeksi;
    }

    
    
    
    
    
}
