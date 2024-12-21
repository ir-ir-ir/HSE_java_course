public class Floor {
    private final int number;
    private static final Elevator[] ElevatorList = new Elevator[5];

    Floor(int number){
        this.number = number;
    }

    public void pressButtonUp(int wishFloor){
        // Выполняем цикл пока не найдём лифт
        boolean flag = true;
        while (flag) {
            synchronized (ElevatorList) {
                // Проходимся по массиву лифтов и ищем подходящий, чтоб ничего с массивом не происходило - захватываем монитор
                for (Elevator elev : ElevatorList) {
                    synchronized (elev.stopsOnUpWay) {
                        // Если направления движения не противоположны и лифт самый близкий - запоминаем
                        if (elev.onOurWay(number, 1)) {
                            elev.setOrder(number, wishFloor);
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Ошибка при поиске подходящего лифта");
                }
            }
        }
    }

    public void pressButtonDown(int wishFloor){
        // Выполняем цикл пока не найдём лифт
        boolean flag = true;
        while (flag) {
            synchronized (ElevatorList) {
                // Проходимся по массиву лифтов и ищем подходящий, чтоб ничего с массивом не происходило - захватываем монитор
                for (Elevator elev : ElevatorList) {
                    synchronized (elev.stopsOnDownWay) {
                        // Если направления движения не противоположны и лифт самый близкий - запоминаем
                        if (elev.onOurWay(number, -1)){
                            elev.setOrder(number, wishFloor);
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Ошибка при поиске подходящего лифта");
                }
            }
        }
    }

    public static void initializeElevatorList(){
        for (int i = 0; i < 5; i++){
            ElevatorList[i] = new Elevator(i + 1);
        }
    }
}
