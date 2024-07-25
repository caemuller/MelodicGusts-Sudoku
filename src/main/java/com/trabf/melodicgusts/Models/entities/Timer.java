package com.trabf.melodicgusts.Models.entities;

import java.time.Duration;
import java.time.Instant;

public class Timer {
    private int seconds;
    private volatile boolean timeOver;
    private Instant startingTime;
    

    public Timer(int seconds) {
        this.seconds = seconds;
        this.timeOver = false;
    }

    public void start() {
        this.startingTime = Instant.now();
    }

    public Instant getStartingTime() {
        return this.startingTime;
    }

    public void checkTime() {
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(this.startingTime, end);
        if (timeElapsed.getSeconds() >= seconds) {
            this.timeOver = true;
        }
    }

    public boolean isTimeOver() {
        return timeOver;
    }

    public void reset() {
        timeOver = false;
    }
}