import javax.swing.*;
/**
 * Write a description of class Ficha here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ficha
{
    // instance variables - replace the example below with your own
    private int izq;
    private int der;
    private boolean esPar;
    
    public void setIzq(int izq){
        this.izq = izq;
    }
    
    public void setDer(int der){
        this.der = der;
        esPar= this.izq == this.der; //lo metí aqui porque es vara hacer un setEsPar xd
    }    
    
    public int getIzq(){
        return this.izq;
    }
    
    public int getDer(){
        return this.der;
    }
    
    public boolean getEsPar(){
        return this.esPar;
    }
    
    public Ficha(Ficha otra){
        init(otra.getIzq(),otra.getDer());
    }
    
    public Ficha(int izq,int der){
        init(izq,der);
    }
    
    public Ficha(){
    }
    
    public int getValor(){
        return this.izq + this.der;
    }
    
    private void init(int izq, int der)
    {
        this.izq = izq;
        this.der = der;
        esPar= this.izq == this.der;
    }
    
    public void swap(){
        int temp = this.izq;
        der = izq;
        izq = temp;
    }
    
    public String toString(){
        return "["+izq+"|"+der+"]";
    }
    
}

