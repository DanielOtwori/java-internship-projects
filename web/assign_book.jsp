<%@ page import="java.util.List" %>
<%@ page import="level3.librarymanagement.model.Book" %>
<%@ page import="level3.librarymanagement.model.User" %>

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

    <!--FIXED LOGOUT-->
    <a href="${pageContext.request.contextPath}/logout" class="text-white px-3">Logout</a>
</header>

<div class="container mt-4">

    <h2>Assign Book</h2>

    <!-- FORM CONNECTED TO SERVLET -->
    <form action="${pageContext.request.contextPath}/assignBook" method="post">

        <!-- BOOK DROPDOWN -->
        <div class="mb-3">
            <label>Select Book</label>
            <select name="bookId" class="form-control" required>
                <%
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    if (books != null) {
                        for (Book b : books) {
                %>
                <option value="<%= b.getBookId() %>">
                    <%= b.getTitle() %> - <%= b.getAuthor() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- USER DROPDOWN -->
        <div class="mb-3">
            <label>Select User</label>
            <select name="userId" class="form-control" required>
                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User u : users) {
                %>
                <option value="<%= u.getUserId() %>">
                    <%= u.getFirstName() %> <%= u.getLastName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- DATES -->
        <div class="mb-3">
            <label>Issue Date</label>
            <input type="date" name="issueDate" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Due Date</label>
            <input type="date" name="dueDate" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary">Assign Book</button>

    </form>

</div>
</body>
</html>