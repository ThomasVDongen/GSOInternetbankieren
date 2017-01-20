package bank.bankieren;

import bank.centrale.IBankCentrale;
import bank.centrale.ICentrale;
import fontys.util.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank extends UnicastRemoteObject implements IBank, IBankCentrale {

    private static final long serialVersionUID = -8728841131739353765L;
    private Map<Integer, IRekeningTbvBank> accounts;
    private Collection<IKlant> clients;
    private int nieuwReknr;
    private String name;
    private ReentrantLock rl;
    private ICentrale cs;
    private IRekeningTbvBank account;

    /**
     *
     * @param name
     * @param centrale
     * @throws java.rmi.RemoteException
     */
    public Bank(String name, ICentrale centrale) throws RemoteException {
        accounts = new HashMap<Integer, IRekeningTbvBank>();
        clients = new ArrayList<IKlant>();
        this.name = name;
        this.cs = centrale;
        rl = new ReentrantLock();
        cs.registreerBank(name, this);
        
    }

    @Override
    public int openRekening(String name, String city) {
        if (name.equals("") || city.equals("")) {
            return -1;
        }
        IKlant klant = getKlant(name, city);
        try {
            account = new Rekening(cs.getRekNr(this.getName()), klant, Money.EURO);
            accounts.put(account.getNr(), account);
        } catch (RemoteException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        rl.lock();

        //Acquire lock, safely increment nieuwReknr
        try {
            System.out.println(account);
        } finally {
            rl.unlock();
        }

        return account.getNr();
    }

    private IKlant getKlant(String name, String city) {
        for (IKlant k : clients) {
            if (k.getNaam().equals(name) && k.getPlaats().equals(city)) {
                return k;
            }
        }
        IKlant klant = new Klant(name, city);
        clients.add(klant);
        return klant;
    }

    @Override
    public IRekening getRekening(int nr) {
        return accounts.get(nr);
    }

    @Override
    public boolean maakOver(int source, int destination, Money money)
            throws NumberDoesntExistException, RemoteException {
        return cs.maakOver(source, destination, money);
        
        /*if (source == destination) {
            throw new RuntimeException(
                    "cannot transfer money to your own account");
        }
        if (!money.isPositive()) {
            throw new RuntimeException("money must be positive");
        }

        IRekeningTbvBank source_account = (IRekeningTbvBank) getRekening(source);
        if (source_account == null) {
            throw new NumberDoesntExistException("account " + source
                    + " unknown at " + name);
        }

        Money negative = Money.difference(new Money(0, money.getCurrency()),
                money);
        boolean success = source_account.muteer(negative);
        if (!success) {
            return false;
        }

        IRekeningTbvBank dest_account = (IRekeningTbvBank) getRekening(destination);
        if (dest_account == null) {
            throw new NumberDoesntExistException("account " + destination
                    + " unknown at " + name);

        }

        rl.lock();
        try {
            success = dest_account.muteer(money);

            if (!success) // rollback
            {
                source_account.muteer(money);
            }

        } catch (Exception ex) {
            success = false;
        } finally {
            rl.unlock();
        }
        return success;
*/
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean muteer(int rekeningNR, Money saldo) throws RemoteException, NumberDoesntExistException {
        IRekeningTbvBank rekening = (IRekeningTbvBank) getRekening(rekeningNR);
        if (rekening != null) {
            return rekening.muteer(saldo);
            
        }
        return false;
    }
}

