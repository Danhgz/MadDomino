/**
 * Write a description of class Domino here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Domino
{    
    private Interfaz interfaz;
    private Dealer dealer;
    private Jugador jugador1;
    private Jugador jugador2;    
    private Ficha[] tablero;
    private int ocupado; //Cantidad de espacios ocupados en el tablero
    
    public Domino() {
        interfaz = new Interfaz();
        dealer = new Dealer();        
        tablero = new Ficha[28];
        ocupado = 0;
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
    
    public void elegirJugadores()
    {      
        Jugador[] prototipo1 = new Jugador[]{new Dummy(),new Virtual1(),new  Virtual2(),new Virtual3()}; 
        Jugador[] prototipo2 = new Jugador[]{new Dummy(),new Virtual1(),new  Virtual2(),new Virtual3()}; //Workaround mieo en caso de 2 instancias iguales
        int[] jugador =  interfaz.imprimirMenuVersus();
        jugador1 =   prototipo1[jugador[0]];
        jugador2 =   prototipo2[jugador[1]];
    }
    
    public void correr1v1(){       
        boolean salir=false;
        do{
            elegirJugadores();
            repartirBarajas();
            salir=jugarPartida();
        }while(!salir);
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
    
    public boolean jugarPartida()
    {
        boolean salir=false;
        tablero[0] = new Ficha(sacarPrimeraFicha());
        ++ocupado;  
        do{
            interfaz.imprimirTablero(tablero,ocupado,jugador1.getPuntaje(),jugador2.getPuntaje());
            jugada(jugador1);
            interfaz.imprimirTablero(tablero,ocupado,jugador1.getPuntaje(),jugador2.getPuntaje());
            jugada(jugador2);
        }while((jugador1.getPuntaje()<100 && jugador2.getPuntaje()<100)&&!salir);
        
        return salir;
    }
    
      
    private void jugada(Jugador jugador)
    {       
        String[] jugada;
        if(jugador instanceof Dummy){
            jugada = interfaz.imprimirBaraja(jugador.getMano());
        }
        else{
            jugada= jugador.hacerJugada();
        }  
        ponerFicha(jugada,jugador);
    }
    
    private void ponerFicha(String[] jugada,Jugador jugador)
    {             
        if(jugada[1].equalsIgnoreCase("I")){
            ordenarTablero();
            tablero[0] = jugador.getFicha(Integer.parseInt(jugada[0]));            
        }
        else{
            tablero[ocupado] = jugador.getFicha(Integer.parseInt(jugada[0]));        
        }
        jugador.sacarFicha(Integer.parseInt(jugada[0]));
        ++ocupado;          
    }
    
    private Ficha sacarPrimeraFicha(){
        Ficha primerFicha = new Ficha(-1,-1); 
        int pos=0;
        int dueno=0;
        for(int i=0;i<7;++i){                       
            if(jugador1.getFicha(i).getEsPar()&& jugador1.getFicha(i).getIzq()>primerFicha.getIzq()){
                primerFicha = new Ficha(jugador1.getFicha(i));
                pos= i;
                dueno=1;
            }
            if(jugador2.getFicha(i).getEsPar()&& jugador2.getFicha(i).getIzq()>primerFicha.getIzq()){
                primerFicha = new Ficha(jugador2.getFicha(i)); 
                pos= i;
                dueno=2;
            }
            ++i;
        }
        
        if(dueno==1){
            jugador1.sacarFicha(pos);
        }
        else{
            jugador2.sacarFicha(pos);
        }
        return primerFicha;
    }
    
            //Civil War 
   /*public Ficha sacarPrimeraFicha(){
        boolean esPar = false;
        int i = 0;
        Ficha primerFicha = new Ficha();
        
        while(!esPar && i < 7){
            if(jugador1.getFicha(i).getEsPar() && jugador1.getFicha(i).getIzq() == i){
                primerFicha = new Ficha(jugador1.getFicha(i));
                jugador1.sacarFicha(i);
                esPar= true;
            }
            else if(jugador2.getFicha(i).getEsPar() && jugador2.getFicha(i).getIzq() == i){
                primerFicha = new Ficha(jugador2.getFicha(i));
                jugador2.sacarFicha(i);
                esPar= true;
            }
            ++i;
        }
        return primerFicha;
    }*/
    
    //Ordena el vector para que se pueda insertar una ficha a la izquierda (en la posicion 0)
    
    private void ordenarTablero()
    {
        for(int i=ocupado-1;i>=0;--i)
        {
            tablero[i+1] = new Ficha(tablero[i]);
        }
    }
    
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
}
