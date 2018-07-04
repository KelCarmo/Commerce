/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author kelvin
 */
public class ManagerFiles implements Serializable{
    
    private final String diretorio = "./src/dados";
    private final byte size = (byte)0;
    //Atributo responsável servir como um semáforo para gerenciar as escritas e leituras no arquivo, feitas pelas
    //portas UDP e TCP.  

    public ManagerFiles() {
        
    }
/**
 * Método que carrega os Produtos das Lojas que se encontram no diretório "dados"
 * @return Lista de Produtos
 */
public ArrayList<Produto> loadWordsCommerce() {
    ArrayList<Produto> dset = new ArrayList<>();
    File file = new File(this.diretorio);
	File afile[] = file.listFiles();
	int i = 0;
	for (int j = afile.length; i < j; i++) {
		File arquivo = afile[i];
                System.out.println(arquivo.getName());
                try {			
			BufferedReader br = new BufferedReader(new  FileReader(arquivo));                        
			String line = "";
                        Produto novo;
			line = br.readLine();                        
			while (line != null) {
                            System.out.println(line);
                                //Cria Produto
                                novo = new Produto(line.split(";")[0],line.split(";")[1] ,
                                        line.split(";")[2], line.split(";")[3], line.split(";")[4],
                                        Integer.valueOf(line.split(";")[5]),Integer.valueOf(line.split(";")[6]),
                                        arquivo.getName(), line.split(";")[7]);
                                        dset.add(novo);
                                    line = br.readLine();                                                                       
			}
			br.close();                        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        return dset;
}
//Camisa Polo;Adidas;Camisas;Azul;G;50;3;id1023
/**
 * Método que escreve as atualizações de produtos no arquivo
 * @param n
 * @param name
 * @throws FileNotFoundException
 * @throws IOException 
 */
synchronized public  void writeWordsCommerce(ArrayList<Produto> n, String name) throws FileNotFoundException, IOException {
    File file = new File(this.diretorio+"/"+name);	
	int i = 0;		
	BufferedWriter wr = new BufferedWriter(new  FileWriter(file));
        for(Produto prod: n){
            wr.write(prod.getName()+";"+prod.getMarca()+";"+prod.getCategoria()+";"+
                    prod.getCor()+";"+prod.getTamanho()+";"+prod.getPreco()+";"+
                    prod.getQtd()+";"+prod.getId());
            wr.newLine();
        }
        wr.close();
			                      
		
	     
}

/**
 * Método que retorna ações pendentes de um servidor
 * @param servidor ip do servidor
 * @return Lista de pendencias de um determinado servidor
 * @throws FileNotFoundException
 * @throws IOException 
 */
synchronized public ArrayList<String> returnPendencias(String servidor) throws FileNotFoundException, IOException {
    ArrayList<String> dset = new ArrayList<>();
    File file = new File("./src/erros/"+servidor);
    
    if(!file.exists()) return null;
    BufferedReader br = new BufferedReader(new  FileReader(file));                        
			String line = "";
                        Produto novo;
			line = br.readLine();                        
			while (line != null) {
                            System.out.println(line);
                                if(line.split(";")[0].equals(servidor)) {
                                    dset.add(line);
                                }
                                line = br.readLine();                                
			}
			br.close();
                        if(dset.isEmpty()) return null;                        
                        else {
                            System.out.println("Pendencia Lida: " + dset.get(0));
                            file.delete();
                            return dset;
                        }
}

/**
 * Registrar que algum servidor não recebeu minha atualização
 * @param idProd
 * @param servidor
 * @param qtd
 * @param index
 * @param time
 * @throws InterruptedException
 * @throws IOException 
 */
public void escrevePendencia(String idProd, String servidor, int qtd, int index, long time) throws InterruptedException, IOException {
    this.writeString(servidor, servidor+";"+idProd+";"+qtd+";"+index+";"+time);
}

/**
 * Escreve uma pendencia no arquivo de log de erros
 * @param servidor
 * @param linha
 * @throws FileNotFoundException
 * @throws IOException 
 */
synchronized private void writeString(String servidor,String linha) throws FileNotFoundException, IOException {
    File file = new File("./src/erros/"+servidor);       
       BufferedWriter br = new BufferedWriter(new  FileWriter(file, true));
       if(file.length() != this.size) br.newLine();
       br.write(linha);
       br.close();            
   
}

/**
 * Escreve uma pendencia no arquivo de "corrigidos". Esse método evita que a mesma pendencia seja feita mais de uma vez;
 * @param linha
 * @throws IOException 
 */
synchronized public void writeCorrigido(String linha) throws IOException {
        File file = new File("./src/corrigidos/corrigidos");
        BufferedWriter br;
            
            br = new BufferedWriter(new  FileWriter(file, true));
            if(file.length() != this.size) br.newLine();
            br.write(linha);
            br.close();
    }
    
/**
 * Método que verifica se uma pendencia já foi corrigida.
 * @param idPendencia
 * @return true, se a pendencia já foi corrigida. Flase, se não foi
 * @throws IOException 
 */
synchronized public boolean readCorrigido(String idPendencia) throws IOException {
        
        File file = new File("./src/corrigidos/corrigidos");
    
    if(!file.exists()) return false;
    BufferedReader br = new BufferedReader(new  FileReader(file));                        
			String line = "";
                        Produto novo;
			line = br.readLine();                        
			while (line != null) {
                            System.out.println(line);
                                if(line.split(";")[4].equals(idPendencia)) {
                                    br.close();
                                    return true;
                                }
                                line = br.readLine();                                
			}
			br.close();
                        return false;
        
    }
    
    


    
}
