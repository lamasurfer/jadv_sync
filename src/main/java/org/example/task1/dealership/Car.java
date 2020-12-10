package org.example.task1.dealership;

public class Car {

    private static int counter = 1;
    private final String name;

    public Car() {
        this.name = "Машина " + counter++;
    }

    @Override
    public String toString() {
        return name;
    }
}
