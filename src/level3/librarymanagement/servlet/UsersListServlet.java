package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //SESSION CHECK
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //FETCH USERS
        UserDAO dao = new UserDAO();
        List<User> users = dao.getAllUsers();

        //SEND TO JSP
        request.setAttribute("users", users);

        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}