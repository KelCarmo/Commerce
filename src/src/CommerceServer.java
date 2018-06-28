/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelvin
 */
public class CommerceServer {
        
    
    public CommerceServer(ManagerFiles manager, String ip, ArrayList<Produto> produtos) throws RemoteException, UnknownHostException {
        try {            
            System.setProperty("java.rmi.server.hostname", ip);
            LocateRegistry.createRegistry(1099);
            Commerce c = new CommerceImple(manager, produtos);
            Naming.bind("CommerceService", (Remote) c);
            System.out.println("Passei aqui");
        } catch (AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(CommerceServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void main(String[] args) throws RemoteException, UnknownHostException{
         
     }
    
}
