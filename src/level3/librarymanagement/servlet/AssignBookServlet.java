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

    //LOAD PAGE
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();

        List<Book> books = bookDAO.getAllBooks();
        List<User> users = userDAO.getAllUsers();

        request.setAttribute("books", books);
        request.setAttribute("users", users);

        request.getRequestDispatcher("assign_book.jsp").forward(request, response);
    }

    //HANDLE FORM SUBMIT
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.borrowBook(userId, bookId);

        response.sendRedirect("dashboard");
    }
}