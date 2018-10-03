
/**
 * Write a description of class JugadorVirtual here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JugadorVirtual
{
    private Ficha[] baraja;
    private Dealer dealer;
    
    //CONSTRUCTOR
    public JugadorVirtual(String jugador){
        baraja = new Ficha[10]; //numero arbitrario
        dealer = new Dealer();
    }
    
    public void pedirBaraja()
    {
        for(int i=0; i<7; i++)
        {
            baraja[i]= new Ficha();
            baraja[i]= dealer.repartir();
        }
    }
    
}
