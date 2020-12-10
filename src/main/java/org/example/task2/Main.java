package org.example.task2;

import org.example.task1.dealership.Dealership;
import org.example.task1.dealership.Vendee;
import org.example.task1.dealership.Vendor;

public class Main {
    public static void main(String[] args) {

        Dealership carDealership = new LockDealership();

        int vendeeCount = 5;
        for (int i = 0; i < vendeeCount; i++) {
            new Vendee(carDealership);
        }

        new Vendor(carDealership);
    }
}
