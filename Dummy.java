/**
 * Write a description of class Dummy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dummy implements Jugador
{   
    private Ficha[] baraja;
    private int ocupado;
    private int puntaje;
    
    public Dummy()
    {
        baraja = new Ficha[7]; 
        puntaje = 0;
        for(int i = 0; i < baraja.length ; ++i){
            baraja[i] = new Ficha();
        }
        ocupado=7;
    }  
    
    public void setFicha(Ficha ficha, int i){
        this.baraja[i].setIzq(ficha.getIzq());
        this.baraja[i].setDer(ficha.getDer());
    } 
    
    public int getPuntaje(){
        return this.puntaje;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public int getOcupado(){
        return this.ocupado;
    }
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
    
    public Ficha[] getMano(){
        return baraja;
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
    
    
    public String[] hacerJugada(int izq, int der)
    {
        return new String[]{"",""};
    }
    
     public int getValor(){
        int suma = 0;
        for(Ficha ficha : baraja){
            suma = ficha.getValor();
        }
        return suma;
    }
    
}
