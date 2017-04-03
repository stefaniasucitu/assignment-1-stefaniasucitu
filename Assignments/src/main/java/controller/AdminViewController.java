package controller;

import model.validation.Notification;
import service.user.UserService;
import view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminViewController {
    private final AdminView adminView;
    private final UserService userService;

    public AdminViewController(AdminView adminView, UserService userService) {
        this.adminView = adminView;
        this.userService = userService;
        adminView.setAddButtonListener(new AddButtonListener());
        adminView.setDeleteButtonListener(new DeleteButtonListener());
        adminView.setGenerateReportButtonListener(new GenerateReportButtonListener());
        adminView.setUpdateButtonListener(new UpdateButtonListener());
        adminView.setViewAllButtonListener(new ViewAllButtonListener());
        adminView.setViewButtonListener(new ViewButtonListener());

    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();

            Notification<Boolean> addNotification = userService.register(username, password);
            if (addNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.frame.getContentPane(), addNotification.getFormattedErrors());
            } else {
                if (!addNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.frame.getContentPane(),
                            "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(adminView.frame.getContentPane(), "Registration successful!");
                }
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getUsername();
            String password = adminView.getPassword();
            Notification<Boolean> deleteNotification = userService.remove(username);
            if (deleteNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.frame.getContentPane(),
                        deleteNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(adminView.frame.getContentPane(), "Deletion successful!");
            }
        }
    }

    private class GenerateReportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub

        }

    }

    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            //Not really used
        }

    }

    private class ViewAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            userService.findAll();
            ///////AFISEZ ASTA in tabel
        }

    }

    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String userName = adminView.getUsername();
            userService.findByName(userName);
            //////AFISAREEE
        }

    }
}
