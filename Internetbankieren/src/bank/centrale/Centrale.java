/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.centrale;

import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author koenv
 */
public class Centrale extends UnicastRemoteObject implements ICentrale {

    final Map<Integer, String> rekeningen;
    private final Map<String, IBankCentrale> banken;
    //private final long THRESHOLD = 1000000000;//private final long THRESHOLD = 1000000000;

    /**
     *
     * @throws RemoteException
     */

    public Centrale() throws RemoteException {
        rekeningen = new HashMap<>();
        banken = new HashMap<>();

    }

    /**
     *
     * @param bankNaam
     * @param bank
     * @throws RemoteException
     */
    @Override
    public void registreerBank(String bankNaam, IBankCentrale bank) throws RemoteException {
        banken.put(bankNaam, bank);
    }

    /**
     *
     * @param source
     * @param destination
     * @param amount
     * @return
     * @throws RemoteException
     * @throws NumberDoesntExistException
     */
    @Override
    public boolean maakOver(int source, int destination, Money amount) throws RemoteException, NumberDoesntExistException {
        IBankCentrale src = getBank(source);
        IBankCentrale dst = getBank(destination);
        Money negative = Money.difference(new Money(0, amount.getCurrency()), amount);
        boolean success = src.muteer(source, negative);
        if (!success) return false;

        success = dst.muteer(destination, amount);

        if (!success) { // rollback
            src.muteer(source, amount);
        }

        return success;
    }

    @Override
    public synchronized int getRekNr(String bankName) throws RemoteException {
        
        for (int i = 100000000;; i++) {
            if (!rekeningen.containsKey(i)) {
                rekeningen.put(i, bankName);
                System.out.println(i);
                return i;
            }
        }
    }
    /**
     * haal de bank op waar het speciale rekeningnr bijhoort
     * @param rek
     * @return
     * @throws NumberDoesntExistException 
     */
    public IBankCentrale getBank(int rek) throws NumberDoesntExistException {
        String bankName = rekeningen.get(rek);
        if (bankName.equals("")) throw new NumberDoesntExistException("Rekening: " + rek + " is onbekend");
        return banken.get(bankName);
    }

}
