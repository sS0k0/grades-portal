package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Student testStudent;
    private Professor testProfessor;
    private Professor testProfessor2;
    private Assignment testAssignment;
    private Course testCourse;
    private Course testCourse2;
    private List<Assignment> testAssignments;

    @BeforeEach
    void runBefore() {
        testStudent = new Student("Sasha", "123grse");
        testProfessor = new Professor("GrandyP", "123rew");
        testProfessor2 = new Professor("BigJoe", "ezTRy");
        testAssignment = new Assignment("Final Exam");
        testAssignments = new ArrayList<>();
        testCourse = new Course("CPSC Models of Computation",
                121, 101, 150, testProfessor, testAssignments);
        testCourse2 = new Course("Statistics in Business",
                201, 101, 80, testProfessor, testAssignments);
    }

    @Test
    void testGetName() {
        assertEquals("Sasha", testStudent.getName());
        assertEquals("GrandyP", testProfessor.getName());
    }

    @Test
    void testGetPassword() {
        assertEquals("123grse", testStudent.getPassword());
        assertEquals("123rew", testProfessor.getPassword());
    }

    @Test
    void testGetRole() {
        assertEquals("student", testStudent.getRole());
        assertEquals("professor", testProfessor.getRole());
    }

    @Test
    void testGetCourses() {
        // account with no courses
        List<Course> testList = new ArrayList<>();
        assertEquals(testList, testStudent.getCourses());
        assertEquals(testList, testProfessor2.getCourses());

        // account with courses
        testList.add(testCourse);
        testList.add(testCourse2);
        testStudent.addCourse(testCourse);
        testProfessor.addCourse(testCourse);
        testStudent.addCourse(testCourse2);
        testProfessor.addCourse(testCourse2);

        assertEquals(testList, testStudent.getCourses());
        assertEquals(testList, testProfessor.getCourses());
    }

}
