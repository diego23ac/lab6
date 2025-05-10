package domain;
import java.awt.Color;

/**
 * Write a description of class MotorByke here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MotorByke extends Agent implements Item{
    private City city;
    private int state = 0;
    private int row, column;
    private final Color color = Color.magenta;
    
    public MotorByke(City city, int row, int column){
        this.city = city;
        this.row = row;
        this.column = column;
        city.setItem(row, column, this);
    }
    
    @Override
    public void decide() {
        City city = getCity();
        int currentRow = getRow();
        int currentColumn = getColumn();
        this.state += 1;
        if (state % 2 != 0) {
            city.setItem(currentRow, currentColumn, null);
        } 
        else {
            city.setItem(currentRow, currentColumn, null);
            int newRow, newColumn;
            boolean posicionValida = false;
            int intentos = 0;
            final int MAX_INTENTOS = 100;
            do {
                newRow = (int)(Math.random() * 25);
                newColumn = (int)(Math.random() * 25);
                posicionValida = city.isEmpty(newRow, newColumn);
                intentos++;
                if (intentos >= MAX_INTENTOS && !posicionValida) {
                    newRow = currentRow;
                    newColumn = currentColumn;
                    break;
                }
            } while (!posicionValida);
            row = newRow;
            column = newColumn;
            city.setItem(newRow, newColumn, this);
        }
    }

    
    public City getCity(){
        return this.city;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getColumn(){
        return this.column;
    }
    
    @Override
    public Color getColor() {
        return this.color; 
    }
    
    @Override
    public int shape() {
        return Item.ROUND; 
    }
    
    @Override
    public boolean isActive() {
        return state % 2 == 0;
    }
}
