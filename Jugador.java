/**
 * Write a description of interface Jugador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface Jugador
{
    
    void setFicha(Ficha ficha, int i);
    
    public int getPuntaje();
    
    public boolean tienePar();
    
    public void sacarFicha(int i);
    
    public Ficha getFicha(int i);
    
    public Ficha[] getMano();
    
    public void hacerJugada(int der, int izq);
    
    public int getValor();
    

}


