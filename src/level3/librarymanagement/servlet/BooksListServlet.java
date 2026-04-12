package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // OPTIONAL: SESSION CHECk
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //Get books from DB
        BookDAO dao = new BookDAO();
        List<Book> books = dao.getAllBooks();

        //Send to JSP
        request.setAttribute("books", books);

        //Forward
        request.getRequestDispatcher("books.jsp").forward(request, response);
    }
}