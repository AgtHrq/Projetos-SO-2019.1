



public class Processo {

    private int chegada;
    private int pico;
    private double tretorno = 0;
    private double tresposta;
    private double tespera;
    private boolean vivo = true;
    private int saida;
    private boolean primeiravez = true;
    private int nacpu;
    private int nafila;
    private boolean jachegou = false;
    
    
    public int getChegada() {
        return chegada;
    }

 
    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public int getPico() {
        return pico;
    }

    public void setPico(int pico) {
        this.pico = pico;
    }

    public double getTretorno() {
        return tretorno;
    }

    public void setTretorno(double tretorno) {
        this.tretorno = tretorno;
    }


    public double getTresposta() {
        return tresposta;
    }


    public void setTresposta(double tresposta) {
        this.tresposta = tresposta;
    }

    public double getTespera() {
        return tespera;
    }

    public void setTespera(double tespera) {
        this.tespera = tespera;
    }

 
    public boolean isVivo() {
        return vivo;
    }

   
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getSaida() {
        return saida;
    }

    public void setSaida(int saida) {
        this.saida = saida;
    }

  
    public boolean isPrimeiravez() {
        return primeiravez;
    }
    

 
    public void setPrimeiravez(boolean primeiravez) {
        this.primeiravez = primeiravez;
    }

   
    public int getNacpu() {
        return nacpu;
    }

   
    public void setNacpu(int nacpu) {
        this.nacpu = nacpu;
    }
    
    public void aumentarNaCpu(int x){
        this.nacpu += x;
    }
    public void aumentarNaFila(int x){
        this.setNafila(this.getNafila() + x);
    }
    
    public void diminuirPico(){
        this.pico--;
    }


    public int getNafila() {
        return nafila;
    }


    public void setNafila(int nafila) {
        this.nafila = nafila;
    }

    
    public boolean isJachegou() {
        return jachegou;
    }

    public void setJachegou(boolean jachegou) {
        this.jachegou = jachegou;
    }

    
    
    

    
    
    
    
}
