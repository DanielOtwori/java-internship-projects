<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, java.time.*, level3.librarymanagement.model.IssuedBook" %>

<%
    // 🔐 Protect page
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<IssuedBook> issuedBooks =
            (List<IssuedBook>) request.getAttribute("issuedBooks");

    if (issuedBooks == null) {
        issuedBooks = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Return Books</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<!-- HEADER -->
<header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand px-3"
       href="${pageContext.request.contextPath}/dashboard">
        <i class="bi bi-book-half me-2"></i>Library
    </a>

    <div class="navbar-nav d-flex flex-row me-3">
        <span class="text-white me-3">
            Welcome, <%= session.getAttribute("user") %>
        </span>

        <a class="nav-link px-3"
           href="${pageContext.request.contextPath}/logout">
            Sign out
        </a>
    </div>
</header>

<div class="container-fluid">
    <div class="row">

        <!-- SIDEBAR -->
        <nav class="col-md-3 col-lg-2 d-md-block sidebar collapse">
            <ul class="nav flex-column">

                <li>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/dashboard">
                        Dashboard
                    </a>
                </li>

                <li>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/assign_book.jsp">
                        Assign Book
                    </a>
                </li>

                <li>
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/returnBooks">
                        Return Books
                    </a>
                </li>

                <li>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/books">
                        Books
                    </a>
                </li>

                <li>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/users">
                        Users
                    </a>
                </li>

            </ul>
        </nav>

        <!-- MAIN -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <h2 class="mt-3">
                <i class="bi bi-arrow-left-circle me-2"></i>Return Books
            </h2>

            <div class="table-responsive mt-4">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>#</th>
                        <th>Book</th>
                        <th>User</th>
                        <th>Issued Date</th>
                        <th>Due Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>

                    <% if (!issuedBooks.isEmpty()) {
                        int i = 1;

                        for (IssuedBook b : issuedBooks) {

                            LocalDate today = LocalDate.now();
                            LocalDate dueDate = b.getDueDate().toLocalDate();

                            boolean overdue = today.isAfter(dueDate);
                    %>

                    <tr>
                        <td><%= i++ %></td>

                        <td><%= b.getBookTitle() %></td>

                        <td><%= b.getUserName() %></td>

                        <td><%= b.getIssueDate() %></td>

                        <td><%= b.getDueDate() %></td>

                        <!-- STATUS -->
                        <td>
                            <% if (overdue) { %>
                            <span class="badge bg-danger">Overdue</span>
                            <% } else { %>
                            <span class="badge bg-success">On Time</span>
                            <% } %>
                        </td>

                        <!-- ACTION BUTTON -->
                        <td>
                            <a href="${pageContext.request.contextPath}/returnBook?id=<%= b.getIssueId() %>"
                               class="btn btn-sm btn-success"
                               onclick="return confirm('Confirm return of this book?');">
                                Return
                            </a>
                        </td>
                    </tr>

                    <%  }
                    } else { %>

                    <tr>
                        <td colspan="7" class="text-center text-muted">
                            No issued books found
                        </td>
                    </tr>

                    <% } %>

                    </tbody>
                </table>
            </div>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>