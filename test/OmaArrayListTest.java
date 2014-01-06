/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.OmaArrayList;
import static org.junit.Assert.*;
/**
 *
 * @author Juho
 */
public class OmaArrayListTest {
    private OmaArrayList<Integer> testattava;
    
    public OmaArrayListTest() {
    }
    
    
    @Before
    public void setUp() {
        testattava = new OmaArrayList<>();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void tallentaako_yhden_alkion() {
        testattava.add(1);
        assertTrue(testattava.get(0) == 1);
    }
    
    @Test
    public void tallentaako_yli_kymmenen_alkiota() {
        for (int i = 0; i < 14; i++) {
            testattava.add(i);
        }
        for (int i = 0; i < 14; i++) {
            assertTrue(i == testattava.get(i));
        }
    }
    
    @Test
    public void poistaminen_toimii() {
        testattava.add(1);
        testattava.add(2);
        testattava.add(3);
        testattava.poista(0);
        assertTrue(testattava.get(0) == 2);
        assertTrue(testattava.get(1) == 3);
        testattava.poista(1);
        assertTrue(testattava.get(0) == 2);
        assertTrue(testattava.getAlkioidenMaara() == 1);
        
    }
    
    
}