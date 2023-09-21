public class Scenario {
    protected int width;
    protected int height;

    protected Place[][] scenario;

    public Scenario(int width, int height){
        this.width = width + 2;
        this.height = height + 2;
        this.scenario = new Place[this.width][this.height];

        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(isWall(x, y))
                    this.scenario[x][y] = new Place('=');
                else
                    this.scenario[x][y] = new Place();
            }
        }
    }

    public boolean isWall(int x, int y) {
        return x == 0 || y == 0 || x == (this.width - 1) || y == (this.height - 1);
    }

    public void printScenario() {
        StringBuilder scenario = new StringBuilder();
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                scenario.append(this.scenario[x][y].getState()).append(" ");
            }
            scenario.append("\n");
        }
        System.out.println(scenario);
    }

    public void prepareScenario(){
        putRandomDirtyPlaces();
        putRandomObstacles();
    }

    public void putRandomDirtyPlaces(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(!isWall(x, y) && Math.random() < 0.2)
                    this.scenario[x][y].setState('*');
            }
        }
    }

    public void putRandomObstacles(){
        for(int x = 0; x < this.width; x++){
            for(int y = 0; y < this.height; y++){
                if(Math.random() < 0.1 && isAccessible(x, y)){
                    this.scenario[x][y].setState('=');
                }
            }
        }
    }

    //#todo: check if this is correct
    public boolean isAccessible(int x, int y){
        return !isWall(x, y) && this.scenario[x][y].getState() != '=';
    }

}
