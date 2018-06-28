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
import java.util.Map;

/**
 *
 * @author kelvin
 */
public class ManagerFiles implements Serializable{
    
    private final String diretorio = "./src/dados";
    private final byte size = (byte)0;
    //Atributo responsável servir como um semáforo para gerenciar as escritas e leituras no arquivo, feitas pelas
    //portas UDP e TCP.
    private boolean status;

    public ManagerFiles() {
        this.status = true;
    }

/*private File verificaArquivo(String idClient) {
    File file = new File(this.diretorio);
	File afile[] = file.listFiles();
	int i = 0;
	for (int j = afile.length; i < j; i++) {
		File arquivo = afile[i];
                System.out.println(arquivo.getName());
                if(arquivo.getName().equals(idClient+".txt")) {
                    return arquivo;
                }
	}
        return null;
}*/
    
/**
 * Escreve as medidas de um determinado Cliente no arquivo {Essas medidas são enviadas pelo sensor}.
 * @param idClient ID do cliente
 * @param medida Medida enviada pelo sensor
 * @return
 * @throws FileNotFoundException
 * @throws IOException 
 */
public int writeString(String idClient, String medida) throws FileNotFoundException, IOException {
    File file = new File(this.diretorio+"/"+idClient+".txt");    
    if(!file.exists()){
       file = new File(this.diretorio+"/"+idClient+".txt");
       if(this.status){
           this.status = false;
       BufferedWriter br = new BufferedWriter(new  FileWriter(file, true));
       if(file.length() != this.size) br.newLine();
       br.write(medida);
       br.close();
       this.status = true;
       }
       return 0;
    }else{
        if(this.status){
            this.status = false;
        BufferedWriter br = new BufferedWriter(new  FileWriter(file, true));
        if(file.length() != this.size) br.newLine();
        br.write(medida);
        br.close();
        this.status = true;
        }
        return 1;
    }
}
/**
 * Método que atende a requisição de número 1 {retorna o consumo total Atual}.
 * @param idClient ID do cliente que requisitou
 * @return Retorna a última medida enviada pelo sensor do cliente.
 */
public String readStringLast(String idClient)  {
    File file = new File(this.diretorio+"/"+idClient+".txt");
    String ultimo = "";
		try {
			if(this.status){
                            this.status = false;
			BufferedReader br = new BufferedReader(new  FileReader(file));

			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					ultimo = line;
				}
			}

			br.close();
                        this.status = true;
			return ultimo;
                        }else{
                            readStringLast(idClient);
                        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
}

/**
 * Método que atende a requisição de número 2 {Retirna para o cliente o total de consumo em um dia
 * determinado pelo usuário}.
 * @param idClient ID do cliente que requisitou
 * @param data data que o cliente especificou na requisição
 * @return Uma String concatenada com a medida do primeiro consumo do dia e o último.
 */
public String calcConsumoData(String idClient, String data)  {
    File file = new File(this.diretorio+"/"+idClient+".txt");
		try {
			if(this.status){
                            this.status = false;
			BufferedReader br = new BufferedReader(new  FileReader(file));
                        String firstValue = "";
                        String lastValue = "";
			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null && firstValue.equals("")) {
                                    if(line.split(";")[2].split(" ")[0].equals(data)){
                                        firstValue = line.split(";")[4];
                                    }
                                }
                                    if(line != null && !firstValue.equals("")){
                                        if(line.split(";")[2].split(" ")[0].equals(data)){
                                        lastValue = line.split(";")[4];
                                    }
				}
			}                       
                            
                            br.close();
                            this.status = true;
                            return firstValue+";"+lastValue;
                                               
                        }else{
                            calcConsumoData(idClient, data);
                        }
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NumberFormatException e){
                            e.printStackTrace();
                        } 
        return null;
}

/**
 * Método Método que atende a requisição de número 3 {Retorna para o cliente o consumo total 
 * em um intervalo de datas especificado por ele}
 * @param idClient ID do Cliente
 * @param data Data inicial.
 * @param dataFinal Data final.
 * @return Retorna uma String concatenada que contém a medida inicial e final nesse intervalo de datas.
 */
public String calcConsumoData(String idClient, String data, String dataFinal)  {
    File file = new File(this.diretorio+"/"+idClient+".txt");
		try {
			if(this.status){
                            this.status = false;
			BufferedReader br = new BufferedReader(new  FileReader(file));
                        String firstValue = "";
                        String lastValue = "";
			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null && firstValue.equals("")) {
                                    if(line.split(";")[2].split(" ")[0].equals(data)){
                                        firstValue = line.split(";")[4];
                                    }
                                }
                                    if(line != null && !firstValue.equals("")){
                                        if(line.split(";")[2].split(" ")[0].equals(dataFinal)){
                                        lastValue = line.split(";")[4];
                                    }
				}
			}                       
                            
                            br.close();
                            this.status = true;
                            return firstValue+";"+lastValue;
                                               
                        }else{
                            calcConsumoData(idClient, data, dataFinal);
                        }
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NumberFormatException e){
                            e.printStackTrace();
                        } 
        return null;
}

public HashMap<Character,HashSet<String>> loadWords() {
    
    HashMap<Character,HashSet<String>> example = new HashMap<Character,HashSet<String>>();        
    File file = new File(this.diretorio+"/words.dic");   
		try {			
			BufferedReader br = new BufferedReader(new  FileReader(file));                        
			String line = "";
                        char firstLine;
                        HashSet<String> dset;
			line = br.readLine();                        
			while (line != null) {                                
                                firstLine = line.charAt(0);
                                if(example.get(firstLine) == null) dset = new HashSet<String>();
                                else dset = example.get(firstLine);
                                if(firstLine == 'Y') System.out.println(line);
				while(firstLine == line.charAt(0)){
                                    dset.add(line);
                                    line = br.readLine();
                                    if(line == null) break;
                                }
                               example.put(firstLine, dset);
			}
			br.close();
                        return example;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;                       
}

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
                                novo = new Produto(line.split(";")[0],line.split(";")[1] ,
                                        line.split(";")[2], line.split(";")[3], line.split(";")[4],
                                        Integer.valueOf(line.split(";")[5]),Integer.valueOf(line.split(";")[6]),
                                        arquivo.getName(), line.split(";")[7]);
                                        dset.add(novo);
                                    line = br.readLine();                                   
                                    if(line.equals("")) break;
			}
			br.close();                        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        return dset;
}


    
}
