/*
* Place states
* ' ' => clear
* '=' => obstacle
* '*' => dirty
* */

public class Place {
    protected char state;

    public Place(){
        this.state = ' ';
    }

    public Place(char state){
        this.state = state;
    }

    public char getState(){
        return this.state;
    }

    public void setState(char state){
        this.state = state;
    }
}
