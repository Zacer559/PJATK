package pl.edu.mas.s16941.mp1.model;

import pl.edu.mas.s16941.mp1.exception.ModelValidationException;

import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {
    private String name, surname;

    public Author(String name, String surname) {
       setName(name);
       setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new ModelValidationException("Name cannot be empty or null.");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isBlank()) {
            throw new ModelValidationException("Surname cannot be empty or null.");
        }
        this.surname = surname;
    }

    // Overriding equals to make sure that there will be no same authors twice in hashset
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) && surname.equals(author.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }


}
