package org.example.task3.people;

import org.example.task3.kitchen.Meal;
import org.example.task3.restaurant.Restaurant;
import org.example.task3.hall.Hall;

import java.util.Random;

public class Visitor implements Runnable {

    private final static long[] ARRIVAL_TIMES = {1000, 2500, 5000, 7500, 10_000};
    private final static long THINKING_TIME = 2000;
    private final static long EATING_TIME = 3000;

    private static int id = 1;

    private final String name;
    private final Hall hall;

    public Visitor(Restaurant restaurant) {
        this.hall = restaurant.enterHall();
        this.name = "Посетитель " + id++;
        new Thread(this, name).start();
    }

    public static void getVisitors(Restaurant restaurant, int visitors) {
        for (int i = 0; i < visitors; i++) {
            new Visitor(restaurant);
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        long walkingTime = ARRIVAL_TIMES[random.nextInt(ARRIVAL_TIMES.length)];
        try {
            Thread.sleep(walkingTime);
            System.out.println(name + " пришел в ресторан");
            Thread.sleep(THINKING_TIME);
            System.out.println(name + " сделал заказ");
            hall.orderMeal();
            Meal mealToEat = hall.waitAndGetMeal();
            System.out.println(name + " ест " + mealToEat);
            Thread.sleep(EATING_TIME);
            System.out.println(name + " довольный ушел");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
