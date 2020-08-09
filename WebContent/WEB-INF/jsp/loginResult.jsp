<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dokotsubu Login Result</title>
</head>
<body>
<% if (loginUser != null) { %>
  <p>Login Successful</p>
  <p>Welcome, <%= loginUser.getUserName() %>!</p>
  <a href="/docoTsubu/Main">Go to Tweets</a>
<% } else { %>
  <p>Login Failed.</p>
  <a href="/docoTsubu">Go to TOP</a>
<% } %>
</body>
</html>