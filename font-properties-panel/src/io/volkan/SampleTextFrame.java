package io.volkan;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class SampleTextFrame extends JFrame implements FontListener {
    //protected FontPropertiesPanel propertiesPanel;

    protected JPanel osman;
    protected JPanel propertiesPanel;
    protected JTextField sampleText;
    protected JLabel displayArea;

    public static void main(String[] args) {
        SampleTextFrame stf = new SampleTextFrame();
        stf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        stf.setVisible(true);
    }

    public SampleTextFrame() {
        super();



        // Dynamic Polymorphism:
        //
        // Suppose a sub class overrides a particular method of the super class.
        // Let’s say, in the program we create an object of the subclass and assign
        // it to the super class reference. Now, if we call the overridden method
        // on the super class reference then the sub class version of the method will be called.
        //
        // At runtime this `createComponents` will resolve to the `crreateComponents` that is
        // defined in `FontPropertiesFrame` by association.
        createComponents();
        createDocumentListener();
        buildLayout();
        // refreshDisplayFont();
        // displayArea.setFont(propertiesPanel.getSelectedFont());
        // propertiesPanel.setFontListener(this);
        pack();
    }

    protected void createComponents() {
        // propertiesPanel = new FontPropertiesPanel();
        sampleText = new JTextField(20);
        displayArea = new JLabel("");
        displayArea.setPreferredSize(new Dimension(200, 75));
        displayArea.setMinimumSize(new Dimension(200, 75));
    }

    protected void createDocumentListener() {
        Document document = sampleText.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleDocumentUpdate();
            }
        });
    }

    protected void buildLayout() {
        Container pane = getContentPane();
        GridBagConstraints constraints = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);

        constraints.insets = new Insets(5, 10, 5, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        constraints.gridx = 0;
        BevelBorder bb = new BevelBorder(BevelBorder.RAISED);
        TitledBorder tb = new TitledBorder(bb, "Font");
        propertiesPanel.setBorder(tb);
        layout.setConstraints(propertiesPanel, constraints);
        pane.add(propertiesPanel);

        osman.setBorder(tb);

        layout.setConstraints(sampleText, constraints);
        pane.add(sampleText);

        layout.setConstraints(displayArea, constraints);
        pane.add(displayArea);
    }

    protected void handleDocumentUpdate() {
        displayArea.setText(sampleText.getText());
    }

//    protected void refreshDisplayFont() {
//        displayArea.setFont(getSelectedFont());
//    }

//    public Font getSelectedFont() {
//        String name = propertiesPanel.getSelectedFontName();
//        int style = 0;
//        style += (propertiesPanel.isBoldSelected() ? Font.BOLD : 0);
//        style += (propertiesPanel.isItalicSelected() ? Font.ITALIC : 0);
//        int size = propertiesPanel.getSelectedFontSize();
//
//        return new Font(name, style, size);
//    }
//
//    public void refeshDisplayFont() {
//        displayArea.setFont(getSelectedFont());
//    }

    @Override
    public void fontChanged(Font newFont) {
        displayArea.setFont(newFont);
    }
}
