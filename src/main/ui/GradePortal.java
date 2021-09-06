package ui;

import model.Account;
import model.Professor;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GradePortal extends JFrame implements ActionListener {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String JSON_STORE = "./data/accounts.json";

    // Data
    private List<Account> accounts = new ArrayList<>();

    // JClasses used in the frame
    JPanel logInPanel, studentPanel;
    JTextField userName, password;
    JCheckBox c1, c2;

    public GradePortal() {
        super("portal ui");
        initializeGraphics();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadAccounts();
    }

    // MODIFIES : this
    // EFFECTS : Draws the JFrame window
    public void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeLogin();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS: Adds JPanel to prompt the user to login
    public void initializeLogin() {
        // panel
        logInPanel = new JPanel(new GridLayout(9,1));
        add(logInPanel, BorderLayout.CENTER);

        // label for username
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("Username");

        // label for password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");

        // textfield to get username from user
        userName = new JTextField(15);

        // textfield to get password from user
        password = new JPasswordField(15);

        // Login button
        JButton b1 = new JButton("LOGIN");
        b1.setActionCommand("login");
        b1.addActionListener(this);

        // Register button
        JButton b2 = new JButton("REGISTER");
        b2.setActionCommand("register");
        b2.addActionListener(this);

        // Quit button
        JButton b3 = new JButton("QUIT");
        b3.setActionCommand("quit");
        b3.addActionListener(this);

        // student/professor checkboxes
        c1 = new JCheckBox("Student");
        c2 = new JCheckBox("Professor");

        // Button group for checkboxes
        ButtonGroup group1 = new ButtonGroup();
        group1.add(c1);
        group1.add(c2);

        logInPanel.add(userNameLabel);
        logInPanel.add(userName);
        logInPanel.add(passwordLabel);
        logInPanel.add(password);
        logInPanel.add(c1);
        logInPanel.add(c2);
        logInPanel.add(b1);
        logInPanel.add(b2);
        logInPanel.add(b3);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        handleLoginAction(e);
        quit(e);
    }

    // MODIFIES : this
    // EFFECTS : Handles event where user quits
    private void quit(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            saveAccounts();
            System.exit(0);
        }
    }

    // MODIFIES : JSON data
    // EFFECTS : Saves accounts to JSON file
    private void saveAccounts() {
        try {
            jsonWriter.open();
            jsonWriter.write(accounts);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // ignore
        }
    }

    // MODIFIES : this
    // EFFECTS : loads accounts from file
    private void loadAccounts() {
        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            // ignore
        }
    }

    // MODIFIES : this
    // EFFECTS : Handles events when user attempts to login or register
    public void handleLoginAction(ActionEvent e) {

        String userNameString = userName.getText();
        String passwordString = password.getText();

        if (e.getActionCommand().equals("login")) {
            if (validLogin(userNameString, passwordString)) {
                removeAll();
                if (c1.isSelected()) {
                    loadStudentPage();
                } else if (c2.isSelected()) {
                    loadProfessorPage();
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Login credentials not valid, please try again or register.");
            }
        } else if (e.getActionCommand().equals("register")) {
            register(userNameString, passwordString);
        }
    }

    // EFFECTS : returns true if textfield username and password matches existing account, false otherwise
    private boolean validLogin(String username, String password) {
        for (Account account : accounts) {
            if (username.equals(account.getName()) && password.equals(account.getPassword())
            && validRole(account)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS : returns true if user checkbox selection for role matches database, false otherwise
    private boolean validRole(Account account) {
        if (c1.isSelected()) {
            return account.getRole().equals("student");
        } else if (c2.isSelected()) {
            return account.getRole().equals("professor");
        } else {
            return false;
        }
    }

    // MODIFIES : this
    // EFFECTS : Creates a student/professor account for the user with given name and password. saves to JSON
    public void register(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            if (userNameNotTaken(username)) {
                if (c1.isSelected()) {
                    Student student = new Student(username, password);
                    accounts.add(student);
                    removeAll();
                    loadStudentPage();
                } else if (c2.isSelected()) {
                    Professor professor = new Professor(username, password);
                    accounts.add(professor);
                    removeAll();
                    loadProfessorPage();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Please select either student or professor");
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "This username is taken");
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please input desired username and password into fields");
        }

    }

    // MODIFIES : this
    // EFFECTS : loads user interface for a student account
    private void loadStudentPage() {
        // panel
        studentPanel = new JPanel(new GridLayout(2, 1));
        add(studentPanel, BorderLayout.CENTER);

        // labels
        JLabel courseRegLabel = new JLabel();
        courseRegLabel.setText("COURSE REGISTRATION");
        JLabel courseGradesLabel = new JLabel();
        courseGradesLabel.setText("VIEW COURSE GRADES");

    }

    // MODIFIES : this
    // EFFECTS : loads user interface for a professor account
    private void loadProfessorPage() {
        // do nothing
    }

    // EFFECTS : return true if username is not already taken by existing Accounts
    private boolean userNameNotTaken(String username) {
        for (Account account : accounts) {
            if (username.equals(account.getName())) {
                return false;
            }
        }
        return true;
    }
}
