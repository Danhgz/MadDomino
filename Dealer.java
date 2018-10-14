/**
 * Write a description of class Dealer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.lang.Math;
public class Dealer
{
    private Ficha [] mazo;
    private int ultimaFicha;
    
    private Interfaz interfaz;
    private Jugador jugador1;
    private Jugador jugador2;    
    private Ficha[] tablero;
    private int ocupado; //Cantidad de espacios ocupados en el tablero
    
    public Dealer(){
        init();
    }
    /*
     * @Funcion: inicializa la interfaz, el mazo, el tablero y el ocupado
     */
    private void init(){
        hacerMazo();
        interfaz = new Interfaz();     
        tablero = new Ficha[28];
        ocupado = 0;
    }  
    /*
     * @Funcion: crea el mazo de fichas
     */
    private void hacerMazo(){
        mazo = new Ficha[28]; 
        ultimaFicha = mazo.length - 1;
        int izq = 0;        
        for(int i = 0; i < mazo.length;)
        {         
            for(int j = izq; j <= 6 ; ++j){
                    mazo[i] = new Ficha();
                    mazo[i].setIzq(izq);
                    mazo[i].setDer(j);
                    ++i;
            }
            ++izq;
        }
        mezclar();
    }
    /*
     * @Funcion: repartir las fichas
     * @Return: retorna la ficha
     */
    public Ficha repartir()
    {           
        return mazo[ultimaFicha--];
    }

    /*
     * @Funcion: mezcla las fichas
     */
    private void mezclar(){
        int x; 
        int y;
        for(int i = 0; i < mazo.length; ++i){
            x  = (int) (Math.random() * mazo.length);
            y = (int) (Math.random() * mazo.length);
            swap(x,y);
        }
    }
    /*
     * @Funcion: cambia el valor izquierdo de la ficha con el derecho y viceversa
     * @Param: recibe el lado izquierdo de la ficha
     * @Param: recibe el lado izquierdo de la ficha
     */
    private void swap(int x, int y){
        Ficha temp = mazo[x];
        mazo[x] = mazo[y];
        mazo[y] = temp;
    }
    /*
     * @Funcion: repartela baraja de 7 fichas inicial a los jugadores
     * @Param: recibe a un jugador como parametro
     */
    public void repartirBarajaInicial(Jugador jugador){
        for(int i = 0; i< 7; ++i){
            jugador.setFicha(repartir());
        }
    }
    /*
     * @Funcion: ejecutar el juego 
     */
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
    /*
     * @Funcion: se encarga de ejecutar la modalidad de juego 1 contra 1
     */
    public void correr1v1(){  
        elegirJugadores();
        repartirBarajas();
        jugarPartida();
    }
    /*
     * @Funcion: se elige a los jugadores 1 y 2 
     */
    public void elegirJugadores()
    {      
        Jugador[] prototipo1 = new Jugador[]{new Dummy("Jugador 1"),new Virtual1(),new  Virtual2(),new Virtual3()}; 
        Jugador[] prototipo2 = new Jugador[]{new Dummy("Jugador 2"),new Virtual1(),new  Virtual2(),new Virtual3()}; //Workaround mieo en caso de 2 instancias iguales
        int[] jugador =  interfaz.imprimirMenuVersus();
        jugador1 =   prototipo1[jugador[0]];
        jugador2 =   prototipo2[jugador[1]];
    }
    
    //Reparte las barajas hasta que alguno tenga un par e iniciar la partida
    /*
     * @Funcion: reparte las barajas a el jugador 1 y el jugador 2
     */
    public void repartirBarajas(){       
        boolean par = false;       
        do{       
            repartirBarajaInicial(jugador1);
            repartirBarajaInicial(jugador2);    
            
            par = jugador1.tienePar() || jugador2.tienePar();       
        }while(!par); 
        
    }
    /*
     * @Funcion: sacar la primera ficha para iniciar juego, la cual debe ser par
     * @Return: retorna la ficha
     */
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
    /*
     * @Funcion: permite jugar la partida
     */
    public void jugarPartida()
    {
        int izq;
        int der;
        boolean salir=false;
        tablero[0] = new Ficha(sacarPrimeraFicha());
        ++ocupado;
        
        boolean quienJuega = true;
        do{              
            do{
                izq = tablero[0].getIzq();
                der = tablero[ocupado-1].getDer();
                if(quienJuega)
                {
                    if(!(jugador1.tieneJugada(izq,der))){
                        darComer(jugador1);                        
                    }
                    if(jugador1.tieneJugada(izq,der)){
                        salir=turno(jugador1);                     
                    }                    
                    quienJuega = false;
                }
                else
                {
                    if(!(jugador2.tieneJugada(izq,der))){
                        darComer(jugador2);                        
                    }
                    if(jugador2.tieneJugada(izq,der)){
                        salir=turno(jugador2);
                    }    
                    quienJuega = true;
                }                 
            }while(!hayGanador(jugador1,jugador2)&&!salir);
            if(!salir){
                interfaz.imprimirTablero(tablero,ocupado,jugador1.getPuntaje(),jugador2.getPuntaje());
            }
            if((jugador1.getPuntaje()<100 && jugador2.getPuntaje()<100)&&!salir){
                init();
                repartirBarajas();
                tablero[0] = new Ficha(sacarPrimeraFicha());
                ++ocupado;
            }
        }while((jugador1.getPuntaje()<100 && jugador2.getPuntaje()<100)&&!salir); 
    }  
    /*
     * @Funcion: da de "comer" fichas al jugador cuando no tiene movimientos
     * @Param: recibe como parametro al jugador
     */
    public void darComer(Jugador jugador)
    {            
        while(!(jugador.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer())) && ultimaFicha>-1){
            jugador.setFicha(mazo[ultimaFicha--]);
        }
    }
    /*
     * @Funcion: determina cual jugador debe jugar
     * @Param: recibe como parametro al jugador
     * @Return: el jugador que debe jugar
     */
    public boolean turno(Jugador jugador){
        interfaz.imprimirTablero(tablero,ocupado,jugador.getPuntaje(),jugador2.getPuntaje());        
        return jugada(jugador);
    }
    /*
     * @Funcion:
     * @Param: el jugador
     * @Return: true si hay jugada
     */
    private boolean jugada(Jugador jugador)
    {       
        String[] jugada;
        boolean err=false;
        boolean salir=false;        
        if(jugador instanceof Dummy)
        {
            do{
                jugada = interfaz.imprimirBaraja(jugador,err);
                salir = jugada[0].equalsIgnoreCase("s")||jugada[1].equalsIgnoreCase("s");
                err=false;
                if(!salir&&!esJugadaValida(jugador,jugada)){
                    err=true;
                }
                else if(!salir&&esJugadaValida(jugador,jugada)){
                    jugador.hacerJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer());
                }
            }while(!(salir||!err)); 
        }
        else{
            jugada = jugador.hacerJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer());
        }  
        if(!salir){
            ponerFicha(jugada,jugador);
        }
        return salir;
    }
    /*
     * @Funcion: evalua si la jugada realiza por un jugador es valida
     * @Param: recibe el jugador
     * @Param: recibe la jugada realizada
     * @Return: true si la jugada es valida
     */
    public boolean esJugadaValida(Jugador jugador, String[] jugada)
    {
        boolean valida=false;
        int izq;
        int der;
        int pos= Integer.parseInt(jugada[0]);
        if(pos>=0 && pos<21 && jugador.getFicha(pos)!=null)
        {
            izq=jugador.getFicha(Integer.parseInt(jugada[0])).getIzq();
            der=jugador.getFicha(Integer.parseInt(jugada[0])).getDer();                        
            if(jugada[1].equalsIgnoreCase("i")){
                valida = izq==tablero[0].getIzq()||der==tablero[0].getIzq();
            }
            else if(jugada[1].equalsIgnoreCase("d")){
                valida = izq==tablero[ocupado-1].getIzq()||der==tablero[ocupado-1].getIzq();
            }
        }
        return valida;
    }
    /*
     * @Funcion: se encarga de poner la ficha en el tablero de juego
     * @Param: recibe la jugada realizada
     * @Param: recibe como parametro al jugador
     */
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
    /*
     * @Funcion: determina si hay un ganador
     * @Param: el jugador 1 
     * @Param: el jugador 2 
     * @Return: true si hay un ganador
     */
    public boolean hayGanador(Jugador jugador1,Jugador jugador2)
    {
        boolean ganador = false;
        int izq=tablero[0].getIzq();
        int der=tablero[ocupado-1].getDer();
        if(ganador= !jugador1.tieneFichas()||(!jugador2.tieneJugada(izq,der)&&!jugador1.tieneJugada(izq,der)&&jugador1.getValor()<jugador2.getValor())){
            jugador1.setPuntaje(jugador2.getValor());
        }
        else if(ganador= !jugador2.tieneFichas()||(!jugador2.tieneJugada(izq,der)&&!jugador1.tieneJugada(izq,der)&&jugador2.getValor()<jugador1.getValor())){
            jugador2.setPuntaje(jugador1.getValor());           
        }  
        else if(!jugador2.tieneJugada(izq,der)&&!jugador1.tieneJugada(izq,der)&&jugador2.getValor()==jugador1.getValor()){
            ganador=true;          
        } 
        return ganador;
    }   
        
   
    /*
     * @Funcion: Ordena el vector para que se pueda insertar una ficha a la izquierda (en la posicion 0)
     */
    private void ordenarTablero()
    {
        for(int i=ocupado-1;i>=0;--i){
            tablero[i+1] = new Ficha(tablero[i]);
        }
    }
    /*
     * @Funcion: inicializa el torneo
     */
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
}


        


