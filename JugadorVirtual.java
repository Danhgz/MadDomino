/**
 * Write a description of class JugadorVirtual here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JugadorVirtual
{
    private Ficha[] baraja;
    private int puntaje;
    //CONSTRUCTOR
    public JugadorVirtual(String jugador){
        init();
    }
    
    public void init(){
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
    
    //Verifica si tiene una ficha par
    public boolean tienePar(){
        boolean existe = false;
        int i = 0;
        while(i < 7 && !existe){
            existe = baraja[i].getIzq() == baraja[i].getDer() && baraja[i].getIzq() == i;
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
