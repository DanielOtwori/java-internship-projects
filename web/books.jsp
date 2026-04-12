<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="level3.librarymanagement.model.Book" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Books</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container mt-4">

    <h2>Books List</h2>

    <table class="table table-bordered">

        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>ISBN</th>
            <th>Copies</th>
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

                <!-- EDIT -->
                <a href="<%= request.getContextPath() %>/editBook?id=<%= book.getBookId() %>"
                   class="btn btn-warning btn-sm">
                    Edit
                </a>

                <!-- DELETE -->
                <a href="<%= request.getContextPath() %>/deleteBook?id=<%= book.getBookId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Are you sure?');">
                    Delete
                </a>

            </td>
        </tr>

        <%
            }
        } else {
        %>

        <tr>
            <td colspan="7" class="text-center">No books found</td>
        </tr>

        <%
            }
        %>

        </tbody>
    </table>

</div>

</body>
</html>