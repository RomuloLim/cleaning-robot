import Robot.SimpleRobot;
import Scenario.Scenario;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Começando limpeza");

        Scenario scenario = new Scenario(10, 10);
        scenario.prepareScenario();

        SimpleRobot robot = new SimpleRobot(1, 1, scenario);

         while (true) {
            Thread.sleep(1500);
            robot.move();
            scenario.printScenario();
         }
         //scenario.printScenario();
         
    }
}