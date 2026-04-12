package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.dao.TransactionDAO;
import level3.librarymanagement.dao.IssuedBookDAO;
import level3.librarymanagement.model.IssuedBook;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //DAO OBJECTS
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        IssuedBookDAO issuedBookDAO = new IssuedBookDAO();

        //COUNTS (MATCH JSP NAMES)
        int totalBooks = bookDAO.getTotalBooks();
        int totalUsers = userDAO.getTotalUsers();
        int issuedBooks = transactionDAO.getBorrowedBooks();
        int returnedBooks = transactionDAO.getReturnedBooks();

        // GET RECENT ISSUED BOOKS (FOR TABLE)
        List<IssuedBook> issuedList = issuedBookDAO.getAllIssuedBooks();

        //SET ATTRIBUTES
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("issuedBooks", issuedBooks);
        request.setAttribute("returnedBooks", returnedBooks);
        request.setAttribute("issuedList", issuedList);

        // FORWARD
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}