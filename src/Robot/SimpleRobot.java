package Robot;

import Scenario.Scenario;

public class SimpleRobot extends Robot{
	
    public SimpleRobot(int posX, int posY, Scenario scenario) {
        super(posY, posY, scenario);
    }

    //Funcao para a movimentacao aleatoria do robo
    @Override
    public void move() {
        int random = ((int) (Math.random() * 10) % 4);
        boolean ableToMove = switch (random) {
            case 0 -> canMoveUp() && moveUp();
            case 1 -> canMoveDown() && moveDown();
            case 2 -> canMoveLeft() && moveLeft();
            case 3 -> canMoveRight() && moveRight();
            default -> false;
        };
        
        if(!ableToMove){
            move();
        } else if (this.sensors.isDirty()){
            clear();
            scenario.dirtyAmount--;
        }      
    }

    //Funcao de limpeza
    @Override
    public void clear() {
        if(this.sensors.isDirty()){
            this.scenario.getScenario()[posX][posY].setState(' ');
            this.points += 10;

            System.out.println("Limpei essa sala (" + this.posX + ", " + this.posY + ")");
        }
    }
}
