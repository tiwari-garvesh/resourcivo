package com.project.resourcivo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "libraryBook")
public class LibraryBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;

	private String name;

	private String category;

	private LocalDate yearBought;

	private String author;

	private Float price;

	private Float rating;

	private String reviews;

	private String about;

	private Boolean isIssued;

	@jakarta.persistence.ManyToOne
	@jakarta.persistence.JoinColumn(name = "library_id")
	private Library library;

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Long getId() {
		return id;
	}

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

	public LibraryBook() {
		super();

	}

	public LibraryBook(String name, String category, String author, Float price) {
		super();
		this.name = name;
		this.category = category;
		this.author = author;
		this.price = price;
	}

	public LibraryBook(String name, String category, LocalDate yearBought, String author, Float price, Float rating,
			String reviews, String about) {
		super();
		this.name = name;
		this.category = category;
		this.yearBought = yearBought;
		this.author = author;
		this.price = price;
		this.rating = rating;
		this.reviews = reviews;
		this.about = about;
	}

	@Override
	public String toString() {
		return "LibraryBook [id=" + id + ", name=" + name + ", category=" + category + ", yearBought=" + yearBought
				+ ", author=" + author + ", price=" + price + ", rating=" + rating + ", reviews=" + reviews + ", about="
				+ about + ", isIssued=" + isIssued + "]";
	}

}
