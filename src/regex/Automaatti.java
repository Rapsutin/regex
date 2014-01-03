/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Epädeterministinen äärellinen automaatti.
 * @author Juho
 */
public class Automaatti {

    
    private List<Tila> tilat;
    private Tila alkutila;
    private Tila lopputila;
    
    
    private Automaatti(List<Tila> tilat, Tila alkutila, Tila lopputila) {
        this.tilat = tilat;
        this.alkutila = alkutila;
        this.lopputila = lopputila;
    }
    
    /**
     * Luo automaatin annetun tavallisessa muodossa
     * olevan säännöllisen lausekkeen perusteella.
     * @param infix Tavallisessa muodossa oleva säännöllinen lauseke.
     * @return 
     */
    public static Automaatti luoAutomaattiRegexista(String infix) {
        String postfix = KaanteinenNotaatio.muunnaKaanteiseksi(infix);
        Stack automaatit = new Stack();
        for (int i = 0; i < postfix.length(); i++) {
            kasitteleMerkki(postfix.charAt(i), automaatit);
        }
        return (Automaatti) automaatit.firstElement();
        
    }
    
    
    private static void kasitteleMerkki(char merkki, Stack automaatit) {
        if(Character.isLetter(merkki)) {
            automaatit.push(luoKirjainautomaatti(merkki));
        } else if(merkki == '|') {
            kasitteleTai(automaatit);
        } else if(merkki == '¤') {
            kasitteleLiitos(automaatit);
        } else if(merkki == '*') {
            kasitteleTahti(automaatit);
        }
    }
    
    private static void kasitteleTai(Stack automaatit) {
        Automaatti toinen = (Automaatti) automaatit.pop();
        Automaatti ensimmainen = (Automaatti) automaatit.pop();
        automaatit.push(luoTaiautomaatti(toinen, ensimmainen));
    }
    private static void kasitteleLiitos(Stack automaatit) {
        Automaatti toinen = (Automaatti) automaatit.pop();
        Automaatti ensimmainen = (Automaatti) automaatit.pop();
        automaatit.push(luoLiitosautomaatti(ensimmainen, toinen));
    }
    private static void kasitteleTahti(Stack automaatit) {
        Automaatti toistuva = (Automaatti) automaatit.pop();
        automaatit.push(luoTahtiautomaatti(toistuva));
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
     * Luo automaatin, joka hyväksyy syötteen,
     * jonka alkuperäinen automaatti hyväksyisi 
     * 0-ääretön kertaa.
     * @param toistuva Toistuva automaatti
     * @return Automaatti-olio.
     */
    public static Automaatti luoTahtiautomaatti(Automaatti toistuva) {
        List<Tila> automaatinTilat = new ArrayList<>();
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();
        
        automaatinTilat.add(automaatinAlkutila);
        automaatinTilat.add(automaatinLopputila);
        automaatinTilat.addAll(toistuva.getTilat());
        
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(toistuva.alkutila, null));
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        toistuva.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(toistuva.getAlkutila(), null));
        toistuva.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        
        
        return new Automaatti(automaatinTilat, automaatinAlkutila, automaatinLopputila);
    }
    
   
    
    /**
     * Antaa automaatille syötejonon.
     * @param  Syötejono.
     */
    public boolean annaSyote(String syote) {
        List<Tila> nykyiset = new ArrayList<>();
        List<Tila> seuraavat = new ArrayList<>();
        lisaaSeuraaviin(alkutila, seuraavat);
        nykyiset = seuraavat;
        seuraavat = new ArrayList<>();
        
        for (int i = 0; i < syote.length(); i++) {
            char kirjain = syote.charAt(i);
            kayLapiSiirtofunktiot(nykyiset, seuraavat, kirjain);
            nykyiset = seuraavat;
            seuraavat = new ArrayList<>();
        }
        return nykyiset.contains(lopputila);
    }
    
    public void kayLapiSiirtofunktiot(List<Tila> nykyiset, List<Tila> seuraavat, char kirjain) {
        for(Tila t : nykyiset) {
            for(Siirtofunktio s : t.getSiirtofunktiot()) {
                if(s.getHyvaksyttySyote() == null) {
                    continue;
                }
                if(s.getHyvaksyttySyote() == kirjain) {
                    lisaaSeuraaviin(s.getLopputila(), seuraavat);
                }
            }
        }
    }
    
    public void lisaaSeuraaviin(Tila lisattava, List<Tila> seuraavat) {
        if(seuraavat.contains(lisattava)) {
            return;
        }
        seuraavat.add(lisattava);
        for(Siirtofunktio s : lisattava.getSiirtofunktiot()) {
            if(s.getHyvaksyttySyote() == null) {
                lisaaSeuraaviin(s.getLopputila(), seuraavat);
            }
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
    
   /**
    * Testausmielessä luotu metodi.
    * Ei mitään merkitystä ohjelman toiminnan
    * kannalta. Siirtofunktiot muotoa (syöte)->lopputila.
    * @param automaatti Tulostettava automaatti.
    */
    public void tulostaAutomaatti() {
        Automaatti automaatti = this;
        for (int i = 0; i < automaatti.getTilat().size(); i++) {
            Tila tulostettava = automaatti.getTilat().get(i);

            System.out.print(i + ".");
            for (int j = 0; j < tulostettava.getSiirtofunktiot().size(); j++) {
                Siirtofunktio kasiteltava = tulostettava.getSiirtofunktiot().get(j);
                System.out.print(" (" + kasiteltava.getHyvaksyttySyote() + ")->" + automaatti.getTilat().indexOf(kasiteltava.getLopputila()));
            }
            
            if (tulostettava.equals(automaatti.lopputila)) {
                System.out.print(" LOPPU");
            }
            if (tulostettava.equals(automaatti.alkutila)) {
                System.out.print(" ALKU");
            }
            System.out.println("");
        }
    }
    
    
    
    
    
    
}
