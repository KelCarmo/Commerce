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
public class CommerceClient {
    
    
    
    
    public static void main(String[] args) throws NotBoundException {
        try{
        Commerce c = (Commerce) Naming.lookup("rmi://192.168.0.103:1099/CalculadoraService");
        System.out.println("Adição: " + c.add(10, 10));
        } catch (RemoteException | MalformedURLException ex) {
            //Logger.getLogger(CommerceClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Deu erro");
        }
    }
    
}
