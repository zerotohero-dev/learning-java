package io.volkan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPress extends JFrame {
    public static void main(String[] args) {
        ButtonPress bp = new ButtonPress();
        bp.setSize(400, 300);
        bp.setVisible(true);
    }

    public ButtonPress() {
        JMenuBar jmb = new JMenuBar();
        JMenu menu = new JMenu("Execute");
        jmb.add(menu);
        JMenuItem jmi = new JMenuItem("Database Query");
        menu.add(jmi);

        jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // performDatabaseQuery();
                Thread t = new Thread(new DatabaseQuery());

                t.start();
            }
        });

        setJMenuBar(jmb);
    }

    private Object performDatabaseQuery() {
        try {
            Thread.sleep(5000);
        } catch(Exception e) {}

        return null;
    }
}
