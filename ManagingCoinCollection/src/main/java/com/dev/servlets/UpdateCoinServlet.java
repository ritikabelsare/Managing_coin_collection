package com.dev.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.constants.GenericConstants;
import com.dev.dal.CoinDAL;
import com.dev.models.Coin;

@WebServlet("/UpdateCoinServlet")
public class UpdateCoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateCoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();  
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String country = request.getParameter("country");
			String denomination = request.getParameter("denomination");
			int yearOfMinting = Integer.parseInt(request.getParameter("yearOfMinting"));
			BigDecimal currValue = new BigDecimal(request.getParameter("currentValue"));
			Date acquiredDate = null;
			 try {
	                // Correcting date parsing for acquiredDate
	                acquiredDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("acquiredDate"));
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
			Coin coin = new Coin(id, country, denomination, yearOfMinting, currValue, acquiredDate);

			CoinDAL coinDAL = new CoinDAL();
			if (coinDAL.updateCoin(coin) > 0) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Updating Coin</title>");
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
				out.println("p {");
				out.println("    color: #666666;");
				out.println("}");
				out.println("button {");
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
				out.println("<h1>Coin Updated Successfully</h1>");
				out.println("<p><strong>COIN COUNTRY:</strong> " + coin.getCountry() + "</p>");
				out.println("<p><strong>COIN DENOMINATION:</strong> " + coin.getDenomination() + "</p>");
				out.println("<p><strong>COIN YEAR OF MINTING:</strong> " + coin.getYearOfMinting() + "</p>");
				out.println("<p><strong>COIN CURRENT VALUE:</strong> " + coin.getCurrentValue() + "</p>");
				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = outputFormat.format(coin.getAcquiredDate());
                out.println("<p><strong>COIN ACQUIRED DATE:</strong> " + formattedDate + "</p>");
                out.println("<br>");
                out.println("<button><a href='index.jsp' style='text-decoration: none; color: white;'>Back To Home</a></button>");
                out.println("<button><a href='/ManagingCoinCollection/DisplayAllCoins' style='text-decoration: none; color: white;'>Show All Coins Collection</a></button>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
			} else {
				out.println("<html>");
				out.println("<head> <title>");
				out.println("Updating Coin");
				out.println("</title> </head>");
				out.println("<body>");
				out.println("<h1> Coin Not Updated Successfully </h1>");
				out.println(
						"<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
				out.println("<input type ='button'> <a href = 'index.jsp'> Back To Home </a>");
				out.println("</body>");
				out.println("</html>");
			} 
		} catch (Exception e) {
			out.println("<html>");
			out.println("<head> <title>");
			out.println("Updating a Coin");
			out.println("</title> </head>");
			out.println("<body>");
			out.println("<h1> Error While Updating a Coin </h1>");
			out.println("<p> ERROR : " + e.getMessage() + " </p>");
			out.println("<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
			out.println("<input type ='button'> <a href = 'index.jsp'> Back To Home </a>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
