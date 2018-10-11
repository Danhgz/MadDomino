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
            System.out.print("~~~~~~~~~~~~~~~~~~~PARTIDA VERSUS~~~~~~~~~~~~~~~~~~~~~\n"+ 
                            "(Digite S para salir)                                  \n"+
                            " Digite el "+(numJugador+1)+" jugador                  \n\n"+
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
    
    public void imprimirTablero(Ficha[] tablero,int ocupado,JugadorVirtual j1,JugadorVirtual j2)
    {   
        String ficha ;
        String tableStr="  ";
        for(int i=0;i<ocupado;++i){
            ficha = "["+tablero[i].getIzq()+"|"+tablero[i].getDer()+"]";
            tableStr+= ficha;
        }
        System.out.print("Jugador 1: "+j1.getPuntaje()+"     Juguador 2: "+j2.getPuntaje()+"+\n\n\tI"+tableStr+"D");
    }
    
    public String[] imprimirBaraja(Ficha[] mano,int ocupado) //Para Dummie solamente, pide la jugada tambien
    {   
        String ficha;
        String[] resul;
        resul = new String [2];
        String manoStr="  ";
        System.out.print("* - - - - - - - - - - - - - - - - - - - - - - *\n\n");
        for(int i=0;i<ocupado;++i){
            ficha = "["+mano[i].getIzq()+"|"+mano[i].getDer()+"]";
            manoStr+= ficha;
        }
        System.out.println(manoStr);
        System.out.print("\n  -Digite la ficha que desea: ");
        resul[0] = ""+input.nextInt();
        System.out.print("\n  -Donde desea colocar la ficha?(I=izq D=der): ");
        resul[1] = ""+input.next().charAt(0);
        return resul;
    }
    
    public void imprimirGane(String jugador)
    {                                            
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~DOMINO~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+ 
                         "           "+jugador+" es el ganandor!                  \n");
    }
    
    public void imprimirTorneo(JugadorVirtual[] jugador)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~RONDA 1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+       
                           "                               ?????                                \n"+                           
                           "                                 |                                  \n"+
                           "                ---------------------------------                   \n"+
                           "               |                                 |                  \n"+
                           "             ?????                             ?????                \n"+
                           "        -------|-------                    ------|------            \n"+
                           "       |               |                  |             |           \n"+                         
                           " "+jugador[0]+"  "+jugador[1]+"     "+jugador[2]+"  "+jugador[3]+"  \n");
        
    }    
    public void imprimirTorneo(JugadorVirtual[] jugador, String ganador1,String ganador2)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~¡FINAL!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+       
                           "                               ?????                                \n"+                           
                           "                                 |                                  \n"+
                           "                --------------------------------                    \n"+
                           "               |                                |                   \n"+
                           "          "+ganador1+"                     "+ganador2+"             \n"+
                           "        -------|-------                   ------|------             \n"+
                           "       |               |                 |             |            \n"+                         
                           " "+jugador[0]+"  "+jugador[1]+"    "+jugador[2]+"  "+jugador[3]+"   \n");
        
    }
    
    public void imprimirTorneo(JugadorVirtual[] jugador, String ganador1,String ganador2, String campeon)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~FIN DEL TORNEO~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n"+       
                           "                            "+campeon+"                             \n"+                           
                           "                                 |                                  \n"+
                           "                --------------------------------                    \n"+
                           "               |                                |                   \n"+
                           "          "+ganador1+"                     "+ganador2+"             \n"+
                           "        -------|-------                   ------|------             \n"+
                           "       |               |                 |             |            \n"+                         
                           " "+jugador[0]+"  "+jugador[1]+"    "+jugador[2]+"  "+jugador[3]+"   \n");
        
    }
    
    public void digiteOpcion(boolean err)//Para ahorrar codigo
    {
       if(err){
           System.out.print("  -Error! Digite una opcion valida: ");
       }       
       else{
           System.out.print("  -Digite la opcion que desea: ");
       }
    }
    
}
