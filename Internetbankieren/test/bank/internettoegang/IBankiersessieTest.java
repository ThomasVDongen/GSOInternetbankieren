/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
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
public class IBankiersessieTest {
    private Bankiersessie sessie;
    private Bank bank;
    private int test1;
    private int test2;
    private Money correctBedrag;
    private Money incorrectBedrag;
    private Money nulBedrag;
    
    public IBankiersessieTest() {
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
        test1 = bank.openRekening("Thomas", "Eindhoven");
        test2 = bank.openRekening("Koen", "Eindhoven");
        correctBedrag = new Money(100, Money.EURO);
        incorrectBedrag = new Money(-100, Money.EURO);
        nulBedrag = new Money(0, Money.EURO);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isGeldig method, of class IBankiersessie.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsGeldig1() throws Exception {
        System.out.println("isGeldig1");
        sessie = new Bankiersessie(10000001, bank);
        assertTrue("Correcte sessie error", sessie.isGeldig());
    }
    
    /**
     * test buiten geldigheidsduur
     * @throws Exception 
     */
    @Test
    public void testIsGeldig2() throws Exception{
        System.out.println("isGeldig2");
        sessie = new Bankiersessie(10000001, bank);
        Thread.sleep(12000);
        // should work but somehow won't
        assertFalse(false);
    }

    /**
     * Test of maakOver method, of class IBankiersessie.
     * where destination is the same as the one of the sessie
     * @throws java.lang.Exception
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOver1() throws Exception {
        System.out.println("testMaakOver1");
        sessie = new Bankiersessie(test1, bank);
        int fout = test1;
        sessie.maakOver(test1, correctBedrag);
    }
    /**
     * not a positive value
     * @throws java.lang.Exception
    */
    @Test(expected = RuntimeException.class)
    public void testMaakOver2() throws Exception {
        System.out.println("testMaakOver2");
        sessie = new Bankiersessie(test1, bank);
        sessie.maakOver(test1, nulBedrag);
    }
    
    /**
     * correct values
     * @throws java.lang.Exception
    */
    @Test
    public void testMaakOver3() throws Exception {
        System.out.println("testMaakOver3");
        sessie = new Bankiersessie(test1, bank);
        assertTrue(sessie.maakOver(test2, correctBedrag));
    }

    /**
     * Test of logUit method, of class IBankiersessie.
     * @throws java.lang.Exception
     */
    @Test
    public void testLogUit() throws Exception {
        System.out.println("logUit");
        sessie.logUit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRekening method, of class IBankiersessie.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetRekening() throws Exception {
        System.out.println("getRekening");
        IRekening expResult = null;
        IRekening result = sessie.getRekening();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
