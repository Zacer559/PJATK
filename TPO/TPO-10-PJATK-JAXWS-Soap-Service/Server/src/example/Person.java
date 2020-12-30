package example;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {


        return "ID: " + this.id + " Name of person: " + this.firstName + " Lastname: " + this.lastName + " Birthdate: " + dateFormat.format(birthDate);
    }

}