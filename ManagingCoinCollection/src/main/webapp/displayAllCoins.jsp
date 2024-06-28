<%@page import="com.dev.models.Coin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Displaying All Coins</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007bff;
            text-decoration:none;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>All Coins Collections Data</h1>
    <table>
        <thead>
            <tr>
                <th>COIN ID</th>
                <th>COUNTRY</th>
                <th>DENOMINATION</th>
                <th>YEAR OF MINTING</th>
                <th>CURRENT VALUE</th>
                <th>ACQUIRED DATE</th>
                <th>UPDATE</th>
                <th>REMOVE</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Coin> allCoins = (List<Coin>) request.getAttribute("allCoins");
            if (allCoins != null) {
                for (Coin coin : allCoins) { %>
                    <tr>
                        <td><%= coin.getId() %></td>
                        <td><%= coin.getCountry() %></td>
                        <td><%= coin.getDenomination() %></td>
                        <td><%= coin.getYearOfMinting() %></td>
                        <td><%= coin.getCurrentValue() %></td>
                        <td><%= coin.getAcquiredDate() %></td>
                        <td><a href='FetchToUpdate?coinId=<%= coin.getId() %>'>EDIT</a></td>
                        <td><a href='RemoveCoinServlet?coinId=<%= coin.getId() %>'>DELETE</a></td>
                    </tr>
                <% 
                }
            } else { %>
                <tr>
                    <td colspan="8">No coins found.</td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <button ><a href="index.jsp" style="text-decoration:none; color:white;">Back To Home</a></button>
</body>
</html>
