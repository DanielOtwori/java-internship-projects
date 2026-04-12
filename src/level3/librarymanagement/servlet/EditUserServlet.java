package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        UserDAO dao = new UserDAO();
        User u = dao.getUserById(id);

        // NULL CHECK (VERY IMPORTANT)
        if (u == null) {
            response.sendRedirect(request.getContextPath() + "/users");
            return;
        }

        //SET ATTRIBUTES
        request.setAttribute("userId", u.getUserId());
        request.setAttribute("firstName", u.getFirstName());
        request.setAttribute("lastName", u.getLastName());
        request.setAttribute("email", u.getEmail());
        request.setAttribute("phone", u.getPhoneNo());
        request.setAttribute("role", u.getRole());
        request.setAttribute("address", u.getAddress());


        // FORWARD
        request.getRequestDispatcher("/edit_user.jsp").forward(request, response);
    }
}