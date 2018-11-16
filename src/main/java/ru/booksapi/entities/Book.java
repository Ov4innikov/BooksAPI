package ru.booksapi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    private String id;

    private String name;

    private String series;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    private Integer countOfPage;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getCountOfPage() {
        return countOfPage;
    }

    public void setCountOfPage(Integer countOfPage) {
        this.countOfPage = countOfPage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
