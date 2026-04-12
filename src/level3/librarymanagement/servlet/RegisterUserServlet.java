package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {




        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole("user"); // default role

            UserDAO dao = new UserDAO();
            dao.addUser(user);

            response.sendRedirect("login");
        }
    }
