
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ProjetoSO {


    
    public static void main(String[] args) {   
       
       //Scanner ler = new Scanner(System.in);
        //System.out.println("Digite a quantidade de processos: ");
        //int n = ler.nextInt();
       // int u = 0;
       /* System.out.println("Insira os processos: ");
        while (u < n) {
        Processo p = new Processo();
        p.setChegada(ler.nextInt());
        p.setPico(ler.nextInt());
        processos.add(p);
        
        for(Processo x : processos) {
            System.out.println(x.getChegada());
            System.out.println(x.getPico());
        }
        u++;
    }*/
    
       
       FCFS();
       SJF();
       RR();
       
       
       /*int testeprint = 1;
       for(Processo x : processos) {
            System.out.println("Processo "+testeprint);
            System.out.println(x.getTrespostafcfs());
            System.out.println(x.getTretornofcfs());
            testeprint++;
        }
        System.out.println(trespostamedio);
        System.out.println(tretornomedio);
       
       System.out.println(tempo);*/
       
       
       
        
    }
    
    static void FCFS(){
        
       List<Processo> processosF = new ArrayList<Processo>();

       //LEITURA DO ARQUIVO
       try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          while(linha != null){
              
              linha = br.readLine();
              String entradas[] = linha.split(" ");
              Processo p = new Processo();
              p.setChegada(Integer.parseInt(entradas[0]));
              p.setPico(Integer.parseInt(entradas[1]));
              processosF.add(p);
              
          }
       }catch(Exception e){
           
       }
       
       int processosmortos = 0;
       int tempo = 0;
       double trespostamedio = 0;
       double tretornomedio = 0;
       double tesperamedio = 0;
       
       //COMEÇO DA FCFS
       while(processosmortos != processosF.size()){
          processosmortos = 0;
           
          //checa se acabou os processos
          for(Processo x : processosF) {
            if(x.isVivo() == false){
                processosmortos++;
            }else{
                
                if(x.isPrimeiravez()){
                    x.setPrimeiravez(false);
                    //TEMPO DE RESPOSTA POR PROCESSO
                    x.setTresposta(tempo - x.getChegada());
                    
                }
                
                tempo += x.getPico();
                
                x.setVivo(false);
                x.setSaida(tempo);
                
            }
        }
          //TEMPO DE RETORNO POR PROCESSO
          for(Processo x : processosF) {
           x.setTretorno(x.getSaida() - x.getChegada());
        }
          
       }
       //OBTENÇÃO DOS TEMPOS MEDIOS
       for(Processo x : processosF) {
           trespostamedio += x.getTresposta();
           tretornomedio += x.getTretorno();
           
        }
         
         tretornomedio = tretornomedio/processosF.size();
         trespostamedio = trespostamedio/processosF.size();
         tesperamedio = trespostamedio; //por ser uma FCFS
          
       
       //PRINT FCFS
        System.out.print("FCFS "+ tretornomedio + " " + trespostamedio + " " + tesperamedio);
        
        //FIM DA FCFS
    }
    
    static void SJF(){
       //ABRINDO ARQUIVO
        List<Processo> processosS = new ArrayList<Processo>();//CADA TIPO DE ESCALONAMENTO UMA LISTA DE PROCESSOS DIFERENTE
        
        
        try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          while(linha != null){
              
              linha = br.readLine();
              String entradas[] = linha.split(" ");
              Processo p = new Processo();
              p.setChegada(Integer.parseInt(entradas[0]));
              p.setPico(Integer.parseInt(entradas[1]));
              processosS.add(p);
              
          }
       }catch(Exception e){
           
       }
        

       //INICIANDO AS VARIÁVEIS LOCAIS
       int processosmortos = 0;
       int tempo = 0;
       double trespostamedio = 0;
       double tretornomedio = 0;
       double tesperamedio = 0;
       Processo atual = new Processo();
       Processo aux = new Processo();
       atual.setPico(10000000);//PICO SETADO DESTA FORMA PARA INCIALIZAR
       aux.setPico(10000000);
       
       
       
       while(processosmortos != processosS.size()){ //ENQUANTO TIVER PROCESSO VIVO
           
           processosmortos = 0;
           
           for(Processo x : processosS){ //CAPTURAR O PROCESSO QUE VAI PARA A CPU
               
               if(atual.getPico() == 0){//EXCEÇÃO PARA PROCESSOS MORTOS
                   atual = aux;
               }
               
              if(x.isVivo()){
                 
                 if(x.getChegada()<= tempo){//JA CHEGOU
                   if(x.getPico() < atual.getPico()){
                       atual = x;
                   }
               }
                 
              }
               
               
              
           }
           
           
           for(Processo x : processosS){//PASSAGEM DE TEMPO
               
               if(x.isVivo()){
                
                if(x.getChegada() <= tempo){
                if(x != atual){
                   x.aumentarNaFila(1);
               }else{
                    
                   if(x.isPrimeiravez()){//PRIMEIRA VEZ ENTRANDO NA CPU
                       x.setPrimeiravez(false);
                       x.setTresposta(tempo - x.getChegada());
                   }
                   x.aumentarNaCpu(1); 
                   x.diminuirPico();
                   
               }  
              }   
               }
              
           }
           
           
           for(Processo x : processosS) { //MATANDO PROCESSO E CONTANDO OS MORTOS
            if(x.isVivo()){
                
                if(x.getPico() == 0){
                x.setVivo(false);
                x.setTretorno(tempo - x.getChegada() + 1);
                x.setTespera(x.getNafila());
                
            }
            }   
            if(x.isVivo() == false){
                processosmortos++;
            }
           } 
           
           tempo++;
           
       }
       
        //int z = 1;
        
//        for(Processo x : processosS){
//           System.out.println("Processo: "+z);
//           System.out.println(x.getTesperafcfs());
//           System.out.println(x.getTrespostafcfs());
//           System.out.println(x.getTretornofcfs());
//           z++;
//     }
        
        for(Processo x : processosS) { //CALCULANDO TEMPOS
          
           trespostamedio += x.getTresposta();
           tretornomedio += x.getTretorno();
           tesperamedio += x.getTespera();
           
        }
        
        tretornomedio = tretornomedio/processosS.size();
        trespostamedio = trespostamedio/processosS.size();
        tesperamedio = tesperamedio/processosS.size();
        
        System.out.println("");
        
        System.out.print("SJF "+ tretornomedio + " " + trespostamedio + " " + tesperamedio);
        
       
       //FIM DA SJF
       
    }
    
    static void RR(){
       
//ABRINDO ARQUIVO
        
        List<Processo> processosR = new ArrayList<Processo>();
        List<Processo> filadeprontos = new ArrayList<Processo>();
        List<Processo> processosmortos = new ArrayList<Processo>();
        
        try{
          
          FileInputStream arquivo = new FileInputStream("arquivo.txt"); 
          InputStreamReader input = new InputStreamReader(arquivo);
          BufferedReader br = new BufferedReader(input);
          String linha = "";
          while(linha != null){
              
              linha = br.readLine();
              String entradas[] = linha.split(" ");
              Processo p = new Processo();
              p.setChegada(Integer.parseInt(entradas[0]));
              p.setPico(Integer.parseInt(entradas[1]));
              processosR.add(p);
              
          }
       }catch(Exception e){
           
       }
        
        //INICIANDO AS VARIÁVEIS LOCAIS
       
       int quantum = 2;
       int tempo = 0;
       double trespostamedio = 0;
       double tretornomedio = 0;
       double tesperamedio = 0;
       Processo atual = new Processo();
       boolean primeiroprocesso = true;
       
       
       while(processosmortos.size() != processosR.size()){
           
           //tempo += passartempo;
           
           for(Processo x : processosR){ //checando quem chegou
               
               
              if(x.getChegada() <= tempo){ //chegou
                 
                  if(!x.isJachegou()){
                         
                   x.setJachegou(true);
                   filadeprontos.add(x);
                      
                   
                   if(primeiroprocesso){
                       primeiroprocesso = false;
                       atual = x;
               }
                   
           } 
        }
               
      }
           if(!filadeprontos.isEmpty()){
               
               //passagem de tempo
                     
                   if(atual.getPico() - quantum <= 0){  //CASO 1
                       
                       
                       tempo += atual.getPico();
                       for(Processo x : processosR){ //checando quem chegou
               
               
              if(x.getChegada() <= tempo){ //chegou
                 
                  if(!x.isJachegou()){
                         
                   x.setJachegou(true);
                   filadeprontos.add(x);
                      
                   
                   if(primeiroprocesso){
                       primeiroprocesso = false;
                       atual = x;
               }
                   
           } 
        }
               
      }
                       
                       
                       
                       
                       
           for(Processo x : filadeprontos){//PASSAGEM DE TEMPO
               
                
                if(x != atual){
                   x.aumentarNaFila(atual.getPico());
               }else{
                    
                   if(x.isPrimeiravez()){//PRIMEIRA VEZ ENTRANDO NA CPU
                       x.setPrimeiravez(false);
                       x.setTresposta(tempo - x.getChegada());
                   }
                   
                   x.aumentarNaCpu(atual.getPico()); 
                   
               }  
               
               
              
           }
                       atual.setPico(0);
                       atual.setVivo(false);
                       atual.setTretorno(tempo - atual.getChegada());
                       atual.setTespera(atual.getNafila());
                       filadeprontos.remove(0);
                       processosmortos.add(atual);
                       
                       
                       if(!filadeprontos.isEmpty()){
                           atual = filadeprontos.get(0);
                       }
                       
                   }else{ //CASO 2
                       
                       
                       
                       
                       
//                       for(int i = 0; i < filadeprontos.size(); i++){
//                          filadeprontos.add(i, filadeprontos.get(i+1));
//                       }
                       
                       
                       
          for(Processo x : filadeprontos){//PASSAGEM DE TEMPO
               
                
                if(x != atual){
                   x.aumentarNaFila(quantum);
               }else{
                    
                   if(x.isPrimeiravez()){//PRIMEIRA VEZ ENTRANDO NA CPU
                       x.setPrimeiravez(false);
                       x.setTresposta(tempo - x.getChegada());
                   }
                   
                   x.aumentarNaCpu(quantum); 
                   
               }  
               
               
              
           }           
                       tempo += quantum;
                       for(Processo x : processosR){ //checando quem chegou
               
               
              if(x.getChegada() <= tempo){ //chegou
                 
                  if(!x.isJachegou()){
                         
                   x.setJachegou(true);
                   filadeprontos.add(x);
                      
                   
                   if(primeiroprocesso){
                       primeiroprocesso = false;
                       atual = x;
               }
                   
           } 
        }
               
      }
                       atual.setPico(atual.getPico() - quantum);
                       filadeprontos.remove(0);
                       filadeprontos.add(atual);
                       atual = filadeprontos.get(0);
                       
                       
                       
                   }
                   
           
           }else{
               tempo++;
           }
         
       }
       
//       int VER = 1;
//       for(Processo x: processosmortos){
//           System.out.println("Processo: "+VER);
//           System.out.println("Tempo de espera: "+x.getTesperafcfs());
//           System.out.println("Tempo de retorno: "+x.getTretornofcfs());
//           System.out.println("Tempo de resposta: "+x.getTrespostafcfs());
//           VER++;
//           
//       }
//        System.out.println("Tempo total: "+tempo);
        
        for(Processo x : processosmortos) { //CALCULANDO TEMPOS
          
           trespostamedio += x.getTresposta();
           tretornomedio += x.getTretorno();
           tesperamedio += x.getTespera();
           
        }
        
        tretornomedio = tretornomedio/processosmortos.size();
        trespostamedio = trespostamedio/processosmortos.size();
        tesperamedio = tesperamedio/processosmortos.size();
        
        System.out.println("");
        
        System.out.print("RR "+ tretornomedio + " " + trespostamedio + " " + tesperamedio);
        
        System.out.println("");
       
    }
   
    
}
