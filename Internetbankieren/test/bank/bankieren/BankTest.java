/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.bankieren;

import fontys.util.NumberDoesntExistException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.lang.RuntimeException;

import static org.junit.Assert.*;

/**
 *
 * @author Thomas
 */
public class BankTest {

    private Bank bank;
    private Money money;

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
     * Test of openRekening method, of class Bank. with 2 empty values so it
     * returns -1
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
     * Test of openRekening method, of class Bank. with 1 empty value so it
     * returns -1
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
     * Test of openRekening method, of class Bank. with 1 empty value so it
     * returns -1
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
     * Test of openRekening method, of class Bank. with correct inputs so wil
     * return 1
     */
    @Test
    public void testOpenRekening4() {
        System.out.println("openRekening4");
        ///setting Thomas
        String name = "Thomas";
        String city = "Eindhoven";
        /// it is supposed to return 100000000 cause its the first one created
        int expResult = 100000000;
        int result = bank.openRekening(name, city);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRekening method, of class Bank. if nr is null the method will
     * return null
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
     * Test of getRekening method, of class Bank. if nr is 1 the method will
     * return an IRekening that we just created
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
     *
     * @throws java.lang.Exception
     * @throws fontys.util.NumberDoesntExistException
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOver1() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver1");
        money = new Money(10, "€");
        int source = 1;
        int destination = 1;
        bank.maakOver(source, destination, money);
    }

    /**
     * method that checks if the method throws an runtime exception when there
     * is not entered a positive number in the money paramater
     *
     * @throws RuntimeException
     * @throws NumberDoesntExistException
     */
    @Test(expected = RuntimeException.class)
    public void testMaakOver2() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver2");
        money = new Money(0, "€");
        int source = 1;
        int destination = 2;
        bank.maakOver(source, destination, money);
    }

    /**
     * there is no source for this method so it throws a number does not excist
     * exception
     *
     * @throws RuntimeException
     * @throws NumberDoesntExistException
     */
    @Test(expected = NumberDoesntExistException.class)
    public void testMaakOver3() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver3");
        money = new Money(10, "€");
        int source = 1;
        int destination = 2;
        bank.maakOver(source, destination, money);
    }

    /**
     *
     * @throws RuntimeException
     * @throws NumberDoesntExistException
     */
    @Test
    public void testMaakOver4() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver4");
        money = new Money(-10, "€");
        int source = bank.openRekening("test1", "eindhoven");
        int destination = bank.openRekening("test2", "eindhoven");
        assertTrue(bank.maakOver(source, destination, money));
    }

    @Test
    public void testMaakOver5() throws RuntimeException, NumberDoesntExistException {
        System.out.println("maakOver5");
        money = new Money(10, "€");
        //test1
        int source = bank.openRekening("test1", "eindhoven");
        System.out.println(source);
        //test2
        int destination = bank.openRekening("test2", "eindhoven");
        System.out.println(destination);
        System.out.println(bank.maakOver(source, destination, money));
        assertTrue(bank.maakOver(source, destination, money));
    }

    /**
     * Test of getName method, of class Bank.
     */
    @Test
    public void testGetName1() {
        System.out.println("getName1");
        String expResult = "Rabobank";
        String result = bank.getName();
        assertEquals(expResult, result);
    }

}
