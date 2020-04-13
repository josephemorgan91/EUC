package edu.csus.csc131.euc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SummerRatesPanel extends JPanel {
    private static final int HOURS_IN_DAY = 24;
    private static Map<String, Integer> TIMES;
    private static String[] TIMES_KEYS;
    private JTextField offPeakRatesField;
    private JTextField midPeakRatesField;
    private JTextField onPeakRatesField;
    private JTextField secondMidPeakRatesField;
    private JComboBox<String> offPeakFromBox;
    private JComboBox<String> offPeakToBox;
    private JComboBox<String> midPeakFromBox;
    private JComboBox<String> midPeakToBox;
    private JComboBox<String> onPeakFromBox;
    private JComboBox<String> onPeakToBox;
    private JComboBox<String> secondMidPeakFromBox;
    private JComboBox<String> secondMidPeakToBox;

    public SummerRatesPanel() {
        System.out.println("In SummerRatesPanel()");
        initTimes();

        buildPanel();
    }

    public void buildPanel() {
        // Init Elements
        offPeakRatesField = new JTextField(8);
        midPeakRatesField = new JTextField(8);
        onPeakRatesField = new JTextField(8);
        secondMidPeakRatesField = new JTextField(8);
        offPeakFromBox = new JComboBox<String>();
        offPeakFromBox.addItem("00:00");
        offPeakToBox = new JComboBox<String>(TIMES_KEYS);
        midPeakFromBox = new JComboBox<String>(TIMES_KEYS);
        midPeakToBox = new JComboBox<String>(TIMES_KEYS);
        onPeakFromBox = new JComboBox<String>(TIMES_KEYS);
        onPeakToBox = new JComboBox<String>(TIMES_KEYS);
        secondMidPeakFromBox = new JComboBox<String>(TIMES_KEYS);
        secondMidPeakToBox = new JComboBox<String>();
        secondMidPeakToBox.addItem("00:00");

        ////// Build Panel //////
        int ratesLabelIndex = 0;
        int fromLabelIndex = 0;
        int toLabelIndex = 0;
        Insets def = new Insets(3, 20, 3, 20);
        Insets right = new Insets(3, 20, 3, 3);
        Insets left = new Insets(3, 3, 3, 20);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.insets = def;

        // Row One
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Off Peak"), c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(offPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("From:"), c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(new JLabel("To:"), c);

        c.gridx = 5;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(offPeakFromBox, c);

        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(offPeakToBox, c);
        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        // Row Two
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Mid Peak"), c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(midPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("From:"), c);

        c.gridx = 4;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(new JLabel("To:"), c);

        c.gridx = 5;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(midPeakFromBox, c);

        c.gridx = 5;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(midPeakToBox, c);
        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        // Row Three
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Peak"), c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(onPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("From:"), c);

        c.gridx = 4;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(new JLabel("To:"), c);

        c.gridx = 5;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(onPeakFromBox, c);

        c.gridx = 5;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(onPeakToBox, c);
        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        // Row Four
        c.gridx = 0;
        c.gridy = 6;
        c.gridheight = 2;
        c.gridwidth = 2;
        add(new JLabel("Mid Peak"), c);

        c.gridx = 2;
        c.gridy = 6;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Rates:"), c);

        c.gridx = 3;
        c.gridy = 6;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(secondMidPeakRatesField, c);

        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 4;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = right;
        c.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("From:"), c);

        c.gridx = 4;
        c.gridy = 7;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(new JLabel("To:"), c);

        c.gridx = 5;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = left;
        c.anchor = GridBagConstraints.LINE_START;
        add(secondMidPeakFromBox, c);

        c.gridx = 5;
        c.gridy = 7;
        c.gridheight = 1;
        c.gridwidth = 1;
        add(secondMidPeakToBox, c);
        c.insets = def;
        c.anchor = GridBagConstraints.CENTER;
    }

    public Double getOffPeakRatesField() {
        return Double.valueOf(offPeakRatesField.getText());
    }

    public Double getMidPeakRatesField() {
        return Double.valueOf(midPeakRatesField.getText());
    }

    public Double getOnPeakRatesField() {
        return Double.valueOf(onPeakRatesField.getText());
    }

    public Double getSecondMidPeakRatesField() {
        return Double.valueOf(secondMidPeakRatesField.getText());
    }

    public int getOffPeakFromBox() {
        return TIMES.get(offPeakFromBox.getSelectedItem().toString());
    }

    public int getOffPeakToBox() {
        return TIMES.get(offPeakToBox.getSelectedItem().toString());
    }

    public int getMidPeakFromBox() {
        return TIMES.get(midPeakFromBox.getSelectedItem().toString());
    }

    public int getMidPeakToBox() {
        return TIMES.get(midPeakToBox.getSelectedItem().toString());
    }

    public int getOnPeakFromBox() {
        return TIMES.get(onPeakFromBox.getSelectedItem().toString());
    }

    public int getOnPeakToBox() {
        return TIMES.get(onPeakToBox.getSelectedItem().toString());
    }

    public int getSecondMidPeakFromBox() {
        return TIMES.get(secondMidPeakFromBox.getSelectedItem().toString());
    }

    public int getSecondMidPeakToBox() {
        return TIMES.get(secondMidPeakToBox.getSelectedItem().toString());
    }

    private static void initTimes() {
        TIMES_KEYS = new String[HOURS_IN_DAY];
        TIMES = new HashMap<String, Integer>();

        TIMES_KEYS[0] = "00:00";
        TIMES_KEYS[1] = "01:00";
        TIMES_KEYS[2] = "02:00";
        TIMES_KEYS[3] = "03:00";
        TIMES_KEYS[4] = "04:00";
        TIMES_KEYS[5] = "05:00";
        TIMES_KEYS[6] = "06:00";
        TIMES_KEYS[7] = "07:00";
        TIMES_KEYS[8] = "08:00";
        TIMES_KEYS[9] = "09:00";
        TIMES_KEYS[10] = "10:00";
        TIMES_KEYS[11] = "11:00";
        TIMES_KEYS[12] = "12:00";
        TIMES_KEYS[13] = "13:00";
        TIMES_KEYS[14] = "14:00";
        TIMES_KEYS[15] = "15:00";
        TIMES_KEYS[16] = "16:00";
        TIMES_KEYS[17] = "17:00";
        TIMES_KEYS[18] = "18:00";
        TIMES_KEYS[19] = "19:00";
        TIMES_KEYS[20] = "20:00";
        TIMES_KEYS[21] = "21:00";
        TIMES_KEYS[22] = "22:00";
        TIMES_KEYS[23] = "23:00";

        for (int i = 0; i < HOURS_IN_DAY; i++) {
            TIMES.put(TIMES_KEYS[i], i);
        }
    }
}
