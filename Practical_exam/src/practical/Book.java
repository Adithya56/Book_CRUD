package practical;

import java.time.LocalDate;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private LocalDate publicationDate;
	private String genre;
	private double price;
	private int quantityInStock;

	public Book(String title, String author, LocalDate publicationDate, String genre, double price,
			int quantityInStock) {
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	public Book(int bookId, String title, String author, LocalDate publicationDate, String genre, double price,
			int quantityInStock) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.price = price;
		this.quantityInStock = quantityInStock;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

}