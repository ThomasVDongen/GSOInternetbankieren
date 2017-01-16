/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.centrale;

import bank.bankieren.Money;
import fontys.util.NumberDoesntExistException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Thomas
 */
public interface IBankCentrale extends Remote {
    
    public boolean NaarCentrale(int RekeningNR, Money saldo) throws RemoteException, NumberDoesntExistException;

    String getName() throws RemoteException;
}
