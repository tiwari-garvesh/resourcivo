package com.project.resourcivo.dto;

import java.time.LocalDate;

public class LibraryBookCreateDTO {
    private String name;
    private String category;
    private LocalDate yearBought;
    private String author;
    private Float price;
    private Float rating;
    private String reviews;
    private String about;
    private Boolean isIssued;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getYearBought() {
        return yearBought;
    }

    public void setYearBought(LocalDate yearBought) {
        this.yearBought = yearBought;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Boolean getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(Boolean isIssued) {
        this.isIssued = isIssued;
    }
}
