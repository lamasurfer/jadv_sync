package org.example.task3.kitchen;

public class Meal {

    private static int counter = 1;
    private final String name;

    public Meal() {
        this.name = "Блюдо " + counter++;
    }

    @Override
    public String toString() {
        return name;
    }
}
