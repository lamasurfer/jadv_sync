package org.example.task1;

import org.example.task1.dealership.Car;
import org.example.task1.dealership.Dealership;

import java.util.ArrayList;
import java.util.List;

public class WaitDealership implements Dealership {

    private final List<Car> cars = new ArrayList<>();
    private int orders = 0;

    public WaitDealership() {
        System.out.println("Продаю машины!");
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

    public synchronized void deliverCar(Car car) {
        cars.add(car);
        notifyAll();
    }

    public synchronized void checkForCars() {
        orders++;
        System.out.println(Thread.currentThread().getName() + " сделал заказ");
        notifyAll();
    }

    public synchronized Car buyCar() {
        try {
            while (cars.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cars.remove(0);
    }
}
