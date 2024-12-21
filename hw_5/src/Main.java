import java.util.Random;

public class Main {
    private final static Floor[] floors = new Floor[20];

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++){
            floors[i] = new Floor(i + 1);
        }
        Floor.initializeElevatorList();
        for (int i = 0; i < 10; i++){
            pressRandomButton generateOfTask = new pressRandomButton();
            Thread generate = new Thread(generateOfTask);
            generate.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private static class pressRandomButton implements Runnable{
        @Override
        public void run() {
            int floorFrom;
            int floorTo;
            do {
                floorFrom = (new Random()).nextInt(1, 20);
                floorTo = (new Random()).nextInt(1, 20);
            } while (floorFrom == floorTo);

            System.out.println("Заказ: " + floorFrom + " -> " + floorTo);
            if (floorFrom < floorTo) floors[floorFrom - 1].pressButtonUp(floorTo);
            else floors[floorFrom - 1].pressButtonDown(floorTo);
        }
    }
}