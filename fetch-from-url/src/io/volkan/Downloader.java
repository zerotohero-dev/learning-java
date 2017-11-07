package io.volkan;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Downloader extends JPanel implements Runnable {
    private URL downloadUrl;
    private InputStream inputStream;
    private OutputStream outputStream;
    private byte[] buffer;
    private int fileSize;
    private int bytesRead;

    private JLabel urlLabel;
    private JLabel sizeLabel;
    private JLabel completeLabel;
    private JProgressBar progressBar;

    public static final int BUFFER_SIZE = 1000;

    private boolean stopped;
    private boolean sleepScheduled;
    private boolean suspended;

    public static final int SLEEP_TIME = 5 * 1000; // 5secs.

    private Thread thisThread;

    public static void main(String[] args) throws Exception {
        Downloader dl = null;

        if (args.length < 2) {
            System.out.println(
                "You must specify the URL of the file to download and " +
                "the name of the local file to which its contents will be written."
            );

            System.exit(1);
        }

        URL url = new URL(args[0]);

        FileOutputStream fos = new FileOutputStream(args[1]);

        try {
            dl = new Downloader(url, fos);
        } catch (FileNotFoundException fnfe) {
            System.out.printf("File “%s” does not exist.%n", args[0]);
            System.exit(1);
        }

        JFrame f = new JFrame();
        f.getContentPane().add(dl);
        f.setSize(400, 300);
        f.setVisible(true);

        dl.thisThread.start();
    }

    public Downloader(URL url, OutputStream os) throws IOException {
        downloadUrl = url;
        outputStream = os;
        bytesRead = 0;

        URLConnection urlConnection = downloadUrl.openConnection();
        fileSize = urlConnection.getContentLength();

        if (fileSize == -1) {
            throw new FileNotFoundException(url.toString());
        }

        inputStream = new BufferedInputStream(urlConnection.getInputStream());
        buffer = new byte[BUFFER_SIZE];
        thisThread = new Thread(this);

        buildLayout();

        stopped = false;
        sleepScheduled = false;
        suspended = false;
    }

    public synchronized void setSuspended(boolean suspended) {suspended = suspended;}
    public synchronized boolean isSuspended() {return suspended;}

    private void buildLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 10, 5, 10);

        constraints.gridx = 0;

        JLabel label = new JLabel("URL:", JLabel.LEFT);
        add(label, constraints);

        label = new JLabel("Complete:", JLabel.LEFT);
        add(label, constraints);

        label = new JLabel("Downloaded:", JLabel.LEFT);
        add(label, constraints);

        constraints.gridx = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        constraints.weightx = 1;
        urlLabel = new JLabel(downloadUrl.toString());
        add(urlLabel, constraints);

        progressBar = new JProgressBar(0, fileSize);
        progressBar.setStringPainted(true);
        add(progressBar, constraints);

        constraints.gridwidth = 1;

        completeLabel = new JLabel(Integer.toString(bytesRead));
        add(completeLabel, constraints);

        constraints.gridx = 2;
        constraints.weightx = 0;
        constraints.anchor =GridBagConstraints.EAST;

        label = new JLabel("Size:", JLabel.LEFT);
        add(label, constraints);

        constraints.gridx = 3;
        constraints.weightx = 1;

        sizeLabel = new JLabel(Integer.toString(fileSize));
        add(sizeLabel, constraints);
    }

    /*
        Thread
              setValue() ----> [Cache] ----[flush]---> [Main Memory]
        Modern CPUs use caches for faster Read/Write operations,
        and those caches are flushed to the main memory after a while.

        It is possible that although a thread sets the value of a class variable,
        that value can still live in the cache for a while before being flushed.

        That also means that, if another thred reads the value in the main memory, it will be
        different than that on the cache, which has not been flushed yet.
        Ergo the data in main memory can be stale for a while.

        Declaring a method, or a block as `synchronized` also makes sure that the data is
        flushed to the main memory.

        Even if `sleepScheduled` is an innocent-looking boolean,
        when it is accessed from multiple threads, the value can
        be written into the thread’s cache; though that cache
        might not have been flushed, so other threads that
        access the variable can still read a stale version of it.
        Also, even if the thread’s cache is flushed, things like…
            thread1: setSleepSchedule(!getSleepSchedule());
            thread2: setSleepSchedule(!getSleepSchedule());
        can give non-deterministic outcomes depending on when a context
        switch occurs.

        So as a rule of thumb, whenever there is access to a shared that that make sure the
        access is synchronized.

        There are two exceptions:
        * If the data will be read, but never be written.
        * If the data will be written but never be read.
        Both of these cases, are only of theoretical interest in general because
        you generally will want to concurrently mutate a share resource; hence you almost always
        need a lock.

        To be defensive, **always** synchronize access to shared resources;
        and if you think you don’t need synchronized access to shared resources, think again.

        The only time you “might” not sync access to a shared resource is because you are using a
        different communication paradigm, like Communicating Sequential Processes (CSP) and the
        paradigm already takes care of resource sharing semantics for you.
     */

    public synchronized void setSleepScheduled(boolean doSleep) {
        sleepScheduled = doSleep;
    }

    public synchronized boolean isSleepScheduled() {
        return sleepScheduled;
    }

    public void run() {
        performDownload();
    }

    public void performDownload() {
        int byteCount;

        while ((bytesRead < fileSize) && !stopped) {
            if (isSleepScheduled()) {
               try {
                   Thread.sleep(SLEEP_TIME);
                   setSleepScheduled(false);
               } catch(InterruptedException ie) {
                   setStopped(true);
                   break;
               }
            }

            try {
                byteCount = inputStream.read(buffer);

                if (byteCount == -1) {
                    setStopped(true);
                    break;
                } else {
                    outputStream.write(buffer, 0, byteCount);
                    bytesRead += byteCount;

                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(bytesRead);
                        completeLabel.setText(Integer.toString(bytesRead));
                    });
                }
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(
                    this,
                    ioe.getMessage(),
                    "I/O Error",
                    JOptionPane.ERROR_MESSAGE
                );

                setStopped(true);
                break;
            }

            synchronized (this) {
                if (isSuspended()) {
                    try {
                        this.wait();
                        setSuspended(false);
                    } catch (InterruptedException ie) {
                        setStopped(true);

                        break;
                    }
                }
            }

            if (Thread.interrupted()) {
                setStopped(true);
                break;
            }
        }

        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException ioe) {}
    }

    public synchronized void resumeDownload() {

        // You need to own the lock before you can call notify.
        // That’s why `resumeDownload` is `synchronized`, even though
        // it does not access to any shared resources shared between
        // AWT event thread and the download thread.
        // This way the event thread will obtain the Downloader object’s monitor,
        // which is necessary for the event thread to be able to call the object’s
        // `notify()` method without raising an `IllegalMonitorStateException`.
        this.notify();
    }

    public synchronized void setStopped(boolean stop) {stopped = stop;}

    public void startDownload() {thisThread.start();}

    public void stopDownload() {thisThread.interrupt();}
}
