/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelvin
 */
public class CommerceImple extends UnicastRemoteObject implements Commerce {

    
    private ManagerFiles manager;
    private ArrayList<Produto> produtos;
    
    protected CommerceImple(ManagerFiles manager, ArrayList<Produto> produtos) throws RemoteException {
        super();
        this.manager = manager;
        this.produtos = produtos;
    }       
    
    @Override
    public long add(long a, long b) throws RemoteException {
        return a + b;
    }

    @Override
    public String sub(int qtd, String idProd , int index) throws RemoteException {
        System.out.println("Esperando ...");       
        this.produtos.get(index).setQtd(qtd);
        System.out.println("Terminado!");
        try {
            this.manager.writeWordsCommerce(this.produtos, "LojaA");
        } catch (IOException ex) {
            Logger.getLogger(CommerceImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
           
    }

    @Override
    synchronized public boolean isOn() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int returnEstoque(String idProd, int index) throws RemoteException{
        return this.produtos.get(index).getQtd();
    }

    @Override
    public ArrayList<String> returnPendencias(String servidor, String idProd, int index) throws RemoteException {
        try {
            return this.manager.returnPendencias(servidor, idProd, index);
        } catch (IOException ex) {
            Logger.getLogger(CommerceImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
