package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;
import level3.librarymanagement.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {

    //HANDLE FORM SUBMIT (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Get form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        //VALIDATION
        if (firstName == null || lastName == null || email == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        //Create user object
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER"); // default role

        //Save user
        UserDAO dao = new UserDAO();
        boolean success = dao.addUser(user);

        if (success) {
            //Redirect to login with success message
            response.sendRedirect(request.getContextPath() + "/login.jsp?success=registered");
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    // HANDLE DIRECT URL ACCESS (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Redirect to register page instead of 405 error
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}