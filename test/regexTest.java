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
import regex.KaanteinenNotaatio;

/**
 *
 * @author Juho
 */
public class regexTest {
    
   
    
    public regexTest() {
        
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
    
    
    
   @Test
   public void toimiiko_automaatti1() {
       Automaatti automaatti = new Automaatti('a');
       automaatti.getAlkutila().muutaAktiiviseksi();
       automaatti.annaSyote('a');
       
       assertTrue(!automaatti.getAlkutila().onAktiivinen());
       assertTrue(automaatti.getLopputila().onAktiivinen());
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