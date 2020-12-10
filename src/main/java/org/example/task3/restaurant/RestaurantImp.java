package org.example.task3.restaurant;

import org.example.task3.hall.Hall;
import org.example.task3.kitchen.Kitchen;
import org.example.task3.people.Cook;
import org.example.task3.people.Waiter;

import java.util.ArrayList;
import java.util.List;

public class RestaurantImp implements Restaurant {

    private final Kitchen kitchen;
    private final Hall hall;

    private final List<Runnable> workers = new ArrayList<>();

    public RestaurantImp(Kitchen kitchen, Hall hall) {
        System.out.println("Ресторан начал работу!");
        this.kitchen = kitchen;
        this.hall = hall;
    }

    public void setCooks(int cooks) {
        for (int i = 0; i < cooks; i++) {
            workers.add(new Cook(kitchen));
        }
    }

    public void setWaiters(int waiters) {
        for (int i = 0; i < waiters; i++) {
            workers.add(new Waiter(kitchen, hall));
        }
    }

    public Hall enterHall() {
        return hall;
    }
}
