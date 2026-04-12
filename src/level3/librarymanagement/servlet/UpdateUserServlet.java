package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // GET FORM DATA
        int id = Integer.parseInt(request.getParameter("userId"));

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String address = request.getParameter("address");

        // CREATE USER OBJECT
        User user = new User();
        user.setUserId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);
        user.setPhoneNo(phone);
        user.setAddress(address);

        // UPDATE USER
        UserDAO dao = new UserDAO();
        dao.updateUser(user);

        // REDIRECT
        response.sendRedirect(request.getContextPath() + "/users");
    }
}