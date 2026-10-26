import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class B5_StudentRegistration extends JFrame {
    private JTextField rollField;
    private JTextField nameField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup genderGroup;
    private JCheckBox javaBox;
    private JCheckBox pythonBox;
    private JCheckBox dbmsBox;
    private JComboBox<String> branchBox;
    private JComboBox<String> cityBox;
    private JTextArea addressArea;
    private JTextArea outputArea;
    private JTextArea recordsArea;

    public B5_StudentRegistration() {
        setTitle("Student Registration Form");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        JLabel logo = new JLabel("STUDENT REGISTRATION", SwingConstants.CENTER);
        logo.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        formPanel.add(logo, BorderLayout.NORTH);

        rollField = new JTextField(18);
        nameField = new JTextField(18);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        javaBox = new JCheckBox("Java");
        pythonBox = new JCheckBox("Python");
        dbmsBox = new JCheckBox("DBMS");
        branchBox = new JComboBox<String>(new String[]{"CSE", "AI & ML", "ECE", "EEE", "IT"});
        cityBox = new JComboBox<String>(new String[]{"Warangal", "Hyderabad", "Karimnagar", "Khammam"});
        addressArea = new JTextArea(3, 18);
        outputArea = new JTextArea(6, 25);
        outputArea.setEditable(false);
        recordsArea = new JTextArea();
        recordsArea.setEditable(false);

        addRow(fieldsPanel, c, 0, "Roll No", rollField);
        addRow(fieldsPanel, c, 1, "Name", nameField);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        addRow(fieldsPanel, c, 2, "Gender", genderPanel);

        JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subjectPanel.add(javaBox);
        subjectPanel.add(pythonBox);
        subjectPanel.add(dbmsBox);
        addRow(fieldsPanel, c, 3, "Subjects", subjectPanel);

        addRow(fieldsPanel, c, 4, "Branch", branchBox);
        addRow(fieldsPanel, c, 5, "City", cityBox);
        addRow(fieldsPanel, c, 6, "Address", new JScrollPane(addressArea));

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(submitButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);

        formPanel.add(new JScrollPane(fieldsPanel), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        formPanel.add(bottomPanel, BorderLayout.SOUTH);

        tabs.addTab("Registration", formPanel);
        tabs.addTab("Submitted Records", new JScrollPane(recordsArea));
        add(tabs);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, Component component) {
        c.gridx = 0;
        c.gridy = row;
        c.weightx = 0;
        panel.add(new JLabel(label), c);
        c.gridx = 1;
        c.weightx = 1;
        panel.add(component, c);
    }

    private void submitForm() {
        try {
            String roll = rollField.getText().trim();
            String name = nameField.getText().trim();

            if (roll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Roll Number");
                return;
            }
            if (!roll.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Roll Number must be numeric");
                return;
            }
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Name");
                return;
            }
            if (!maleButton.isSelected() && !femaleButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select Gender");
                return;
            }

            String subjects = "";
            if (javaBox.isSelected()) subjects += "Java ";
            if (pythonBox.isSelected()) subjects += "Python ";
            if (dbmsBox.isSelected()) subjects += "DBMS ";
            if (subjects.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one subject");
                return;
            }

            String gender = maleButton.isSelected() ? "Male" : "Female";
            String details = "ROLL NO: " + roll
                    + "\nNAME: " + name
                    + "\nGENDER: " + gender
                    + "\nSUBJECTS: " + subjects.trim()
                    + "\nBRANCH: " + branchBox.getSelectedItem()
                    + "\nCITY: " + cityBox.getSelectedItem()
                    + "\nADDRESS: " + addressArea.getText().trim()
                    + "\n------------------------------";

            outputArea.setText(details);
            recordsArea.append(details + "\n");
            JOptionPane.showMessageDialog(this, "Registration submitted successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to submit: " + e.getMessage());
        }
    }

    private void resetForm() {
        rollField.setText("");
        nameField.setText("");
        genderGroup.clearSelection();
        javaBox.setSelected(false);
        pythonBox.setSelected(false);
        dbmsBox.setSelected(false);
        branchBox.setSelectedIndex(0);
        cityBox.setSelectedIndex(0);
        addressArea.setText("");
        outputArea.setText("");
        rollField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new B5_StudentRegistration().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Unable to open form: " + e.getMessage());
                }
            }
        });
    }
}