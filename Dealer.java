
/**
 * Write a description of class Dealer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dealer
{
    private Ficha [] mazo;
   
    /**
     * Constructor for objects of class Dealer
     */
    public Dealer(){
    }
    
    private void init(){
        mazo = new Ficha[28];
        int izq  = 0;
        for(int i = 0;i<mazo.length;++i){
            for(int j = izq;i<=6;j++){
                mazo[i].setIzq(izq);
                mazo[i].setDer(j);
            }
            ++izq;
        }
    }


}
