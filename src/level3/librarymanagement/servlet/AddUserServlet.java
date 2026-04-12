package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String email     = request.getParameter("email");
        String password  = request.getParameter("password");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO dao = new UserDAO();

        boolean saved = dao.addUser(user); // make this return boolean

        if (saved) {
            session.setAttribute("success", "User added successfully!");
        } else {
            session.setAttribute("error", "Failed to add user.");
        }

        //redirect to users list
        response.sendRedirect("users");
    }
}