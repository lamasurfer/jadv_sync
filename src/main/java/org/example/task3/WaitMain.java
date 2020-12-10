package org.example.task3;

import org.example.task3.restaurant.Restaurant;
import org.example.task3.restaurant.Restaurants;
import org.example.task3.people.Visitor;

public class WaitMain {
    public static void main(String[] args) {

        Restaurant restaurant = Restaurants.getWaitRestaurant(1, 3);
        Visitor.getVisitors(restaurant, 5);

    }
}
