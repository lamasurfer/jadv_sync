package org.example.task1.dealership;

public class Vendor implements Runnable {

    private final static long PRODUCTION_TIME = 3000;
    private static int counter = 1;

    private final Dealership dealership;
    private final String name;

    public Vendor(Dealership dealership) {
        this.dealership = dealership;
        this.name = "Производитель " + counter++;
        Thread thread = new Thread(this, name);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(name + " начал работу!");
        while (true) {
            try {
                dealership.checkForOrders();
                Car car = new Car();
                System.out.println(name + " производит " + car);
                Thread.sleep(PRODUCTION_TIME);
                System.out.println(car + " готова!");
                dealership.deliverCar(car);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}