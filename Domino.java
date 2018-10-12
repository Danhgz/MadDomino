/**
 * Write a description of class Domino here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Domino
{
    private Dummy dummy;
    private Interfaz interfaz;
    private JugadorVirtual ia;

    public Domino()
    {
        interfaz = new Interfaz();
    }

    public void ejecutar()
    {
        int op = 0;
        do
        {
            // op=interfaz.elegirModoDeJuego();
            switch(op)
            {
                case 1:
                    correr1v1();
                    break;
                
                case 2:
                    correrTorneo();
                    break;
                
                case 3:
                    break;
                
                default:
                    //mensaje de error
            }
        }while(op!=3);
    }    
    
    public void correr1v1()
    {
        //  interfaz.elegirJugadores();
        //  ia = new JugadorVirtual("Mr.Brito");
        //  ia.pedirBaraja();
    }
    
    public void correrTorneo()
    {
        //  laspatasdeMauro.repartir(); 
    }
}
