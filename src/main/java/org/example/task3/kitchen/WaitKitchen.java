package org.example.task3.kitchen;

import java.util.ArrayList;
import java.util.List;

public class WaitKitchen implements Kitchen {


    private final List<Meal> meals = new ArrayList<>();
    private int demand = 0;

    public WaitKitchen() {
        System.out.println("Кухня начала работу!");
    }

    public synchronized void demandMeal() {
        demand++;
        notifyAll();
    }

    public synchronized void checkForDemand() {
        try {
            while (demand == 0) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demand--;
    }

    public synchronized Meal getMeal() {
        Meal meal = null;
        try {
            while (meals.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        meal = meals.remove(0);
        return meal;
    }

    public synchronized void addMeal(Meal meal) {
        meals.add(meal);
        notifyAll();
    }
}
