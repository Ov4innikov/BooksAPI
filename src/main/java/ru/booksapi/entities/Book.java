package ru.booksapi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Eugene Ovchinnikov
 */
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String series;

    private  String cost;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    private Integer countOfPage;

    private String description;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Collection<Desire> desires;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Collection<Desire> getDesires() {
        return desires;
    }

    public void setDesires(Collection<Desire> desires) {
        this.desires = desires;
    }
}
