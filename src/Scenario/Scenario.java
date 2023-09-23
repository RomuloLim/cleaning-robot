package Scenario;

import java.util.Arrays;

public class Scenario {
    protected int width;
    protected int height;
    protected Place[][] scenario;
    int cont = 0;

    public Scenario(int width, int height){
        this.width = width;
        this.height = height;
        this.scenario = new Place[this.width][this.height];

        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(this.isBorder(x, y))
                    this.scenario[x][y] = new Place('=');
                else
                    this.scenario[x][y] = new Place();
            }
        }
    }

    protected boolean isBorder(int x, int y){
        return x == 0 || y == 0 || x == this.width - 1 || y == this.height - 1;
    }

    public void printScenario() {
        StringBuilder scenario = new StringBuilder();
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(this.scenario[x][y].isOccupied()) {
                    scenario.append('R').append(" ");
                    continue;
                }

                scenario.append(this.scenario[x][y].getState()).append(" ");
            }
            scenario.append("\n");
        }
        System.out.println(scenario);
    }

    public void prepareScenario() throws InterruptedException{
        putRandomDirtyPlaces();
        putRandomObstacles();
//        movimentarRobo();
    }

    public void putRandomDirtyPlaces(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(!isBorder(x,y) && !this.scenario[x][y].isWall() && Math.random() < 0.2)
                    this.scenario[x][y].setState('*');
            }
        }
    }

    public void putRandomObstacles(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(!isBorder(x,y) && Math.random() < 0.1 && !this.scenario[x][y].isWall()){
                    this.scenario[x][y].setState('=');
                }
            }
        }
    }
    
//    private void movimentarRobo() throws InterruptedException {
//    	for(int x = 0; x < this.width; x++){
//            for(int y = 0; y < this.height; y++){
//                if(this.scenario[x][y].isWall()) {
//                	if(this.scenario[x][y].getState() == '*') {
//                		this.scenario[x][y].setState('X');
//                		cont++;
//                		printScenario();
//                		this.scenario[x][y].setState(' ');
//                		Thread.sleep(1000);
//                	}
//                	this.scenario[x][y].setState('X');
//                	printScenario();
//            		this.scenario[x][y].setState(' ');
//            		Thread.sleep(1000);
//                }
//            }
//        }
//    	System.out.println("Total: " + cont);
//	}

    // GETTERS AND SETTERS
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Place[][] getScenario() {
        return this.scenario;
    }
}
