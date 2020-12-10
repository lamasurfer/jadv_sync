package org.example.task3.people;

import org.example.task3.kitchen.Meal;
import org.example.task3.hall.Hall;
import org.example.task3.kitchen.Kitchen;

public class Waiter implements Runnable {

    private final static long DELIVERY_TIME = 1000;
    private static int counter = 1;
    private final Kitchen kitchen;
    private final Hall hall;
    private final String name;

    public Waiter(Kitchen kitchen, Hall hall) {
        this.kitchen = kitchen;
        this.hall = hall;
        this.name = "Официант " + counter++;
        Thread thread = new Thread(this, name);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(name + " начал работать!");
        try {
            while (true) {
                hall.checkForOrders();
                kitchen.demandMeal();
                Meal meal = kitchen.getMeal();
                System.out.println(name + " несет заказ " + meal);
                Thread.sleep(DELIVERY_TIME);
                hall.bringMeal(meal);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
