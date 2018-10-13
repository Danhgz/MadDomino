
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
        puntaje = 0;
        for(int i = 0; i < baraja.length ; ++i){
            baraja[i] = new Ficha();
        }  
    }
    
    public void setFicha(Ficha ficha, int i){
        this.baraja[i].setIzq(ficha.getIzq());
        this.baraja[i].setDer(ficha.getDer());
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

    public String[] hacerJugada(int izq, int der)
    {
        String[] jugada = new String[2];
        for(int i=0;i<baraja.length;++i)
        {
            
        }
        return jugada;
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
        for(int j=i;j<ocupado;++j)
        {
            swap(j,j+1);
        }
        --ocupado;
    }
    
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
