package domain;
import java.awt.Color;

/**
 * Write a description of class Walker here.
 *
 * @author Roger Duran
 * @version 1
 */
public class Walker extends Person{
    public Walker(City city, int row, int column){
        super(city, row, column);
        this.color = Color.green;
        this.state = Agent.INDIFFERENT;
    }
    
    /*
     * El caminante se mueva hacia el norte.
     */
    @Override
    public void change(){
        City city = getCity();
        int northRow = getRow();
        int column = getColumn();
        
        if(city.isEmpty(northRow - 1, column)){
            city.setItem(northRow, column , null);
            setRow(northRow - 1);
            state = Agent.INDIFFERENT;
            city.setItem(getRow(), column, this);
        }
    }
    
    /*
     * Decide el estado de animo del caminante
     */
    @Override
    public void decide() {
        change();
        City city = getCity();
        int row = getRow(); 
        int column = getColumn();
        boolean n = getNeightbors(city, row, column);
        if (n) {
            state = Agent.HAPPY;
        } else if(!city.isEmpty(row - 1, column)){
            state = Agent.DISSATISFIED;
        }
    }
    
    @Override
    public int shape(){
        return SQUARE;
    }
    
    /*
     * Verifica si hay vecinos abyacentes en las celdas de los Walkers
     */
    public boolean getNeightbors(City city, int r, int c){
        boolean itemFound = false;
        
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                if (city.inLocations(r + dr, c + dc) && 
                    !city.isEmpty(r + dr, c + dc)) {
                    itemFound = true;
                    break;
                }
            }
            if (itemFound) break;
        }
        return itemFound;
    }
    
    public char getState(){
        return this.state;
    }
}   
