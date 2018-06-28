/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import src.Commerce;
import src.CommerceClient;
import src.CommerceServer;
import src.ManagerFiles;
import src.Produto;

/**
 *
 * @author kelvin
 */
public class CommerceCtrl {
    
    private CommerceClient client;
    private CommerceServer server;
    private ManagerFiles manager;
    private String[] servidores = {"192.168.0.105"};
    private ArrayList<String> vetor;
    private ArrayList<Produto> produtos;    

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public CommerceCtrl() throws RemoteException, UnknownHostException {
        this.vetor = new ArrayList(this.servidores.length);
         for (String server : this.servidores) {
            this.vetor.add(server);
        }
        this.manager = new ManagerFiles();        
        this.produtos = this.manager.loadWordsCommerce();
        this.server = new CommerceServer(this.manager, "192.168.0.105", this.produtos);
        this.manager = new ManagerFiles();        
       
    }
    
     public DefaultListModel initializaList() {
        DefaultListModel model = new DefaultListModel();
        for (Produto produto : this.produtos) {
            model.addElement(produto.toString());
        }
        return model;
    }
     
     public void updateServer(int index, String idProd, int qtd) throws NotBoundException{               
         for(String servidor: this.vetor) {
             
              try{
        Commerce c = (Commerce) Naming.lookup("rmi://"+servidor+":1099/CommerceService");        
        c.sub(qtd, idProd, index);
        System.out.println("Requi enviada para: " + servidor);
        } catch (RemoteException | MalformedURLException ex) {
            //Logger.getLogger(CommerceClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Deu erro");
        }
             
             
             
             
             
         }               
     }

    
    
}
