<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	request.setAttribute("name","Hong");
    	request.setAttribute("address","서울시 강남구");
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		RequestDispatcher dispatch = request.getRequestDispatcher("request2.jsp");
		dispatch.forward(request,response);
	%>

</body>
</html>