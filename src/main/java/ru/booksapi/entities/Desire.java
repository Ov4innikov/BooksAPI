package ru.booksapi.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Eugene Ovchinnikov
 */
@Entity
public class Desire implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String userId;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
