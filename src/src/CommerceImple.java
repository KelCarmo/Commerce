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
    
    /**
     * Realiza atualização de estoque
     * @param qtd quantidade do produto na compra
     * @param idProd id do produto
     * @param index índice do produto na lista
     * @return
     * @throws RemoteException 
     */
    @Override
    public String sub(int qtd, String idProd , int index) throws RemoteException {
        System.out.println("Esperando ...");
        if(this.produtos.get(index).getQtd() < qtd) {
            this.produtos.get(index).setQtd(qtd);
            try {
            this.manager.writeWordsCommerce(this.produtos, "LojaA");
            } catch (IOException ex) {
            Logger.getLogger(CommerceImple.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Terminado!");
            return "Terminado!";
        }
        
        
        
        return "Não foi possível realizar transação";
           
    }

    @Override
    synchronized public boolean isOn() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Retorna a quantidade de estoque de um determinado produto
     * @param idProd id do produto
     * @param index índice do produto na lista de produtos
     * @return
     * @throws RemoteException 
     */
    @Override
    public int returnEstoque(String idProd, int index) throws RemoteException{
        return this.produtos.get(index).getQtd();
    }
    
    /**
     * Método remoto que solicita e retorna pendencias de um servidor
     * @param servidor
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<String> returnPendencias(String servidor) throws RemoteException {
        try {
            return this.manager.returnPendencias(servidor);
        } catch (IOException ex) {
            Logger.getLogger(CommerceImple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Método remoto que envia pendencias de servidores para outros servidores
     * @param idProd
     * @param servidor
     * @param qtd
     * @param index
     * @param time
     * @throws RemoteException 
     */
    @Override
    public void escrevePendencia(String idProd, String servidor, int qtd, int index, long time) throws RemoteException {
        try {
            this.manager.escrevePendencia(idProd, servidor, qtd, index, time);
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(CommerceImple.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
