/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String[] servidores = {"127.0.0.1"};
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
        this.server = new CommerceServer(this.manager, "127.0.0.1", this.produtos);
        this.manager = new ManagerFiles();        
       
    }
    
     public DefaultListModel initializaList() {
        DefaultListModel model = new DefaultListModel();
        for (Produto produto : this.produtos) {
            model.addElement(produto.toString());
        }
        return model;
    }
     
     public void verificaEstoqueTodos(String idProd, int index) {
         ArrayList<Integer> vetor = new ArrayList(this.vetor.size());
         for(String servidor: this.vetor) {                          
                  
                 Thread t1 = new Thread(() -> {
                     try {                         
                         Commerce c = (Commerce) Naming.lookup("rmi://"+servidor+":1099/CommerceService");        
                            vetor.add(c.returnEstoque(idProd,index));
                            System.out.println("Requi executada em: " + servidor);
                        } catch (RemoteException | MalformedURLException | NotBoundException ex) {
                        //Logger.getLogger(CommerceClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Deu erro no servidor: " + servidor);                        
                        }
                                                                                                                                                                          
                 });                                 
             t1.start();
         } 
         
     }
     
     synchronized private void escrevePendenciasLocal(ArrayList<String> vetor) throws IOException {
         if(vetor == null) return; 
         for(String line: vetor) {                          
              this.produtos.get(Integer.valueOf(line.split(";")[3])).setQtd(Integer.valueOf(line.split(";")[2]));
          }
         this.manager.writeWordsCommerce(this.produtos,"LojaA");
     }
     
     public void verificaPendencias(String serv, String idProd, int index) {         
         for(String servidor: this.vetor) {                          
                  
                 Thread t1 = new Thread(() -> {
                     try {
                         //Verifico se tem pendencias para atualizar deste produto
                         Commerce c = (Commerce) Naming.lookup("rmi://"+servidor+":1099/CommerceService");        
                            this.escrevePendenciasLocal(c.returnPendencias(serv,idProd, index));
                            System.out.println("Requi executada em: " + servidor);
                        } catch (RemoteException | MalformedURLException | NotBoundException ex) {
                        //Logger.getLogger(CommerceClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Deu erro no servidor: " + servidor);                        
                        } catch (IOException ex) {
                         Logger.getLogger(CommerceCtrl.class.getName()).log(Level.SEVERE, null, ex);
                     }
                                                                                                                                                                          
                 });                                 
             t1.start();
         } 
         
     }

    public CommerceServer getServer() {
        return server;
    }

    public void setServer(CommerceServer server) {
        this.server = server;
    }
     
     public void updateServer(int index, String idProd, int qtd, String loja) throws NotBoundException{                        
         for(String servidor: this.vetor) {                          
                  
                 Thread t1 = new Thread(() -> {
                     try {
                         //Verifico se tem pendencias para atualizar deste produto
                         Commerce c = (Commerce) Naming.lookup("rmi://"+servidor+":1099/CommerceService");        
                            c.sub(qtd, idProd, index);
                            System.out.println("Requi executada em: " + servidor);
                        } catch (RemoteException | MalformedURLException | NotBoundException ex) {
                        //Logger.getLogger(CommerceClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Deu erro no servidor: " + servidor);
                         try {
                             this.manager.escrevePendencia(idProd, servidor, qtd, index);
                         } catch (InterruptedException | IOException ex1) {
                             Logger.getLogger(CommerceCtrl.class.getName()).log(Level.SEVERE, null, ex1);
                         }
                        }
                                                                                                                                                                          
                 });                                 
             t1.start();
         }               
     }
     
}

    
    

