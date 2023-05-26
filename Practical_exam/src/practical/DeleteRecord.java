package practical;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteRecord")
public class DeleteRecord extends HttpServlet {
	private BookDAL bookDAL;

	@Override
	public void init() throws ServletException {
		super.init();
		bookDAL = new BookDAL();
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bookId = Integer.parseInt(request.getParameter("bookId"));

		try {
			// Call the DAL to delete the book from the database
			bookDAL.deleteBook(bookId);

			// Redirect to the readBooks action to display the updated list of books
			response.sendRedirect(request.getContextPath() + "/BookServlet?action=read");
			return;
		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
			response.getWriter().write("Error occurred while deleting the book");
		}
	}

}
