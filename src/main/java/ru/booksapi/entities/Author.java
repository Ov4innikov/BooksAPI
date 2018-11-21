package ru.booksapi.entities;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated author ID")
    private Integer id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirthDay;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Collection<Book> books;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getDateOfBirthDay() {
        return dateOfBirthDay;
    }

    public void setDateOfBirthDay(LocalDate dateOfBirthDay) {
        this.dateOfBirthDay = dateOfBirthDay;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> book) {
        this.books = book;
    }
}
