package ru.booksapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Genre implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String genre;

    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    private Collection<Book> theBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Collection<Book> getTheBooks() {
        return theBooks;
    }

    public void setTheBooks(Collection<Book> theBooks) {
        this.theBooks = theBooks;
    }
}
