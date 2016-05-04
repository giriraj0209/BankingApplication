
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banking Application</title>
    </head>
    <body>
        <%@ page import ="java.sql.*" %>
        <%
            String userid = request.getParameter("userid");
            String passwd = request.getParameter("passwd");

            bank.BankClient client = new bank.BankClient();

            int returnValue = client.login(userid, passwd);
            if (returnValue == 1) {
                session.setAttribute("userid", userid);
                response.sendRedirect("home.jsp");
            } else {
                response.sendRedirect("index.jsp?displayMessage=Invalid User. Try Again!");
            }
        %>

    </body>
</html>
