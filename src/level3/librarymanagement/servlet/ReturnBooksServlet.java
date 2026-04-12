package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.IssuedBookDAO;
import level3.librarymanagement.model.IssuedBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/returnBooks")
public class ReturnBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //FETCH ISSUED BOOKS
        IssuedBookDAO dao = new IssuedBookDAO();
        List<IssuedBook> issuedBooks = dao.getAllIssuedBooks();

        //SEND DATA
        request.setAttribute("issuedBooks", issuedBooks);

        //FORWARD
        request.getRequestDispatcher("/return_books.jsp").forward(request, response);
    }
}