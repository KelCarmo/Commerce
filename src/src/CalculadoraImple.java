/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author kelvin
 */
public class CalculadoraImple extends UnicastRemoteObject implements Calculadora {

    protected CalculadoraImple() throws RemoteException {
        super();
    }
    
    @Override
    public long add(long a, long b) throws RemoteException {
        return a + b;
    }

    @Override
    public String sub(Produto a, int qtd) throws RemoteException {
        return null;
    }
    
}
