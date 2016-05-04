<%@page import="bank.BankClient"%>
<%
    String custId = request.getParameter("custId");
    String strAmount = request.getParameter("amount");
    BankClient client = new BankClient();

    int returnValue = client.withdrawAccount(custId, Double.parseDouble(strAmount));

    if (returnValue == 0) {
        response.sendRedirect("withdraw1.jsp?displayMessage=Amount withdrawn Successfully!");
    } else if (returnValue == 1) {
        response.sendRedirect("withdraw1.jsp?displayMessage=Error: Account Does Not Exists!");
    } else if (returnValue == 2) {
        response.sendRedirect("withdraw1.jsp?displayMessage=Error: Insufficient Balance!");
    } else {
        response.sendRedirect("withdraw1.jsp?displayMessage=Error: Exception creating account!");
    }

%>