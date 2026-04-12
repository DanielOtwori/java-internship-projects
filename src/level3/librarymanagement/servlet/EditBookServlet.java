package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;   //
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            BookDAO dao = new BookDAO();
            Book book = dao.getBookById(id);

            //Safety check
            if (book == null) {
                response.sendRedirect("books.jsp");
                return;
            }

            //Set attributes
            request.setAttribute("bookId", book.getBookId());
            request.setAttribute("title", book.getTitle());
            request.setAttribute("author", book.getAuthor());
            request.setAttribute("isbn", book.getIsbn());
            request.setAttribute("category", book.getCategory());
            request.setAttribute("publisher", book.getPublisher());
            request.setAttribute("copies", book.getTotalCopies());

            //Forward
            request.getRequestDispatcher("edit_book.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("books.jsp"); // fallback
        }
    }
}