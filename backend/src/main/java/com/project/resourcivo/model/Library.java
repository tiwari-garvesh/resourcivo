package com.project.resourcivo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id")
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<LibraryBook> books;

    @OneToOne(mappedBy = "library")
    private Librarian librarian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<LibraryBook> getBooks() {
        return books;
    }

    public void setBooks(List<LibraryBook> books) {
        this.books = books;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
}
