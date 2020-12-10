package org.example.task2;

import org.example.task1.dealership.Car;
import org.example.task1.dealership.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDealership implements Dealership {

    private final List<Car> cars = new ArrayList<>();
    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();
    private int orders = 0;

    public LockDealership() {
        System.out.println("Продаю машины!");

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

    public void deliverCar(Car car) {
        lock.lock();
        try {
            cars.add(car);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void checkForCars() {
        lock.lock();
        try {
            orders++;
            System.out.println(Thread.currentThread().getName() + " сделал заказа");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Car buyCar() {
        Car car = null;
        lock.lock();
        try {
            while (cars.size() == 0) {
                condition.await();
            }
            car = cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return car;
    }
}
