/**
 * Write a description of class JugadorVirtual here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual1 implements Jugador
{
    private Ficha[] baraja;
    private int puntaje;
    //CONSTRUCTOR
    public Virtual1(){
        baraja = new Ficha[7]; 
        puntaje = 0;
        for(int i = 0; i < baraja.length ; ++i){
            baraja[i] = new Ficha();
        }
    }
    
    public void setFicha(Ficha ficha, int i){
        this.baraja[i].setIzq(ficha.getIzq());
        this.baraja[i].setDer(ficha.getDer());
    }
    
    public int getPuntaje(){
        return this.puntaje;
    }
    
    public Ficha[] getMano(){
        return baraja;
    }
    
    public String[] hacerJugada()//Workaround mieo
    {
        return new String[]{"a","a"};
    }
    
    public String[] hacerJugada(int izq, int der)
    {
        String []resultado = new String[2];
        for(int i = 0; i < baraja.length; ++i){
            if(baraja[i].getEsPar() && baraja[i].getIzq() == izq){
                resultado[0] = "" + i;
                resultado[1] = "I";
            }
            else if(baraja[i].getEsPar() && baraja[i].getDer() == der ){
                resultado[0] = "" + i;
                resultado [1] = "D";
            }   
        }
        
        for(int i = 0; i < baraja.length; ++i){
           if(baraja[i].getDer() == der && der > izq){
               resultado[0] = "" + i;
               resultado[1] = "D";
           }
           else  if(baraja[i].getIzq() == izq && izq > der){
               resultado[0] = "" + i;
               resultado[1] = "I";
           }
        }
        return new String[]{"",""};
    }
    
    //Verifica si tiene una ficha par
    public boolean tienePar(){
        boolean existe = false;
        int i = 0;
        while(i < 7 && !existe){
            existe = baraja[i].getIzq() == baraja[i].getDer();
            i++;
        }
        
        return existe;
    }
    
    public void sacarFicha(int i){
        baraja[i] = null;
    }
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
    
    /*public void comer()
    {
        Ficha[] barajaAux = new Ficha[baraja.length+1];
        for(int i=0; i<barajaAux.length; ++i)
        {         
            barajaAux[i]= new Ficha();
            if(i<barajaAux.length-1){                
                barajaAux[i]= baraja[i];
            }
            else{                
                barajaAux[i] = dealer.repartir();
            }
        }
        baraja = new Ficha[barajaAux.length];
        for(int i=0; i<baraja.length; ++i)
        {
            baraja[i]= new Ficha();
            baraja[i]= barajaAux[i];
        }
    }*/
    
}
