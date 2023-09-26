package Robot;

import Scenario.Scenario;

public class SimpleRobot extends Robot{

    public SimpleRobot(int posX, int posY, Scenario scenario) {
        super(posY, posY, scenario);
    }

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
            System.out.println("Limpei essa sala (" + this.posX + ", " + this.posY + ")");
        }
    }

    @Override
    public void clear() {
        if(this.sensors.isDirty()){
            this.scenario.getScenario()[posX][posY].setState(' ');
        }
    }
}
