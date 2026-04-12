<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    // PROTECT PAGE
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String user = session.getAttribute("user").toString();

    //SUCCESS MESSAGE (FROM SESSION)
    String success = (String) session.getAttribute("success");
    if (success != null) {
        session.removeAttribute("success"); // clear after showing
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Library Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<!-- HEADER -->
<header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 px-3"
       href="${pageContext.request.contextPath}/dashboard">
        <i class="bi bi-book-half me-2"></i>Library
    </a>

    <div class="navbar-nav d-flex flex-row align-items-center me-3">
        <span class="text-white me-3">Welcome, <%= user %></span>

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
            <div class="position-sticky sidebar-sticky">
                <ul class="nav flex-column">

                    <li class="nav-item">
                        <a class="nav-link active"
                           href="${pageContext.request.contextPath}/dashboard">
                            <i class="bi bi-speedometer2"></i> Dashboard
                        </a>
                    </li>

                    <!-- FIXED LINKS -->

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/assignBook">
                            <i class="bi bi-arrow-right-circle"></i> Assign Book
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/returnBooks">
                            <i class="bi bi-arrow-left-circle"></i> Return Books
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/books">
                            <i class="bi bi-book"></i> Books
                        </a>
                    </li>

                    <!-- MAIN FIX HERE -->
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/add_book.jsp">
                            <i class="bi bi-plus-circle"></i> Add Book
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/users">
                            <i class="bi bi-people"></i> Users
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/add_user.jsp">
                            <i class="bi bi-person-plus"></i> Add User
                        </a>
                    </li>

                </ul>
            </div>
        </nav>

        <!-- MAIN CONTENT -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div class="page-header mt-3">
                <h1 class="h2">
                    <i class="bi bi-speedometer2 me-2"></i>Dashboard
                </h1>
            </div>

            <!-- SUCCESS MESSAGE -->
            <% if (success != null) { %>
            <div class="alert alert-success alert-dismissible fade show mt-3">
                <%= success %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <% } %>

            <!-- CARDS -->
            <div class="row mb-4 mt-3">

                <div class="col-md-3">
                    <div class="card text-white bg-primary mb-3 shadow">
                        <div class="card-body">
                            <h6>Total Books</h6>
                            <h3><%= request.getAttribute("totalBooks") != null ? request.getAttribute("totalBooks") : 0 %></h3>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card text-white bg-warning mb-3 shadow">
                        <div class="card-body">
                            <h6>Books Assigned</h6>
                            <h3><%= request.getAttribute("borrowedBooks") != null ? request.getAttribute("borrowedBooks") : 0 %></h3>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card text-white bg-success mb-3 shadow">
                        <div class="card-body">
                            <h6>Books Returned</h6>
                            <h3><%= request.getAttribute("returnedBooks") != null ? request.getAttribute("returnedBooks") : 0 %></h3>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card text-white bg-info mb-3 shadow">
                        <div class="card-body">
                            <h6>Users</h6>
                            <h3><%= request.getAttribute("totalUsers") != null ? request.getAttribute("totalUsers") : 0 %></h3>
                        </div>
                    </div>
                </div>

            </div>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>