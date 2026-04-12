<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    //PROTECT PAGE
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String user = session.getAttribute("user").toString();

    String error = (String) request.getAttribute("error");
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
    <title>Add Book - Library Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<!-- HEADER -->
<header class="navbar navbar-dark sticky-top shadow">
    <a class="navbar-brand px-3"
       href="${pageContext.request.contextPath}/dashboard">
        <i class="bi bi-book-half me-2"></i>Library
    </a>

    <div class="navbar-nav d-flex flex-row me-3">
        <span class="text-white me-3">
            Welcome, <%= user %>
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

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/dashboard">
                        <i class="bi bi-speedometer2"></i> Dashboard
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/assign_book.jsp">
                        <i class="bi bi-arrow-right-circle"></i> Assign Book
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/add_book.jsp">
                        <i class="bi bi-plus-circle"></i> Add Book
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/books">
                        <i class="bi bi-book"></i> View Books
                    </a>
                </li>

            </ul>
        </nav>

        <!-- MAIN CONTENT -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <h2 class="mt-3">
                <i class="bi bi-plus-circle me-2"></i>Add New Book
            </h2>

            <!-- ERROR MESSAGE -->
            <% if (error != null) { %>
            <div class="alert alert-danger mt-3">
                <%= error %>
            </div>
            <% } %>

            <!-- SUCCESS MESSAGE -->
            <% if (success != null) { %>
            <div class="alert alert-success mt-3">
                <%= success %>
            </div>
            <% } %>

            <!-- FORM -->
            <form action="${pageContext.request.contextPath}/addBook"
                  method="post"
                  class="mt-4">

                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Title</label>
                        <input type="text" name="title"
                               class="form-control" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Author</label>
                        <input type="text" name="author"
                               class="form-control" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">ISBN</label>
                        <input type="text" name="isbn"
                               class="form-control">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Category</label>
                        <select name="category" class="form-select">
                            <option value="Fiction">Fiction</option>
                            <option value="Technology">Technology</option>
                            <option value="Science">Science</option>
                        </select>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Publisher</label>
                        <input type="text" name="publisher"
                               class="form-control">
                    </div>

                    <div class="col-md-6 mb-3">
                        <label class="form-label">Copies</label>
                        <input type="number" name="copies"
                               class="form-control" min="1" required>
                    </div>

                </div>

                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-check-circle me-1"></i> Save Book
                </button>

                <a href="${pageContext.request.contextPath}/books"
                   class="btn btn-secondary">
                    Cancel
                </a>

            </form>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>