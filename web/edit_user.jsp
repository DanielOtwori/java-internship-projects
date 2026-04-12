<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String username = session.getAttribute("user").toString();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User - Library Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<header class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
    <a class="navbar-brand px-3"
       href="${pageContext.request.contextPath}/dashboard">
        <i class="bi bi-book-half me-2"></i>Library
    </a>

    <div class="navbar-nav d-flex flex-row me-3">
        <span class="text-white me-3">Welcome, <%= username %></span>

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
                <li><a class="nav-link" href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/books.jsp">Books</a></li>
                <li><a class="nav-link" href="${pageContext.request.contextPath}/users.jsp">Users</a></li>
            </ul>
        </nav>

        <!-- MAIN -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <h2 class="mt-3">Edit User</h2>

            <!-- FORM -->
            <form action="${pageContext.request.contextPath}/updateUser" method="post">

                <!-- USER ID -->
                <input type="hidden" name="userId"
                       value="<%= request.getAttribute("userId") %>">

                <div class="row">

                    <div class="col-md-6">
                        <label>First Name</label>
                        <input type="text" name="firstName" class="form-control"
                               value="<%= request.getAttribute("firstName") %>" required>
                    </div>

                    <div class="col-md-6">
                        <label>Last Name</label>
                        <input type="text" name="lastName" class="form-control"
                               value="<%= request.getAttribute("lastName") %>" required>
                    </div>

                </div>

                <div class="row mt-3">

                    <div class="col-md-6">
                        <label>Email</label>
                        <input type="email" name="email" class="form-control"
                               value="<%= request.getAttribute("email") %>" required>
                    </div>

                    <div class="col-md-6">
                        <label>Phone</label>
                        <input type="text" name="phone" class="form-control"
                               value="<%= request.getAttribute("phone") %>">
                    </div>

                </div>

                <div class="row mt-3">

                    <div class="col-md-6">
                        <label>Date of Birth</label>
                        <input type="date" name="dateOfBirth" class="form-control"
                               value="<%= request.getAttribute("dateOfBirth") %>">
                    </div>

                    <div class="col-md-6">
                        <label>User Role</label>
                        <select name="role" class="form-control">
                            <option value="member">Member</option>
                            <option value="librarian">Librarian</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>

                </div>

                <div class="mt-3">
                    <label>Address</label>
                    <textarea name="address" class="form-control"><%= request.getAttribute("address") %></textarea>
                </div>

                <h6 class="mt-4">Change Password (Optional)</h6>

                <div class="row">

                    <div class="col-md-6">
                        <input type="password" name="password" class="form-control" placeholder="New Password">
                    </div>

                    <div class="col-md-6">
                        <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password">
                    </div>

                </div>

                <div class="mt-4">
                    <button class="btn btn-success">
                        <i class="bi bi-save"></i> Update User
                    </button>
                </div>

            </form>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>