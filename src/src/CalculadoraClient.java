/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author kelvin
 */
public class CalculadoraClient {
    
    
    
    
    public static void main(String[] args) throws NotBoundException {
        try{
        Calculadora c = (Calculadora) Naming.lookup("rmi://127.0.0.1:1099/CalculadoraService");
        System.out.println("Adição: " + c.add(10, 10));
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(CalculadoraClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
