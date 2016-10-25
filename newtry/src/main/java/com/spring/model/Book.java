package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="bookapp")
public class Book {// Book has Category
	@Transient
	private MultipartFile bookImage;

public MultipartFile getBookImage() {
		return bookImage;
	}
	public void setBookImage(MultipartFile bookImage) {
		this.bookImage = bookImage;
	}
@Id
@Column(name="isbn")
@GeneratedValue(strategy=GenerationType.AUTO)
@NotNull
private int isbn;

@NotNull(message="Title is required")
@Size(min=2,max=50)
private String title;


@NotNull(message="publication is required")
@Size(min=2,max=50)
private String publication;



@NotNull(message="enter the value of price")
private int price;

@NotNull(message="enter the author name")
@Size(min=2,max=50)
private String author;
@ManyToOne
@JoinColumn(name="category")//category is the name of the foreign key column in bookApp table
private Category category;

public int getIsbn() {
	return isbn;
}
public void setIsbn(int isbn) {
	this.isbn = isbn;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getPublication() {
	return publication;
}
public void setPublication(String publication) {
	this.publication = publication;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}

public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}


}
