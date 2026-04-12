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

        <!--  CONNECTED FORM -->
        <form action="${pageContext.request.contextPath}/registerUser" method="post">

            <div class="form-floating">
                <input type="text" class="form-control" name="firstName" placeholder="First Name" required>
                <label><i class="bi bi-person me-2"></i>First Name</label>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" name="lastName" placeholder="Last Name" required>
                <label><i class="bi bi-person me-2"></i>Last Name</label>
            </div>

            <div class="form-floating">
                <input type="email" class="form-control" name="email" placeholder="Email" required>
                <label><i class="bi bi-envelope me-2"></i>Email</label>
            </div>

            <div class="form-floating">
                <input type="password" class="form-control" name="password" id="password" required>
                <label><i class="bi bi-lock me-2"></i>Password</label>
            </div>

            <!--  add name attribute -->
            <div class="form-floating">
                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
                <label><i class="bi bi-lock me-2"></i>Confirm Password</label>
            </div>

            <button type="submit" class="btn btn-login mt-3">
                <i class="bi bi-check-circle me-2"></i>Create Account
            </button>

            <div class="text-center mt-3">
                <p class="mb-0">
                    Already have an account?
                    <!-- LINK -->
                    <a href="${pageContext.request.contextPath}/login.jsp" class="login-link">
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