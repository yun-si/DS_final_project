<%@page import="java.io.IOException"%>
<%@page import="Process.Translator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Process.Keyword"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Coffee Master</title>
	<link rel="stylesheet" href="SearchResult.css">
</head>
<body class="body">
	<div class="search-div">
		<h1 class="heading">Coffee Master</h1>
		<div class="form-div">
			<form id="search-form" name="search-form">
				<input type="text" class="text-field" maxlength="100" name="inputKeyword" id="inputKeyword"> 
				<input type="submit" value="Search" data-wait="Please wait..." class="submit-button">
			</form>
		</div>
	</div>
	<div class="search_result_div">
			<%
			Keyword keyword = new Keyword(request.getParameter("inputKeyword"), 3);
			//System.out.println(keyword.getName()); //showed in console
			String keywordName = keyword.getName();
			Translator translator = new Translator(keywordName);
			try{
				System.out.println("Translated text: " + translator.translate("en", "zh-TW"));
			}
			catch(IOException e){
				System.out.println("IOException");
			}catch(Exception e){
				System.out.println("Other Exception");
			}
			%>
			<a href="#" class="link">link text</a>
	</div>
	
</body>
</html>