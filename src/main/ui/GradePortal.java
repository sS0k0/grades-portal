package ui;

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
        // Create panel
        logInPanel = new JPanel(new GridLayout(6,1));
        add(logInPanel, BorderLayout.CENTER);

        // Create label for username
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("Username");

        // Create label for password
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");

        // Create textfield to get username from user
        userName = new JTextField(15);

        // Create textfield to get password from user
        password = new JPasswordField(15);

        // Create Login button
        JButton b1 = new JButton("LOGIN");
        b1.setActionCommand("login");
        b1.addActionListener(this);

        // Create Register button
        JButton b2 = new JButton("REGISTER");
        b2.setActionCommand("register");
        b2.addActionListener(this);

        logInPanel.add(userNameLabel);
        logInPanel.add(userName);
        logInPanel.add(passwordLabel);
        logInPanel.add(password);
        logInPanel.add(b1);
        logInPanel.add(b2);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        handleLoginAction(e);
    }

    public void handleLoginAction(ActionEvent e) {
        if (e.getActionCommand().equals("login")) {

            String userNameString = userName.getText();
            String passwordString = password.getText();

        } else if (e.getActionCommand().equals("register")) {
            //stub
        }
    }
}
