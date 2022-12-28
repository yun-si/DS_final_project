<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
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
			<input type="submit" value="Search" data-wait="Please wait..." class="submit-button">
			<input type='image' src="coffee_search_icon.png" class='search-img'/>
			</div>
			
		</form>
	</div>
</div>
</body>
</html>