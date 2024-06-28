<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>COIN COLLECTION MANAGING SYSTEM</title>
	 <link rel ="stylesheet" href="style.css">
</head>

<body>
	<h1> COIN COLLECTION APPLICATION</h1>
	<br><br>
	<form action="AddCoinServlet" method="post">
		<table >
			<tr>
				<td><label>Country : </label></td>
				<td><input type="text" name="country"
						required> <!-- name should be same as written in the AddCoinServlet as parameter => String country = request.getParameter("country");-->
				</td>
			</tr>
			<tr>
				<td><label>Denomination : </label></td>
				<td><input type="text" name="denomination" required></td>
			</tr>
			<tr>
				<td><label>Year of Minting : </label></td>
				<td><input type="number" name="yearOfMinting" required></td>
			</tr>
			<tr>
				<td><label>Current Value : </label></td>
				<td><input type="text" name="currentValue" required></td>
			</tr>
			<tr>
				<td><label>Acquired Date : </label></td>
				<td><input type="date" name="acquiredDate" required></td>
			</tr>

		</table>
			
		<br><input type="submit" value="Add Coin">
		<br><br><button> <a href ='/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>  
		<!-- After adding coin there will be url is : http://localhost:8080/ManagingCoinCollection/AddCoinServlet -->
	</form>
	<br>
	<a href="index.jsp">Back To Home</a>
</body>

</html>