package org.example.task3.hall;

import org.example.task3.kitchen.Meal;

import java.util.ArrayList;
import java.util.List;

public class WaitHall implements Hall {

    private final List<Meal> meals = new ArrayList<>(10);
    private int orders = 0;

    public WaitHall() {
        System.out.println("Зал начал работу!");
    }

    public synchronized void orderMeal() {
        orders++;
        notifyAll();
    }

    public synchronized void checkForOrders() {
        try {
            while (orders == 0) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orders--;
    }

    public synchronized void bringMeal(Meal meal) {
        meals.add(meal);
        notifyAll();
    }

    public synchronized Meal waitAndGetMeal() {
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
}
