package org.example.task1.dealership;

import java.util.concurrent.TimeUnit;

public class Vendee implements Runnable {

    private final static long ARRIVAL_TIME = 1000;
    private final static long THINKING_TIME = 2000;
    private static int counter = 1;

    private final Dealership dealership;
    private final String name;

    public Vendee(Dealership dealership) {
        this.dealership = dealership;
        this.name = "Покупатель " + counter++;
        new Thread(this, name).start();
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(ARRIVAL_TIME);
            System.out.println(name + " зашел в автосалон");
            TimeUnit.MILLISECONDS.sleep(THINKING_TIME);
            dealership.checkForCars();
            Car car = dealership.buyCar();
            System.out.println(name + " довольный уехал на новенькой " + car);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}