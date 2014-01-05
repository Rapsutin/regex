/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 *
 * @author Juho
 */
public class OmaArrayList <E>{
    
    private int seuraavaIndeksi;
    private Object[] taulukko;
  
    
  
    public OmaArrayList() {
        seuraavaIndeksi = 0;
        taulukko = new Object[10];
    }
    
    public void add(E lisattava) {
        muutaTaulukonKokoaTarvittaessa();
        taulukko[seuraavaIndeksi] = lisattava;
        seuraavaIndeksi++;
    }
    
    public E get(int indeksi) {
        if(indeksi >= seuraavaIndeksi || indeksi < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (E) taulukko[indeksi];
    }
    
    private void muutaTaulukonKokoaTarvittaessa() {
        if(getAlkioidenMaara() == taulukko.length) {
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
    
    
    public int getAlkioidenMaara() {
        return seuraavaIndeksi;
    }
    
    
    
    
}
