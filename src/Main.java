import Robot.SimpleRobot;
import Robot.SmartRobot;
import Scenario.Scenario;

import java.util.Random;

public class Main {
    public static final long SEED = new Random().nextLong();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Começando limpeza");

        Scenario scenario = new Scenario(6, 6);

        scenario.prepareScenario(SEED);

        Scenario scenarioToSmart = new Scenario(6,6);
        scenarioToSmart.prepareScenario(SEED);

        SimpleRobot robot = new SimpleRobot(1, 1, scenario);
        SmartRobot smartRobot = new SmartRobot(1, 1, scenarioToSmart);

        long startTimeSimpleRobot = System.currentTimeMillis();
        System.out.println("Iniciando limpeza com o robo simples");
         while (scenario.dirtyAmount > 0) {
            Thread.sleep(1100);
            robot.move();
            scenario.printScenario();
         }

         long elapsedTimeSimpleRobot = System.currentTimeMillis() - startTimeSimpleRobot;

        System.out.println("|========== LIMPEZA FINALIZADA ==========|");


        System.out.println("Iniciando a limpeza da mesma sala com o robo inteligente");
        long startTimeSmartRobot = System.currentTimeMillis();
        while (scenarioToSmart.dirtyAmount > 0) {
            Thread.sleep(1100);
            smartRobot.prepareNextMovement();
            scenarioToSmart.printScenario();
        }

        long elapsedTimeSmartRobot = System.currentTimeMillis() - startTimeSmartRobot;

        System.out.println("|========== LIMPEZA FINALIZADA ==========|");

        System.out.println("|============ ROBO SIMPLES ============|");
        System.out.println("Pontos: " + robot.getPoints());
        System.out.println("Tempo que levou para limpar (s): " + elapsedTimeSimpleRobot / 1000);

        System.out.println("|============ ROBO INTELIGENTE ============|");
        System.out.println("Pontos: " + smartRobot.getPoints());
        System.out.println("Tempo que levou para limpar (s): " + elapsedTimeSmartRobot / 1000);
    }
}