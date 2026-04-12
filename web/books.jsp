<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="level3.librarymanagement.model.Book" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");

    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books - Library</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container mt-4">

    <h2 class="mb-3"> Books List</h2>

    <!-- SUCCESS MESSAGE -->
    <% if (success != null) { %>
    <div class="alert alert-success alert-dismissible fade show">
        <%= success %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>

    <!-- ERROR MESSAGE -->
    <% if (error != null) { %>
    <div class="alert alert-danger alert-dismissible fade show">
        <%= error %>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    <% } %>

    <!-- TABLE -->
    <table class="table table-bordered table-striped">

        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>ISBN</th>
            <th>Total</th>
            <th>Available</th>
            <th>Actions</th>
        </tr>
        </thead>

        <tbody>

        <%
            if (books != null && !books.isEmpty()) {
                for (Book book : books) {
        %>

        <tr>
            <td><%= book.getBookId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getCategory() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getTotalCopies() %></td>
            <td>
                <span class="badge bg-<%= book.getAvailableCopies() > 0 ? "success" : "danger" %>">
                    <%= book.getAvailableCopies() %>
                </span>
            </td>

            <td>

                <!-- EDIT -->
                <a href="${pageContext.request.contextPath}/editBook?id=<%= book.getBookId() %>"
                   class="btn btn-warning btn-sm">
                     Edit
                </a>

                <!-- DELETE -->
                <a href="${pageContext.request.contextPath}/deleteBook?id=<%= book.getBookId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Are you sure you want to delete this book?');">
                    Delete
                </a>

            </td>
        </tr>

        <%
            }
        } else {
        %>

        <tr>
            <td colspan="8" class="text-center text-muted">
                No books found
            </td>
        </tr>

        <%
            }
        %>

        </tbody>
    </table>

    <!-- BACK BUTTON -->
    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">
        Back to Dashboard
    </a>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>