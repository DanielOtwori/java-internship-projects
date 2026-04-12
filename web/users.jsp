<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, level3.librarymanagement.model.User" %>

<%
    // PROTECT PAGE
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<User> users = (List<User>) request.getAttribute("users");

    //MESSAGES (from AddUserServlet)
    String success = (String) session.getAttribute("success");
    String error = (String) session.getAttribute("error");

    if (success != null) session.removeAttribute("success");
    if (error != null) session.removeAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-4">

<h2>Users List</h2>

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

<table class="table table-bordered table-hover mt-3">
    <thead class="table-dark">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>

    <tbody>

    <% if (users != null && !users.isEmpty()) {
        for (User u : users) { %>

    <tr>
        <td><%= u.getUserId() %></td>
        <td><%= u.getFirstName() %> <%= u.getLastName() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getRole() %></td>
    </tr>

    <%  }
    } else { %>

    <tr>
        <td colspan="4" class="text-center text-muted">
            No users found
        </td>
    </tr>

    <% } %>

    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>