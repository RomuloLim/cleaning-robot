package Robot;

import Scenario.Scenario;

public class SimpleRobot extends Robot{

    public SimpleRobot(int posX, int posY, Scenario scenario) {
        super(scenario);
    }

    @Override
    public void move() {
        int random = ((int) (Math.random() * 10) % 4);
        boolean ableToMove = switch (random) {
            case 0 -> moveUp();
            case 1 -> moveDown();
            case 2 -> moveLeft();
            case 3 -> moveRight();
            default -> false;
        };

        if(!ableToMove){
            move();
        } else if (this.sensors.isDirty()){
            clear();
            System.out.println("Limpei esse caralho de sala");
        }
    }

    @Override
    public void clear() {
        if(this.sensors.isDirty()){
            this.scenario.getScenario()[posX][posY].setState(' ');
        }
    }
}
