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
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    private void init(){
        hacerMazo();
        interfaz = new Interfaz();     
        tablero = new Ficha[28];
        ocupado = 0;
    }  
    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    private void hacerMazo(){
        mazo = new Ficha[28]; 
        ultimaFicha = mazo.length - 1;
        int izq = 0;        
        for(int i = 0; i < mazo.length;++i)
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
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
     */
    public Ficha repartir()
    {           
        return mazo[ultimaFicha--];
    }

    /*
     * @Funcion:
     * @Param:
     * @Param:
     * @Return:
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
    
    private void swap(int x, int y){
        Ficha temp = mazo[x];
        mazo[x] = mazo[y];
        mazo[y] = temp;
    }
   
    public void repartirBarajaInicial(Jugador jugador){
        for(int i = 0; i< 7; ++i){
            jugador.setFicha(repartir());
        }
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
        boolean salir=false;
        do{
            elegirJugadores();
            repartirBarajas();
            salir=jugarPartida();
        }while(!salir);
    }
    
    public void elegirJugadores()
    {      
        Jugador[] prototipo1 = new Jugador[]{new Dummy(),new Virtual1(),new  Virtual2(),new Virtual3()}; 
        Jugador[] prototipo2 = new Jugador[]{new Dummy(),new Virtual1(),new  Virtual2(),new Virtual3()}; //Workaround mieo en caso de 2 instancias iguales
        int[] jugador =  interfaz.imprimirMenuVersus();
        jugador1 =   prototipo1[jugador[0]];
        jugador2 =   prototipo2[jugador[1]];
    }
    
    //Reparte las barajas hasta que alguno tenga un par e iniciar la partida
    public void repartirBarajas(){       
        boolean par = false;       
        do{       
            repartirBarajaInicial(jugador1);
            repartirBarajaInicial(jugador2);    
            
            par = jugador1.tienePar() || jugador2.tienePar();       
        }while(!par); 
        
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
    
    public boolean jugarPartida()
    {
        boolean finPartida=false;
        tablero[0] = new Ficha(sacarPrimeraFicha());
        ++ocupado;
        
        boolean quienJuega = true;
        do{              
            do{
                if(quienJuega){
                    if(!(jugador1.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer()))){
                        darComer(jugador1);                        
                    }
                    if(jugador1.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer())){
                        turno(jugador1);                     
                    }                    
                    quienJuega = false;
                }
                else{
                    if(!(jugador2.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer()))){
                        darComer(jugador2);                        
                    }
                    if(jugador2.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer())){
                        turno(jugador2);
                    }    
                    quienJuega = true;
                } 
                if(hayGanador(jugador1,jugador2)){
                    init();
                    repartirBarajas();
                    tablero[0] = new Ficha(sacarPrimeraFicha());
                    ++ocupado;
                }
            }while(!hayGanador(jugador1,jugador2));
        }while((jugador1.getPuntaje()<100 && jugador2.getPuntaje()<100)&&!finPartida);      
        return  finPartida;
    }  
    
    public void darComer(Jugador jugador)
    {            
        while(!(jugador.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer()))&&ultimaFicha>-1){
            jugador.setFicha(mazo[ultimaFicha--]);
        }
    }
    
    public void turno(Jugador jugador){
        interfaz.imprimirTablero(tablero,ocupado,jugador.getPuntaje(),jugador2.getPuntaje());
        jugada(jugador); 
    }
    
    private void jugada(Jugador jugador)
    {       
        String[] jugada;
        boolean err = false;
        if(jugador instanceof Dummy){
            do{
                jugada = interfaz.imprimirBaraja(jugador.getMano(),err);
                err=false;
                if(!esJugadaValida(jugador,jugada)){
                    err=true;
                }
            }while(err);
        }
        else{
            jugada = jugador.hacerJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer());
        }  
        ponerFicha(jugada,jugador);
    }
    
    public boolean esJugadaValida(Jugador jugador, String[] jugada)
    {
        boolean valida=false;
        int izq=jugador.getFicha(Integer.parseInt(jugada[0])).getIzq();
        int der=jugador.getFicha(Integer.parseInt(jugada[0])).getDer();
        if(jugada[1].equalsIgnoreCase("i")){
            valida = izq==tablero[0].getIzq()||der==tablero[0].getIzq();
        }
        else if(jugada[1].equalsIgnoreCase("d")){
            valida = izq==tablero[ocupado-1].getIzq()||der==tablero[ocupado-1].getIzq();
        }
        return valida;
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
    
    public boolean hayGanador(Jugador jugador1,Jugador jugador2)
    {
        boolean ganador = false;
        if(ganador = jugador1.getValor()==0||!jugador2.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer())){
            jugador1.setPuntaje(jugador2.getValor());
        }
        else{
            if(ganador =jugador2.getValor()==0||!jugador1.tieneJugada(tablero[0].getIzq(),tablero[ocupado-1].getDer())){
                jugador2.setPuntaje(jugador1.getValor());
            }
        }        
        return ganador;
    }
    
    
        
    //Ordena el vector para que se pueda insertar una ficha a la izquierda (en la posicion 0)    
    private void ordenarTablero()
    {
        for(int i=ocupado-1;i>=0;--i){
            tablero[i+1] = new Ficha(tablero[i]);
        }
    }
    
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
    
    public int pedirInicio(int j1, int j2)
    {
        int caso;
        if(j1==-1&&j2==-1){
            caso = 0;
        }
        else
        {
            if(j1>j2){
                caso = 1;
            }
            else{
                caso = 2;
            }
        }
        return caso;
    }

}


        


