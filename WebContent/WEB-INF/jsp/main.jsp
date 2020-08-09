<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.Mutter,java.util.List" %>
<% User loginUser = (User) session.getAttribute("loginUser"); %>
<% List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList"); %>
<% String errorMsg = (String) request.getAttribute("errorMsg"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dokotsubu Tweets</title>
</head>
<body>
  <h1>Dokotsubu Main</h1>
  <p>
  Currently logged in as <%= loginUser.getUserName() %>

  <p><a href="/docoTsubu/Logout">Logout</a>

  <p><a href="/docoTsubu/Main">Refresh</a>

  <form action="/docoTsubu/Main" method="POST">
    <input type="text" name="text">
    <input type="submit" value="Tweet!">
  </form>
  <% if (errorMsg != null) { %>
    <p><%= errorMsg %>
  <% } %>

  <% for (Mutter mutter: mutterList) { %>
    <p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
  <% } %>
</body>
</html>