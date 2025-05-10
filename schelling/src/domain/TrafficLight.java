package domain;
import java.awt.Color;

/**
 * Write a description of class Semaphore here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TrafficLight extends Agent implements Item{
    private City city;
    private int state = 0;
    private int row, column;
    private static final Color[] STATECOLORS = {Color.RED, Color.YELLOW, Color.GREEN, Color.YELLOW};
    
    public TrafficLight(City city, int row, int column){
        this.city = city;
        this.row = row;
        this.column = column;
        city.setItem(row, column, this);
    }
    
    @Override
    public void decide(){
        state = (state + 1) % STATECOLORS.length;
    }
    
    @Override
    public Color getColor(){
        return STATECOLORS[this.state];
    }
    
    @Override
    public int shape(){
        return Item.ROUND;
    }
    
    @Override
    public boolean isActive(){
        return true;
    }
}
