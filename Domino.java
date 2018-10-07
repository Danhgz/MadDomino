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
    private int ocupado; //Cantidad de espacios ocupados en el tablero
    
    public Domino() {
        interfaz = new Interfaz();
        dealer = new Dealer();
        jugador = new String[2];
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
    

    public void correr1v1(){
        jugador =  interfaz.imprimirMenuVersus(jugador);
        jugador1 = new JugadorVirtual(jugador[0]);
        jugador2 = new JugadorVirtual(jugador[1]);
        
        repartirBarajas();
        
        tablero[0] = new Ficha(sacarPrimeraFicha());
        ++ocupado;  
        do{
            
        }while(jugador1.getPuntaje()<100||jugador2.getPuntaje()<100);
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
    
    /*private Ficha sacarPrimeraFicha(){
        Ficha primerFicha = new Ficha(-1,-1); 
        int pos=0;
        int dueno=0;
        for(int i=0;i<7;++i){                       
            if(jugador1.getFicha(i).getIzq() == jugador1.getFicha(i).getDer()&& jugador1.getFicha(i).getIzq()>primerFicha.getIzq()){
                primerFicha = new Ficha(jugador1.getFicha(i));
                pos= i;
                dueno=1;
            }
            if(jugador2.getFicha(i).getIzq() == jugador2.getFicha(i).getDer()&& jugador2.getFicha(i).getIzq()>primerFicha.getIzq()){
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
    }*/
            //Civil War 
   public Ficha sacarPrimeraFicha(){
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
    }
    
    //Ordena el vector para que se pueda insertar una ficha a la izquierda (en la posicion 0)
    private void ordenarTablero(Ficha ficha)
    {
        for(int i=ocupado;i>=0;++i)
        {
            tablero[i+1] = new Ficha(tablero[i]);
        }
    }
    
    public void correrTorneo()  {
        //  laspatasdeMauro.repartir(); 
    }
}
