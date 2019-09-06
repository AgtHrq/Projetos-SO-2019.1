

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ProjetoSO2 {

    
    
    public static void main(String[] args) {
        FIFO();
        OTIMO();
        LRU();
        
    }
    
    static void FIFO(){
        
        List<Pagina> paginas = new ArrayList();
        
        
        int quadros = 0;
                
        try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          int pegarquadro = 1;
          
          while(linha != null){
              
              if(pegarquadro == 1){
                  pegarquadro++;
                  linha = br.readLine();
                  quadros = Integer.parseInt(linha);
                  
              }else{
                  
              linha = br.readLine();
              Pagina p = new Pagina();
              p.setReferencia(Integer.parseInt(linha));
              paginas.add(p);
              
              }
              
              
          }
       }catch(Exception e){
           
       }
        
        List<Integer> memoria = new ArrayList(quadros);
        
        int erro = 0;
        int cashit = 0;
        
       
            for(Pagina x : paginas){
                
                if(memoria.contains(x.getReferencia())){
                    cashit++;
                }else{
                    erro++;
                    if(memoria.size() < quadros){
                        memoria.add(x.getReferencia());
                    }else if(memoria.size() == quadros){
                            memoria.remove(0);
                            memoria.add(x.getReferencia());
                        }
                    }
                }
            
            System.out.println("FIFO " + erro);
           // System.out.println("CASH HIT " + cashit);
            
            
            
            }
    
    public static void OTIMO(){
        
        List<Pagina> paginas = new ArrayList();
        
        
        int quadros = 0;
                
        try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          int pegarquadro = 1;
          
          while(linha != null){
              
              if(pegarquadro == 1){
                  pegarquadro++;
                  linha = br.readLine();
                  quadros = Integer.parseInt(linha);
                  
              }else{
                  
              linha = br.readLine();
              Pagina p = new Pagina();
              p.setReferencia(Integer.parseInt(linha));
              paginas.add(p);
              
              }
              
              
          }
       }catch(Exception e){
           
       }
        
        List<Integer> memoria = new ArrayList(quadros);
        List<Integer> atingidas = new ArrayList();
        List<Integer> auxiliar = new ArrayList();
       
        
        int erro = 0;
        int cashit = 0;
        int indice = 1;
        int escolhida = 0;
        
        
        for(Pagina x : paginas){
            if(!auxiliar.contains(x.getReferencia())){
                auxiliar.add(x.getReferencia());
            }
        }
        
        //System.out.println("TAMANHO DO ARRAY AUXILIAR " + auxiliar.size());
        
       
            for(Pagina x : paginas){
                
                if(memoria.contains(x.getReferencia())){
                   // System.out.println("entrou primeiro");
                    cashit++;
                }else{
                    erro++;
                    if(memoria.size() < quadros){
                        //System.out.println("entrou segundo");
                        memoria.add(x.getReferencia());

                    }else if(memoria.size() == quadros){
                        //System.out.println("entrou terceiro");
                            atingidas.clear();
                            
                            for(int i = indice; i < paginas.size(); i++){
                               
                                if(atingidas.size() < auxiliar.size()){
                                    
                                 if(!atingidas.contains(paginas.get(i).getReferencia())){
                                    atingidas.add(paginas.get(i).getReferencia());

                                }   
                                 
                                }else{
                                    break;
                                }
                                
                                
                            }

                
                for(int i = 0; i < auxiliar.size(); i++){
                        if(!atingidas.contains(auxiliar.get(i))){
                            atingidas.add(auxiliar.get(i));
                        }
                }

                 for(int i = atingidas.size() - 1; i >= 0; i-- ){
                        
                        if(memoria.contains(atingidas.get(i))){
                            escolhida = atingidas.get(i);
                            break;
                        }

                 }
                 
                            
                  for(int i = 0; i < memoria.size(); i++){
                        
                                
                        if(memoria.get(i) == escolhida){
                            memoria.remove(i);
                            memoria.add(i, x.getReferencia());
                            
                        }
                        
                        
                    }
                            
                            
                        }
                    }
                
                indice++;
                }
        
        System.out.println("OTIMO " + erro);
    }
    
    public static void LRU(){
       
        List<Pagina> paginas = new ArrayList();
        
        
        int quadros = 0;
                
        try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          int pegarquadro = 1;
          
          while(linha != null){
              
              if(pegarquadro == 1){
                  pegarquadro++;
                  linha = br.readLine();
                  quadros = Integer.parseInt(linha);
                  
              }else{
                  
              linha = br.readLine();
              Pagina p = new Pagina();
              p.setReferencia(Integer.parseInt(linha));
              paginas.add(p);
              
              }
              
              
          }
       }catch(Exception e){
           
       }
        
        List<Integer> memoria = new ArrayList(quadros);
        List<Integer> filadeterminante = new ArrayList();
        
        int erro = 0;
        int cashit = 0;
        int indice;
        
        for(Pagina x : paginas){
            
            if(memoria.contains(x.getReferencia())){
                indice = 0;
                
                    for(int i = 0; i < filadeterminante.size(); i++){
                        if(filadeterminante.get(i) == x.getReferencia()){
                            filadeterminante.remove(i);
                        }
                    }
                    filadeterminante.add(x.getReferencia());
                    cashit++;
                }else{
                    erro++;
                    if(memoria.size() < quadros){
                        memoria.add(x.getReferencia());
                        filadeterminante.add(x.getReferencia());
                    }else if(memoria.size() == quadros){
                            memoria.remove(filadeterminante.get(0));
                            filadeterminante.remove(0);
                            memoria.add(x.getReferencia());
                            filadeterminante.add(x.getReferencia());
                        }
                    }
        }
        System.out.println("LRU " + erro);
        
    }
    
    
    
    
        
        
        
}

