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
    private String[] jugador;
    
    public Domino()
    {
        interfaz = new Interfaz();
        jugador = new String[2];
    }

    public void ejecutar()
    {
        String op;
        boolean err = false;
        do
        {
            op = interfaz.imprimirMenuPrincipal(err);
            err = false;
            switch(op)
            {
                case "1":
                correr1v1();
                break;

                case "2":
                correrTorneo();
                break;                

                case "3":   
                System.out.println("~~~~~~~~~~~~ Gracias por jugar, hasta luego! :) ~~~~~~~~~~~");
                break;                 

                default:
                err = true;                        
            }
        }while(!op.equals("3"));
        System.exit(0);
    }    
    
    public void correr1v1()
    {
        jugador = interfaz.imprimirMenuVersus(jugador);
        //  ia = new JugadorVirtual("Mr.Brito");
        //  ia.pedirBaraja();
    }
    
    public void correrTorneo()
    {
        //  laspatasdeMauro.repartir(); 
    }
}
