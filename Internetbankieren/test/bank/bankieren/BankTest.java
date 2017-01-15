/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas
 */
public class BankTest {
    
    private Bank bank;
    public BankTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bank = new Bank("Rabobank");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openRekening method, of class Bank.
     * with 2 empty values so it returns -1
     */
    @Test
    public void testOpenRekening1() {
        System.out.println("openRekening1");
        ///setting null
        String name = "";
        String city = "";
        /// it is supposed to return -1 after both values are empty
        int expResult = -1;
        int result = bank.openRekening(name, city);
        assertEquals(expResult, result);
    }   
    /**
     * Test of openRekening method, of class Bank.
     * with 1 empty value so it returns -1
     */
    @Test
    public void testOpenRekening2() {
        System.out.println("openRekening2");
        ///setting Thomas
        String name = "Thomas";
        String city = "";
        /// it is supposed to return -1 after 1 value is empty
        int expResult = -1;
        int result = bank.openRekening(name, city);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of openRekening method, of class Bank.
     * with 1 empty value so it returns -1
     */
    @Test
    public void testOpenRekening3() {
        System.out.println("openRekening3");
        ///setting Thomas
        String name = "";
        String city = "Eindhoven";
        /// it is supposed to return -1 after 1 value is empty
        int expResult = -1;
        int result = bank.openRekening(name, city);
        assertEquals(expResult, result);
    }
    /**
     * Test of openRekening method, of class Bank.
     * with correct inputs so wil return 1
     */
    @Test
    public void testOpenRekening4() {
        System.out.println("openRekening4");
        ///setting Thomas
        String name = "Thomas";
        String city = "Eindhoven";
        /// it is supposed to return 1 cause its the first one created
        int expResult = 1;
        int result = bank.openRekening(name, city);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRekening method, of class Bank.
     * if nr is null the method will return null
     */
    @Test
    public void testGetRekening1() {
        System.out.println("getRekening1");
        int nr = 0;
        IRekening expResult = null;
        IRekening result = bank.getRekening(nr);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getRekening method, of class Bank.
     * if nr is 1 the method will return an IRekening that we just created
     */
    @Test
    public void testGetRekening2() {
        System.out.println("getRekening1");
        int nr = 1;
        bank.openRekening("Thomas", "Eindhoven");
        IRekening result = bank.getRekening(nr);
        assertEquals(nr, result.getNr());
    }
    

    /**
     * Test of maakOver method, of class Bank.
     */
    @Test
    public void testMaakOver() throws Exception {
        System.out.println("maakOver");
        int source = 0;
        int destination = 0;
        Money money = null;
        Bank instance = null;
        boolean expResult = false;
        boolean result = instance.maakOver(source, destination, money);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName1");
        Bank instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
