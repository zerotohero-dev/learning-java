package io.volkan;

public class DatabaseQuery implements Runnable {

    @Override
    public void run() {
        performDatabaseQuery();
    }

    private Object performDatabaseQuery() {
        try {
            Thread.sleep(5000);
        } catch(Exception e) {}

        return null;
    }
}
