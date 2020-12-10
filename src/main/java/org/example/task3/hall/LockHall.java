package org.example.task3.hall;

import org.example.task3.kitchen.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockHall implements Hall {

    private final List<Meal> meals = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int orders = 0;

    public LockHall() {
        System.out.println("Зал начал работу!");
    }

    public void orderMeal() {
        lock.lock();
        try {
            orders++;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void checkForOrders() {
        lock.lock();
        try {
            while (orders == 0) {
                condition.await();
            }
            orders--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void bringMeal(Meal meal) {
        lock.lock();
        try {
            meals.add(meal);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Meal waitAndGetMeal() {
        Meal meal = null;
        lock.lock();
        try {
            while (meals.isEmpty()) {
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


}

