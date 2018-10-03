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
    private Icon [] ficha;
    
    public Ficha(){
        //this.izq = 0;
        //this.der = 0;
    }
    
    public void setIzq(int izq){
        this.izq = izq;
    }
    
    public void setDer(int der){
        this.der = der;
    }
    
    public int getIzq(){
        return this.izq;
    }
    
    public int getDer(){
        return this.der;
    }
    
    public String toString(){
        return "izq: " + this.izq + " der: " + this.der;
    }

}
