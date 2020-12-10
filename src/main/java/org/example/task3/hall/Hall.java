package org.example.task3.hall;

import org.example.task3.kitchen.Meal;

public interface Hall {

    void checkForOrders();

    void bringMeal(Meal meal);

    void orderMeal();

    Meal waitAndGetMeal();
}
