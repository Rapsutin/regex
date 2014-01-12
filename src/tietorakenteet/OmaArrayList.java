/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;



/**
 *Vastaa osittain Javan ArrayList-luokkaa.
 * @author Juho
 */
public class OmaArrayList <E> {
    
    private int seuraavaIndeksi;
    private Object[] taulukko;
  
    
    /**
     * Lista, joka kasvattaa itseään,
     * kun siihen lisätään uusia alkioita.
     */
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
    
    /**
     * Kertoo onko annettu
     * olio listassa.
     * @param object
     * @return True, jos olio on listassa, muuten false.
     */
    public boolean contains(E object) {
        for (int i = 0; i < size(); i++) {
            if(object.equals(get(i))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Poistaa listasta sen
     * viimeisen olion.
     */
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
    
    /**
     * Palauttaa listan viimeisen olion.
     * @return Listan viimeinen olio.
     */
    public E getLast() {
        return get(seuraavaIndeksi - 1);
    }
    /**
     * Palauttaa listan ensimmäisen olion.
     * @return Listan ensimmäinen olio.
     */
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
    
    
    /**
     * Palauttaa listan alkioiden määrän.
     * @return Listan alkioiden määrä.
     */
    public int size() {
        return seuraavaIndeksi;
    }

    
    
    
    
    
}
