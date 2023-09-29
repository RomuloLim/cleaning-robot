package Scenario;

import java.util.Random;

public class Scenario {
    protected int width;
    protected int height;
    protected Place[][] scenario;
    public int dirtyAmount = 0;

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

    //Funcao pra verificar se eh parede
    protected boolean isBorder(int x, int y){
        return x == 0 || y == 0 || x == this.width - 1 || y == this.height - 1;
    }

    //Funcao para imprimir a sala no console
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

    public void prepareScenario(long seed) throws InterruptedException {
        Random random = new Random(seed);

        putRandomDirtyPlaces(random);
        putRandomObstacles(random);
    }

    //Funcao para implementar sujeiras na sala em lugares aleatorios
    public void putRandomDirtyPlaces(Random random){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                double randomValue = (double) Math.abs(random.nextInt())/Integer.MAX_VALUE;
                if(!isBorder(x,y) && !this.scenario[x][y].isWall() && randomValue < 0.2){
                    this.scenario[x][y].setState('*');
                    dirtyAmount++;
                }
            }
        }
    }

  //Funcao para implementar obstaculos na sala em lugares aleatorios
    public void putRandomObstacles(Random random){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                double randomValue = (double) Math.abs(random.nextInt())/Integer.MAX_VALUE;

                if(!isBorder(x,y) && randomValue < 0.1 && !this.scenario[x][y].isWall()){
                    this.scenario[x][y].setState('=');
                }
            }
        }
    }

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
