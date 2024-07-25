package com.trabf.melodicgusts.Models.entities;

public class Timer {
    private int seconds;
    private volatile boolean timeOver;
    private Thread timerThread;

    public Timer(int seconds) {
        this.seconds = seconds;
        this.timeOver = false;
    }

    public void start() {
        if (timerThread != null && timerThread.isAlive()) {
            // If the timer is already running, stop it before starting a new one
            timerThread.interrupt();
        }

        timeOver = false;
        timerThread = new Thread(() -> {
            try {
                Thread.sleep(seconds * 1000);
                timeOver = true;
                System.out.println("Time is over!");
            } catch (InterruptedException e) {
                System.out.println("Timer was interrupted.");
            }
        });
        timerThread.start();
    }

    public boolean isTimeOver() {
        return timeOver;
    }

    public void reset() {
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
        timeOver = false;
    }
}