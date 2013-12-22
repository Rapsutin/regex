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
    
}