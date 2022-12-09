<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Process.Keyword"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee Master</title>
</head>
<body>
	<div>
		<div>
			search
			<form>
				<input type="text" name="inputKeyword"> <input type="submit"
					value="Search">
			</form>
		</div>
		<div>
			Search Result
			<%
			Keyword keyword = new Keyword(request.getParameter("inputKeyword"), 3);
			System.out.println(keyword.getName()); //showed in console
			%>
		</div>
	</div>
</body>
</html>