package pl.edu.mas.s16941.mp1.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.mas.s16941.mp1.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AuthorTest {
    Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Jan", "Brzechwa");
    }

    @Test
    void getName() {
        assertEquals("Jan",testAuthor.getName());
    }

    @Test
    void setName() {
        testAuthor.setName("Franek");
        assertNotEquals("Jan",testAuthor.getName());
        assertEquals("Franek",testAuthor.getName());
    }

    @Test
    void getSurname() {
        assertEquals("Brzechwa",testAuthor.getSurname());
    }

    @Test
    void setSurname() {
        testAuthor.setSurname("Konopny");
        assertNotEquals("Brzechwa",testAuthor.getSurname());
        assertEquals("Konopny",testAuthor.getSurname());
    }

    @Test
    void testToString() {
        System.out.println(testAuthor.toString());
    }
    @Test
    void equals(){
        assertEquals(new Author("Jan","Brzechwa"), testAuthor);
    }
}