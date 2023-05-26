package practical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAL {

	private static final String INSERT_QUERY = "INSERT INTO books_adi (title, author, publication_date, genre, price, quantity_in_stock) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM books_adi";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM books_adi WHERE book_id = ?";
	private static final String UPDATE_QUERY = "UPDATE books_adi SET title = ?, author = ?, publication_date = ?, genre = ?, price = ?, quantity_in_stock = ? WHERE book_id = ?";
	private static final String DELETE_QUERY = "DELETE FROM books_adi WHERE book_id = ?";

	public void addBook(Book book) throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(INSERT_QUERY);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setDate(3, java.sql.Date.valueOf(book.getPublicationDate()));
			statement.setString(4, book.getGenre());
			statement.setDouble(5, book.getPrice());
			statement.setInt(6, book.getQuantityInStock());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getAllBooks() throws ClassNotFoundException {
		List<Book> books = new ArrayList<>();

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(SELECT_ALL_QUERY);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int bookId = resultSet.getInt("book_id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
				String genre = resultSet.getString("genre");
				double price = resultSet.getDouble("price");
				int quantityInStock = resultSet.getInt("quantity_in_stock");

				Book book = new Book(bookId, title, author, publicationDate, genre, price, quantityInStock);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	public Book getBookById(int bookId) throws ClassNotFoundException {
		Book book = null;

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(SELECT_BY_ID_QUERY);
			statement.setInt(1, bookId);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String title = resultSet.getString("title");
					String author = resultSet.getString("author");
					LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
					String genre = resultSet.getString("genre");
					double price = resultSet.getDouble("price");
					int quantityInStock = resultSet.getInt("quantity_in_stock");

					book = new Book(title, author, publicationDate, genre, price, quantityInStock);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	public void updateBook(Book book) throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(UPDATE_QUERY);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setDate(3, java.sql.Date.valueOf(book.getPublicationDate()));
			statement.setString(4, book.getGenre());
			statement.setDouble(5, book.getPrice());
			statement.setInt(6, book.getQuantityInStock());
			statement.setInt(7, book.getBookId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(int bookId) throws ClassNotFoundException {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(DELETE_QUERY);
			statement.setInt(1, bookId);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
