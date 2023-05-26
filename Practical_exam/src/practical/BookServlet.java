package practical;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			case "logout":
				logout(request, response);
				break;
			default:
				response.getWriter().write("Invalid action!");
				break;
			}
		} else {
			response.getWriter().write("No action specified!");
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			// Invalidate the session
			session.invalidate();
			// Redirect the user to the login page or any other desired page
			response.sendRedirect("login.jsp");
		}
	}

	private void createBook(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// Generate HTML content for the create book form
		/*
		 * String htmlContent = "<h3>Create Book</h3>" + "<form method='POST' action='CreateRecord'>" + "  <table>" +
		 * "    <tr>" + "      <td><label>Title:</label></td>" + "      <td><input type='text' name='title'></td>" +
		 * "    </tr>" + "    <tr>" + "      <td><label>Author:</label></td>" +
		 * "      <td><input type='text' name='author'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td><label>Publication Date:</label></td>" +
		 * "      <td><input type='date' name='publicationDate'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td><label>Genre:</label></td>" + "      <td><input type='text' name='genre'></td>" + "    </tr>" +
		 * "    <tr>" + "      <td><label>Price:</label></td>" + "      <td><input type='number' name='price'></td>" +
		 * "    </tr>" + "    <tr>" + "      <td><label>Quantity in Stock:</label></td>" +
		 * "      <td><input type='number' name='quantityInStock'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td colspan='2'><button type='submit'>Save</button></td>" + "    </tr>" + "  </table>" + "</form>";
		 */
		String htmlContent = "<h3>Create Book</h3>" + "<form class='form' action='CreateRecord' method='POST'>"
				+ "  <div class='form-group'>" + "    <label>Title:</label>"
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

	private void readBooks(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			// Code to handle the read action
			// Retrieve books from the database using the DAL
			List<Book> books = bookDAL.getAllBooks();
			request.setCharacterEncoding("UTF-8");
			// Generate HTML content or perform any other required processing
			/*
			 * String htmlContent = "<h3>Read Books</h3>" + "<table border='1'>" + "  <thead>" + "    <tr>" +
			 * "<th>BookId</th>" + "      <th>Title</th>" + "      <th>Author</th>" + "      <th>Publication Date</th>"
			 * + "      <th>Genre</th>" + "      <th>Price</th>" + "      <th>Quantity in Stock</th>" + "    </tr>" +
			 * "  </thead>" + "  <tbody>";
			 * 
			 * for (Book book : books) { htmlContent += "<tr>" + "<td>" + book.getBookId() + "</td>" + "<td>" +
			 * book.getTitle() + "</td>" + "<td>" + book.getAuthor() + "</td>" + "<td>" + book.getPublicationDate() +
			 * "</td>" + "<td>" + book.getGenre() + "</td>" + "<td>" + book.getPrice() + "</td>" + "<td>" +
			 * book.getQuantityInStock() + "</td>" + "</tr>"; }
			 * 
			 * htmlContent += "</tbody>" + "</table>";
			 */
			String htmlContent = "<h3>Read Books</h3>" + "<table class='table table-striped'>" + "  <thead>"
					+ "    <tr>" + "<th>BookId</th>" + "      <th>Title</th>" + "      <th>Author</th>"
					+ "      <th>Publication Date</th>" + "      <th>Genre</th>" + "      <th>Price</th>"
					+ "      <th>Quantity in Stock</th>" + "    </tr>" + "  </thead>" + "  <tbody>";

			for (Book book : books) {
				htmlContent += "<tr>" + "<td>" + book.getBookId() + "</td>" + "<td>" + book.getTitle() + "</td>"
						+ "<td>" + book.getAuthor() + "</td>" + "<td>" + book.getPublicationDate() + "</td>" + "<td>"
						+ book.getGenre() + "</td>" + "<td>" + book.getPrice() + "</td>" + "<td>"
						+ book.getQuantityInStock() + "</td>" + "</tr>";
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

		// Generate HTML content for the update book form
		/*
		 * String htmlContent = "<h3>Update Book</h3>" + "<form method='POST' action='UpdateRecord'>" + "  <table>" +
		 * "    <tr>" + "      <td><label>Book ID:</label></td>" + "      <td><input type='text' name='bookId'></td>" +
		 * "    </tr>" + "    <tr>" + "      <td><label>Title:</label></td>" +
		 * "      <td><input type='text' name='title'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td><label>Author:</label></td>" + "      <td><input type='text' name='author'></td>" + "    </tr>" +
		 * "    <tr>" + "      <td><label>Publication Date:</label></td>" +
		 * "      <td><input type='date' name='publicationDate'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td><label>Genre:</label></td>" + "      <td><input type='text' name='genre'></td>" + "    </tr>" +
		 * "    <tr>" + "      <td><label>Price:</label></td>" + "      <td><input type='number' name='price'></td>" +
		 * "    </tr>" + "    <tr>" + "      <td><label>Quantity in Stock:</label></td>" +
		 * "      <td><input type='number' name='quantityInStock'></td>" + "    </tr>" + "    <tr>" +
		 * "      <td colspan='2'><button type='submit'>Save</button></td>" + "    </tr>" + "  </table>" + "</form>";
		 */
		String htmlContent = "<h3>Update Book</h3>" + "<form class='form' action='UpdateRecord' method='POST'>"
				+ "  <div class='form-group'>" + "    <label>Book ID:</label>"
				+ "    <input type='text' name='bookId' class='form-control'>" + "  </div>"
				+ "  <div class='form-group'>" + "    <label>Title:</label>"
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
		/*
		 * String htmlContent = "<h3>Delete Book</h3>" + "<form method='POST' action='DeleteRecord'>" + "  <table>" +
		 * "    <tr>" + "      <td><label>Book ID:</label></td>" + "      <td><input type='text' name='bookId'></td>" +
		 * "    </tr>" + "    <tr>" + "      <td colspan='2'><button type='submit'>Delete</button></td>" + "    </tr>" +
		 * "  </table>" + "</form>";
		 */
		String htmlContent = "<h3>Delete Book</h3>" + "<form class='form' action='DeleteRecord' method='POST'>"
				+ "  <div class='form-group'>" + "    <label>Book ID:</label>"
				+ "    <input type='text' name='bookId' class='form-control'>" + "  </div>"
				+ "  <button type='submit' class='btn btn-danger'>Delete</button>" + "</form>";

		response.setContentType("text/html");
		response.getWriter().write(htmlContent);
	}

}
