<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //PROTECT PAGE
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String success = (String) session.getAttribute("success");
    String error = (String) session.getAttribute("error");

    if (success != null) session.removeAttribute("success");
    if (error != null) session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add User - Library Management</title>

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

    <div class="navbar-nav d-flex flex-row align-items-center me-3">
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
            <div class="position-sticky sidebar-sticky">
                <ul class="nav flex-column">

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/dashboard">
                            <i class="bi bi-speedometer2"></i> Dashboard
                        </a>
                    </li>

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

                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/addBook">
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
                        <a class="nav-link active"
                           href="${pageContext.request.contextPath}/addUser">
                            <i class="bi bi-person-plus"></i> Add User
                        </a>
                    </li>

                </ul>
            </div>
        </nav>

        <!-- MAIN CONTENT -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <div class="mt-3">
                <h2>
                    <i class="bi bi-person-plus me-2"></i>Add New User
                </h2>
            </div>

            <!-- SUCCESS MESSAGE -->
            <% if (success != null) { %>
            <div class="alert alert-success mt-3">
                <%= success %>
            </div>
            <% } %>

            <!-- ERROR MESSAGE -->
            <% if (error != null) { %>
            <div class="alert alert-danger mt-3">
                <%= error %>
            </div>
            <% } %>

            <!-- FORM -->
            <div class="mt-4">

                <form action="${pageContext.request.contextPath}/addUser" method="post">

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control"
                                   name="firstName" placeholder="First Name" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control"
                                   name="lastName" placeholder="Last Name" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <input type="email" class="form-control"
                                   name="email" placeholder="Email" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control"
                                   name="phoneNo" placeholder="Phone">
                        </div>
                    </div>

                    <div class="mb-3">
                    <textarea class="form-control"
                              name="address" placeholder="Address"></textarea>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <input type="password" class="form-control"
                                   name="password" placeholder="Password" required>
                        </div>

                        <div class="col-md-6 mb-3">
                            <input type="password" class="form-control"
                                   name="confirmPassword" placeholder="Confirm Password" required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-person-plus"></i> Create User
                    </button>

                </form>

            </div>

        </main>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>