
/**
 * Write a description of class Virtual2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual2 implements Jugador
{
    private Ficha[] baraja;
    private int puntaje;
    private int ocupado;
    //CONSTRUCTOR
    public Virtual2(){
        baraja = new Ficha[21]; 
        ocupado=0;
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
    public String[] hacerJugada(int izq, int der)
    {
        String[] jugada = new String[2];
        for(int i=0;i<baraja.length;++i)
        {
            
        }
        return jugada;
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
        for(int j=i;j<ocupado;++j)
        {
            swap(j,j+1);
        }
        --ocupado;
    }
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    private void swap(int x, int y){
        Ficha temp = baraja[x];
        baraja[x] = baraja[y];
        baraja[y] = temp;
    }   
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
        
    public int getValor(){
        int suma = 0;
        for(Ficha ficha : baraja){
            if(ficha!=null){
                suma += ficha.getValor()+1;
            }
        }
        return suma;
    }
    
}
