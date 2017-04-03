package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextArea hello;
    private JTextField tfUsername;
    private JLabel lbUsername;
    private JPasswordField tfPassword;
    private JLabel lbPassword;
    private JButton btnLogin;

    public LoginView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));

        hello.setPreferredSize(new Dimension(50, 50));
        hello.setAlignmentX(CENTER_ALIGNMENT);
        hello.setBackground(new Color(124, 225, 10));
        add(hello);
        add(lbUsername);
        add(tfUsername);

        tfUsername.setSize(50, 150);
        add(lbPassword);
        add(tfPassword);

        tfPassword.setSize(50, 150);
        add(btnLogin);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        hello = new JTextArea("Welcome!");
        tfUsername = new JTextField();
        tfPassword = new JPasswordField();
        lbUsername = new JLabel("username:");
        lbPassword = new JLabel("password:");
        btnLogin = new JButton("Login");

    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }


}
