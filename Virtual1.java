/**
 * Write a description of class JugadorVirtual here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual1 implements Jugador
{
    private Ficha[] baraja;
    private String nombre;
    private int puntaje;
    private int ocupado;
    //CONSTRUCTOR
    public Virtual1(){
        baraja = new Ficha[21]; 
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
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public Ficha[] getMano(){
        return baraja;
    }
    
    private String[] extremosIguales(int izq, int der){
        String []resultado = new String[2];
        for(int i = 0; i < ocupado; ++i){
            if(baraja[i].getEsPar() && baraja[i].getIzq() == der){
                resultado[0] = "" + i;
                resultado[1] = "I";
            }
            else if(baraja[i].getEsPar() && baraja[i].getDer() == izq ){
                resultado[0] = "" + i;
                resultado [1] = "D";
            }   
        }
        
        for(int i = 0; i < ocupado; ++i){
           if(baraja[i].getIzq() == der && der > izq){
               resultado[0] = "" + i;
               resultado[1] = "D";
           }
           else  if(baraja[i].getDer() == izq && izq > der){
               resultado[0] = "" + i;
               resultado[1] = "I";
           }
           else if(baraja[i].getDer() == der && der > izq){
               baraja[i].swap();
               resultado[0] = "" + i;
               resultado[1] = "D";
           }
           else if(baraja[i].getIzq() == izq && izq > der){
               baraja[i].swap();
               resultado[0] = "" + i;
               resultado[1] = "I";
           }
        }
        return resultado;
    }
    
    public String[] hacerJugada(int izq, int der)
    {
        return extremosIguales(izq , der);
    }
    
    public boolean tieneJugada(int izq, int der)
    {
        boolean tiene=false;
        for(int i = 0; i < ocupado; ++i){            
            if(baraja[i].getIzq() == izq || baraja[i].getIzq() == der||baraja[i].getDer() == izq||baraja[i].getDer() == der){
                tiene = true; 
            }
        }
        return tiene;
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
        for(int j=i;j<ocupado-1;++j)
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
    
    public boolean tieneFichas(){
        boolean tiene = false;
        for(Ficha ficha : baraja){
            if(ficha!=null){
                tiene=true;
            }
        }
        return tiene;
    }
        
    public int getValor(){
        int suma = 0;
        for(Ficha ficha : baraja){
            if(ficha!=null){
                suma += ficha.getValor();
            }
        }
        return suma;
    }
    
}

