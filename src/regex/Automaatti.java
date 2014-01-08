/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import tietorakenteet.OmaArrayList;

import tietorakenteet.OmaStack;


/**
 * Epädeterministinen äärellinen automaatti.
 * @author Juho
 */
public class Automaatti {
    private Tila alkutila;
    private Tila lopputila;
    
    private Automaatti(Tila alkutila, Tila lopputila) {
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
        OmaStack automaatit = new OmaStack();
        for (int i = 0; i < postfix.length(); i++) {
            kasitteleMerkki(postfix.charAt(i), automaatit);
        }
        return (Automaatti) automaatit.firstElement();
        
    }
    
    /**
     * Valitsee oikean toiminnon
     * syötteen merkin mukaan.
     * @param merkki Käsiteltävä merkki.
     * @param automaatit Pinossa olevat automaatit.
     */
    private static void kasitteleMerkki(char merkki, OmaStack automaatit) {
        if(Character.isLetter(merkki)) {
            automaatit.push(luoKirjainautomaatti(merkki));
        } else if(merkki == '|') {
            kasitteleTai(automaatit);
        } else if(merkki == '¤') {
            kasitteleLiitos(automaatit);
        } else if(merkki == '*') {
            kasitteleTahti(automaatit);
        } else if(merkki == '+') {
            kasittelePlus(automaatit);
        }
    }
    
    private static void kasitteleTai(OmaStack automaatit) {
        Automaatti toinen = (Automaatti) automaatit.pop();
        Automaatti ensimmainen = (Automaatti) automaatit.pop();
        automaatit.push(luoTaiautomaatti(toinen, ensimmainen));
    }
    private static void kasitteleLiitos(OmaStack automaatit) {
        Automaatti toinen = (Automaatti) automaatit.pop();
        Automaatti ensimmainen = (Automaatti) automaatit.pop();
        automaatit.push(luoLiitosautomaatti(ensimmainen, toinen));
    }
    private static void kasitteleTahti(OmaStack automaatit) {
        Automaatti toistuva = (Automaatti) automaatit.pop();
        automaatit.push(luoTahtiautomaatti(toistuva));
    }
    private static void kasittelePlus(OmaStack automaatit) {
        Automaatti toistuva = (Automaatti) automaatit.pop();
        automaatit.push(luoPlusautomaatti(toistuva));
    }
    
    /**
     * Luo kaksitilaisen automaatin, joka hyväksyy ainoastaan yhden
     * syötteen.
     * @param hyvaksyttySyote Syöte, jonka automaatti hyväksyy.
     * @return Automaatti-olio.
     */
    public static Automaatti luoKirjainautomaatti (Character hyvaksyttySyote) {
       
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();
        
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, hyvaksyttySyote));
        return new Automaatti(automaatinAlkutila, automaatinLopputila);
    }
    
    /**
     * Luo automaatin, jossa on
     * kaksi automaattia "sarjassa."
     * @param edeltava Automaatin ensimmäinen osa.
     * @param jalkimmainen Automaatin jälkimmäinen osa.
     * @return Automaatti-olio.
     */
    public static Automaatti luoLiitosautomaatti(Automaatti edeltava, Automaatti jalkimmainen) {
        Tila automaatinAlkutila = edeltava.getAlkutila();
        Tila automaatinLopputila = jalkimmainen.getLopputila();
        edeltava.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(jalkimmainen.getAlkutila(), null));
        
        return new Automaatti(automaatinAlkutila, automaatinLopputila);
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
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();

        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(joko.getAlkutila(), null));
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(tai.getAlkutila(), null));
        joko.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        tai.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));

        return new Automaatti(automaatinAlkutila, automaatinLopputila);
    }
    
    /**
     * Luo automaatin, joka hyväksyy syötteen,
     * jonka alkuperäinen automaatti hyväksyisi 
     * 0-ääretön kertaa.
     * @param toistuva Toistuva automaatti.
     * @return Automaatti-olio.
     */
    public static Automaatti luoTahtiautomaatti(Automaatti toistuva) {
        Tila automaatinAlkutila = new Tila();
        Tila automaatinLopputila = new Tila();
        
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(toistuva.alkutila, null));
        automaatinAlkutila.lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        toistuva.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(toistuva.getAlkutila(), null));
        toistuva.getLopputila().lisaaSiirtofunktio(new Siirtofunktio(automaatinLopputila, null));
        
        
        return new Automaatti(automaatinAlkutila, automaatinLopputila);
    }
    
    /**
     * Luo automaatin, joka
     * hyväksyy annetun syötteen,
     * jonka alkuperäinen automaatti 
     * hyväksyisi 1-ääretön kertaa.
     * 
     * @param toistuva Toistuva automaatti.
     * @return Automaatti-olio.
     */
    public static Automaatti luoPlusautomaatti(Automaatti toistuva) {
        return luoLiitosautomaatti(toistuva, luoTahtiautomaatti(toistuva));
    }
    
    /**
     * Antaa automaatille syötejonon.
     * @param syote Syötejono.
     */
    public boolean annaSyote(String syote) {
        OmaArrayList<Tila> nykyiset = new OmaArrayList<>();
        OmaArrayList<Tila> seuraavat = new OmaArrayList<>();
        lisaaSeuraaviin(alkutila, seuraavat);
        nykyiset = seuraavat;
        seuraavat = new OmaArrayList<>();
        
        for (int i = 0; i < syote.length(); i++) {
            char kirjain = syote.charAt(i);
            kayLapiSiirtofunktiot(nykyiset, seuraavat, kirjain);
            nykyiset = seuraavat;
            seuraavat = new OmaArrayList<>();
        }
        return nykyiset.contains(lopputila);
    }
    
    private void kayLapiSiirtofunktiot(OmaArrayList<Tila> nykyiset, OmaArrayList<Tila> seuraavat, char kirjain) {
        
        for (int i = 0; i < nykyiset.size(); i++) {
            Tila t = nykyiset.get(i);
            
            for (int j = 0; j < t.getSiirtofunktiot().size(); j++) {
                Siirtofunktio s = t.getSiirtofunktiot().get(j);
                if(s.getHyvaksyttySyote() == null) {
                    continue;
                }
                if(s.getHyvaksyttySyote() == kirjain) {
                    lisaaSeuraaviin(s.getLopputila(), seuraavat);
                }
            }
        }
    }
    
    public void lisaaSeuraaviin(Tila lisattava, OmaArrayList<Tila> seuraavat) {
        if(seuraavat.contains(lisattava)) {
            return;
        }
        seuraavat.add(lisattava);
        
        for (int i = 0; i < lisattava.getSiirtofunktiot().size(); i++) {
            Siirtofunktio s = lisattava.getSiirtofunktiot().get(i);
            if(s.getHyvaksyttySyote() == null) {
                lisaaSeuraaviin(s.getLopputila(), seuraavat);
            }
        }
        
    }
    
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
