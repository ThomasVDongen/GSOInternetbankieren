package bank.centrale;

import bank.bankieren.Money;
import bank.centrale.IBankCentrale;
import fontys.util.NumberDoesntExistException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICentrale extends Remote {

    void registreerBank(String bankName, IBankCentrale bank) throws RemoteException;

    boolean maakOver(int source, int destination, Money amount) throws RemoteException, NumberDoesntExistException;

    int getRekNr(String bankNaam) throws RemoteException;
}
