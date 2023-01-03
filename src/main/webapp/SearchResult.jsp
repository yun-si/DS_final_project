<%@page import="java.io.IOException"%>
<%@page import="Process.Translator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Process.Keyword"%>
<%@ page import="Process.QuickSort"%>
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
			<form id="search-form" name="search-form">
				<div class="search-field-div">
				<input type="text" class="text-field" maxlength="100" name="inputKeyword" id="inputKeyword" placeholder='請輸入關鍵字'> 
				</div>
				<div class="search-button-div">
				<a href ='http://localhost:8080/CoffeeShopSearch/SearchResult.jsp'> <img src="coffee_search_icon.png" class='search-img'/> </a>
				</div>
				
			</form>
		</div>
	</div>
	
	<div class="result_div">
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
			
			
			<a href="https://github.com/yun-si/DS_final_project" class="link">https://github.com/yun-si/DS_final_project</a>
			<%
			QuickSort quickSort = new QuickSort();
			String[][] result = (String[][]) quickSort.output();
			
			String[][] resul = new String[10][2];
			String nomad=(String)"Coffee Nomad";
			String nurl=(String)"https://cafenomad.tw";
			result[1][0]=nomad;
			result[1][1]=nurl;
			
			%>
			
			<a href='<%=result[1][1]%>'><%=result[1][0]%></a>
			<br>
			<%
	%>
			
	</div>
	
</body>
</html>