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
				<div class="search-field-div">
				<input type="text" class="text-field" maxlength="100" name="inputKeyword" id="inputKeyword" placeholder='請輸入關鍵字'> 
				</div>
				<div class="search-button-div">
				<input type="submit" value="Search" data-wait="Please wait..." class="submit-button">
				<input type='image' src="coffee_search_icon.png" class='image'/>
				</div>
				
			</form>
		</div>
	</div>
	<div class="search_result_div">
			<%
			Keyword keyword = new Keyword(request.getParameter("inputKeyword"), 3);
			String keywordName = keyword.getName();
			Translator translator = new Translator();
			
			try{
				System.out.println("Translated text: " + translator.translate("", "zh-TW", keywordName));
			}
			catch(IOException e){
				System.out.println("IOException");
			}catch(Exception e){
				System.out.println("");
			}
			%>
			<a href="#" class="link">link text</a>
	</div>
	
</body>
</html>