package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.BookDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("bookId"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String isbn = request.getParameter("isbn");
            String category = request.getParameter("category");
            String publisher = request.getParameter("publisher");
            int copies = Integer.parseInt(request.getParameter("copies"));

            BookDAO dao = new BookDAO();
            dao.updateBook(id, title, author, isbn, category, publisher, copies);

            //PATH
            response.sendRedirect(request.getContextPath() + "/books.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // optional
        }
    }
}