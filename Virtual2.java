
/**
 * Write a description of class Virtual2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Virtual2 implements Jugador
{
    private Ficha[] baraja;
    private String nombre;
    private int puntaje;
    private int ocupado;
    //CONSTRUCTOR
    public Virtual2(){
        baraja = new Ficha[21]; 
        ocupado=0;
        puntaje = 0;
        for(int i = 0; i < baraja.length ; ++i){
            baraja[i] = new Ficha();
        }  
    }
    
    public void setFicha(Ficha ficha){
        this.baraja[ocupado].setIzq(ficha.getIzq());
        this.baraja[ocupado].setDer(ficha.getDer());
        ++ocupado;
    }
    
    public int getPuntaje(){
        return this.puntaje;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    
    public Ficha[] getMano(){
        return baraja;
    }   

    /*
     * @Funcion: verifica si hay movimientos posibles con las fichas disponibles
     * en la baraja
     * @Param: se recibe el int i como parametro que es la posicion de la ficha
     * @Param: se recibe el lado de la ficha 
     * @Return: retorna un true si se puede jugar, de lo contrario un false
     */

    private boolean sePuedeJugar(int i, int lado){
	return baraja[i].getIzq() == lado || baraja[i].getDer() == lado; 
    }
    /*
     * @Funcion: posee la estrategia del jugador virtual 2
     * @Param: valor izquierdo de la ficha
     * @Param: valor derecho de la ficha
     * @Return: retorna un String con la ficha que se juega
     */
    public String[] hacerJugada(int izq, int der)
    {
        String[] jugada = {"-1","-1"};
        String jugables="";
        int posMayor;
        int posMenor;
        String mayorStr="";
        String menorStr="";
        if(izq>der){
            posMayor=izq;
            posMenor=der;
            mayorStr= "I";
            menorStr="D";
        }
        else{
            posMayor=der;
            posMenor=izq;
            mayorStr= "D";
            menorStr="I";
        }
        
        for(int i=0;i<ocupado;++i)//Si hay pares
        {
            if(baraja[i].getEsPar() && baraja[i].getIzq() == posMayor){
                jugada[0] = "" + i;
                jugada[1] = mayorStr;
            }
            else if(baraja[i].getEsPar() && baraja[i].getIzq() == posMenor ){
                jugada[0] = "" + i;
                jugada[1] = menorStr;
            } 
            else if(sePuedeJugar(i,posMayor)||sePuedeJugar(i,posMayor)){//Marca las fichas probables si no hay pares
                jugables+= ""+i;
            }            
        }
        
        if(jugada[0].equals("-1")){//Si no hay jugada todavia
            char[] pos = jugables.toCharArray();
            int posInt;
            jugada[0]= ""+pos[0]; 
            for(int i=0;i<pos.length;++i){
                posInt= Integer.parseInt(String.valueOf(pos[i]));
                if(baraja[posInt].getValor() > baraja[Integer.parseInt(jugada[0])].getValor()){
                    jugada[0] = "" + pos[i];
                }           
            }
            int mejorFicha= Integer.parseInt(jugada[0]);
            if(baraja[mejorFicha].getIzq() == der){
               jugada[1] = "D";
            }
            else  if(baraja[mejorFicha].getDer() == izq){
               jugada[1] = "I";
            }
                else if(baraja[mejorFicha].getDer() == der ){
                    baraja[mejorFicha].swap();
                    jugada[1] = "D";
                }
                    else if(baraja[mejorFicha].getIzq() == izq ){
                        baraja[mejorFicha].swap();
                        jugada[1] = "I";
                    }
        }
        return jugada;
    }
    /*
     * @Funcion: verifica los valores de la ficha del jugador y si puede
     * realizar movimientos
     * @Param: se recibe el valor izquierdo de la ficha
     * @Param: se recibe el valor derecho de la ficha
     * @Return: retorna true si tiene jugada, false de lo contrario
     */
    public boolean tieneJugada(int izq, int der)
    {
        boolean tiene=false;
        for(int i = 0; i < ocupado; ++i){            
            if(baraja[i].getIzq() == izq || baraja[i].getIzq() == der||baraja[i].getDer() == izq||baraja[i].getDer() == der){
                tiene = true; 
            }
        }
        return tiene;
    }
    
    
    /*
     * @Funcion: Verifica si tiene una ficha par
     * @Return: true si es par
     */
    public boolean tienePar(){
        boolean existe = false;
        int i = 0;
        while(i < 7 && !existe){
            existe = baraja[i].getIzq() == baraja[i].getDer();
            i++;
        }
        
        return existe;
    }
    /*
     * @Funcion: saca la ficha y no puede utilizarse mas
     * @Param: el i representa la posicion de la ficha
     */
    public void sacarFicha(int i){
        baraja[i] = null;
        for(int j=i;j<ocupado-1;++j)
        {
            swap(j,j+1);
        }
        --ocupado;
    }
    /*
     * @Funcion: cambia el valor izquierdo de la ficha a la derecha y viceversa
     * @Param: recibe la posicion izquierda
     * @Param: recibe la posicion derecha
     */
    private void swap(int x, int y){
        Ficha temp = baraja[x];
        baraja[x] = baraja[y];
        baraja[y] = temp;
    }   
    
    public Ficha getFicha(int i){
        return baraja[i];
    }
    /*
     * @Funcion: verifica si al jugador le quedan fichas
     * @Return: true si aun quedan fichas
     */
    public boolean tieneFichas(){
        boolean tiene = false;
        for(Ficha ficha : baraja){
            if(ficha!=null){
                tiene=true;
            }
        }
        return tiene;
    }
        
    public int getValor(){
        int suma = 0;
        for(Ficha ficha : baraja){
            if(ficha!=null){
                suma += ficha.getValor();
            }
        }
        return suma;
    }
    
}
