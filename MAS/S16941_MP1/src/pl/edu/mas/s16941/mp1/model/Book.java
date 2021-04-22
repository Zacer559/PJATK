package pl.edu.mas.s16941.mp1.model;

import pl.edu.mas.s16941.mp1.exception.ModelValidationException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Book implements Serializable {
    public static final String FILE_NAME = "./data/Book.ser";
    private static List<Book> extent = new ArrayList<>();

    private int ISBN;
    private String title;
    private Author author;
    private LocalDate releaseDate;
    private Set<String> category = new HashSet<>();
    private BookRating rating;
    private Integer buyPrice;
    private Integer sellPrice;
    private String publisher;
    private String edition;
    private String description;
    private static Set<Author> knownAuthors = new HashSet<>();

    static {
        knownAuthors.add(new Author("William", "Shakespeare"));
    }

    // Mandatory
    public Book(int ISBN, String title, Author author, LocalDate releaseDate, String category, BookRating rating, String publisher, String edition, String description) {
        this.ISBN = ISBN;
        setTitle(title);
        setAuthor(author);
        setReleaseDate(releaseDate);
        addCategory(category);
        setRating(rating);
        setPublisher(publisher);
        setDescription(description);
        setEdition(edition);
        extent.add(this);
    }

    // With optionals
    public Book(int ISBN, String title, Author author, LocalDate releaseDate, String category, BookRating rating, String publisher, String edition, String description, Integer buyPrice, Integer sellPrice) {
        this.ISBN = ISBN;
        setTitle(title);
        setAuthor(author);
        setReleaseDate(releaseDate);
        addCategory(category);
        setRating(rating);
        setPublisher(publisher);
        setDescription(description);
        setEdition(edition);
        setBuyPrice(buyPrice);
        setSellPrice(sellPrice);
        extent.add(this);
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new ModelValidationException("Title cannot be empty or null.");
        }
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        if (author == null) {
            throw new ModelValidationException("Author cannot be empty or null.");
        }
        this.author = author;
        //Adds author to known authors.
        addKnownAuthor(author);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null) {
            throw new ModelValidationException("ReleaseDate cannot be null.");
        }
        this.releaseDate = releaseDate;
    }

    public Set<String> getCategory() {
        return Collections.unmodifiableSet(category);
    }

    public void addCategory(String categoryToAdd) {
        if (categoryToAdd == null || categoryToAdd.isBlank()) {
            throw new ModelValidationException("Category cannot be empty or null.");
        }
        this.category.add(categoryToAdd);
    }

    public void removeCategory(String categoryToRemove) {
        if (this.category.size() <= 1) {
            throw new ModelValidationException("Cannot remove last category.");
        }
        this.category.remove(categoryToRemove);
    }

    public BookRating getRating() {
        return rating;
    }

    public void setRating(BookRating rating) {
        if (rating == null) {
            throw new ModelValidationException("Rating cannot be nuull.");
        }
        this.rating = rating;
    }

    public Optional<Integer> getBuyPrice() {
        return Optional.ofNullable(buyPrice);
    }

    public void setBuyPrice(Integer buyPrice) {
        if (buyPrice != null && buyPrice < 0) {
            throw new ModelValidationException("BuyPrice cannot be lower than 0");
        }
        this.buyPrice = buyPrice;
    }

    public Optional<Integer> getSellPrice() {
        return Optional.ofNullable(sellPrice);
    }

    public void setSellPrice(Integer sellPrice) {
        if (sellPrice != null && sellPrice < 0) {
            throw new ModelValidationException("BuyPrice cannot be lower than 0");
        }
        this.sellPrice = sellPrice;
    }

    public Optional<Integer> getProfit() {
        if (sellPrice == null || buyPrice == null) {
            return Optional.empty();
        }
        return Optional.of(sellPrice - buyPrice);
    }

    public static Set<Author> getKnownAuthors() {
        return Collections.unmodifiableSet(knownAuthors);
    }

    public static void addKnownAuthor(Author author) {
        if (author == null) {
            throw new ModelValidationException("Known author cannot be null.");
        }
        knownAuthors.add(author);
    }

    public static void removeAuthor(Author authorToRemove) {
        if (knownAuthors.size() <= 1) {
            throw new ModelValidationException("Cannot remove last known author.");
        }
        knownAuthors.remove(authorToRemove);
    }

    public static List<Book> getExtent() {
        return Collections.unmodifiableList(extent);
    }

    public static void saveExtent() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(extent);
        }
    }

    public static void loadExtent() throws IOException, ClassNotFoundException {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            extent = (List<Book>) oos.readObject();
        }
    }

    public static List<Book> findByFirstLettersOfTitle(String beginningOfTitle) {
        if (beginningOfTitle == null || beginningOfTitle.trim().equals("")) {
            return new ArrayList<Book>();
        }
        return extent.stream().filter(b -> b.title.startsWith(beginningOfTitle)).collect(Collectors.toList());
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        if (publisher == null || publisher.isBlank()) {
            throw new ModelValidationException("Publisher cannot be empty or null.");
        }
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        if (edition == null || edition.isBlank()) {
            throw new ModelValidationException("Edition cannot be empty or null.");
        }
        this.edition = edition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new ModelValidationException("Description cannot be empty or null.");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + ISBN +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", rating=" + rating +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
