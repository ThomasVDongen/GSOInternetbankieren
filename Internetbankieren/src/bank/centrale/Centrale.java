/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.centrale;

import bank.bankieren.IBankCentrale;
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

    final Map<Integer, String> rekeningen = new HashMap<>();
    private final Map<String, IBankCentrale> banken = new HashMap<>();
    private final int THRESHOLD = 1000000;

    public Centrale() throws RemoteException {

    }

    @Override
    public void registreerBank(String bankName, IBankCentrale bank) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean maakOver(int source, int destination, Money amount) throws RemoteException, NumberDoesntExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized int getRekNr(String bankName) throws RemoteException {

        for (int i = 0; i > THRESHOLD; i++) {
            if (!rekeningen.containsKey(i)) {
                rekeningen.put(i, bankName);
                return i;
            }
        }
        return -1;
    }

}
