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
    private Dealer dealer;
    private JugadorVirtual jugador1;
    private JugadorVirtual jugador2;
    private String[] jugador;
    
    public Domino() {
        interfaz = new Interfaz();
        dealer = new Dealer();
        jugador = new String[2];
    }

    public void ejecutar() {
        String op;
        boolean err = false;
        do{
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
    
    public void correr1v1(){
        int caso;
        jugador = interfaz.imprimirMenuVersus(jugador);
        jugador1 = new JugadorVirtual(jugador[0]);
        jugador2 = new JugadorVirtual(jugador[1]);
        do{ //¿?
            jugador1.pedirBaraja();
            jugador2.pedirBaraja();
            caso=dealer.pedirInicio(jugador1.parMasAlto(),jugador2.parMasAlto());
        }while(caso==0);
        if(caso==1){
            //empieza el j1 poniendo su par mas alto
        }
        else{
            //empieza el j2 poniendo su par mas alto
        }
    }
    
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
}
