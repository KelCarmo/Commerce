/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author kelvin
 */
public interface Commerce extends Remote{
    
    public long add(long a, long b) throws RemoteException;
    
    public void sub(int qtd, String idProd, int index) throws RemoteException;

    public boolean isOn() throws RemoteException;    
    
    
    
}
