import java.util.Comparator;
import java.util.PriorityQueue;

public class Elevator {
    private final int number;
    private int direction = 0; // = -1 если едет вниз
                               // = 0 если едет к goal
                               // = 1 если едет наверх
    private int floor = 1;
    private int goal = -1; // = -1 если цели нет
                           // = -2 если цель достигнута
    public final PriorityQueue<Integer> stopsOnUpWay = new PriorityQueue<>();
    public final PriorityQueue<Integer> stopsOnDownWay = new PriorityQueue<>(Comparator.reverseOrder());

    Elevator(int number){
        this.number = number;
    }

    // По пути ли лифту
    public boolean onOurWay(int orderFloor, int wishDirection){
        if (goal == -1) return true;
        if (direction != wishDirection) return false;
        if (direction == 1){
            return floor < orderFloor;
        } else {
            return floor > orderFloor;
        }
    }

    // Взять заказ
    public void setOrder(int floorFrom, int floorTo){
        if (direction != 0) {
            if (direction == 1){
                stopsOnUpWay.add(floorFrom);
                stopsOnUpWay.add(floorTo);
            } else {
                stopsOnDownWay.add(floorFrom);
                stopsOnDownWay.add(floorTo);
            }
        } else {
            goal = floorFrom;
            if (floorFrom < floorTo) {
                direction = 1;
                stopsOnUpWay.add(floorTo);
            }
            else {
                direction = -1;
                stopsOnDownWay.add(floorTo);
            }
            Engine myEngine = new Engine();
            Thread engine = new Thread(myEngine);
            engine.start();
        }
    }

    private class Engine implements Runnable{
        @Override
        public void run() {
            try {
                Thread toGoal = new Thread(new ToGoal());
                toGoal.start();
                toGoal.join();
            } catch (InterruptedException e){
                System.out.println("Ошибка при попытке доехать до вызвавшего этажа");
            }

            if (direction == 1){
                while (stopsOnUpWay.peek() != null) {
                    try {
                        Thread upMoving = new Thread(new UpMoving());
                        upMoving.start();
                        upMoving.join();
                    } catch (InterruptedException e) {
                        System.out.println("Ошибка при попытке движения вверх");
                    }
                }
            } else {
                while (stopsOnDownWay.peek() != null) {
                    try {
                        Thread downMoving = new Thread(new DownMoving());
                        downMoving.start();
                        downMoving.join();
                    } catch (InterruptedException e) {
                        System.out.println("Ошибка при попытке движения вниз");
                    }
                }
            }

            goal = -1;
            direction = 0;
            System.out.println("\tЛифт " + number + " > движение окончено");
        }
    }

    private class ToGoal implements Runnable{
        @Override
        public void run() {
            while (floor != goal) {
                if (floor < goal) {
                    ignoringMoveUp();
                } else {
                    ignoringMoveDown();
                }
            }
            try {
                System.out.println("\t\tЛифт " + number + " > Двери открылись");
                Thread.sleep(3000);
                System.out.println("\t\tЛифт " + number + " > Двери закрылись");
            } catch (InterruptedException e){
                System.out.print("\t\tЛифт " + number + " >  Не удалось открыть двери");
            }
        }

        private void ignoringMoveUp(){
            try {
                Thread.sleep(1500);
                floor++;
                System.out.println("\tЛифт " + number + " > приехал на " + floor + " этаж");
                if (stopsOnUpWay.peek() != null && stopsOnUpWay.peek() == floor && direction == 1){
                    System.out.println("\t\tЛифт " + number + " > Двери открылись");
                    Thread.sleep(3000);
                    System.out.println("\t\tЛифт " + number + " > Двери закрылись");
                    boolean isDeleted;
                    do {
                        isDeleted = stopsOnUpWay.remove(floor);
                    } while (isDeleted);
                }
            } catch (InterruptedException e) {
                return;
            }
        }

        private void ignoringMoveDown(){
            try {
                Thread.sleep(1500);
                floor--;
                System.out.println("\tЛифт " + number + " > приехал на " + floor + " этаж");
                if (stopsOnDownWay.peek() != null && stopsOnDownWay.peek() == floor && direction == -1){
                    System.out.println("\t\tЛифт " + number + " > Двери открылись");
                    Thread.sleep(3000);
                    System.out.println("\t\tЛифт " + number + " > Двери закрылись");
                    boolean isDeleted;
                    do {
                        isDeleted = stopsOnDownWay.remove(floor);
                    } while (isDeleted);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private class DownMoving implements Runnable{
        @Override
        public void run() {
            synchronized (stopsOnDownWay) {
                try {
                    Thread.sleep(1500);
                    floor--;
                    System.out.println("\tЛифт " + number + " > приехал на " + floor + " этаж");
                    if (stopsOnDownWay.peek() != null && stopsOnDownWay.peek() == floor) {
                        System.out.println("\t\tЛифт " + number + " > Двери открылись");
                        Thread.sleep(3000);
                        System.out.println("\t\tЛифт " + number + " > Двери закрылись");
                        boolean isDeleted;
                        do {
                            isDeleted = stopsOnDownWay.remove(floor);
                        } while (isDeleted);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private class UpMoving implements Runnable{
        @Override
        public void run() {
            synchronized (stopsOnUpWay) {
                try {
                    Thread.sleep(1500);
                    floor++;
                    System.out.println("\tЛифт " + number + " > приехал на " + floor + " этаж");
                    if (stopsOnUpWay.peek() != null && stopsOnUpWay.peek() == floor){
                        System.out.println("\t\tЛифт " + number + " > Двери открылись");
                        Thread.sleep(3000);
                        System.out.println("\t\tЛифт " + number + " > Двери закрылись");
                        boolean isDeleted;
                        do {
                            isDeleted = stopsOnUpWay.remove(floor);
                        } while (isDeleted);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
}
