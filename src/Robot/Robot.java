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

	public void clear() {
		if(this.sensors.isDirty()){
			this.scenario.getScenario()[posX][posY].setState(' ');
			this.scenario.dirtyAmount--;
		}
	}

	protected boolean canMoveUp(){
		return this.sensors.nextPlace(scenario.getScenario()[posX + 1][posY]);
	}

	protected boolean moveUp(){
		this.posX++;
		this.points++;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	protected boolean canMoveDown(){
		return this.sensors.nextPlace(scenario.getScenario()[posX - 1][posY]);
	}

	protected boolean moveDown(){
		this.posX--;
		this.points++;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	protected boolean canMoveLeft(){
		return this.sensors.nextPlace(scenario.getScenario()[posX][posY + 1]);
	}

	protected boolean moveLeft(){
		this.posY++;
		this.points++;
		sensors.setPlace(this.scenario.getScenario()[posX][posY]);
		return true;
	}

	protected boolean canMoveRight(){
		return this.sensors.nextPlace(scenario.getScenario()[posX][posY - 1]);
	}

	protected boolean moveRight(){
		this.posY--;
		this.points++;
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