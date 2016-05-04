<%@ page import ="java.sql.*" %>
<%
    String userId = request.getParameter("userid");
    String password = request.getParameter("passwd");
    String firstName = request.getParameter("fname");
    String lastName = request.getParameter("lname");

    bank.BankClient client = new bank.BankClient();

    int returnValue = client.registerUser(firstName, lastName, userId, password);

    if (returnValue == 1) {
        response.sendRedirect("registration1.jsp?displayMessage=Error: User ID already exist!");
    } else if (returnValue == 2) {
        response.sendRedirect("registration1.jsp?displayMessage=Exception: When registering!");
    } else {
        response.sendRedirect("index.jsp?displayMessage=Successfully Registered! Login Here!");
    }
%>