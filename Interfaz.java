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
    public int[] imprimirMenuVersus()
    {           
        int[] jugador = {-1,-1};
        boolean err=false;
        int numJugador=0;
        String jugadorAux;
        do{
            System.out.print("~~~~~~~~~~~~~~~~~~~PARTIDA VERSUS~~~~~~~~~~~~~~~~~~~~~\n"+ 
                            " Digite el "+(numJugador+1)+" jugador                  \n\n"+
                            " 1. Humano                                              \n"+
                            " 2. Allanbrito                                          \n"+
                            " 3. Henao                                               \n"+
                            " 4. Maura                                               \n\n");
            digiteOpcion(err);
            jugadorAux = input.next();
        
            if(jugadorAux.matches("^[1234]$")){
                jugador[numJugador++]= Integer.parseInt(jugadorAux)-1;//-1 para leer la posicion en prototipo
            }   
            else{
            err=true;
            }
        }while(jugador[1]==-1);        
        return jugador;
    }
    
    public void imprimirTablero(Ficha[] tablero,int ocupado,int j1,int j2)
    {   
        String ficha ;
        String tableStr="";
        for(int i=0;i<ocupado;++i){
            ficha = "["+tablero[i].getIzq()+"|"+tablero[i].getDer()+"]";
            tableStr+= ficha;
        }
        System.out.println("Jugador 1: "+j1+"     Juguador 2: "+j2+"\n\n\tI"+tableStr+"D\n");
    }
    
    public String[] imprimirBaraja(Ficha[] mano) //Para Dummie solamente, pide la jugada ademas
    {           
        String[] resul = new String [2];
        String numFicha="  ";
        String manoStr="";
        System.out.print("* - - - - - - - - - - - - - - - - - - - - - - *\n\n");
        for(int i=0;i<mano.length;++i){
            if(mano[i]!=null){
                numFicha+= "  "+i+"    ";
                manoStr += "  "+mano[i];
            }
        }
        System.out.println(numFicha);
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
    
    public void imprimirTorneo(Jugador[] jugador)
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
    public void imprimirTorneo(Jugador[] jugador, String ganador1,String ganador2)
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
    
    public void imprimirTorneo(Jugador[] jugador, String ganador1,String ganador2, String campeon)
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
