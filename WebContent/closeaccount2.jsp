<%@page import="bank.BankClient"%>
<%
    String custId = request.getParameter("custId");
    BankClient client = new BankClient();

    int returnValue = client.closeAccount(custId);

    if (returnValue == 0) {
        response.sendRedirect("closeaccount.jsp?displayMessage=Account Closed Successfully!");
    } else if (returnValue == 1) {
        response.sendRedirect("closeaccount.jsp?displayMessage=Error: Exception!");
    } 

%>