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
    private int ocupado;
    //CONSTRUCTOR
    public Virtual3(){
        baraja = new Ficha[7]; 
        puntaje = 0;
        for(int i = 0; i < baraja.length ; ++i){
            baraja[i] = new Ficha();
        }
    }
    
   public void setFicha(Ficha ficha){
        this.baraja[ocupado].setIzq(ficha.getIzq());
        this.baraja[ocupado].setDer(ficha.getDer());
        ++ocupado;
    }
    
    public int getPuntaje(){
        return this.puntaje;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public Ficha[] getMano(){
        return baraja;
    }
    
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    public String[] hacerJugada(int zq, int der)
    {
        return new String[]{"",""};
    }
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    public boolean tieneJugada(int izq, int der)
    {
        boolean tiene=false;
        for(int i = 0; i < ocupado; ++i){            
            tiene = baraja[i].getIzq() == izq || baraja[i].getIzq() == der||baraja[i].getDer() == izq||baraja[i].getDer() == der; 
        }
        return tiene;
    }
    
    //Verifica si tiene una ficha par
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    public boolean tienePar(){
        boolean existe = false;
        int i = 0;
        while(i < 7 && !existe){
            existe = baraja[i].getIzq() == baraja[i].getDer();
            i++;
        }
        
        return existe;
    }
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
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
