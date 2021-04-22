package pl.edu.mas.s16941.mp1.tests;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.mas.s16941.mp1.model.Author;
import pl.edu.mas.s16941.mp1.model.Book;
import pl.edu.mas.s16941.mp1.model.BookRating;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    Book testBook1, testBook2;
    Author auth1, auth2;

    @BeforeEach
    void setUp() {
        auth1 = new Author("William", "Shakespeare");
        auth2 = new Author("Stanisław", "Lem");

        testBook1 = new Book(1, "Romeo and Juliet", auth1, LocalDate.parse("1597-08-12"), "Drama", BookRating.GOOD, "Simon & Schuster", "First", """
                In Romeo and Juliet, Shakespeare creates a violent world, in which two young people fall in love. It is not simply that their families disapprove; the Montagues and the Capulets are engaged in a blood feud.
                                
                In this death-filled setting, the movement from love at first sight to the lovers’ final union in death seems almost inevitable. And yet, this play set in an extraordinary world has become the quintessential story of young love. In part because of its exquisite language, it is easy to respond as if it were about all young lovers.
                                
                The authoritative edition of Romeo and Juliet from The Folger Shakespeare Library, the trusted and widely used Shakespeare series for students and general readers, includes:
                                
                -Freshly edited text based on the best early printed version of the play
                -Newly revised explanatory notes conveniently placed on pages facing the text of the play
                -Scene-by-scene plot summaries
                -A key to the play’s famous lines and phrases
                -An introduction to reading Shakespeare’s language
                -An essay by a leading Shakespeare scholar providing a modern perspective on the play
                -Fresh images from the Folger Shakespeare Library’s vast holdings of rare books
                -An up-to-date annotated guide to further reading
                                
                Essay by Gail Kern Paster
                                
                The Folger Shakespeare Library in Washington, DC, is home to the world’s largest collection of Shakespeare’s printed works, and a magnet for Shakespeare scholars from around the globe. In addition to exhibitions open to the public throughout the year, the Folger offers a full calendar of performances and programs. For more information, visit Folger.edu.""");
        testBook2 = new Book(2, "Solaris", auth2, LocalDate.parse("1961-02-26"), "Science-Fiction", BookRating.GREAT, "Wydawnictwo Literackie", "Second", """
                Solaris is a 1961 science fiction novel by Polish writer Stanisław Lem. 
                It follows a crew of scientists on a research station as they attempt to understand an extraterrestrial intelligence, which takes the form of a vast ocean on the titular alien planet.
                The novel is among Lem's best-known works.[2]
                                 
                The book has been adapted numerous times for film, radio, and theater. Prominent film adaptations include Andrei Tarkovsky's 1972 version and Steven Soderbergh's 2002 version, although Lem later remarked that none of these films reflected the book's thematic emphasis on the limitations of human rationality.""", 10, 20);

    }

    @Test
    void setId() {
        assertEquals(1, testBook1.getISBN());
        testBook1.setISBN(5);
        assertEquals(5, testBook1.getISBN());
    }

    @Test
    void getId() {
        assertEquals(1, testBook1.getISBN());
        assertEquals(2, testBook2.getISBN());
    }

    @Test
    void getTitle() {
        assertEquals("Romeo and Juliet", testBook1.getTitle());
        assertEquals("Solaris", testBook2.getTitle());
    }

    @Test
    void setTitle() {
        assertEquals("Romeo and Juliet", testBook1.getTitle());
        testBook1.setTitle("Very wrong title");
        assertEquals("Very wrong title", testBook1.getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals(auth1, testBook1.getAuthor());
    }

    @Test
    void setAuthor() {
        assertEquals(auth1, testBook1.getAuthor());
        Author buffAuthor = new Author("Wrong", "Author");
        testBook1.setAuthor(buffAuthor);
        assertEquals(buffAuthor, testBook1.getAuthor());

    }

    @Test
    void getReleaseDate() {
        assertEquals(LocalDate.parse("1597-08-12"), testBook1.getReleaseDate());
    }

    @Test
    void setReleaseDate() {
        assertEquals(LocalDate.parse("1597-08-12"), testBook1.getReleaseDate());
        testBook1.setReleaseDate(LocalDate.parse("3000-08-12"));
        assertEquals(LocalDate.parse("3000-08-12"), testBook1.getReleaseDate());
    }

    @Test
    void getCategory() {
        Set<String> buff = new HashSet<>();
        buff.add("Drama");
        assertEquals(buff, testBook1.getCategory());
    }

    @Test
    void addCategory() {
        Set<String> buff = new HashSet<>();
        buff.add("Drama");
        buff.add("Comedy");
        testBook1.addCategory("Comedy");
        assertEquals(buff, testBook1.getCategory());
    }

    @Test
    void removeCategory() {
        Set<String> buff = new HashSet<>();
        buff.add("Drama");
        buff.add("Comedy");
        testBook1.addCategory("Comedy");
        assertEquals(buff, testBook1.getCategory());
        testBook1.removeCategory("Drama");
        buff.remove("Drama");
        assertEquals(buff, testBook1.getCategory());

    }

    @Test
    void getRating() {
        assertEquals(BookRating.GOOD, testBook1.getRating());
    }

    @Test
    void setRating() {
        assertEquals(BookRating.GOOD, testBook1.getRating());
        testBook1.setRating(BookRating.BAD);
        assertEquals(BookRating.BAD, testBook1.getRating());
    }

    @Test
    void getBuyPrice() {
        assertEquals(Optional.of(10), testBook2.getBuyPrice());
    }

    @Test
    void setBuyPrice() {
        assertEquals(Optional.of(10), testBook2.getBuyPrice());
        testBook2.setBuyPrice(20);
        assertEquals(Optional.of(20), testBook2.getBuyPrice());
    }

    @Test
    void getSellPrice() {
        assertEquals(Optional.of(20), testBook2.getSellPrice());
    }

    @Test
    void setSellPrice() {
        assertEquals(Optional.of(20), testBook2.getSellPrice());
        testBook2.setSellPrice(50);
        assertEquals(Optional.of(50), testBook2.getSellPrice());
    }

    @Test
    void getProfit() {
        assertEquals(Optional.of(10), testBook2.getProfit());
    }



    @Test
    void addKnownAuthor() {
        Set<Author> au = new HashSet<>();
        au.add(auth1);
        au.add(auth2);
        au.add(new Author("Wrong", "Author"));
        assertEquals(au, Book.getKnownAuthors());
        Author buff = new Author("Some","Author");
        Book.addKnownAuthor(buff);
        au.add(buff);
        assertEquals(au, Book.getKnownAuthors());
    }
    @Test
    void getKnownAuthors() {
        Set<Author> au = new HashSet<>();
        au.add(auth1);
        au.add(auth2);
        au.add(new Author("Wrong", "Author"));
        au.add(new Author("Some", "Author"));
        assertEquals(au, Book.getKnownAuthors());
    }

    @Test
    void removeAuthor() {
        Set<Author> au = new HashSet<>();
        au.add(auth1);
        au.add(auth2);
        assertEquals(au, Book.getKnownAuthors());
        Book.removeAuthor(auth1);
        au.remove(auth1);
        assertEquals(au, Book.getKnownAuthors());
    }

    @Test
    void getExtent() {
        assertEquals(testBook2.getISBN(), Book.getExtent().get(1).getISBN());
    }

    @Test
    void saveLoadExtent() throws IOException, ClassNotFoundException {
        Book.saveExtent();
        Book.loadExtent();
        assertEquals(testBook2.getISBN(), Book.getExtent().get(1).getISBN());
        File myObj = new File(Book.FILE_NAME);
        myObj.delete();
    }

    @Test
    void findByFirstLettersOfTitle() {
        assertEquals(19,Book.findByFirstLettersOfTitle("Romeo").size());
        assertEquals(1,Book.findByFirstLettersOfTitle("Romeo").get(1).getISBN());
    }

    @Test
    void getPublisher() {
        assertEquals("Simon & Schuster", testBook1.getPublisher());
    }

    @Test
    void setPublisher() {
        assertEquals("Simon & Schuster", testBook1.getPublisher());
        testBook1.setPublisher("EEE");
        assertEquals("EEE", testBook1.getPublisher());
    }

    @Test
    void getEdition() {
        assertEquals("First", testBook1.getEdition());
    }

    @Test
    void setEdition() {
        assertEquals("First", testBook1.getEdition());
        testBook1.setEdition("Second");
        assertEquals("Second", testBook1.getEdition());
    }

    @Test
    void testToString() {
        System.out.println(testBook1);
    }

}