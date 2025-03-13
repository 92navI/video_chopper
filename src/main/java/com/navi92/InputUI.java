package com.navi92;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static com.navi92.util.TextUtils.format;

public class InputUI {
    protected static void createAndShowGUI() {
        JFrame frame = new JFrame("Conversion Input UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel videoLabel = new JLabel("Target Video:", SwingConstants.RIGHT);
        JTextField videoField = new JTextField();
        JButton videoButton = new JButton("Browse");

        JLabel folderLabel = new JLabel("Output Folder:", SwingConstants.RIGHT);
        JTextField folderField = new JTextField();
        JButton folderButton = new JButton("Browse");

        JLabel nameLabel = new JLabel("Name:", SwingConstants.RIGHT);
        JTextField nameField = new JTextField();

        JLabel intLabel = new JLabel("Frame Rate:", SwingConstants.RIGHT);
        JSpinner intSpinner = new JSpinner(new SpinnerNumberModel(20, 1, Integer.MAX_VALUE, 1));

        JLabel formattingLabel = new JLabel("Number Formatting:", SwingConstants.RIGHT);
        JTextField formattingField = new JTextField("0000");

        JCheckBox toggleCheck = new JCheckBox("Do Correct Structure");
        toggleCheck.setSelected(true);


        JButton submitButton = new JButton("Submit");

        videoButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                videoField.setText(selectedFile.getAbsolutePath());
            }
        });

        folderButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                folderField.setText(selectedFolder.getAbsolutePath());
            }
        });

        submitButton.addActionListener(e -> {
            String videoPath = videoField.getText();
            String outputPath = folderField.getText();
            String name = nameField.getText();
            int frameRate = (int) intSpinner.getValue();
            String numberFormatting = formattingField.getText();
            boolean doCorrectStructure = toggleCheck.isSelected();

            if (!videoPath.isEmpty() && !outputPath.isEmpty()) {
                frame.dispose();
                Main.execute(videoPath, outputPath, name, frameRate, numberFormatting, doCorrectStructure);
            } else {
                System.out.println(format("&rPlease select the file and folder!"));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(videoLabel, gbc);
        gbc.gridx = 1;
        frame.add(videoField, gbc);
        gbc.gridx = 2;
        frame.add(videoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(folderLabel, gbc);
        gbc.gridx = 1;
        frame.add(folderField, gbc);
        gbc.gridx = 2;
        frame.add(folderButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(nameLabel, gbc);
        gbc.gridx = 1;
        frame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(intLabel, gbc);
        gbc.gridx = 1;
        frame.add(intSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(formattingLabel, gbc);
        gbc.gridx = 1;
        frame.add(formattingField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        frame.add(toggleCheck, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        frame.add(submitButton, gbc);

        frame.setVisible(true);
    }
}
