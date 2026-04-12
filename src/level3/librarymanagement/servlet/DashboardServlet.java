package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.dao.TransactionDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK (MUST BE INSIDE METHOD)
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        //Create DAO objects
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        //Get counts
        int totalBooks = bookDAO.getTotalBooks();
        int totalUsers = userDAO.getTotalUsers();
        int borrowedBooks = transactionDAO.getBorrowedBooks();
        int returnedBooks = transactionDAO.getReturnedBooks();

        //Set attributes
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("borrowedBooks", borrowedBooks);
        request.setAttribute("returnedBooks", returnedBooks);

        //Forward to dashboard
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}