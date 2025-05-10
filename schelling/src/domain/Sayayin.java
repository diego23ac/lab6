package domain;
import java.awt.Color;

/**
 * Write a description of class Sayayin here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sayayin extends Person{
    public Sayayin(City city, int row, int column){
        super(city, row, column);
        this.color = Color.black;
        this.state = Agent.INDIFFERENT;
    }
    
    /*
     * Me realiza el cambio de la posición a cualquier parte del mapa
     */
    @Override
    public void change(){
        City city = getCity();
        int nRow = getRow();
        int nColumn = getColumn();
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int arbitrarilyDirection = (int)(Math.random() * directions.length);
        
        int newR = nRow + directions[arbitrarilyDirection][0];
        int newC = nColumn + directions[arbitrarilyDirection][0];
        
        if(((newR >= 0 && newR < 25) && (newC >= 0 && newC < 25)) && city.isEmpty(newR, newC)){
            city.setItem(nRow, nColumn, null);
            
            setRow(newR); 
            setColumn(newC);
            
            city.setItem(newR, newC, this);
        }
    }
    
    /*
     * Decide el estado de animo del caminante
     */
    @Override
    public void decide(){
        change();
        state = Agent.HAPPY;
    }
    
    /*
     * Se sobre escribe la forma que va a tener el sayayin
     */
    @Override
    public int shape(){
        return ROUND;
    }
    
    /*
     * Nos el estado del Sayayin
     */
    public char getState(){
        return this.state;
    }
}
