package org.example.task3.restaurant;

import org.example.task3.hall.LockHall;
import org.example.task3.kitchen.LockKitchen;
import org.example.task3.hall.WaitHall;
import org.example.task3.kitchen.WaitKitchen;

public class Restaurants {

    public static Restaurant getWaitRestaurant(int cooks, int waiters) {
        Restaurant restaurant = new RestaurantImp(new WaitKitchen(), new WaitHall());
        restaurant.setCooks(cooks);
        restaurant.setWaiters(waiters);
        return restaurant;
    }

    public static Restaurant getLockRestaurant(int cooks, int waiters) {
        Restaurant restaurant = new RestaurantImp(new LockKitchen(), new LockHall());
        restaurant.setCooks(cooks);
        restaurant.setWaiters(waiters);
        return restaurant;
    }
}
