package org.example.task3;

import org.example.task3.restaurant.Restaurant;
import org.example.task3.restaurant.Restaurants;
import org.example.task3.people.Visitor;

public class LockMain {
    public static void main(String[] args) {

        Restaurant restaurant = Restaurants.getLockRestaurant(100, 150);
        Visitor.getVisitors(restaurant, 1000);
    }
}
