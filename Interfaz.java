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
    public String imprimirMenuPrincipal(boolean err){ 
             
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~DOMINO~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+
                        " 1. Jugar partida 1 vs. 1                                       \n"+
                        " 2. Jugar Torneo                                                \n"+
                        " 3. Salir                                                       \n\n");
        digiteOpcion(err);
        return input.next();                       
    }

    //metodo para imprimir el menu de juego en consola.
    public String[] imprimirMenuVersus(String[] actual){
           
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

    // metodo para determinar el numero de rondas en la partida.
    public int modificarRondas(){
        int rondas = 0;    
        boolean err= false;
        boolean modificado=false;
        String rondasAux;
        do{
            System.out.print('\u000C');
            System.out.print("~~~~~~~~~~~~~~~~~~~~~~~HELIO GAME~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            
            if(err){
                System.out.print(" Error! Digite un numero entero positivo o \"S\" para salir\n\n");
            }
            else{
                System.out.print(" (Digite S para salir)\n\n");
            }
            
            System.out.print(" -Digite la cantidad de rondas por partida: ");
            rondasAux= input.next();
            
            if(rondasAux.matches("^[0-9]+$") && !rondasAux.matches("^0+$")){
                rondas = Integer.parseInt(rondasAux);
                modificado= true;
            }   
            else{
                err=true;
            }
            
            if(modificado){ //Mensaje de confirmacion de cambio
            
                try
                {
                    System.out.print('\u000C');
                    System.out.print("~~~~~~~~~~~~~~~~~~~~~~~HELIO GAME~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+
                                     "          -- Cambio realizado exitosamente! --\n");
                    Thread.sleep(1250);
                }
                catch(Exception e){
                }
            }
            
        }while(!((rondasAux.matches("^[0-9]+$") && !rondasAux.matches("^0+$"))|| rondasAux.equalsIgnoreCase("s")));
        return rondas;
    }

    public void digiteOpcion(boolean err){//Para ahorrar codigo
    
       if(err){
           System.out.print("  -Error! Digite una una opcion valida: ");
       }       
       else{
           System.out.print("  -Digite la opcion que desea: ");
       }
    }
}
