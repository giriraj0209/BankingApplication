<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"[]>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Banking Application</title>
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" media="screen" />
        <script type="text/javascript" src="jquery.js"></script>
        <script type="text/javascript" src="script.js"></script>
        <link href="css/login.css" rel="stylesheet"  type="text/css" media="screen"/>
        <style>
            body{
background: #4B79A1; /* fallback for old browsers */
background: -webkit-linear-gradient(to left, #4B79A1 , #283E51); /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to left, #4B79A1 , #283E51); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
              }
        </style>
    </head>
<body>
<%
                                                String displayMessage = request.getParameter("displayMessage");
                                                if (displayMessage != null && displayMessage.length() > 0) {
                                                    out.println("<center><h3>" + displayMessage + "</h3></center>");
                                                }
 %>
 <div class="container">
<div class="modal-dialog">
<div class="loginmodal-container">
<h2>Enter Information Here</h2>

<form method="post" action="registration2.jsp">
<input type="text" name="fname" value="" placeholder="First Name"/>
<input type="text" name="lname" value="" placeholder="Last Name "/>
<input type="text" name="userid" value="" placeholder="User ID" />
<input type="password" name="passwd" value="" placeholder="Password"/>
<input type="submit" value="Submit" class="login loginmodal-submit" />
</form>
<div class="login-help">
     Already registered!! <a href="index.jsp">Login Here</a>
</div>
</div>
</div>
 </div>
</body>
</html>

