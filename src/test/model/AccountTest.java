package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Student testStudent;
    private Professor testProfessor;

    @BeforeEach
    void runBefore() {
        testStudent = new Student("Sasha", "123grse");
        testProfessor = new Professor("GrandyP", "123rew");
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

}
