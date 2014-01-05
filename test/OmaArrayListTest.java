/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    
    
    
}