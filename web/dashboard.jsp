<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, level3.librarymanagement.model.IssuedBook" %>

<%
    Integer totalBooks = (Integer) request.getAttribute("totalBooks");
    Integer issuedBooks = (Integer) request.getAttribute("issuedBooks");
    Integer returnedBooks = (Integer) request.getAttribute("returnedBooks");
    Integer totalUsers = (Integer) request.getAttribute("totalUsers");

    if (totalBooks == null) totalBooks = 0;
    if (issuedBooks == null) issuedBooks = 0;
    if (returnedBooks == null) returnedBooks = 0;
    if (totalUsers == null) totalUsers = 0;

    List<IssuedBook> issuedList =
            (List<IssuedBook>) request.getAttribute("issuedList");

    if (issuedList == null) issuedList = new ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f4f6f9;
        }

        .sidebar {
            height: 100vh;
            background: #2c3e50;
            color: white;
            padding: 20px;
        }

        .sidebar a {
            display: block;
            color: white;
            padding: 10px;
            margin-bottom: 10px;
            text-decoration: none;
            border-radius: 8px;
        }

        .sidebar a:hover, .sidebar a.active {
            background: #1abc9c;
        }

        .card-box {
            border-radius: 15px;
            padding: 20px;
            color: white;
        }

        .card-green { background: linear-gradient(45deg, #16a085, #1abc9c); }
        .card-yellow { background: linear-gradient(45deg, #f39c12, #f1c40f); }
        .card-success { background: linear-gradient(45deg, #27ae60, #2ecc71); }
        .card-blue { background: linear-gradient(45deg, #3498db, #5dade2); }

        .table-card {
            background: white;
            border-radius: 12px;
            padding: 20px;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* THEME MATCH LOGOUT BUTTON */
        .btn-logout {
            background: linear-gradient(45deg, #16a085, #1abc9c);
            color: white;
            border: none;
        }

        .btn-logout:hover {
            opacity: 0.9;
            color: white;
        }
    </style>
</head>

<body>

<div class="container-fluid">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-2 sidebar">
            <h4>Library</h4>

            <a href="dashboard" class="active">Dashboard</a>
            <a href="assignBook">Assign Book</a>
            <a href="returnBooks">Return Book</a>
            <a href="books">Books</a>
            <a href="add_book.jsp">Add Book</a>
            <a href="users">Users</a>
            <a href="add_user.jsp">Add User</a>
        </div>

        <!-- MAIN -->
        <div class="col-md-10 p-4">

            <!-- TOP BAR -->
            <div class="top-bar mb-3">
                <h2>Dashboard</h2>

                <!-- LOGOUT BUTTON -->
                <a href="<%= request.getContextPath() %>/logout"
                   class="btn btn-sm btn-logout">
                    <i class="bi bi-box-arrow-right me-1"></i> Logout
                </a>
            </div>

            <!-- STATS -->
            <div class="row g-3 mb-4">

                <div class="col-md-3">
                    <div class="card-box card-green">
                        <h6>Total Books</h6>
                        <h2><%= totalBooks %></h2>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box card-yellow">
                        <h6>Books Assigned</h6>
                        <h2><%= issuedBooks %></h2>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box card-success">
                        <h6>Books Returned</h6>
                        <h2><%= returnedBooks %></h2>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card-box card-blue">
                        <h6>Users</h6>
                        <h2><%= totalUsers %></h2>
                    </div>
                </div>

            </div>

            <!-- TABLE -->
            <div class="table-card">

                <h5 class="mb-3">Currently Issued Books</h5>

                <table class="table table-hover align-middle">

                    <thead class="table-light">
                    <tr>
                        <th>#</th>
                        <th>Title</th>
                        <th>User</th>
                        <th>Due Date</th>
                    </tr>
                    </thead>

                    <tbody>

                    <%
                        if (!issuedList.isEmpty()) {

                            int i = 1;

                            for (IssuedBook b : issuedList) {

                                String dueDate = "N/A";

                                if (b.getDueDate() != null) {
                                    dueDate = b.getDueDate().toString();
                                }
                    %>

                    <tr>
                        <td><%= i++ %></td>
                        <td><strong><%= b.getBookTitle() %></strong></td>
                        <td><%= b.getUserName() %></td>
                        <td>
                            <span class="badge bg-warning text-dark">
                                <%= dueDate %>
                            </span>
                        </td>
                    </tr>

                    <%
                        }
                    } else {
                    %>

                    <tr>
                        <td colspan="4" class="text-center text-muted">
                            No issued books found
                        </td>
                    </tr>

                    <% } %>

                    </tbody>

                </table>

            </div>

        </div>
    </div>
</div>

</body>
</html>