<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    //Prevent null errors
    String user = (String) session.getAttribute("user");

    Integer bookId = (Integer) request.getAttribute("bookId");
    String title = (String) request.getAttribute("title");
    String author = (String) request.getAttribute("author");
    String isbn = (String) request.getAttribute("isbn");
    String category = (String) request.getAttribute("category");
    String publisher = (String) request.getAttribute("publisher");
    Integer copies = (Integer) request.getAttribute("copies");

    //Default values (avoid null showing)
    if (user == null) user = "Admin";
    if (bookId == null) bookId = 0;
    if (title == null) title = "";
    if (author == null) author = "";
    if (isbn == null) isbn = "";
    if (category == null) category = "";
    if (publisher == null) publisher = "";
    if (copies == null) copies = 0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Book</title>

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
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/dashboard">
                        Dashboard
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/books.jsp">
                        Books
                    </a>
                </li>
            </ul>
        </nav>

        <!-- MAIN -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

            <h2 class="mt-3">Edit Book</h2>

            <form action="${pageContext.request.contextPath}/updateBook" method="post">

                <!-- BOOK ID -->
                <input type="hidden" name="bookId" value="<%= bookId %>">

                <div class="row">

                    <div class="col-md-6">
                        <label>Title</label>
                        <input type="text" class="form-control"
                               name="title"
                               value="<%= title %>" required>
                    </div>

                    <div class="col-md-6">
                        <label>Author</label>
                        <input type="text" class="form-control"
                               name="author"
                               value="<%= author %>" required>
                    </div>

                </div>

                <div class="row mt-3">

                    <div class="col-md-6">
                        <label>ISBN</label>
                        <input type="text" class="form-control"
                               name="isbn"
                               value="<%= isbn %>">
                    </div>

                    <div class="col-md-6">
                        <label>Category</label>
                        <input type="text" class="form-control"
                               name="category"
                               value="<%= category %>">
                    </div>

                </div>

                <div class="row mt-3">

                    <div class="col-md-6">
                        <label>Publisher</label>
                        <input type="text" class="form-control"
                               name="publisher"
                               value="<%= publisher %>">
                    </div>

                    <div class="col-md-6">
                        <label>Copies</label>
                        <input type="number" class="form-control"
                               name="copies"
                               value="<%= copies %>" min="1">
                    </div>

                </div>

                <div class="mt-4">
                    <button class="btn btn-success">
                        <i class="bi bi-save"></i> Update Book
                    </button>
                </div>

            </form>

        </main>
    </div>
</div>

</body>
</html>