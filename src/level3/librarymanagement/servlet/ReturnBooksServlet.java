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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Protect page
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //Get issued books
        IssuedBookDAO dao = new IssuedBookDAO();
        List<IssuedBook> issuedBooks = dao.getAllIssuedBooks();

        //Send to JSP
        request.setAttribute("issuedBooks", issuedBooks);

        //Open JSP
        request.getRequestDispatcher("return_books.jsp").forward(request, response);
    }
}