package org.example.task3.people;

import org.example.task3.kitchen.Meal;
import org.example.task3.kitchen.Kitchen;

public class Cook implements Runnable {

    private final static long COOKING_TIME = 2000;
    private static int counter = 1;

    private final Kitchen kitchen;
    private final String name;

    public Cook(Kitchen kitchen) {
        this.kitchen = kitchen;
        this.name = "Повар " + counter++;
        Thread thread = new Thread(this, name);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(name + " начал работу!");
        while (true) {
            try {
                kitchen.checkForDemand();
                Meal meal = new Meal();
                System.out.println(name + " готовит " + meal);
                Thread.sleep(COOKING_TIME);
                kitchen.addMeal(meal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
