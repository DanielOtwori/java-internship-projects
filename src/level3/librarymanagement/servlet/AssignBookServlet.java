package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.dao.TransactionDAO;
import level3.librarymanagement.model.Book;
import level3.librarymanagement.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/assignBook")
public class AssignBookServlet extends HttpServlet {

    // LOAD PAGE
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // SESSION CHECK
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // LOAD DATA
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();

        List<Book> books = bookDAO.getAllBooks();
        List<User> users = userDAO.getAllUsers();

        request.setAttribute("books", books);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/assign_book.jsp").forward(request, response);
    }

    // HANDLE FORM SUBMIT
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // SESSION CHECK
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            String bookParam = request.getParameter("bookId");
            String userParam = request.getParameter("userId");
            String dueDate = request.getParameter("dueDate");

            // VALIDATION
            if (bookParam == null || userParam == null || dueDate == null || dueDate.isEmpty()) {
                response.sendRedirect("assignBook?error=Missing+data");
                return;
            }

            int bookId = Integer.parseInt(bookParam);
            int userId = Integer.parseInt(userParam);

            // CHECK AVAILABLE COPIES
            BookDAO bookDAO = new BookDAO();
            int available = bookDAO.getAvailableCopies(bookId);

            if (available <= 0) {
                response.sendRedirect("assignBook?error=No+copies+available");
                return;
            }

            // SAVE TRANSACTION WITH DUE DATE
            TransactionDAO transactionDAO = new TransactionDAO();
            boolean success = transactionDAO.borrowBook(userId, bookId, dueDate);

            if (success) {
                response.sendRedirect("assignBook?success=Book+assigned+successfully");
            } else {
                response.sendRedirect("assignBook?error=Failed+to+assign+book");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("assignBook?error=Invalid+input");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("assignBook?error=Something+went+wrong");
        }
    }
}