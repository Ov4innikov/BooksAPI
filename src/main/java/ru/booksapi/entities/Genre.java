package ru.booksapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Eugene Ovchinnikov
 */
@Entity
public class Genre implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String genre;

    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER)
    private Collection<Book> theBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    // wolf 5b:3c:6b:9f:b3:23:75:b6:82:49:16:c9:cd:4f:b6:62
    // ovch 07:6e:28:79:4c:32:96:c5:f0:dc:02:46:1c:f8:80:5e
}
