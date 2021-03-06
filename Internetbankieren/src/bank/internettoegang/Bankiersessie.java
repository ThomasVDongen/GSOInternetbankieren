package bank.internettoegang;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;

import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;
import fontyspublisher.RemotePublisher;

public class Bankiersessie extends UnicastRemoteObject implements
        IBankiersessie {

    private static final long serialVersionUID = 1L;
    private long laatsteAanroep;
    private int reknr;
    private IBank bank;
    private IRemotePublisherForDomain rp;
    private final String prop = "Bank";

    public Bankiersessie(int reknr, IBank bank, IRemotePublisherForDomain rp) throws RemoteException {
        laatsteAanroep = System.currentTimeMillis();
        this.reknr = reknr;
        this.bank = bank;
        this.rp = rp;
    }

    public boolean isGeldig() {
        return System.currentTimeMillis() - laatsteAanroep < GELDIGHEIDSDUUR;
    }

    @Override
    public boolean maakOver(int bestemming, Money bedrag)
            throws NumberDoesntExistException, InvalidSessionException,
            RemoteException {

        updateLaatsteAanroep();
           
        if (reknr == bestemming) {
            throw new RuntimeException(
                    "source and destination must be different");
        }
        if (!bedrag.isPositive()) {
            throw new RuntimeException("amount must be positive");
        }

        if(bank.maakOver(reknr, bestemming, bedrag))
        {
           this.Update();
           return true;
        }
        
        return false;
    }

    private void updateLaatsteAanroep() throws InvalidSessionException {
        if (!isGeldig()) {

            throw new InvalidSessionException("session has been expired");
        }

        laatsteAanroep = System.currentTimeMillis();
    }

    @Override
    public IRekening getRekening() throws InvalidSessionException,
            RemoteException {

        updateLaatsteAanroep();
        IRekening rekening = bank.getRekening(reknr);

        return rekening;
    }

    @Override
    public void logUit() throws RemoteException {

        UnicastRemoteObject.unexportObject(this, true);
    }

    @Override
    public void Update() throws RemoteException, InvalidSessionException {
        rp.inform(prop, this.getRekening(), this.getRekening());
    }
}
