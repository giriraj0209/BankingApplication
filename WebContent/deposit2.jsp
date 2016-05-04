<%@page import="bank.BankClient"%>
<%
    String custId = request.getParameter("custId");
    String strAmount = request.getParameter("amount");
    BankClient client = new BankClient();

    int returnValue = client.depositAccount(custId, Double.parseDouble(strAmount));

    if (returnValue == 0) {
        response.sendRedirect("deposit1.jsp?displayMessage=Amount Deposited Successfully!");
    }else if (returnValue == 1){
        response.sendRedirect("deposit1.jsp?displayMessage=Error: Account Does Not Exists!");
    }else {
        response.sendRedirect("deposit1.jsp?displayMessage=Error: Exception creating account!");
    }

%>