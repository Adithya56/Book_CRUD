package practical;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateRecord")
public class UpdateRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAL bookDAL;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDAL = new BookDAL();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		LocalDate publicationDate = LocalDate.parse(request.getParameter("publicationDate"));
		String genre = request.getParameter("genre");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantityInStock = Integer.parseInt(request.getParameter("quantityInStock"));

		// Create a new Book object
		Book book = new Book(bookId, title, author, publicationDate, genre, price, quantityInStock);

		try {
			// Call the DAL to update the book in the database
			bookDAL.updateBook(book);

			// Redirect to the readBooks action to display the updated list of books
			response.sendRedirect(request.getContextPath() + "/BookServlet?action=read");
			return;
		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
			response.getWriter().write("Error occurred while updating the book");
		}
	}

}
