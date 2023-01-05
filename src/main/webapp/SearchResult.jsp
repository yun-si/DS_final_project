<%@page import="java.io.IOException"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Process.*"%>
<%@page import="Process.Translator"%>
<%@page import="Process.QuickSort"%>
<%@page import="Process.GoogleQuery"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coffee Master</title>
<link rel="stylesheet" href="SearchResult.css">
</head>
<body>
	<div class="search-div">
		<h1 class="heading">Coffee Master</h1>
		<div class="form-div">
			<form id="search-form" name="search-form" class="form">
				<div class="search-field-div">
					<input type="text" class="text-field" maxlength="100"
						name="inputKeyword" id="inputKeyword" placeholder='請輸入關鍵字'>
				</div>
			</form>
		</div>
	</div>

	<div class="result_div">

		<%
		GoogleQuery g = new GoogleQuery(request.getParameter("inputKeyword"));
		%>

		<%
		String[][] r = (String[][]) request.getAttribute("query");

		for (int i = 0; i < r.length; i++) {
			String s = r[i][1];
		%>
		
		<a class="link" href=<%=s%>><%=r[i][0]%> </a>
		<%
		}
		%>
	</div>

</body>
</html>