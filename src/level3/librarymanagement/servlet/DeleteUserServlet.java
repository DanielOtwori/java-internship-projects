package level3.librarymanagement.servlet;

import level3.librarymanagement.dao.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {


        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException {

            int id = Integer.parseInt(request.getParameter("id"));

            UserDAO dao = new UserDAO();
            dao.deleteUser(id);

            response.sendRedirect("users");
        }
    }