<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Library Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- FIXED PATHS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body class="auth-page">

<div class="login-container">
    <div class="login-card">

        <div class="login-header">
            <div class="login-icon">
                <i class="bi bi-book-half"></i>
            </div>
            <h3 class="login-title">Welcome Back</h3>
            <p class="login-subtitle">Please sign in to your library account</p>
        </div>

        <!-- ERROR MESSAGE -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <h6><%= error %></h6>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>

        <!-- SUCCESS MESSAGE (after register) -->
        <%
            String success = request.getParameter("success");
            if ("registered".equals(success)) {
        %>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            Account created successfully! Please login.
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>

        <!-- FORM FIXED -->
        <form action="${pageContext.request.contextPath}/login" method="post">

            <div class="form-floating mb-3">
                <input type="text" name="email" class="form-control" placeholder="Email" required>
                <label><i class="bi bi-person-fill me-2"></i>Email</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <label><i class="bi bi-lock-fill me-2"></i>Password</label>
            </div>

            <button type="submit" class="btn btn-login w-100">
                <i class="bi bi-box-arrow-in-right me-2"></i>Sign In
            </button>

            <div class="text-center mt-3">
                <a href="#" class="forgot-link">Forgot your password?</a>
            </div>

            <div class="divider">
                <span>or</span>
            </div>

            <div class="text-center">
                <p class="mb-0">
                    Don't have an account?
                    <a href="${pageContext.request.contextPath}/registerUser" class="forgot-link">
                        Sign up here
                    </a>
                </p>
            </div>

        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const formControls = document.querySelectorAll('.form-control');

        formControls.forEach(control => {
            control.addEventListener('focus', function() {
                this.parentElement.style.transform = 'translateY(-2px)';
            });

            control.addEventListener('blur', function() {
                this.parentElement.style.transform = 'translateY(0)';
            });
        });
    });
</script>

</body>
</html>