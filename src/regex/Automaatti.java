/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;

/**
 * Epädeterministinen äärellinen automaatti.
 * @author Juho
 */
public class Automaatti {
    
    private List<Tila> tilat;
    private Tila alkutila;
    private Tila lopputila;
    
    
    public Automaatti(List<Tila> tilat, Tila alkutila, Tila lopputila) {
        this.tilat = tilat;
        this.alkutila = alkutila;
        this.lopputila = lopputila;
    }
    
    /**
     * Luo kaksitilaisen automaatin, joka hyväksyy ainoastaan yhden
     * syötteen.
     * @param hyvaksyttySyote Syöte, jonka automaatti hyväksyy.
     * @return Automaatti-olio.
     */
    public static Automaatti luoKirjainautomaatti (Character hyvaksyttySyote) {
        List<Tila> automaatinTilat = new ArrayList<>();
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();
        automaatinTilat.add(automaatinAlkutila);
        automaatinTilat.add(automaatinLopputila);
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, hyvaksyttySyote));
        return new Automaatti(automaatinTilat, automaatinAlkutila, automaatinLopputila);
    }
    
    /**
     * Luo automaatin, jossa on
     * kaksi automaattia "sarjassa."
     * @param edeltava Automaatin ensimmäinen osa.
     * @param jalkimmainen Automaatin jälkimmäinen osa.
     * @return Automaatti-olio.
     */
    public static Automaatti luoLiitosautomaatti(Automaatti edeltava, Automaatti jalkimmainen) {
        List <Tila> automaatinTilat = new ArrayList<>();
        Tila automaatinAlkutila = edeltava.getAlkutila();
        Tila automaatinLopputila = jalkimmainen.getLopputila();
        automaatinTilat.addAll(edeltava.getTilat());
        automaatinTilat.addAll(jalkimmainen.getTilat());
        edeltava.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(jalkimmainen.getAlkutila(), null));
        
        return new Automaatti(automaatinTilat, automaatinAlkutila, automaatinLopputila);
    }
    
    /**
     * Luo automaatin, jossa on
     * kaksi automaattia "rinnan."
     * Automaatti hyväksyy syötteen, jonka
     * jompi kumpi automaateista hyväksyy.
     * @param joko Ensimmainen vaihtoehto
     * @param tai Toinen vaihtoehto
     * @return Automaatti-olio.
     */
    public static Automaatti luoTaiautomaatti(Automaatti joko, Automaatti tai) {
        List<Tila> automaatinTilat = new ArrayList<>();
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();
        
        automaatinTilat.add(automaatinAlkutila);
        automaatinTilat.add(automaatinLopputila);
        automaatinTilat.addAll(joko.getTilat());
        automaatinTilat.addAll(tai.getTilat());
        
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(joko.getAlkutila(), null));
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(tai.getAlkutila(), null));
        joko.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        tai.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        
        return new Automaatti(automaatinTilat, automaatinAlkutila, automaatinLopputila);
   }
    
    /**
     * Antaa automaatille syötteen
     * ja käsittelee aktiivisten tilojen
     * siirtofunktiot.
     * @param syote Annettava syöte.
     */
    public void annaSyote(Character syote) {
        List<Tila> aktiivisetTilat = new ArrayList<>();
        lisaaAktiiviset(aktiivisetTilat);
        kutsuMerkittomiaFunktioita(aktiivisetTilat, syote);
        kutsuFunktioita(aktiivisetTilat, syote);
        kutsuMerkittomiaFunktioita(aktiivisetTilat, syote);
    }
    
    
    public void annaSyote(String syote) {
        for (int i = 0; i < syote.length(); i++) {
            annaSyote(syote.charAt(i));
        }
    }
    
     private void lisaaAktiiviset(List<Tila> aktiivisetTilat) {
        for(Tila t : tilat) {
            if(t.onAktiivinen() && !aktiivisetTilat.contains(t)) {
                aktiivisetTilat.add(t);
            }
        }
    }
     
      private void kutsuMerkittomiaFunktioita(List<Tila> aktiivisetTilat, Character syote) {
          int aktiivisiaTiloja = 0;
          while(aktiivisetTilat.size() != aktiivisiaTiloja) {
              aktiivisiaTiloja = aktiivisetTilat.size();
              for(Tila t : aktiivisetTilat) {
                t.kutsuMerkittomiaSiirtofunktioita();
              }
              lisaaAktiiviset(aktiivisetTilat);
          }
    }

    private void kutsuFunktioita(List<Tila> aktiivisetTilat, Character syote) {
        for(Tila t : aktiivisetTilat) {
            t.kutsuSiirtofunktioita(syote);
        }
    }

  
    
    public void muutaKaikkiTilatEpaaktiivisiksi() {
        for(Tila t : tilat) {
            t.muutaEpaaktiiviseksi();
        }
    }
    
    
    
    /**
     * Lisää automaattiin tilan.
     * @param lisattava Lisättävä tila.
     */
    public void lisaaTila(Tila lisattava) {
        tilat.add(lisattava);
    }
    
    /**
     * 
     * @return Kaikki automaatin tilat.
     */
    public List<Tila> getTilat() {
        return tilat;
    }
    
    /**
     * 
     * @return Alkutila.
     */
    public Tila getAlkutila() {
        return alkutila;
    }

    public Tila getLopputila() {
        return lopputila;
    }
    
     public void setAlkutila(Tila alkutila) {
        this.alkutila = alkutila;
    }

    public void setLopputila(Tila lopputila) {
        this.lopputila = lopputila;
    }

   
    
    
    
    
    
    
}
