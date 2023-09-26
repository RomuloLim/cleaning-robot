package Robot;

import Scenario.Place;
import Scenario.Scenario;

import java.util.ArrayList;

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
    * prepara o próximo movimento checando para qual posição pode ir e se já foi visitada
    * os métodos canMoveUp checam a possibilidade baseado na existência ou não de uma parede
    * o método isVisited checam se a posição já foi visitada
    * o método getNextPosition retorna a posição que o robô irá se mover
    * o método loopCheck checa se o robô entrou em loop, se sim, limpa o histórico de salas visitadas
    * creio que o ideal seria que o robô não limpasse o histórico de salas visitadas, mas sim que ele
    * não entrasse em loop, porém não consegui pensar em uma solução para isso
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
    * Move o robô para a próxima posição baseado no valor de nextMove
    * O sensor é checado para ver se a sala está suja, se sim, limpa a sala
    * e adiciona a sala ao histórico de salas visitadas
    * o método move é chamado recursivamente até que o robô encontre uma sala suja
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
            System.out.println("Limpei essa sala (" + this.posX + ", " + this.posY + ")");
        }

        visitedPlaces.add(new VisitedPlace(this.posX, this.posY));
    }

    /*
    * Checa se a posição já foi visitada
    * se sim, retorna true
    * se não, retorna false
    * */
    private boolean isVisited(int x, int y){
        for(VisitedPlace visitedPlace : visitedPlaces){
            if(visitedPlace.x == x && visitedPlace.y == y){
                System.out.println("visitado (" + x + ", " + y + ")");
                return true;
            }
        }
        return false;
    }

    /*
    * pegar a próxima posição baseado no valor de nextMove
    * creio que tenha ficado um pouco confuso, não consegui pensar em uma forma melhor de fazer
    * basicamente, dependendo do valor de nextMove, a posição é alterada
    * 0 = direita
    * 1 = esquerda
    * 2 = cima
    * 3 = baixo
    * se o valor for diferente, retorna a posição atual
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
    * checa se o robô entrou em loop
    * se sim, retorna true
    * se não, retorna false
    * o loopCounter é um ArrayList de booleanos que guarda se o robô já tentou ir para uma posição
    * */
    private boolean loopCheck(int movementSide) {
        if(!loopCounter.get(movementSide)) {
            loopCounter.set(movementSide, true);
        }

        return !loopCounter.contains(false);
    }

}
