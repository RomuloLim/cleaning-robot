import Robot.SimpleRobot;
import Robot.SmartRobot;
import Scenario.Scenario;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Começando limpeza");

        Scenario scenario = new Scenario(10, 10);
        scenario.prepareScenario();

//        SimpleRobot robot = new SimpleRobot(1, 1, scenario);
        SmartRobot robot = new SmartRobot(1, 1, scenario);

         while (scenario.dirtyAmount > 0) {
            Thread.sleep(1500);
//            robot.move();
             robot.prepareNextMovement();
            scenario.printScenario();
         }
         //scenario.printScenario();
         
    }
}