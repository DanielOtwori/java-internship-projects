package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int bookId = Integer.parseInt(request.getParameter("id"));

            BookDAO dao = new BookDAO();
            dao.deleteBook(bookId);

            response.sendRedirect("books?success=Book+deleted+successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("books?error=Failed+to+delete+book");
        }
    }
}