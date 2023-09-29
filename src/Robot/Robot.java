package Robot;
import Scenario.Scenario;

public abstract class Robot extends Sensors {
	protected int points;
	protected int posX;
	protected int posY;

	protected Sensors sensors;

	protected Scenario scenario;

	public Robot(int posX, int posY, Scenario scenario) {
		super(scenario.getScenario()[1][1]);
		this.points = 0;
		this.posX = posX;
		this.posY = posY;
		this.scenario = scenario;
		this.sensors = new Sensors(scenario.getScenario()[posX][posY]);
	}

	public abstract void move();

	//Funcao para limpar a sujeira
	public void clear() {
		if(this.sensors.isDirty()){
			this.scenario.getScenario()[posX][posY].setState(' ');
		}
	}

	//Funcao para ver a possibilidade de se movimentar para cima
	protected boolean canMoveUp(){
		return this.sensors.nextPlace(scenario.getScenario()[posX + 1][posY]);
	}

	//Funcao para mover para cima
	protected boolean moveUp(){
		this.posX++;
		this.points--;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}
	
	//Funcao para ver a possibilidade de se movimentar para baixo
	protected boolean canMoveDown(){
		return this.sensors.nextPlace(scenario.getScenario()[posX - 1][posY]);
	}

	//Funcao para mover para baixo
	protected boolean moveDown(){
		this.posX--;
		this.points--;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	//Funcao para ver a possibilidade de se movimentar para a esquerda
	protected boolean canMoveLeft(){
		return this.sensors.nextPlace(scenario.getScenario()[posX][posY + 1]);
	}

	//Funcao para mover para a esquerda
	protected boolean moveLeft(){
		this.posY++;
		this.points--;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	//Funcao para ver a possibilidade de se movimentar para a direita
	protected boolean canMoveRight(){
		return this.sensors.nextPlace(scenario.getScenario()[posX][posY - 1]);
	}

	//Funcao para mover para a direita
	protected boolean moveRight(){
		this.posY--;
		this.points--;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	public int getPoints() {
		return points;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Scenario getScenario() {
		return scenario;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}