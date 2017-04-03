package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView {

    public JFrame frame;
    private JTextField tfusername;
    private JTextField tfpassword;
    private JButton btnAddEmployee;
    private JButton btnUpdateEmployee;
    private JButton btnViewEmployee;
    private JButton btnDeleteEmployee;
    private JButton btnViewAllEmployees;
    private JButton btnGenerateReport;

    public AdminView() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public void display() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AdminView window = new AdminView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 644, 398);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{82, 125, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblWelcome = new JLabel("Welcome Mr. Admin!");
        GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
        gbc_lblWelcome.gridwidth = 3;
        gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
        gbc_lblWelcome.gridx = 2;
        gbc_lblWelcome.gridy = 1;
        frame.getContentPane().add(lblWelcome, gbc_lblWelcome);

        JLabel lblOperations = new JLabel("Operations");
        GridBagConstraints gbc_lblOperations = new GridBagConstraints();
        gbc_lblOperations.insets = new Insets(0, 0, 5, 5);
        gbc_lblOperations.gridx = 2;
        gbc_lblOperations.gridy = 3;
        frame.getContentPane().add(lblOperations, gbc_lblOperations);

        JLabel lblNewLabel = new JLabel("Employee Info");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 4;
        frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);


        JLabel lblName = new JLabel("Name");
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 1;
        gbc_lblName.gridy = 5;
        frame.getContentPane().add(lblName, gbc_lblName);

        tfusername = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 2;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 5;
        frame.getContentPane().add(tfusername, gbc_textField);
        tfusername.setColumns(10);

        btnAddEmployee = new JButton("Add Employee");
        GridBagConstraints gbc_btnAddEmployee = new GridBagConstraints();
        gbc_btnAddEmployee.insets = new Insets(0, 0, 5, 5);
        gbc_btnAddEmployee.gridx = 5;
        gbc_btnAddEmployee.gridy = 5;
        frame.getContentPane().add(btnAddEmployee, gbc_btnAddEmployee);

        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 6;
        frame.getContentPane().add(lblPassword, gbc_lblPassword);

        tfpassword = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 2;
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 2;
        gbc_textField_1.gridy = 6;
        frame.getContentPane().add(tfpassword, gbc_textField_1);
        tfpassword.setColumns(10);

        btnUpdateEmployee = new JButton("Update Employee");
        GridBagConstraints gbc_btnUpdateEmployee = new GridBagConstraints();
        gbc_btnUpdateEmployee.insets = new Insets(0, 0, 5, 5);
        gbc_btnUpdateEmployee.gridx = 5;
        gbc_btnUpdateEmployee.gridy = 6;
        frame.getContentPane().add(btnUpdateEmployee, gbc_btnUpdateEmployee);

        btnViewEmployee = new JButton("View Employee");
        GridBagConstraints gbc_btnViewEmployee = new GridBagConstraints();
        gbc_btnViewEmployee.insets = new Insets(0, 0, 5, 5);
        gbc_btnViewEmployee.gridx = 5;
        gbc_btnViewEmployee.gridy = 7;
        frame.getContentPane().add(btnViewEmployee, gbc_btnViewEmployee);

        btnViewAllEmployees = new JButton("View All Employees");
        GridBagConstraints gbc_btnViewAllEmployees = new GridBagConstraints();
        gbc_btnViewAllEmployees.insets = new Insets(0, 0, 5, 5);
        gbc_btnViewAllEmployees.gridx = 1;
        gbc_btnViewAllEmployees.gridy = 8;
        frame.getContentPane().add(btnViewAllEmployees, gbc_btnViewAllEmployees);

        btnDeleteEmployee = new JButton("Delete Employee");
        GridBagConstraints gbc_btnDeleteEmployee = new GridBagConstraints();
        gbc_btnDeleteEmployee.insets = new Insets(0, 0, 5, 5);
        gbc_btnDeleteEmployee.gridx = 5;

        gbc_btnDeleteEmployee.gridy = 8;
        frame.getContentPane().add(btnDeleteEmployee, gbc_btnDeleteEmployee);

        btnGenerateReport = new JButton("Generate Report");
        GridBagConstraints gbc_btnGenerateReport = new GridBagConstraints();
        gbc_btnGenerateReport.insets = new Insets(0, 0, 0, 5);
        gbc_btnGenerateReport.gridx = 1;
        gbc_btnGenerateReport.gridy = 9;
        frame.getContentPane().add(btnGenerateReport, gbc_btnGenerateReport);
    }

    public String getUsername() {
        return tfusername.getText();
    }

    public String getPassword() {
        return tfpassword.getText();
    }
////SET action listeners

    public void setAddButtonListener(ActionListener AddButtonListener) {
        btnAddEmployee.addActionListener(AddButtonListener);
    }

    public void setUpdateButtonListener(ActionListener UpdateButtonListener) {
        btnUpdateEmployee.addActionListener(UpdateButtonListener);
    }

    public void setDeleteButtonListener(ActionListener DeleteButtonListener) {
        btnDeleteEmployee.addActionListener(DeleteButtonListener);
    }

    public void setViewButtonListener(ActionListener ViewButtonListener) {
        btnViewEmployee.addActionListener(ViewButtonListener);
    }

    public void setViewAllButtonListener(ActionListener ViewAllButtonListener) {
        btnViewAllEmployees.addActionListener(ViewAllButtonListener);
    }

    public void setGenerateReportButtonListener(ActionListener GenerateReportButtonListener) {
        btnGenerateReport.addActionListener(GenerateReportButtonListener);
    }


}
