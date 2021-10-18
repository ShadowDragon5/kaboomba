package com.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class Scheduler {
    public static void scheduleTask(Callable<Void> method, String name, Long duration) {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    method.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer(name);
        timer.schedule(task, duration);
    }
}
