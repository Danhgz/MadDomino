/**
 * Write a description of class Interfaz here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner; 

public class Interfaz{
    private Scanner input;
   
    public Interfaz(){
        input = new Scanner (System.in);
    }
    
    //metodo para imprimir el menu inicial del juego en consola. 
    public String imprimirMenuPrincipal(boolean err)
    {              
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~DOMINO~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+
                        " 1. Jugar partida 1 vs. 1                                       \n"+
                        " 2. Jugar Torneo                                                \n"+
                        " 3. Salir                                                       \n\n");
        digiteOpcion(err);
        return input.next();                       
    }


    //metodo para imprimir el menu de juego en consola.
    public String[] imprimirMenuVersus(String[] actual)
    {           
        String[] jugador = {"0","0"};
        boolean err=false;
        int numJugador=0;
        String jugadorAux;
        do{
            System.out.print("~~~~~~~~~~~~~~~~~~~PARTIDA VERSUS~~~~~~~~~~~~~~~~~~~~~\n\n"+ 
                            "(Digite S para salir)                                  \n"+
                            " Digite el "+(numJugador+1)+" jugador                     \n\n"+
  

                            " 1. Humano                                              \n"+
                            " 2. Allanbrito                                          \n"+
                            " 3. Henao                                               \n"+
                            " 4. Maura                                               \n\n");
            digiteOpcion(err);
            jugadorAux = input.next();
        
            if(jugadorAux.matches("^[1234]$")){
                jugador[numJugador++]= jugadorAux;
            }   
            else{
            err=true;
            }
        }while(!(!jugador[1].equals("0")||jugadorAux.equalsIgnoreCase("s")));
        
        if(jugadorAux.equalsIgnoreCase("s")){
            jugador = actual;
        }
        return jugador;
    }


    public void digiteOpcion(boolean err)//Para ahorrar codigo
    {
       if(err){
           System.out.print("  -Error! Digite una una opcion valida: ");
       }       
       else{
           System.out.print("  -Digite la opcion que desea: ");
       }
    }
}
