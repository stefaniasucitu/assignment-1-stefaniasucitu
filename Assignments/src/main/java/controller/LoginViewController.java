package controller;

import model.Role;
import model.User;
import model.validation.Notification;
import service.account.AccountService;
import service.client.ClientService;
import service.user.UserService;
import view.AdminView;
import view.UserView;
import view.WelcomeLoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginViewController {
    private final WelcomeLoginView loginView;
    private final UserService userService;
    private final ClientService clientService;
    private final AccountService accountService;


    public LoginViewController(WelcomeLoginView loginView, UserService userService, ClientService clientService, AccountService accountService) {
        this.loginView = loginView;
        this.userService = userService;
        this.clientService = clientService;
        this.accountService = accountService;
        loginView.setLoginButtonListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = userService.login(username, password);
            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.frame.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                User user = loginNotification.getResult();
                Role employeeRole = userService.findRoleByTitle("Employee");
                Role adminRole = userService.findRoleByTitle("Administrator");
                if (user.getRoles().contains(adminRole)) {
                    JOptionPane.showMessageDialog(loginView.frame.getContentPane(), "Administrator Login successful!");
                    new AdminViewController(new AdminView(), userService);
                } else if (user.getRoles().contains(employeeRole)) {
                    JOptionPane.showMessageDialog(loginView.frame.getContentPane(), "Employee Login successful!");
                    new UserViewController(new UserView(), accountService, clientService);
                }
            }
        }
    }
}

