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
        hacerMazo();
        interfaz = new Interfaz();     
        tablero = new Ficha[28];
        ocupado = 0;
    }
    
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
            --i;
            ++izq;
        }
        mezclar();
    }
    
    public Ficha repartir()
    {           
        return mazo[ultimaFicha--];
    }
    
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
            jugador.setFicha(repartir(),i);
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
            repartirBarajaInicial(jugador1);
            repartirBarajaInicial(jugador2);    
            
            par = jugador1.tienePar() || jugador2.tienePar();       
        }while(!par); 
        
    }
    
    public boolean jugarPartida()
    {
        boolean finPartida=false;
        tablero[0] = new Ficha(sacarPrimeraFicha());
        ++ocupado;  
        do{
            interfaz.imprimirTablero(tablero,ocupado,jugador1.getPuntaje(),jugador2.getPuntaje());
            jugada(jugador1);
            interfaz.imprimirTablero(tablero,ocupado,jugador1.getPuntaje(),jugador2.getPuntaje());
            jugada(jugador2);
        }while((jugador1.getPuntaje()<100 && jugador2.getPuntaje()<100)&&!finPartida);
        
        return  finPartida;
    }
    
      
    private void jugada(Jugador jugador)
    {       
        String[] jugada;
        boolean err = false;
        if(jugador instanceof Dummy){
            do
            jugada = interfaz.imprimirBaraja(jugador.getMano());
            while(err);
        }
        else{
            jugada = new String[]{"",""};//aqui va jugada alv
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