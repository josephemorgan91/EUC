package edu.csus.csc131.euc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;

public class AddDayDialog extends JDialog implements ActionListener {

    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JButton finalizeButton;
    private JButton readFromFileButton;
    private JButton cancelButton;
    private JButton enterManuallyButton;
    private DayEntryPanel fieldPanel;
    private Week week;
    private JTextField usageTextFields[] = new JTextField[HOURS_IN_DAY];
    private JTextField dateField = new JTextField(LocalDate.now().toString(), 24);
    public double[] hoursInDayUsage = new double[HOURS_IN_DAY];
    private int focusCounter = 0;
    private static int HOURS_IN_DAY = 24;
    private static Dimension TF_DIMENSION = new Dimension(100, 25);
    private static Dimension BUTTON_DIMENSION = new Dimension(150, 25);
    private static Dimension LABEL_DIMENSION = new Dimension(180, 25);

    public AddDayDialog(Frame owner, Week wk) {
        super(owner, true);
        this.week = wk;

        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        topPanel = new JPanel();
        midPanel = new JPanel();
        bottomPanel = new JPanel();
        fieldPanel = new DayEntryPanel();

        topPanel.setLayout(new FlowLayout());
        midPanel.setLayout(new GridBagLayout());
        bottomPanel.setLayout(new GridBagLayout());

        finalizeButton = new JButton ("Finalize Usage");
        readFromFileButton = new JButton ("Read From File");
        enterManuallyButton = new JButton ("Enter Usage Manually");
        cancelButton = new JButton("Cancel");

        finalizeButton.addActionListener(this);
        readFromFileButton.addActionListener(this);
        enterManuallyButton.addActionListener(this);
        cancelButton.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        bottomPanel.add(finalizeButton, c);

        c.gridx = 1;
        c.gridy = 0;
        bottomPanel.add(cancelButton, c);

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        midPanel.add(readFromFileButton, c);

        c.weighty = .5;
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        midPanel.add(new JLabel("Or"), c);

        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        midPanel.add(enterManuallyButton, c);

        Border innerBorder = BorderFactory.createTitledBorder("How would you like to enter usage?");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);

        midPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        midPanel.setPreferredSize(new Dimension(400, 100));

        //add left and right panels to the Add Day Dialog
        topPanel.add(new JLabel("Enter Date: "));
        topPanel.add(dateField);

        this.add(midPanel, BorderLayout.CENTER);

        //add(topPanel, BorderLayout.NORTH);
        //add(fieldPanel, BorderLayout.CENTER);
        //add(bottomPanel, BorderLayout.SOUTH);

        pack ();

        //setting focus initially for new frame must be after pack() according to stack overflow
        //usageTextFields[focusCounter].requestFocusInWindow();
        //usageTextFields[focusCounter].selectAll();

        //must be last or bugggggggs
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == enterManuallyButton) {
            this.remove(midPanel);
            this.add(topPanel, BorderLayout.NORTH);
            this.add(fieldPanel, BorderLayout.CENTER);
            this.add(bottomPanel, BorderLayout.SOUTH);
            this.pack();
            this.validate();
            this.repaint();
        }
        else if (source == finalizeButton) {
            Day dayToAdd;
            Usage usageToAdd = new Usage();
            for (int i = 0; i < HOURS_IN_DAY; ++i)
            {
               usageToAdd.setUsage(i, fieldPanel.getTextFieldContents(i));
            }
            dayToAdd = new Day(dateField.getText(), usageToAdd);
            week.addDay(dayToAdd);
            this.dispose();
        }
        else if (source == cancelButton) {
            this.dispose();
        }
        else if (source == readFromFileButton)
        {
            //Open a file browser, save file path
            JFileChooser fileChooser = new JFileChooser();

            //default the directory to the users home directory
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            //variable to store the result of the dialog menu
            int result = fileChooser.showOpenDialog(this);

            //if the result is an approved option (a file)
            if (result == JFileChooser.APPROVE_OPTION) {
                //new file object to hold the file
                File selectedFile = fileChooser.getSelectedFile();

                //Fetch data from file and add a day to week collection
                week.fetchDayFromFile(selectedFile.getAbsolutePath());

                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                this.dispose();
            }
            else
            {
                System.out.println("Failed to select a file or choose an approved file.");
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            usageTextFields[focusCounter + 1].requestFocus();
        }
    }
}
