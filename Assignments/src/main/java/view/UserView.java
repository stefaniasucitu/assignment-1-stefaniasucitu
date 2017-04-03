package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class UserView extends JFrame {

    public JFrame frame;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField CNP;
    private JTextField card;


    private JTextField address;
    private JTextField accountType;
    private JTextField accountDate;
    private JTextField accounId;
    private JTextField fromAccount;
    private JTextField toAccount;
    private JTextField sum;
    private JButton btnAddNewClient;
    private JButton btnUpdateClient;
    private JButton btnViewAllClients;
    private JButton btnViewClient;
    private JButton btnAddNewAccount;
    private JButton btnViewAccount;
    private JButton btnDeleteAccount;
    private JButton btnTransfer;
    private JButton btnUpdateAccount;
    private JLabel lblOperationsToAccount;
    private JLabel lblOperation;
    private JTextField txtAddOrWithdraw;
    private JLabel lblPayUtilities;
    private JLabel lblUtilitiesAccounts;
    private JTextField txtElectricaId;
    private JTextField txtApaId;
    private JTextField txtGazId;

    /**
     * Create the application.
     */
    public UserView() {
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
                    UserView window = new UserView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(500, 500, 1100, 795);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 43, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Employee Operations");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 2;
        gbc_lblNewLabel.gridwidth = 8;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 0;
        frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblCleintOperations = new JLabel("Client Operations");
        GridBagConstraints gbc_lblCleintOperations = new GridBagConstraints();
        gbc_lblCleintOperations.gridwidth = 2;
        gbc_lblCleintOperations.insets = new Insets(0, 0, 5, 5);
        gbc_lblCleintOperations.gridx = 2;
        gbc_lblCleintOperations.gridy = 2;
        frame.getContentPane().add(lblCleintOperations, gbc_lblCleintOperations);

        btnAddNewClient = new JButton("Add New Client");
        GridBagConstraints gbc_btnAddNewClient = new GridBagConstraints();
        gbc_btnAddNewClient.insets = new Insets(0, 0, 5, 5);
        gbc_btnAddNewClient.gridx = 2;
        gbc_btnAddNewClient.gridy = 3;
        frame.getContentPane().add(btnAddNewClient, gbc_btnAddNewClient);

        JLabel lblClientInfo = new JLabel("Client Info");
        GridBagConstraints gbc_lblClientInfo = new GridBagConstraints();
        gbc_lblClientInfo.anchor = GridBagConstraints.EAST;
        gbc_lblClientInfo.insets = new Insets(0, 0, 5, 5);
        gbc_lblClientInfo.gridx = 4;
        gbc_lblClientInfo.gridy = 3;
        frame.getContentPane().add(lblClientInfo, gbc_lblClientInfo);

        btnUpdateClient = new JButton("Update Client");
        GridBagConstraints gbc_btnUp = new GridBagConstraints();
        gbc_btnUp.insets = new Insets(0, 0, 5, 5);
        gbc_btnUp.gridx = 2;
        gbc_btnUp.gridy = 4;
        frame.getContentPane().add(btnUpdateClient, gbc_btnUp);

        JLabel lblNewLabel_2 = new JLabel("First Name");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 4;
        frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

        firstName = new JTextField();
        GridBagConstraints gbc_firstName = new GridBagConstraints();
        gbc_firstName.gridwidth = 3;
        gbc_firstName.insets = new Insets(0, 0, 5, 5);
        gbc_firstName.fill = GridBagConstraints.HORIZONTAL;
        gbc_firstName.gridx = 5;
        gbc_firstName.gridy = 4;
        frame.getContentPane().add(firstName, gbc_firstName);
        firstName.setColumns(10);

        btnViewAllClients = new JButton("View All Clients");
        GridBagConstraints gbc_btnViewAllClients = new GridBagConstraints();
        gbc_btnViewAllClients.insets = new Insets(0, 0, 5, 5);
        gbc_btnViewAllClients.gridx = 2;
        gbc_btnViewAllClients.gridy = 5;
        frame.getContentPane().add(btnViewAllClients, gbc_btnViewAllClients);

        JLabel lblLastName = new JLabel("Last Name");
        GridBagConstraints gbc_lblLastName = new GridBagConstraints();
        gbc_lblLastName.anchor = GridBagConstraints.EAST;
        gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
        gbc_lblLastName.gridx = 4;
        gbc_lblLastName.gridy = 5;
        frame.getContentPane().add(lblLastName, gbc_lblLastName);

        lastName = new JTextField();
        GridBagConstraints gbc_lastName = new GridBagConstraints();
        gbc_lastName.gridwidth = 3;
        gbc_lastName.insets = new Insets(0, 0, 5, 5);
        gbc_lastName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lastName.gridx = 5;
        gbc_lastName.gridy = 5;
        frame.getContentPane().add(lastName, gbc_lastName);
        lastName.setColumns(10);

        btnViewClient = new JButton("View Client");
        GridBagConstraints gbc_btnViewClient = new GridBagConstraints();
        gbc_btnViewClient.insets = new Insets(0, 0, 5, 5);
        gbc_btnViewClient.gridx = 2;
        gbc_btnViewClient.gridy = 6;
        frame.getContentPane().add(btnViewClient, gbc_btnViewClient);

        JLabel lblCnp = new JLabel("CNP");
        GridBagConstraints gbc_lblCnp = new GridBagConstraints();
        gbc_lblCnp.anchor = GridBagConstraints.EAST;
        gbc_lblCnp.insets = new Insets(0, 0, 5, 5);
        gbc_lblCnp.gridx = 4;
        gbc_lblCnp.gridy = 6;
        frame.getContentPane().add(lblCnp, gbc_lblCnp);

        CNP = new JTextField();
        GridBagConstraints gbc_CNP = new GridBagConstraints();
        gbc_CNP.gridwidth = 3;
        gbc_CNP.insets = new Insets(0, 0, 5, 5);
        gbc_CNP.fill = GridBagConstraints.HORIZONTAL;
        gbc_CNP.gridx = 5;
        gbc_CNP.gridy = 6;
        frame.getContentPane().add(CNP, gbc_CNP);
        CNP.setColumns(10);

        JLabel lblIdcard = new JLabel("Card");
        GridBagConstraints gbc_lblIdcard = new GridBagConstraints();
        gbc_lblIdcard.anchor = GridBagConstraints.EAST;
        gbc_lblIdcard.insets = new Insets(0, 0, 5, 5);
        gbc_lblIdcard.gridx = 4;
        gbc_lblIdcard.gridy = 7;
        frame.getContentPane().add(lblIdcard, gbc_lblIdcard);

        card = new JTextField();
        GridBagConstraints gbc_card = new GridBagConstraints();
        gbc_card.gridwidth = 3;
        gbc_card.insets = new Insets(0, 0, 5, 5);
        gbc_card.fill = GridBagConstraints.HORIZONTAL;
        gbc_card.gridx = 5;
        gbc_card.gridy = 7;
        frame.getContentPane().add(card, gbc_card);
        card.setColumns(10);

        JLabel lblAddress = new JLabel("Address");
        GridBagConstraints gbc_lblAddress = new GridBagConstraints();
        gbc_lblAddress.anchor = GridBagConstraints.EAST;
        gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
        gbc_lblAddress.gridx = 4;
        gbc_lblAddress.gridy = 8;
        frame.getContentPane().add(lblAddress, gbc_lblAddress);

        address = new JTextField();
        GridBagConstraints gbc_address = new GridBagConstraints();
        gbc_address.insets = new Insets(0, 0, 5, 5);
        gbc_address.gridwidth = 3;
        gbc_address.fill = GridBagConstraints.HORIZONTAL;
        gbc_address.gridx = 5;
        gbc_address.gridy = 8;
        frame.getContentPane().add(address, gbc_address);
        address.setColumns(10);

        JLabel lblAccountOperations = new JLabel("Account Operations");
        GridBagConstraints gbc_lblAccountOperations = new GridBagConstraints();
        gbc_lblAccountOperations.gridwidth = 2;
        gbc_lblAccountOperations.insets = new Insets(0, 0, 5, 5);
        gbc_lblAccountOperations.gridx = 2;
        gbc_lblAccountOperations.gridy = 10;
        frame.getContentPane().add(lblAccountOperations, gbc_lblAccountOperations);

        JLabel lblAccountInfo = new JLabel("Account Info");
        GridBagConstraints gbc_lblAccountInfo = new GridBagConstraints();
        gbc_lblAccountInfo.insets = new Insets(0, 0, 5, 5);
        gbc_lblAccountInfo.gridx = 4;
        gbc_lblAccountInfo.gridy = 11;
        frame.getContentPane().add(lblAccountInfo, gbc_lblAccountInfo);

        JLabel lblId = new JLabel("Id");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.EAST;
        gbc_lblId.insets = new Insets(0, 0, 5, 5);
        gbc_lblId.gridx = 4;
        gbc_lblId.gridy = 12;
        frame.getContentPane().add(lblId, gbc_lblId);

        accounId = new JTextField();
        GridBagConstraints gbc_accounId = new GridBagConstraints();
        gbc_accounId.gridwidth = 3;
        gbc_accounId.insets = new Insets(0, 0, 5, 5);
        gbc_accounId.fill = GridBagConstraints.HORIZONTAL;
        gbc_accounId.gridx = 5;
        gbc_accounId.gridy = 12;
        frame.getContentPane().add(accounId, gbc_accounId);
        accounId.setColumns(10);


        btnAddNewAccount = new JButton("Add New Account");
        GridBagConstraints gbc_btnAddNewAccount = new GridBagConstraints();
        gbc_btnAddNewAccount.insets = new Insets(0, 0, 5, 5);
        gbc_btnAddNewAccount.gridx = 2;
        gbc_btnAddNewAccount.gridy = 13;
        frame.getContentPane().add(btnAddNewAccount, gbc_btnAddNewAccount);

        btnViewAccount = new JButton("View Account");
        GridBagConstraints gbc_btnViewAccount = new GridBagConstraints();
        gbc_btnViewAccount.insets = new Insets(0, 0, 5, 5);
        gbc_btnViewAccount.gridx = 2;
        gbc_btnViewAccount.gridy = 14;
        frame.getContentPane().add(btnViewAccount, gbc_btnViewAccount);

        JLabel lblType = new JLabel("Type");
        GridBagConstraints gbc_lblType = new GridBagConstraints();
        gbc_lblType.insets = new Insets(0, 0, 5, 5);
        gbc_lblType.anchor = GridBagConstraints.EAST;
        gbc_lblType.gridx = 4;
        gbc_lblType.gridy = 14;
        frame.getContentPane().add(lblType, gbc_lblType);

        accountType = new JTextField();
        GridBagConstraints gbc_accountType = new GridBagConstraints();
        gbc_accountType.gridwidth = 3;
        gbc_accountType.insets = new Insets(0, 0, 5, 5);
        gbc_accountType.fill = GridBagConstraints.HORIZONTAL;
        gbc_accountType.gridx = 5;
        gbc_accountType.gridy = 14;
        frame.getContentPane().add(accountType, gbc_accountType);
        accountType.setColumns(10);

        btnDeleteAccount = new JButton("Delete Account");
        GridBagConstraints gbc_btnDeleteAccount = new GridBagConstraints();
        gbc_btnDeleteAccount.insets = new Insets(0, 0, 5, 5);
        gbc_btnDeleteAccount.gridx = 2;
        gbc_btnDeleteAccount.gridy = 15;
        frame.getContentPane().add(btnDeleteAccount, gbc_btnDeleteAccount);

        JLabel lblDate = new JLabel("Date");
        GridBagConstraints gbc_lblDate = new GridBagConstraints();
        gbc_lblDate.anchor = GridBagConstraints.EAST;
        gbc_lblDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblDate.gridx = 4;
        gbc_lblDate.gridy = 15;
        frame.getContentPane().add(lblDate, gbc_lblDate);

        accountDate = new JTextField();
        GridBagConstraints gbc_accountDate = new GridBagConstraints();
        gbc_accountDate.gridwidth = 3;
        gbc_accountDate.insets = new Insets(0, 0, 5, 5);
        gbc_accountDate.fill = GridBagConstraints.HORIZONTAL;
        gbc_accountDate.gridx = 5;
        gbc_accountDate.gridy = 15;
        frame.getContentPane().add(accountDate, gbc_accountDate);
        accountDate.setColumns(10);

        lblOperationsToAccount = new JLabel("Operations to Account");
        GridBagConstraints gbc_lblOperationsToAccount = new GridBagConstraints();
        gbc_lblOperationsToAccount.insets = new Insets(0, 0, 5, 5);
        gbc_lblOperationsToAccount.gridx = 2;
        gbc_lblOperationsToAccount.gridy = 16;
        frame.getContentPane().add(lblOperationsToAccount, gbc_lblOperationsToAccount);

        lblOperation = new JLabel("Operation");
        GridBagConstraints gbc_lblOperation = new GridBagConstraints();
        gbc_lblOperation.anchor = GridBagConstraints.EAST;
        gbc_lblOperation.insets = new Insets(0, 0, 5, 5);
        gbc_lblOperation.gridx = 2;
        gbc_lblOperation.gridy = 17;
        frame.getContentPane().add(lblOperation, gbc_lblOperation);

        btnUpdateAccount = new JButton("Update Account");
        GridBagConstraints gbc_btnUpdateAccount = new GridBagConstraints();
        gbc_btnUpdateAccount.insets = new Insets(0, 0, 5, 5);
        gbc_btnUpdateAccount.gridx = 2;
        gbc_btnUpdateAccount.gridy = 18;
        frame.getContentPane().add(btnUpdateAccount, gbc_btnUpdateAccount);

        txtAddOrWithdraw = new JTextField("AddOrWithdraw");
        txtAddOrWithdraw.setText("Add or Withdraw money");
        GridBagConstraints gbc_txtAddOrWithdraw = new GridBagConstraints();
        gbc_txtAddOrWithdraw.insets = new Insets(0, 0, 5, 5);
        gbc_txtAddOrWithdraw.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAddOrWithdraw.gridx = 3;
        gbc_txtAddOrWithdraw.gridy = 18;
        frame.getContentPane().add(txtAddOrWithdraw, gbc_txtAddOrWithdraw);
        txtAddOrWithdraw.setColumns(10);

        lblPayUtilities = new JLabel("Pay Utilities");
        GridBagConstraints gbc_lblPayUtilities = new GridBagConstraints();
        gbc_lblPayUtilities.insets = new Insets(0, 0, 5, 5);
        gbc_lblPayUtilities.gridx = 1;
        gbc_lblPayUtilities.gridy = 19;
        frame.getContentPane().add(lblPayUtilities, gbc_lblPayUtilities);

        lblUtilitiesAccounts = new JLabel("Utilities Accounts:");
        GridBagConstraints gbc_lblUtilitiesAccounts = new GridBagConstraints();
        gbc_lblUtilitiesAccounts.insets = new Insets(0, 0, 5, 5);
        gbc_lblUtilitiesAccounts.gridx = 1;
        gbc_lblUtilitiesAccounts.gridy = 20;
        frame.getContentPane().add(lblUtilitiesAccounts, gbc_lblUtilitiesAccounts);

        txtElectricaId = new JTextField();
        txtElectricaId.setText("Electrica id:");
        GridBagConstraints gbc_txtElectricaId = new GridBagConstraints();
        gbc_txtElectricaId.insets = new Insets(0, 0, 5, 5);
        gbc_txtElectricaId.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtElectricaId.gridx = 1;
        gbc_txtElectricaId.gridy = 21;
        frame.getContentPane().add(txtElectricaId, gbc_txtElectricaId);
        txtElectricaId.setColumns(10);

        txtApaId = new JTextField();
        txtApaId.setText("Apa id:");
        GridBagConstraints gbc_txtApaId = new GridBagConstraints();
        gbc_txtApaId.insets = new Insets(0, 0, 5, 5);
        gbc_txtApaId.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtApaId.gridx = 1;
        gbc_txtApaId.gridy = 22;
        frame.getContentPane().add(txtApaId, gbc_txtApaId);
        txtApaId.setColumns(10);

        txtGazId = new JTextField();
        txtGazId.setText("Gaz id:");
        GridBagConstraints gbc_txtGazId = new GridBagConstraints();
        gbc_txtGazId.insets = new Insets(0, 0, 5, 5);
        gbc_txtGazId.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtGazId.gridx = 1;
        gbc_txtGazId.gridy = 23;
        frame.getContentPane().add(txtGazId, gbc_txtGazId);
        txtGazId.setColumns(10);

        JLabel lblTransferMoney = new JLabel("Transfer Money");
        GridBagConstraints gbc_lblTransferMoney = new GridBagConstraints();
        gbc_lblTransferMoney.gridwidth = 4;
        gbc_lblTransferMoney.insets = new Insets(0, 0, 5, 5);
        gbc_lblTransferMoney.gridx = 1;
        gbc_lblTransferMoney.gridy = 25;
        frame.getContentPane().add(lblTransferMoney, gbc_lblTransferMoney);

        JLabel lblFrom = new JLabel("From");
        GridBagConstraints gbc_lblFrom = new GridBagConstraints();
        gbc_lblFrom.anchor = GridBagConstraints.EAST;
        gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
        gbc_lblFrom.gridx = 1;
        gbc_lblFrom.gridy = 26;
        frame.getContentPane().add(lblFrom, gbc_lblFrom);

        JLabel lblTo = new JLabel("To");
        GridBagConstraints gbc_lblTo = new GridBagConstraints();
        gbc_lblTo.insets = new Insets(0, 0, 5, 5);
        gbc_lblTo.gridx = 4;
        gbc_lblTo.gridy = 26;
        frame.getContentPane().add(lblTo, gbc_lblTo);

        JLabel lblAccountId = new JLabel("Account ID");
        GridBagConstraints gbc_lblAccountId = new GridBagConstraints();
        gbc_lblAccountId.insets = new Insets(0, 0, 5, 5);
        gbc_lblAccountId.anchor = GridBagConstraints.EAST;
        gbc_lblAccountId.gridx = 1;
        gbc_lblAccountId.gridy = 27;
        frame.getContentPane().add(lblAccountId, gbc_lblAccountId);

        fromAccount = new JTextField();
        GridBagConstraints gbc_fromAccount = new GridBagConstraints();
        gbc_fromAccount.insets = new Insets(0, 0, 5, 5);
        gbc_fromAccount.fill = GridBagConstraints.HORIZONTAL;
        gbc_fromAccount.gridx = 2;
        gbc_fromAccount.gridy = 27;
        frame.getContentPane().add(fromAccount, gbc_fromAccount);
        fromAccount.setColumns(10);

        toAccount = new JTextField();
        GridBagConstraints gbc_toAccount = new GridBagConstraints();
        gbc_toAccount.gridwidth = 2;
        gbc_toAccount.insets = new Insets(0, 0, 5, 5);
        gbc_toAccount.fill = GridBagConstraints.HORIZONTAL;
        gbc_toAccount.gridx = 4;
        gbc_toAccount.gridy = 27;
        frame.getContentPane().add(toAccount, gbc_toAccount);
        toAccount.setColumns(10);

        JLabel lblSum = new JLabel("Sum");
        GridBagConstraints gbc_lblSum = new GridBagConstraints();
        gbc_lblSum.anchor = GridBagConstraints.EAST;
        gbc_lblSum.insets = new Insets(0, 0, 5, 5);
        gbc_lblSum.gridx = 1;
        gbc_lblSum.gridy = 28;
        frame.getContentPane().add(lblSum, gbc_lblSum);

        sum = new JTextField();
        GridBagConstraints gbc_sum = new GridBagConstraints();
        gbc_sum.insets = new Insets(0, 0, 5, 5);
        gbc_sum.fill = GridBagConstraints.HORIZONTAL;
        gbc_sum.gridx = 2;
        gbc_sum.gridy = 28;
        frame.getContentPane().add(sum, gbc_sum);
        sum.setColumns(10);

        btnTransfer = new JButton("Transfer");
        GridBagConstraints gbc_btnTransfer = new GridBagConstraints();
        gbc_btnTransfer.insets = new Insets(0, 0, 5, 5);
        gbc_btnTransfer.gridx = 3;
        gbc_btnTransfer.gridy = 29;
        frame.getContentPane().add(btnTransfer, gbc_btnTransfer);
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getCNP() {
        return CNP.getText();
    }

    public String getCard() {
        return card.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public String getAccountType() {
        return accountType.getText();
    }

    public String getAccountDate() {
        return accountDate.getText();
    }

    public String getAccounId() {
        return accounId.getText();
    }

    public String getFromAccount() {
        return fromAccount.getText();
    }

    public String getToAccount() {
        return toAccount.getText();
    }

    public String getSum() {
        return sum.getText();
    }


    public String getOperation() {
        return txtAddOrWithdraw.getText();
    }

    public void setAddNewClientButtonListener(ActionListener addNewClientButtonListener) {
        btnAddNewClient.addActionListener(addNewClientButtonListener);
    }

    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
        btnUpdateClient.addActionListener(updateClientButtonListener);
    }

    public void setViewAllClientsButtonListener(ActionListener viewAllClientsButtonListener) {
        btnViewAllClients.addActionListener(viewAllClientsButtonListener);
    }

    public void setViewClientButtonListener(ActionListener viewClientButtonListener) {
        btnViewClient.addActionListener(viewClientButtonListener);
    }

    public void setAddNewAccountButtonListener(ActionListener AddNewAccountButtonListener) {
        btnAddNewAccount.addActionListener(AddNewAccountButtonListener);
    }

    public void setViewAccountButtonListener(ActionListener viewAccountButtonListener) {
        btnViewAccount.addActionListener(viewAccountButtonListener);
    }

    public void setDeleteAccountButtonListener(ActionListener deleteAccountButtonListener) {
        btnDeleteAccount.addActionListener(deleteAccountButtonListener);
    }

    public void setTransferButtonListener(ActionListener transferButtonListener) {
        btnTransfer.addActionListener(transferButtonListener);
    }

    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener) {
        btnUpdateAccount.addActionListener(updateAccountButtonListener);
    }


}
