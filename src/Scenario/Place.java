package Scenario;/*
* Scenario.Place states
* ' ' => clear
* '=' => obstacle
* '*' => dirty
* */

public class Place {
    protected char state;
    protected boolean occupied;

    public Place(){
        this.state = ' ';
    }

    public Place(char state){
        this.state = state;
    }

    public void occupe(){
        this.occupied = true;
    }

    public void vacate(){
        this.occupied = false;
    }

    public boolean isWall() {
        return this.state == '=';
    }

    public boolean isDirty() {
        return this.state == '*';
    }

    public boolean isClear() {
        return this.state == ' ';
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public char getState(){
        return this.state;
    }

    public void setState(char state){
        this.state = state;
    }
}
