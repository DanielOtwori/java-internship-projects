<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="level3.librarymanagement.model.IssuedBook" %>

<%
    //Protect page
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    IssuedBook b = (IssuedBook) request.getAttribute("issuedBook");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Return Book Detail</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<!-- HEADER -->
<header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand px-3"
       href="${pageContext.request.contextPath}/dashboard">
        Library
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

                <li><a class="nav-link"
                       href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>

                <li><a class="nav-link"
                       href="${pageContext.request.contextPath}/assign_book.jsp">Assign Book</a></li>

                <li><a class="nav-link active"
                       href="${pageContext.request.contextPath}/returnBooks">Return Book</a></li>

                <li><a class="nav-link"
                       href="${pageContext.request.contextPath}/books">Books</a></li>

                <li><a class="nav-link"
                       href="${pageContext.request.contextPath}/users">Users</a></li>

            </ul>
        </nav>

        <!-- MAIN -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div class="d-flex justify-content-between align-items-center mt-3">
                <h2><i class="bi bi-arrow-left-circle me-2"></i>Return Book Detail</h2>

                <a href="${pageContext.request.contextPath}/returnBooks"
                   class="btn btn-secondary">
                    Back
                </a>
            </div>

            <div class="row mt-4">

                <!-- LEFT CARD -->
                <div class="col-md-4">
                    <div class="card p-3 shadow-sm">

                        <h5><%= b.getBookTitle() %></h5>
                        <p class="text-muted"><%= b.getUserName() %></p>

                        <hr>

                        <p><strong>ISBN:</strong> <%= b.getIsbn() %></p>
                        <p><strong>Issued Date:</strong> <%= b.getIssueDate() %></p>
                        <p><strong>Due Date:</strong> <%= b.getDueDate() %></p>

                    </div>
                </div>

                <!-- RIGHT FORM -->
                <div class="col-md-8">

                    <!-- FORM CONNECTED -->
                    <form action="${pageContext.request.contextPath}/returnBook" method="post">

                        <!-- IMPORTANT -->
                        <input type="hidden" name="issueId" value="<%= b.getIssueId() %>">

                        <div class="row">

                            <div class="col-md-6">
                                <label>Return Date</label>
                                <input type="date" name="returnDate"
                                       class="form-control" required>
                            </div>

                            <div class="col-md-6">
                                <label>Condition</label>
                                <select name="condition" class="form-control">
                                    <option value="Good">Good</option>
                                    <option value="Fair">Fair</option>
                                    <option value="Damaged">Damaged</option>
                                    <option value="Lost">Lost</option>
                                </select>
                            </div>

                        </div>

                        <div class="mt-3">
                            <label>Notes</label>
                            <textarea name="notes"
                                      class="form-control"
                                      rows="4"></textarea>
                        </div>

                        <div class="mt-4">
                            <button class="btn btn-success">
                                Confirm Return
                            </button>
                        </div>

                    </form>

                </div>

            </div>

        </main>
    </div>
</div>

</body>
</html>