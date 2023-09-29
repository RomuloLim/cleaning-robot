package Robot;

import java.util.ArrayList;

import Scenario.Scenario;

public class SmartRobot extends Robot{
    private static class VisitedPlace {
        int x,y;

        public VisitedPlace(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private final ArrayList<VisitedPlace> visitedPlaces;
    private int nextMove;
    private final ArrayList<Boolean> loopCounter = new ArrayList<Boolean>(4);
    
    public SmartRobot(int posX, int posY, Scenario scenario) {
            super(posX, posY, scenario);
            this.visitedPlaces = new ArrayList<>();

            for(int i = 0; i < 4; i++) {
                loopCounter.add(false);
            }
    }

    /*
    * prepara o proximo movimento checando para qual posicao pode ir e se ja foi visitada
    * os metodos canMoveUp checam a possibilidade baseado na existencia ou nao de uma parede
    * o metodo isVisited checam se a posi�ao ja foi visitada
    * o metodo getNextPosition retorna a posi�ao que o robo ira se mover
    * o metodo loopCheck checa se o robo entrou em loop, se sim, limpa o historico de salas visitadas
    * creio que o ideal seria que o robo nao limpasse o historico de salas visitadas, mas sim que ele
    * nao entrasse em loop, porem nao consegui pensar em uma solucao para isso
     */
    public void prepareNextMovement() {
        int random = ((int) (Math.random() * 10) % 4);

        boolean ableToMove = switch (random) {
            case 0 -> canMoveUp();
            case 1 -> canMoveDown();
            case 2 -> canMoveLeft();
            case 3 -> canMoveRight();
            default -> false;
        };

        int[] nextPosition = getNextPosition(random);

        if(!ableToMove || isVisited(nextPosition[0], nextPosition[1])) {
            if(loopCheck(random)){
                System.out.println("Entrou em loop, limpando historico de salas visitadas");
                this.visitedPlaces.clear();
                for(int i = 0; i < 4; i++) {
                    loopCounter.set(i, false);
                }
            }

            prepareNextMovement();
        } else {
            this.nextMove = random;
            move();
        }
    }

    /*
    * Move o robo para a proxima posicao baseado no valor de nextMove
    * O sensor eh checado para ver se a sala esta suja, se sim, limpa a sala
    * e adiciona a sala ao historico de salas visitadas
    * o metodo move eh chamado recursivamente ate que o robo encontre uma sala suja
    * */
    @Override
    public void move() {
        switch (this.nextMove) {
            case 0 -> moveUp();
            case 1 -> moveDown();
            case 2 -> moveLeft();
            case 3 -> moveRight();
        }

        if (this.sensors.isDirty()) {
            clear();
            scenario.dirtyAmount--;
            System.out.println("Limpei essa sala (" + this.posX + ", " + this.posY + ")");
        }

        visitedPlaces.add(new VisitedPlace(this.posX, this.posY));
    }

    /*
    * Checa se a posicao ja foi visitada
    * se sim, retorna true
    * se nao, retorna false
    * */
    private boolean isVisited(int x, int y){
        for(VisitedPlace visitedPlace : visitedPlaces){
            if(visitedPlace.x == x && visitedPlace.y == y){
//                System.out.println("visitado (" + x + ", " + y + ")");
                return true;
            }
        }
        return false;
    }

    /*
    * pegar a proxima posicao baseado no valor de nextMove
    * creio que tenha ficado um pouco confuso, nao consegui pensar em uma forma melhor de fazer
    * basicamente, dependendo do valor de nextMove, a posicao eh alterada
    * 0 = direita
    * 1 = esquerda
    * 2 = cima
    * 3 = baixo
    * se o valor for diferente, retorna a posicao atual
    * */
    private int[] getNextPosition(int position) {
        switch (position) {
            case 0 -> {
                return new int[]{this.posX + 1, this.posY};
            }
            case 1 -> {
                return new int[]{this.posX - 1, this.posY};
            }
            case 2 -> {
                return new int[]{this.posX, this.posY + 1};
            }
            case 3 -> {
                return new int[]{this.posX, this.posY - 1};
            }
            default -> {
                return new int[]{this.posX, this.posY};
            }
        }
    }


    /*
    * checa se o robo entrou em loop
    * se sim, retorna true
    * se nao, retorna false
    * o loopCounter eh um ArrayList de booleanos que guarda se o robo ja tentou ir para uma posicao
    * */
    private boolean loopCheck(int movementSide) {
        if(!loopCounter.get(movementSide)) {
            loopCounter.set(movementSide, true);
        }

        return !loopCounter.contains(false);
    }
}
