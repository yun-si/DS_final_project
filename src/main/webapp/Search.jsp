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
	<section>
		<div class="container-3">
			<div class="hero-wrapper-two">
				<img src="coffee_shop.jpg" alt="coffee shop">
				<h1 class="heading">Coffee Master</h1>
				<div class="w-form">
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
		</div>
	</section>
</body>
</html>