<%@page import="bank.BankClient"%>
<%
    String custId = request.getParameter("custId");
    BankClient client = new BankClient();

    int returnValue = client.createAccount(custId);

    if (returnValue == 0) {
        response.sendRedirect("createaccount1.jsp?displayMessage=Account Created Successfully!");
    }else if (returnValue == 1){
        response.sendRedirect("createaccount1.jsp?displayMessage=Error: Account already Exists!");
    }else {
        response.sendRedirect("createaccount1.jsp?displayMessage=Error: Exception creating account!");
    }

%>