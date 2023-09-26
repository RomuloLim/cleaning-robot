package Robot;

import Scenario.Scenario;

public class SimpleRobot extends Robot{

	private int cont = 0;
	
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
            cont++;
            System.out.println("Limpei essa sala (" + this.posX + ", " + this.posY + ")");
        }      
    }

    //Funcao de limpeza
    @Override
    public void clear() {
        if(this.sensors.isDirty()){
            this.scenario.getScenario()[posX][posY].setState(' ');
        }
    }
    
	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	@Override
	public int simpleAssessment() {
		return getCont();
	}
}
