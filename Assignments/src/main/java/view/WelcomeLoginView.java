package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeLoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    public JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnLogin;

    public WelcomeLoginView() {
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
                    WelcomeLoginView window = new WelcomeLoginView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblWelcome = new JLabel("Welcome!");
        GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
        gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
        gbc_lblWelcome.gridx = 4;
        gbc_lblWelcome.gridy = 1;
        frame.getContentPane().add(lblWelcome, gbc_lblWelcome);

        JLabel lblUsername = new JLabel("Username");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.gridx = 3;
        gbc_lblUsername.gridy = 3;
        frame.getContentPane().add(lblUsername, gbc_lblUsername);

        usernameField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 4;
        gbc_textField.gridy = 3;
        frame.getContentPane().add(usernameField, gbc_textField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 3;
        gbc_lblPassword.gridy = 4;
        frame.getContentPane().add(lblPassword, gbc_lblPassword);

        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.gridx = 4;
        gbc_passwordField.gridy = 4;
        frame.getContentPane().add(passwordField, gbc_passwordField);

        btnLogin = new JButton("Login");
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
        gbc_btnLogin.gridx = 4;
        gbc_btnLogin.gridy = 6;
        frame.getContentPane().add(btnLogin, gbc_btnLogin);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setLoginButtonListener(ActionListener LoginButtonListener) {
        btnLogin.addActionListener(LoginButtonListener);
    }

}
