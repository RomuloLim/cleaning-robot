package Robot;
import Scenario.Scenario;

public abstract class Robot extends Sensors {
	protected int points;
	protected int posX;
	protected int posY;

	protected Sensors sensors;

	protected Scenario scenario;

	public Robot(Scenario scenario) {
		super(scenario.getScenario()[1][1]);
		this.points = 0;
		this.posX = 1;
		this.posY = 1;
		this.scenario = scenario;
		this.sensors = new Sensors(scenario.getScenario()[posX][posY]);
	}

	public abstract void move();

	public abstract void clear();

	protected boolean moveUp(){
		if(this.sensors.nextScenario(scenario.getScenario()[posX + 1][posY])){
			this.posX++;
			this.points++;
			sensors.setPlace(this.scenario.getScenario()[posX][posY]);
			return true;
		}

		return false;
	}

	protected boolean moveDown(){
		if(this.sensors.nextScenario(scenario.getScenario()[posX - 1][posY])){
			this.posX--;
			this.points++;
			sensors.setPlace(this.scenario.getScenario()[posX][posY]);
			return true;
		}

		return false;
	}

	protected boolean moveLeft(){
		if(this.sensors.nextScenario(scenario.getScenario()[posX][posY + 1])){
			this.posY++;
			this.points++;
			sensors.setPlace(this.scenario.getScenario()[posX][posY]);
			return true;
		}

		return false;
	}

	protected boolean moveRight(){
		if(this.sensors.nextScenario(scenario.getScenario()[posX][posY - 1])){
			this.posY--;
			this.points++;
			sensors.setPlace(this.scenario.getScenario()[posX][posY]);
			return true;
		}

		return false;
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