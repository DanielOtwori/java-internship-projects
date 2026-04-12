<%@ page import="java.util.List" %>
<%@ page import="level3.librarymanagement.model.Book" %>
<%@ page import="level3.librarymanagement.model.User" %>

<%
    // SESSION PROTECTION
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Book> books = (List<Book>) request.getAttribute("books");
    List<User> users = (List<User>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Assign Book</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<header class="navbar navbar-dark bg-dark">
    <span class="navbar-brand px-3">Library</span>

    <a href="${pageContext.request.contextPath}/logout" class="text-white px-3">Logout</a>
</header>

<div class="container mt-4">

    <h2>Assign Book</h2>

    <!-- SUCCESS MESSAGE -->
    <% if (request.getParameter("success") != null) { %>
    <div class="alert alert-success">
        <%= request.getParameter("success") %>
    </div>
    <% } %>

    <!-- ERROR MESSAGE -->
    <% if (request.getParameter("error") != null) { %>
    <div class="alert alert-danger">
        <%= request.getParameter("error") %>
    </div>
    <% } %>

    <!-- FORM -->
    <form action="${pageContext.request.contextPath}/assignBook" method="post">

        <!-- BOOK DROPDOWN -->
        <div class="mb-3">
            <label>Select Book</label>
            <select name="bookId" class="form-control" required>

                <% if (books != null && !books.isEmpty()) {
                    for (Book b : books) { %>

                <option value="<%= b.getBookId() %>">
                    <%= b.getTitle() %> - <%= b.getAuthor() %> (Available: <%= b.getAvailableCopies() %>)
                </option>

                <%  }
                } else { %>

                <option disabled>No books available</option>

                <% } %>

            </select>
        </div>

        <!-- USER DROPDOWN -->
        <div class="mb-3">
            <label>Select User</label>
            <select name="userId" class="form-control" required>

                <% if (users != null && !users.isEmpty()) {
                    for (User u : users) { %>

                <option value="<%= u.getUserId() %>">
                    <%= u.getFirstName() %> <%= u.getLastName() %>
                </option>

                <%  }
                } else { %>

                <option disabled>No users found</option>

                <% } %>

            </select>
        </div>

        <!-- OPTIONAL DATES -->
        <div class="mb-3">
            <label>Issue Date</label>
            <input type="date" name="issueDate" class="form-control">
        </div>

        <div class="mb-3">
            <label>Due Date</label>
            <input type="date" name="dueDate" class="form-control">
        </div>

        <button type="submit" class="btn btn-primary">
            Assign Book
        </button>

    </form>

</div>
</body>
</html>