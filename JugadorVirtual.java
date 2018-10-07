
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
        baraja = new Ficha[7]; 
        dealer = new Dealer();
    }
    
    public void pedirBaraja()
    {
        for(int i=0; i<7; ++i)
        {
            baraja[i]= new Ficha();
            baraja[i]= dealer.repartir();
        }
    }
    
    public void comer()
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
    }
    
    public int parMasAlto()
    {
        int par = -1;
        for(int i=0; i<baraja.length; ++i)
        {
            if(baraja[i].getIzq()==baraja[i].getDer()&&baraja[i].getIzq()>par)
            {
                par= baraja[i].getIzq();
            }
        }
        return par;
    }   
}
