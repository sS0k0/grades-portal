package ui;

import model.Account;
import model.Course;
import model.Professor;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
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
    private Student studentUser;
    private Professor professorUser;

    // JClasses used in the frame
    JPanel logInPanel, studentPanel, professorPanel, courseRegPanel;
    JTextField userName, password, courseName;
    JFormattedTextField courseId;
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
        handleStudentAction(e);
        handleProfessorAction(e);
    }

    // MODIFIES : this
    // EFFECTS : Handles button click event by student account
    private void handleStudentAction(ActionEvent e) {
        if (e.getActionCommand().equals("courseRegister")) {
            getContentPane().removeAll();
            repaint();
            registerCourse();
            handleCourseRegAction(e);
        } else if (e.getActionCommand().equals("courseDrop")) {
            // Drop a course UI
        } else if (e.getActionCommand().equals("courseView")) {
            // Display courses
        }
    }

    // MODIFIES : this
    // EFFECTS : Handles action events when a student is registering for a course
    private void handleCourseRegAction(ActionEvent e) {
        if (e.getActionCommand().equals("menu")) {
            getContentPane().removeAll();
            repaint();
            loadStudentPage();
        } else if (e.getActionCommand().equals("courseRegSubmit")) {
            for (Account account : accounts) {
                for (Course course : account.getCourses()) {
                    int courseIdInt = (Integer) courseId.getValue();
                    if (courseIdInt == course.getId()) {
                        course.addStudent(studentUser);
                    }
                }
            }
                getContentPane().removeAll();
                repaint();
                loadStudentPage();
                JOptionPane.showMessageDialog(this,
                        "You have been registered for " + courseName + courseId);
        }
    }

    // MODIFIES : this
    // EFFECTS : Handles button click event by professor account
    private void handleProfessorAction(ActionEvent e) {
        if (e.getActionCommand().equals("courseCreate")) {
            // Create course UI
        } else if (e.getActionCommand().equals("courseEdit")) {
            // Edit course UI
        } else if (e.getActionCommand().equals("courseClose")) {
            // Close course UI
        } else if (e.getActionCommand().equals("courseProfessorView")) {
            // Display courses (professor version)
        }
    }

    // MODIFIES : this
    // EFFECTS : Handles event where user quits
    private void quit(ActionEvent e) {
        if (e.getActionCommand().equals("quit")) {
            saveAccounts();
            System.exit(0);
        }
    }

    // MODIFIES : this
    // EFFECTS : Creates UI for a student to register for a course
    private void registerCourse() {
        // panel
        courseRegPanel = new JPanel(new GridLayout(6,1));
        add(courseRegPanel, BorderLayout.CENTER);

        // labels
        JLabel courseNameLabel = new JLabel();
        courseNameLabel.setText("Input course name");
        JLabel courseIdLabel = new JLabel();
        courseIdLabel.setText("Input course Id");

        // Input fields
        courseName = new JTextField(15);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setAllowsInvalid(false);
        courseId = new JFormattedTextField(formatter);

        // Back to menu button
        JButton menu = new JButton("RETURN TO MENU");
        menu.setActionCommand("menu");
        menu.addActionListener(this);

        JButton submit = new JButton("SUBMIT");
        submit.setActionCommand("courseRegSubmit");
        submit.addActionListener(this);

        courseRegPanel.add(courseNameLabel);
        courseRegPanel.add(courseName);
        courseRegPanel.add(courseIdLabel);
        courseRegPanel.add(courseId);
        courseRegPanel.add(submit);
        courseRegPanel.add(menu);

        setVisible(true);
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
                getContentPane().removeAll();
                repaint();
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
    //
    private boolean validLogin(String username, String password) {
        for (Account account : accounts) {
            if (username.equals(account.getName()) && password.equals(account.getPassword())
            && validRole(account)) {
                if (account instanceof Student) {
                    studentUser = (Student) account;
                } else {
                    professorUser = (Professor) account;
                }
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
                    studentUser = student;
                    getContentPane().removeAll();
                    repaint();
                    loadStudentPage();
                } else if (c2.isSelected()) {
                    Professor professor = new Professor(username, password);
                    accounts.add(professor);
                    professorUser = professor;
                    getContentPane().removeAll();
                    repaint();
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
        studentPanel = new JPanel(new GridLayout(4, 1));
        add(studentPanel, BorderLayout.CENTER);

        // buttons
        JButton courseReg = new JButton("REGISTER FOR A COURSE");
        courseReg.setActionCommand("courseRegister");
        courseReg.addActionListener(this);
        JButton courseDrop = new JButton("DROP A COURSE");
        courseDrop.setActionCommand("courseDrop");
        courseDrop.addActionListener(this);
        JButton courseView = new JButton("VIEW MY COURSES");
        courseView.setActionCommand("courseView");
        courseView.addActionListener(this);
        // Quit button
        JButton b3 = new JButton("QUIT");
        b3.setActionCommand("quit");
        b3.addActionListener(this);

        studentPanel.add(courseReg);
        studentPanel.add(courseDrop);
        studentPanel.add(courseView);
        studentPanel.add(b3);

        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : loads user interface for a professor account
    private void loadProfessorPage() {
        // panel
        professorPanel = new JPanel(new GridLayout(5, 1));
        add(professorPanel, BorderLayout.CENTER);

        // buttons
        JButton courseCreate = new JButton("CREATE A COURSE");
        courseCreate.setActionCommand("courseCreate");
        courseCreate.addActionListener(this);
        JButton courseEdit = new JButton("EDIT A COURSE");
        courseEdit.setActionCommand("courseEdit");
        courseEdit.addActionListener(this);
        JButton courseClose = new JButton("CLOSE A COURSE");
        courseClose.setActionCommand("courseClose");
        courseClose.addActionListener(this);
        JButton courseProfessorView = new JButton("VIEW MY COURSES");
        courseProfessorView.setActionCommand("courseProfessorView");
        courseProfessorView.addActionListener(this);
        // Quit button
        JButton b3 = new JButton("QUIT");
        b3.setActionCommand("quit");
        b3.addActionListener(this);

        professorPanel.add(courseCreate);
        professorPanel.add(courseEdit);
        professorPanel.add(courseClose);
        professorPanel.add(courseProfessorView);
        professorPanel.add(b3);

        setVisible(true);
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
