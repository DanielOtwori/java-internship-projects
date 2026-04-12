<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, java.time.*, level3.librarymanagement.model.IssuedBook" %>

<%
    //PROTECT PAGE
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
                       href="${pageContext.request.contextPath}/assignBook">
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

            <!-- SUCCESS / ERROR MESSAGES -->
            <%
                String success = request.getParameter("success");
                String error = request.getParameter("error");
            %>

            <% if (success != null) { %>
            <div class="alert alert-success mt-3">
                <%= success %>
            </div>
            <% } %>

            <% if (error != null) { %>
            <div class="alert alert-danger mt-3">
                <%= error %>
            </div>
            <% } %>

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
                            LocalDate dueDate = null;
                            boolean overdue = false;

                            if (b.getDueDate() != null) {
                                dueDate = b.getDueDate().toLocalDate();
                                overdue = today.isAfter(dueDate);
                            }

                            boolean returned = "RETURNED".equalsIgnoreCase(b.getStatus());
                    %>

                    <tr class="<%= overdue && !returned ? "table-danger" : "" %>">

                        <td><%= i++ %></td>

                        <td><%= b.getBookTitle() != null ? b.getBookTitle() : "N/A" %></td>

                        <td><%= b.getUserName() != null ? b.getUserName() : "N/A" %></td>

                        <td><%= b.getIssueDate() != null ? b.getIssueDate() : "N/A" %></td>

                        <td><%= b.getDueDate() != null ? b.getDueDate() : "N/A" %></td>

                        <!-- STATUS -->
                        <td>
                            <% if (returned) { %>
                            <span class="badge bg-secondary">Returned</span>

                            <% } else if (overdue) { %>
                            <span class="badge bg-danger">Overdue</span>

                            <% } else { %>
                            <span class="badge bg-success">On Time</span>
                            <% } %>
                        </td>

                        <!-- ACTION -->
                        <td>
                            <% if (!returned) { %>
                            <a href="${pageContext.request.contextPath}/returnBook?id=<%= b.getIssueId() %>"
                               class="btn btn-sm btn-success"
                               onclick="return confirm('Confirm return of this book?');">
                                Return
                            </a>
                            <% } else { %>
                            <span class="text-muted">Completed</span>
                            <% } %>
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