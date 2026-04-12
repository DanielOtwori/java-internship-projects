package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.TransactionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            int issueId = Integer.parseInt(request.getParameter("id"));

            TransactionDAO dao = new TransactionDAO();

            // USE CLEAN VERSION (only issueId)
            boolean success = dao.returnBook(issueId);

            if (success) {
                response.sendRedirect("returnBooks?success=Book+returned+successfully");
            } else {
                response.sendRedirect("returnBooks?error=Failed+to+return+book");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("returnBooks?error=Invalid+request");
        }
    }
}