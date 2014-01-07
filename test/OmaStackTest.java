/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.OmaStack;
import static org.junit.Assert.*;
/**
 *
 * @author Juho
 */
public class OmaStackTest {
    private OmaStack<Integer> pino;
    
    public OmaStackTest() {
       
    }
    
    
    @Before
    public void setUp() {
        pino = new OmaStack<>();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaa_pinoa_pienella_maaralla() {
        pino.push(3);
        pino.push(2);
        pino.push(1);
        for (int i = 1; i <= 3; i++) {
            assertTrue(pino.pop() == i);
        }
    }
    
    @Test
    public void testaa_pinoa_suurella_maaralla() {
        for (int i = 0; i < 100000; i++) {
            pino.push(i);
        }
        for (int i = 99999; i >= 0; i--) {
            assertTrue(pino.pop() == i);
        }
    }
    
    @Test
    public void isEmpty_toimii() {
        assertTrue(pino.isEmpty());
        pino.push(1);
        assertTrue(!pino.isEmpty());
        pino.pop();
        assertTrue(pino.isEmpty());
    }
    
    @Test
    public void peek_toimii() {
        pino.push(1);
        assertTrue(pino.peek() == 1);
        assertTrue(!pino.isEmpty());
    }
}