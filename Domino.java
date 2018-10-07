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
    private Ficha[] tablero; 
    
    public Domino() {
        interfaz = new Interfaz();
        dealer = new Dealer();
        jugador = new String[2];
        tablero = new Ficha[28];
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
        
        jugador =  interfaz.imprimirMenuVersus(jugador);
        jugador1 = new JugadorVirtual(jugador[0]);
        jugador2 = new JugadorVirtual(jugador[1]);
        
        repartirBarajas();
        
    }
    
    //Reparte las barajas hasta que alguno tenga un par e iniciar la partida
    public void repartirBarajas(){
       
        boolean par = false;
        
        do{
       
            dealer.repartirBarajaInicial(jugador1);
            dealer.repartirBarajaInicial(jugador2);    
            
            par = jugador1.tienePar() || jugador2.tienePar();
       
        }while(!par);
        
    }
  
    
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
}
