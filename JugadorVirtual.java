
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
    
    public void jugadorAllan(){
       pedirBaraja();
       for(int i = 0; i < baraja.length-3; i++){
           //Validar si hay fichas pares
            if(baraja[i].getIzq() == baraja[i].getDer()){
                
            }
            //validar las fichas que posean valores mayores en el lado izquierdo o derecho
            else if((baraja[i].getIzq() < baraja[i].getDer()) || (baraja[i].getIzq() > baraja[i].getDer()) ){
            
            }
       }
        
        
    }
    
}
