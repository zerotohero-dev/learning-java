package io.volkan;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontPropertiesPanel extends JPanel {
    protected JList<String> nameList;
    protected JComboBox<Integer> sizeBox;
    protected JCheckBox boldBox;
    protected JCheckBox italicBox;

    public final static int[] fontSizes = {10, 20, 14, 18, 24, 32, 48, 64};

    protected FontListener listener;


    public FontPropertiesPanel() {
        super();

        createComponents();
        buildLayout();
    }

    protected void buildLayout() {
        JLabel label;

        GridBagConstraints constraints = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();

        setLayout(layout);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 10, 5, 10);

        constraints.gridx = 0;
        label = new JLabel("Name:", JLabel.LEFT);
        layout.setConstraints(label, constraints);
        add(label);
        label = new JLabel( "Size:", JLabel.LEFT);
        layout.setConstraints(label, constraints);
        add(label);
        layout.setConstraints(boldBox, constraints);

        constraints.gridx++;
        nameList.setVisibleRowCount(3);
        JScrollPane jsp = new JScrollPane(nameList);
        add(jsp);
        layout.setConstraints(sizeBox, constraints);
        add(sizeBox);
        layout.setConstraints(boldBox, constraints);
        add(boldBox);
        layout.setConstraints(italicBox, constraints);
        add(italicBox);
    }

    protected void createComponents() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] names = ge.getAvailableFontFamilyNames();

        nameList = new JList<>(names);
        nameList.setSelectedIndex(0);
        nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nameList.addListSelectionListener(event -> handleFontPropertyChange());

        Integer sizes[] = new Integer[fontSizes.length];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = fontSizes[i];
        }
        sizeBox = new JComboBox<>(sizes);
        sizeBox.addActionListener(event -> handleFontPropertyChange());

        boldBox = new JCheckBox("Bold");
        boldBox.addActionListener(event -> handleFontPropertyChange());

        italicBox = new JCheckBox("Italic");
        italicBox.addActionListener(event -> handleFontPropertyChange());
    }

    protected void handleFontPropertyChange() {
        listener.fontChanged(getSelectedFont());
    }

//    public String getSelectedFontName() {
//        return nameList.getSelectedValue();
//    }
//
//    public boolean isBoldSelected() {
//        return boldBox.isSelected();
//    }
//
//    public boolean isItalicSelected() {
//        return italicBox.isSelected();
//    }
//
//    public int getSelectedFontSize() {
//        return ((Integer) sizeBox.getSelectedItem()).intValue();
//    }

    public Font getSelectedFont() {
        String name = nameList.getSelectedValue();
        int style = 0;
        style += (boldBox.isSelected() ? Font.BOLD : 0);
        style += (italicBox.isSelected() ? Font.ITALIC : 0);
        int size = ((Integer) sizeBox.getSelectedItem()).intValue();

        return new Font(name, style, size);
    }

    public void setFontListener(FontListener fontListener) {
        this.listener = fontListener;
    }
}
