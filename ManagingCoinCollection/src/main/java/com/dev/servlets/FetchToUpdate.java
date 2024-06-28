package com.dev.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dal.CoinDAL;
import com.dev.models.Coin;

@WebServlet("/FetchToUpdate")
public class FetchToUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FetchToUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int coinId = Integer.parseInt(request.getParameter("coinId"));
		CoinDAL coinDAL = new CoinDAL();
		Coin coinToUpdate = coinDAL.getById(coinId);
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Fetch to Update Coin</title>");
			out.println("<style>");
			out.println("body {");
			out.println("    font-family: Arial, sans-serif;");
			out.println("    background-color: #f4f4f4;");
			out.println("    margin: 0;");
			out.println("    padding: 0;");
			out.println("}");
			out.println(".container {");
			out.println("    max-width: 600px;");
			out.println("    margin: 50px auto;");
			out.println("    background-color: #ffffff;");
			out.println("    padding: 20px;");
			out.println("    border-radius: 10px;");
			out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
			out.println("}");
			out.println("h1 {");
			out.println("    color: #333333;");
			out.println("}");
			out.println("form {");
			out.println("    margin-top: 20px;");
			out.println("}");
			out.println("label {");
			out.println("    font-weight: bold;");
			out.println("}");
			out.println("input[type='text'], input[type='number'], input[type='date'] {");
			out.println("    width: 100%;");
			out.println("    padding: 8px;");
			out.println("    margin-top: 5px;");
			out.println("    margin-bottom: 10px;");
			out.println("    border: 1px solid #ccc;");
			out.println("    border-radius: 4px;");
			out.println("    box-sizing: border-box;");
			out.println("}");
			out.println("input[type='submit'], button {");
			out.println("    background-color: #007bff;");
			out.println("    color: white;");
			out.println("    padding: 10px 20px;");
			out.println("    border: none;");
			out.println("    cursor: pointer;");
			out.println("    border-radius: 4px;");
			out.println("}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='container'>");
			out.println("<h1>Update Coin Details</h1>");
			
			if(coinToUpdate != null) {
				out.println("<form action='UpdateCoinServlet' method='post'>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td><label>Coin ID : </label></td>");
				out.println("<td><input type='hidden' name='id' value="+ coinToUpdate.getId()+"></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label>Country : </label></td>");
				out.println("<td><input type='text' name='country' value="+ coinToUpdate.getCountry()+" required></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label>Denomination : </label></td>");
				out.println("<td><input type='text' name='denomination'  value= "+ coinToUpdate.getDenomination()+ " required></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label>Year of Minting : </label></td>");
				out.println("<td><input type='number' name='yearOfMinting' value="+ coinToUpdate.getYearOfMinting()+" required></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label>Current Value : </label></td>");
				out.println("<td><input type='text' name='currentValue' value="+ coinToUpdate.getCurrentValue()+" required></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td><label>Acquired Date : </label></td>");
				out.println("<td><input type='date' name='acquiredDate' value="+ coinToUpdate.getAcquiredDate()+" required></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<br><input type='submit' value='Update Coin'>");
			}
			else {
				out.println("<h1> Not able to Fetch a Coin for Update</h1>");
			}
		}
		catch(NumberFormatException e) {
			out.println("<h1> Error While Fetch a Coin For Update</h1>");
			out.println("<p> ERROR : " + e.getMessage() + " </p>");

		}
		out.println("<br><button> <a href = 'index.jsp'> Back to Home </a> </button>");
		out.println(
				"<br><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
