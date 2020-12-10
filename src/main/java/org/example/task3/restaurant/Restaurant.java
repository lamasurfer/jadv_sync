package org.example.task3.restaurant;

import org.example.task3.hall.Hall;

public interface Restaurant {

    void setCooks(int cooks);

    void setWaiters(int waiters);

    Hall enterHall();

}
