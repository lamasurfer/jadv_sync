package org.example.task3.kitchen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockKitchen implements Kitchen {

    private final List<Meal> meals = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int demand = 0;

    public LockKitchen() {
        System.out.println("Кухня начала работу!");
    }

    public void demandMeal() {
        lock.lock();
        try {
            demand++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void checkForDemand() {
        lock.lock();
        try {
            while (demand == 0) {
                condition.await();
            }
            demand--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Meal getMeal() {
        Meal meal = null;
        lock.lock();
        try {
            while (meals.size() == 0) {
                condition.await();
            }
            meal = meals.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return meal;
    }

    public void addMeal(Meal meal) {
        lock.lock();
        try {
            meals.add(meal);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

