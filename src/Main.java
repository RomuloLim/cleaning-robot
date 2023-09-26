import Robot.SimpleRobot;
import Robot.SmartRobot;
import Scenario.Scenario;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Comešando limpeza");

        Scenario scenario = new Scenario(7, 7);
        scenario.prepareScenario();

        SimpleRobot robot = new SimpleRobot(1, 1, scenario);
        SmartRobot smartRobot = new SmartRobot(1, 1, scenario);

         while (scenario.dirtyAmount > 0) {
            Thread.sleep(1100);
//            robot.move();
            smartRobot.prepareNextMovement();
            scenario.printScenario();
         }
         //scenario.printScenario();
         System.out.println("Limpeza finalizada !!");
    }
}