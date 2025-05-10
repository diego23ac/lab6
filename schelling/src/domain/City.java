package domain;
import java.util.*;

/*No olviden adicionar la documentacion*/
public class City{
    static private int SIZE=25;
    private Item[][] locations;
    
    public City() {
        locations=new Item[SIZE][SIZE];
        for (int r=0;r<SIZE;r++){
            for (int c=0;c<SIZE;c++){
                locations[r][c]=null;
            }
        }
        someItems();
    }

    public int  getSize(){
        return SIZE;
    }

    public Item getItem(int r,int c){
        return locations[r][c];
    }

    public void setItem(int r, int c, Item e){
        locations[r][c]=e;
    }

    /*
     * Creacion de Adan y Eva en posiciones especificas
     */
    public void someItems(){
        new Person(this, 10, 10);
        new Person(this, 15, 15);
        new Walker(this, 10, 2);
        new Walker(this, 13, 9);
        new TrafficLight(this, 0, 0);
        new TrafficLight(this, 0, SIZE - 1); //SIZE - 1 utilizado para el iltimo valor de la tabla sin ser nulo
        new Sayayin(this, 9, 0);
        new Sayayin(this, 10, 4);
        new MotorByke(this, 15, 15);
    }
    
    public int neighborsEquals(int r, int c){
        int num=0;
        if (inLocations(r,c) && locations[r][c]!=null){
            for(int dr=-1; dr<2;dr++){
                for (int dc=-1; dc<2;dc++){
                    if ((dr!=0 || dc!=0) && inLocations(r+dr,c+dc) && 
                    (locations[r+dr][c+dc]!=null) &&  (locations[r][c].getClass()==locations[r+dr][c+dc].getClass())) num++;
                }
                }
        }
        return num;
    }
    
    public boolean isEmpty(int r, int c){
        return (inLocations(r,c) && locations[r][c]==null);
    }    
     
    public boolean inLocations(int r, int c){
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /*
     * Movimiento de la parson para cambiar su estado 
     */
    public void ticTac(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(locations[i][j] != null){
                    locations[i][j].decide(); 
                }
            }
        }
    }
}