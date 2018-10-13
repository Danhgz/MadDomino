/**
 * Write a description of class Virtual3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual3 implements Jugador
{
    private Ficha[] baraja;
    private int puntaje;
    //CONSTRUCTOR
    public Virtual3(){
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
    

    public String[] hacerJugada(int zq, int der)
    {
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
    
     public int getValor(){
        int suma = 0;
        for(Ficha ficha : baraja){
            suma = ficha.getValor();
        }
        return suma;
    }
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
}
