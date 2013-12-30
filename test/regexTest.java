/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import regex.Automaatti;
import regex.Siirtofunktio;
import regex.Tila;
import static org.junit.Assert.*;
import regex.AutomaatinRakentaja;
import regex.KaanteinenNotaatio;

/**
 *
 * @author Juho
 */
public class regexTest {
    
    private Automaatti automaatti;
    private Tila alkutila;
    public regexTest() {
        alkutila = new Tila();
        automaatti = new Automaatti(alkutila);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    public void automaatti1() {
        automaatti.lisaaTila(new Tila());
        alkutila.lisaaSiirtofunktio(new Siirtofunktio(1, 'a', automaatti.getTilat()));
        automaatti.annaSyote('a');
    }
    
    @Test
    public void muuttaako_siirtofunktio_toisen_tilan_aktiiviseksi() {
        automaatti1();
        assertTrue(automaatti.getTilat().get(1).onAktiivinen());
    }
    
    @Test
    public void onko_lahtofunktio_epaaktiivinen() {
        automaatti1();
        assertTrue(!automaatti.getTilat().get(0).onAktiivinen());
    }
    
    @Test
    public void kahden_syotteen_jalkeen_molemmat_epaaktiivisia() {
        automaatti1();
        automaatti.annaSyote('a');
        assertTrue(!automaatti.getTilat().get(0).onAktiivinen());
        assertTrue(!automaatti.getTilat().get(1).onAktiivinen());
    }
    
    @Test
    public void toimivatko_siirtofunktiot_jotka_eivat_tarvitse_syotetta() {
        automaatti1();
        alkutila.muutaAktiiviseksi();
        alkutila.lisaaSiirtofunktio(new Siirtofunktio(alkutila, null));
        
        for (int i = 0; i < 10; i++) {
            automaatti.annaSyote('a');
        }
        assertTrue(alkutila.onAktiivinen());
        assertTrue(automaatti.getTilat().get(1).onAktiivinen());
    }
    
    @Test
    public void lisaako_merkitseLiitokset_liitosmerkin_kirjainten_valiin() {
        assertEquals("a¤b¤a¤a", KaanteinenNotaatio.merkitseLiitokset("abaa"));
        assertEquals("b¤b", KaanteinenNotaatio.merkitseLiitokset("bb"));
    }
    
    @Test
    public void lisaako_merkitseLiitokset_liitosmerkin_kirjaimen_ja_vasemman_sulun_valiin() {
        assertEquals("a¤(b¤a¤a)*", KaanteinenNotaatio.merkitseLiitokset("a(baa)*"));
    }
    
    @Test
    public void lisaako_merkitseLiitokset_liitosmerkin_osan_paattavan_operaattorin_ja_kirjaimen_valiin() {
        assertEquals("(a¤b)*¤a¤a", KaanteinenNotaatio.merkitseLiitokset("(ab)*aa"));
        assertEquals("(a)?¤b", KaanteinenNotaatio.merkitseLiitokset("(a)?b"));
    }
    
    @Test
    public void lisaako_merkitseLiitokset_liitosmerkin_osan_paattavan_operaattorin_ja_vasemman_sulun_valiin() {
        assertEquals("(a¤b)*¤(a)*", KaanteinenNotaatio.merkitseLiitokset("(ab)*(a)*"));
    }
    
    @Test
    public void toimivatko_yksinkertaiset_muunnokset_kaanteiseksi_puolalaiseksi_notaatioksi() {
        assertEquals("ab|", KaanteinenNotaatio.muunnaKaanteiseksi("a|b"));
        assertEquals("ba¤ab¤|", KaanteinenNotaatio.muunnaKaanteiseksi("ba|ab"));
        assertEquals("ba|ab¤|", KaanteinenNotaatio.muunnaKaanteiseksi("b|a|ab"));
    }
    
    @Test
    public void toimivatko_sululliset_muutokset() {
        
        assertEquals("ab¤*", KaanteinenNotaatio.muunnaKaanteiseksi("(ab)*"));
        assertEquals("ab|*a¤", KaanteinenNotaatio.muunnaKaanteiseksi("(a|b)*a"));
        assertEquals("aab¤*|*c¤", KaanteinenNotaatio.muunnaKaanteiseksi("(a|(ab)*)*c"));
    }
    
}