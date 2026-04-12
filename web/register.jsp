<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Library Management</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body class="auth-page">

<div class="login-container">
    <div class="login-card">

        <div class="login-header">
            <div class="login-icon">
                <i class="bi bi-person-plus-fill"></i>
            </div>
            <h3 class="login-title">Join Us Today</h3>
            <p class="login-subtitle">Create your library account</p>
        </div>

        <!-- SUCCESS / ERROR MESSAGE -->
        <%
            String success = request.getParameter("success");
            String error = request.getParameter("error");
        %>

        <% if (success != null) { %>
        <div class="alert alert-success">Account created successfully!</div>
        <% } %>

        <% if (error != null) { %>
        <div class="alert alert-danger">Registration failed. Try again.</div>
        <% } %>

        <!-- FORM -->
        <form action="${pageContext.request.contextPath}/registerUser" method="post">

            <div class="form-floating mb-2">
                <input type="text" class="form-control" name="firstName" placeholder="First Name" required>
                <label>First Name</label>
            </div>

            <div class="form-floating mb-2">
                <input type="text" class="form-control" name="lastName" placeholder="Last Name" required>
                <label>Last Name</label>
            </div>

            <div class="form-floating mb-2">
                <input type="email" class="form-control" name="email" placeholder="Email" required>
                <label>Email</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" class="form-control" name="password" id="password" required>
                <label>Password</label>
            </div>

            <div class="form-floating mb-2">
                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
                <label>Confirm Password</label>
            </div>

            <button type="submit" class="btn btn-primary w-100 mt-3">
                Create Account
            </button>

            <div class="text-center mt-3">
                <p>
                    Already have an account?
                    <a href="${pageContext.request.contextPath}/login.jsp">
                        Sign in
                    </a>
                </p>
            </div>

        </form>
    </div>
</div>

<script>
    const password = document.getElementById("password");
    const confirm = document.getElementById("confirmPassword");

    confirm.addEventListener("input", function () {
        if (password.value !== confirm.value) {
            confirm.setCustomValidity("Passwords do not match");
        } else {
            confirm.setCustomValidity("");
        }
    });
</script>

</body>
</html>