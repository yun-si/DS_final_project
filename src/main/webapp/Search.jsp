<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.IOException"%>
<%@page import="Process.Translator"%>
<%@ page import="Process.Keyword"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="BIG5">
	<title>Coffee Master</title>
	<link rel="stylesheet" href="Search.css">
</head>
<body>
<div class="search-div">
	<div class="main-img-div"><img src="cafe.jpg" alt="Cafe" class="main-img"></div>
	<h1 class="heading">Coffee Master</h1>
	<div class="form-div">
		<form id="search-form" name="search-form">
			<div class="search-field-div">
				<input type="text" class="text-field" maxlength="100" name="inputKeyword" id="inputKeyword" placeholder='請輸入關鍵字'> 
			</div>
			<div class="search-button-div">
				<!-- <a href ='http://localhost:8080/CoffeeShopSearch/SearchResult.jsp'> <img src="coffee_search_icon.png" class='search-img'/> </a> -->
				<img src="coffee_search_icon.png" class='search-img' onclick="doSearch()" />
				<%-- javaScript --%>
				<script type="text/javascript">
					function doSearch(){
						
						if(document.getElementById("inputKeyword").value == ""){
							window.alert("Please Input Something!")
						}else{
							window.location.assign("http://localhost:8080/CoffeeShopSearch/SearchResult.jsp")
							// window.alert("getInput")
						}
					}
				</script>
			</div>
		</form>
	</div>
</div>
<%System.out.println("hello"); %>
</body>
</html>