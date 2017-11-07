package io.volkan;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class DownloadManager extends JPanel {
    private Downloader downloader;

    private JButton startButton;
    private JButton sleepButton;
    private JButton suspendButton;
    private JButton resumeButton;
    private JButton stopButton;

    public static void main(String[] args) throws Exception {
        URL url = new URL(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        JFrame f = new JFrame();
        DownloadManager dm = new DownloadManager(url, fos);
        f.getContentPane().add(dm);
        f.setSize(400, 300);
        f.setVisible(true);
    }

    public DownloadManager(URL source, OutputStream os) throws IOException {
        downloader = new Downloader(source, os);

        buildLayout();

        Border border = new BevelBorder(BevelBorder.RAISED);

        String name = source.toString();
        int index = name.lastIndexOf("/");
        border = new TitledBorder(border, name.substring(index + 1));
        setBorder(border);
    }

    private void buildLayout() {
        setLayout(new BorderLayout());
        downloader.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(downloader, BorderLayout.CENTER);
        add(getButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonPanel() {
        JPanel outerPanel;
        JPanel innerPanel;

        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(1, 5, 10, 0));

        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            sleepButton.setEnabled(true);
            resumeButton.setEnabled(false);
            suspendButton.setEnabled(true);

            downloader.startDownload();
        });
        innerPanel.add(startButton);

        sleepButton = new JButton("Sleep");
        sleepButton.setEnabled(false);
        sleepButton.addActionListener(e -> {
            downloader.setSleepScheduled(true);
        });
        innerPanel.add(sleepButton);

        suspendButton = new JButton("Suspend");
        suspendButton.setEnabled(false);
        suspendButton.addActionListener(e -> {
            suspendButton.setEnabled(false);
            resumeButton.setEnabled(true);
            stopButton.setEnabled(true);

            downloader.setSuspended(true);
        });
        innerPanel.add(suspendButton);

        resumeButton = new JButton("Resume");
        resumeButton.setEnabled(false);
        resumeButton.addActionListener(e -> {
            resumeButton.setEnabled(false);
            suspendButton.setEnabled(true);
            stopButton.setEnabled(true);

            downloader.resumeDownload();
        });
        innerPanel.add(resumeButton);

        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener((e) -> {
            stopButton.setEnabled(false);
            sleepButton.setEnabled(false);
            suspendButton.setEnabled(false);
            resumeButton.setEnabled(false);

            downloader.stopDownload();
        });
        innerPanel.add(stopButton);

        outerPanel = new JPanel();
        outerPanel.add(innerPanel);
        return outerPanel;
    }
}
