package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;
import level3.librarymanagement.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    //HANDLE FORM SUBMISSION (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            //GET FORM DATA (SAFE)
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            String category = request.getParameter("category");
            String publisher = request.getParameter("publisher");

            //SAFE INTEGER PARSE (PREVENT CRASH)
            int copies = 1;
            try {
                copies = Integer.parseInt(request.getParameter("copies"));
            } catch (Exception e) {
                copies = 1;
            }

            //BASIC VALIDATION
            if (title == null || title.trim().isEmpty() ||
                    author == null || author.trim().isEmpty()) {

                request.setAttribute("error", "Title and Author are required!");
                request.getRequestDispatcher("add_book.jsp").forward(request, response);
                return;
            }

            //CREATE OBJECT
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setCategory(category);
            book.setPublisher(publisher);
            book.setTotalCopies(copies);
            book.setAvailableCopies(copies);
            book.setStatus("AVAILABLE");

            //SAVE TO DATABASE
            BookDAO bookDAO = new BookDAO();
            bookDAO.addBook(book);

            // SUCCESS MESSAGE
            request.getSession().setAttribute("success", "Book added successfully!");

            //REDIRECT
            response.sendRedirect(request.getContextPath() + "/books");

        } catch (Exception e) {
            e.printStackTrace();

            //ERROR HANDLING
            request.setAttribute("error", "Something went wrong while adding book.");
            request.getRequestDispatcher("add_book.jsp").forward(request, response);
        }
    }

    //HANDLE DIRECT URL ACCESS (FIXES 405 ERROR)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Redirect to form page
        response.sendRedirect(request.getContextPath() + "/add_book.jsp");
    }
}