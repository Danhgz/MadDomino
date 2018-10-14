/**
 * Write a description of class Virtual3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual3 implements Jugador
{
    private Ficha[] baraja;
    private String nombre;
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
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public Ficha[] getMano(){
        return baraja;
    }
        
    private boolean sePuedeJugar(int i, int lado){
	return baraja[i].getIzq() == lado || baraja[i].getDer() == lado; 
    }

    public String[] hacerJugada(int izq, int der)
    {       
	String[] jugada = new String[2];
	Ficha fichaMasGrande = new Ficha(-1,-1);
	for(int i = 0; i < ocupado ; ++i){
	    if(sePuedeJugar(i, izq) && baraja[i].getValor() > fichaMasGrande.getValor()){
	        fichaMasGrande = baraja[i];
		if(baraja[i].getIzq() == izq){
		    baraja[i].swap();	
		}
		jugada[0] = "" + i;
		jugada[1] = "I";
            }
            else if(sePuedeJugar(i, der) && baraja[i].getValor() > fichaMasGrande.getValor()){
		fichaMasGrande = baraja[i];
		if(baraja[i].getDer() == der){
		    baraja[i].swap();
                }
                jugada[0] = "" + i;
                jugada[1] = "D";		
            }
	}
	return jugada;	
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
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
}
