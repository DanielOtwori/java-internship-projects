<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // If user already logged in → go to dashboard
    if (session != null && session.getAttribute("user") != null) {
        response.sendRedirect("dashboard");
    } else {
        // Otherwise → go to login page
        response.sendRedirect("login.jsp");
    }
%>