# Book_CRUD

///////////////////////////////////Main.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>CRUD Operations</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
  $(document).ready(function() {
    // Function to handle AJAX call and display response
    function Request(url) {
      $.ajax({
        url: url,
        method: 'GET',
        success: function(response) {
          $('#content').html(response);
        },
        error: function(xhr, status, error) {
          console.log('AJAX Error: ' + error);
        }
      });
    }

    // Event handlers for CRUD buttons
    $('#createBtn').click(function() {
      Request('BookServlet?action=create');
    });

    $('#readBtn').click(function() {
      Request('BookServlet?action=read');
    });

    $('#updateBtn').click(function() {
      Request('BookServlet?action=update');
    });

    $('#deleteBtn').click(function() {
      Request('BookServlet?action=delete');
    });

    // Event handler for logout button
    $('#logoutBtn').click(function() {
      $.post('logout', function() {
        // Redirect to the login page
        window.location.href = 'login.jsp';
      });
    });
  });
</script>

</head>
<body>
  <div class="container mt-5">
    <h2>CRUD Operations</h2>
    <div class="text-center my-3">
      <button id="createBtn" class="btn btn-primary mr-2">Create</button>
      <button id="readBtn" class="btn btn-primary mr-2">Read</button>
      <button id="updateBtn" class="btn btn-primary mr-2">Update</button>
      <button id="deleteBtn" class="btn btn-danger">Delete</button>
    </div>
    <div class="text-right">
      <button id="logoutBtn" class="btn btn-danger">Logout</button>
    </div>
    <div id="content"></div>
  </div>
</body>






///////////////////////////////////BookServlet.java


package practical;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAL bookDAL;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDAL = new BookDAL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action != null) {
			switch (action) {
			case "create":
				createBook(request, response);
				break;
			case "read":
				readBooks(request, response);
				break;
			case "update":
				updateBook(request, response);
				break;
			case "delete":
				deleteBook(request, response);
				break;
			default:
				response.getWriter().write("Invalid action!");
				break;
			}
		} else {
			response.getWriter().write("No action specified!");
		}
	}

	private void createBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Code to handle the create action
		// Generate HTML content or perform any other required processing

		String htmlContent = "<h3>Create Book</h3>" + "<form class='form'>" + "  <div class='form-group'>"
				+ "    <label>Title:</label>" + "    <input type='text' name='title' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Author:</label>"
				+ "    <input type='text' name='author' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Publication Date:</label>"
				+ "    <input type='date' name='publicationDate' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Genre:</label>"
				+ "    <input type='text' name='genre' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Price:</label>"
				+ "    <input type='number' name='price' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Quantity in Stock:</label>"
				+ "    <input type='number' name='quantityInStock' class='form-control'>" + "  </div>"
				+ "  <button type='submit' class='btn btn-primary'>Save</button>" + "</form>";

		response.setContentType("text/html");
		response.getWriter().write(htmlContent);
	}

	private void readBooks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// Code to handle the read action
			// Retrieve books from the database using the DAL
			List<Book> books = bookDAL.getAllBooks();

			// Generate HTML content or perform any other required processing
			String htmlContent = "<h3>Read Books</h3>" + "<table class='table table-striped'>" + "  <thead>"
					+ "    <tr>" + "      <th>Title</th>" + "      <th>Author</th>" + "      <th>Publication Date</th>"
					+ "      <th>Genre</th>" + "      <th>Price</th>" + "      <th>Quantity in Stock</th>" + "    </tr>"
					+ "  </thead>" + "  <tbody>";

			for (Book book : books) {
				htmlContent += "<tr>" + "<td>" + book.getTitle() + "</td>" + "<td>" + book.getAuthor() + "</td>"
						+ "<td>" + book.getPublicationDate() + "</td>" + "<td>" + book.getGenre() + "</td>" + "<td>"
						+ book.getPrice() + "</td>" + "<td>" + book.getQuantityInStock() + "</td>" + "</tr>";
			}

			htmlContent += "</tbody>" + "</table>";

			response.setContentType("text/html");
			response.getWriter().write(htmlContent);
		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
			response.getWriter().write("Error occurred while retrieving books");
		}
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Code to handle the update action
		// Generate HTML content or perform any other required processing

		String htmlContent = "<h3>Update Book</h3>" + "<form class='form'>" + "  <div class='form-group'>"
				+ "    <label>Book ID:</label>" + "    <input type='text' name='bookId' class='form-control'>"
				+ "  </div>" + "  <div class='form-group'>" + "    <label>Title:</label>"
				+ "    <input type='text' name='title' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Author:</label>"
				+ "    <input type='text' name='author' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Publication Date:</label>"
				+ "    <input type='date' name='publicationDate' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Genre:</label>"
				+ "    <input type='text' name='genre' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Price:</label>"
				+ "    <input type='number' name='price' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Quantity in Stock:</label>"
				+ "    <input type='number' name='quantityInStock' class='form-control'>" + "  </div>"
				+ "  <button type='submit' class='btn btn-primary'>Save</button>" + "</form>";

		response.setContentType("text/html");
		response.getWriter().write(htmlContent);
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Code to handle the delete action
		// Generate HTML content or perform any other required processing

		String htmlContent = "<h3>Delete Book</h3>" + "<form class='form'>" + "  <div class='form-group'>"
				+ "    <label>Book ID:</label>" + "    <input type='text' name='bookId' class='form-control'>"
				+ "  </div>" + "  <button type='submit' class='btn btn-danger'>Delete</button>" + "</form>";

		response.setContentType("text/html");
		response.getWriter().write(htmlContent);
	}
}




///////////////////////Book.java



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




///////////////////////BookDal.java










package practical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAL {

	// SQL queries
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
				Book book = new Book();
				book.setBookId(resultSet.getInt("book_id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPublicationDate(resultSet.getDate("publication_date").toLocalDate());
				book.setGenre(resultSet.getString("genre"));
				book.setPrice(resultSet.getDouble("price"));
				book.setQuantityInStock(resultSet.getInt("quantity_in_stock"));

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
					book = new Book();
					book.setBookId(resultSet.getInt("book_id"));
					book.setTitle(resultSet.getString("title"));
					book.setAuthor(resultSet.getString("author"));
					book.setPublicationDate(resultSet.getDate("publication_date").toLocalDate());
					book.setGenre(resultSet.getString("genre"));
					book.setPrice(resultSet.getDouble("price"));
					book.setQuantityInStock(resultSet.getInt("quantity_in_stock"));
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



</html>
