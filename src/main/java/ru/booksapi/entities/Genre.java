package ru.booksapi.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Genre {

    @Id
    private String id;

    private String genre;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private Collection<Book> theBooks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
