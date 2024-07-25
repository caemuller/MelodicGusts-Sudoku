package com.trabf.melodicgusts.Models.entities;

public class Timer {
    private int seconds;
    private boolean timeOver;

    public Timer() {
        this.seconds = 5;
        this.timeOver = false;
    }

    public Timer(int seconds) {
        this.seconds = seconds;
        this.timeOver = false;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        try {
            Thread.sleep(this.seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.timeOver = true;
    }

    public void reset() {
        this.seconds = 5;
        this.timeOver = false;
    }

    public boolean isTimeOver() {
        return timeOver;
    }
}
