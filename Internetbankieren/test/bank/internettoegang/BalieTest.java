/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.internettoegang;

import bank.bankieren.Bank;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author koenv
 */
public class BalieTest {

    public Balie instance;
    public Bank bank;

    public BalieTest() throws RemoteException {
        bank = new Bank("testBank");
        bank.openRekening("koen", "Hapert");
        instance = new Balie(bank);
    }

    @BeforeClass
    public static void setUpClass(){

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

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekening() {
        System.out.println("openRekening");
        String naam = "koen";
        String plaats = "hapert";
        String wachtwoord = "test123";

        String result = instance.openRekening(naam, plaats, wachtwoord);

        if (result != null) {
            return;
        }
        fail("Method didn't return expected string");
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekeningFailName() {
        System.out.println("openRekening");
        String naam = "";
        String plaats = "hapert";
        String wachtwoord = "test123";

        String result = instance.openRekening(naam, plaats, wachtwoord);

        if (result == null) {
            return;
        }
        fail("Method returned something while it shouldn't");
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekeningFailPlaats() {
        System.out.println("openRekening");
        String naam = "koen";
        String plaats = "";
        String wachtwoord = "test123";

        String result = instance.openRekening(naam, plaats, wachtwoord);

        if (result == null) {
            return;
        }
        fail("Method returned something while it shouldn't");
    }

    /**
     * Test of openRekening method, of class Balie.
     */
    @Test
    public void testOpenRekeningFailPassword() {
        System.out.println("openRekening");
        String naam = "koen";
        String plaats = "hapert";
        String wachtwoord = "";

        String result = instance.openRekening(naam, plaats, wachtwoord);

        if (result == null) {
            return;
        }
        fail("Method returned something while it shouldn't");
    }

    /**
     * Test of logIn method, of class Balie.
     */
    @Test
    public void testLogIn() throws Exception {
        System.out.println("logIn");
        String accountnaam = "koen";
        String wachtwoord = "test123";
        if (instance.logIn(accountnaam, wachtwoord) != null) return;
        // TODO review the generated test code and remove the default call to fail.
        fail("The user didn't retrieve a session");
    }
    
    @Test
    public void testLogInFail() throws Exception {
        System.out.println("logIn");
        String accountnaam = "";
        String wachtwoord = "test123";
        if (instance.logIn(accountnaam, wachtwoord) == null) return;
        // TODO review the generated test code and remove the default call to fail.
        fail("The user retrieved a session erroniously");
    }

}
