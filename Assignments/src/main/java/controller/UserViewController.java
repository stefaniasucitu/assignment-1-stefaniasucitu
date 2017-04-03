package controller;

import model.Account;
import model.Client;
import model.validation.Notification;
import org.joda.time.DateTime;
import service.account.AccountService;
import service.client.ClientService;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserViewController {
    private final UserView userView;
    private final AccountService accountService;
    private final ClientService clientService;

    public UserViewController(UserView userView, AccountService accountService, ClientService clientService) {

        this.userView = userView;
        this.accountService = accountService;
        this.clientService = clientService;
        userView.setAddNewAccountButtonListener(new AddNewAccountButtonListener());
        userView.setAddNewClientButtonListener(new AddNewClientButtonListener());
        userView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
        userView.setUpdateClientButtonListener(new UpdateClientButtonListener());
        userView.setDeleteAccountButtonListener(new DeleteAccountButtonListener());
        userView.setViewClientButtonListener(new ViewClientButtonListener());

        userView.setViewAllClientsButtonListener(new ViewAllClientsButtonLister());
        userView.setTransferButtonListener(new TransferButtonLister());
        userView.setViewAccountButtonListener(new ViewAccountButtonLister());

    }

    private class AddNewAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String firstNameClient = userView.getFirstName();
            String lastNameClient = userView.getLastName();
            String type = userView.getAccountType();
            DateTime date = new org.joda.time.DateTime();
            Client client;

            Notification<Account> addNewAccountNotification = accountService.addNewAccount(type, date);
            Account addedAccount = addNewAccountNotification.getResult();

            client = clientService.findByName(firstNameClient, lastNameClient).getResult();

            Notification<Boolean> addAccountForClientNotification = clientService.addAccountForClient(client.getId(), addedAccount.getId());

            if (addNewAccountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(),
                        addNewAccountNotification.getFormattedErrors());
            } else {
                if (!addAccountForClientNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "new Account add op  not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "new Account added successful!");
                }
            }

        }
    }

    private class AddNewClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstNameClient = userView.getFirstName();
            String lastNameClient = userView.getLastName();
            String CNP = userView.getCNP();
            String card = userView.getCard();
            String address = userView.getAddress();

            Notification<Boolean> addClientNotification = clientService.addNewClient(null, firstNameClient, lastNameClient,
                    CNP, card, address);
            if (addClientNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(), addClientNotification.getFormattedErrors());
            } else {
                if (!addClientNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Client add not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Client added successful!");
                }
            }
        }
    }

    private class UpdateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long idAccount = Long.parseLong(userView.getAccounId());
            Float money = Float.parseFloat(userView.getSum());
            String operation = userView.getOperation();

            Notification<Boolean> updateAccountNotification = accountService.updateAccount(idAccount, money, operation);
            if (updateAccountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(),
                        updateAccountNotification.getFormattedErrors());
            } else {
                if (!updateAccountNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Update not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Update successful!");
                }
            }

        }
    }

    private class DeleteAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long idAccount = Long.parseLong(userView.getAccounId());
            Notification<Boolean> deleteAccountNotification = accountService.removeAccount(idAccount);

            if (deleteAccountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(),
                        deleteAccountNotification.getFormattedErrors());
            } else {
                if (!deleteAccountNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Delete not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Delete successful!");
                }
            }
        }
    }

    private class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String firstNameClient = userView.getFirstName();
            String lastNameClient = userView.getLastName();
            String card = userView.getCard();
            String cnp = userView.getCNP();
            String address = userView.getAddress();

            Notification<Boolean> updateClientNotification = clientService.updateClient(null, firstNameClient, lastNameClient,
                    cnp, card, address);

            if (updateClientNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(), updateClientNotification.getFormattedErrors());
            } else {
                if (!updateClientNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Update not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Update successful!");
                }
            }
        }
    }

    private class ViewClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String firstNameClient = userView.getFirstName();
            String lastNameClient = userView.getLastName();

            Client client = clientService.findByName(firstNameClient, lastNameClient).getResult();
            Notification<Client> findClientNotification;
            findClientNotification = clientService.findByName(firstNameClient, lastNameClient);

            if (findClientNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(), findClientNotification.getFormattedErrors());
            } else {
                if (findClientNotification.getResult() != null)
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Client  not found, please try again later.");
                else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Update successful!");
                    //afisez clientul
                }
            }
        }
    }


    private class ViewAllClientsButtonLister implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<Client> clients = clientService.findAll();

            //afisez asta in tabel
        }

    }

    private class TransferButtonLister implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            Long fromId = Long.parseLong(userView.getFromAccount());
            Long toId = Long.parseLong(userView.getToAccount());
            Float sum = Float.parseFloat(userView.getSum());
            Notification<Boolean> transferMoneyNotification = accountService.transferMoney(fromId, toId, sum);
            if (transferMoneyNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(),
                        transferMoneyNotification.getFormattedErrors());
            } else {
                if (!transferMoneyNotification.getResult()) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Transfer not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Transfer successful!");
                }
            }
        }
    }

    private class ViewAccountButtonLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long accountId = Long.parseLong(userView.getAccounId());
            Notification<Account> accountFoundNotification = accountService.findById(accountId);
            if (accountFoundNotification.hasErrors()) {
                JOptionPane.showMessageDialog(userView.getContentPane(),
                        accountFoundNotification.getFormattedErrors());
            } else {
                if (accountFoundNotification.getResult() == null) {
                    JOptionPane.showMessageDialog(userView.getContentPane(),
                            "Account not found.");
                } else {
                    JOptionPane.showMessageDialog(userView.getContentPane(), "Account successful!");
                    //afisez contul
                }
            }
        }
    }
}


