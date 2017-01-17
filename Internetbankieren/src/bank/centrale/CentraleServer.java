/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.centrale;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author koenv
 */
public class CentraleServer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Centrale centrale = new Centrale();

            Registry registry = LocateRegistry.createRegistry(1100);
            registry.rebind("centrale", centrale);
            System.out.println("binding gelukt");
        } catch(RemoteException rE) {
            rE.printStackTrace();
        }

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
