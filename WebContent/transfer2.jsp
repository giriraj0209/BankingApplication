<%@page import="bank.BankClient"%>
<%
    String custId1 = request.getParameter("custId1");
    String custId2 = request.getParameter("custId2");
    String strAmount = request.getParameter("amount");
    BankClient client = new BankClient();
    if (custId1.equals(custId2)) {
        response.sendRedirect("transfer1.jsp?displayMessage=Select Customer 1 & 2 different!");
    } else {
        int returnValue = client.transferAmount(custId1, custId2, Double.parseDouble(strAmount));

        if (returnValue == 0) {
            response.sendRedirect("transfer1.jsp?displayMessage=Amount Transferred Successfully!");
        } else if (returnValue == 1) {
            response.sendRedirect("transfer1.jsp?displayMessage=Error: Customer 1 account does not exist!");
        } else if (returnValue == 2) {
            response.sendRedirect("transfer1.jsp?displayMessage=Error: Customer 2 account does not exist!");
        } else if (returnValue == 3) {
            response.sendRedirect("transfer1.jsp?displayMessage=Error: Exception!");
        }
    }

%>