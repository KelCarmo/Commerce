/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
    public void sub(int qtd, String idProd , int index) throws RemoteException {
        this.produtos.get(index).setQtd(qtd);
        
    }

    @Override
    public boolean isOn() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
