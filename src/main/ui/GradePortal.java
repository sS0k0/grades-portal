package ui;

import model.Professor;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradePortal extends JFrame implements ActionListener {

    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;

    // JClasses used in the frame
    JPanel logInPanel;
    JTextField userName, password;
    JCheckBox c1, c2;

    public GradePortal() {
        super("portal ui");
        initializeGraphics();
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
    public void quit(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        }
    }

    // MODIFIES : this
    // EFFECTS : Handles events when user attempts to login or register
    public void handleLoginAction(ActionEvent e) {

        String userNameString = userName.getText();
        String passwordString = password.getText();

        if (e.getActionCommand().equals("login")) {
            if (false) { //valid login credentials
                // proceed to next page
            } else {
                JOptionPane.showMessageDialog(this,
                        "Login credentials not valid, please try again or register.");
            }
        } else if (e.getActionCommand().equals("register")) {
            register(userNameString, passwordString);
            // proceed to next page
        }
    }

    // MODIFIES : this
    // EFFECTS : Creates a student/professor account for the user with given name and password. saves to JSON
    public void register(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()) {
            if (true) { // username does not already exist
                if (c1.isSelected()) {
                    Student student = new Student(username, password);
                    // proceed to next page
                } else if (c2.isSelected()) {
                    Professor professor = new Professor(username, password);
                    // proceed to next page
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

}
