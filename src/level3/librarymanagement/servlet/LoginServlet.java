package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();

        boolean isValid = userDAO.loginUser(email, password);

        if (isValid) {

            //CREATE SESSION
            HttpSession session = request.getSession();
            session.setAttribute("user", email);

            //REDIRECT TO DASHBOARD
            response.sendRedirect("dashboard");

        } else {

            //INVALID LOGIN
            request.setAttribute("error", "Invalid email or password!");

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}