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
    private boolean[] repetido;
   
    /**
     * Constructor for objects of class Dealer
     */
    public Dealer(){
        init();
    }
    
    private void init(){
        mazo = new Ficha[28];
        repetido = new boolean[28];        
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
    }
    
    public Ficha repartir()
    {       
        int numFicha;
        do
        {
            numFicha = (int)(Math.random()*28);      
        }while(repetido[numFicha]);
        
        repetido[numFicha]= true;        
        return mazo[numFicha];
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