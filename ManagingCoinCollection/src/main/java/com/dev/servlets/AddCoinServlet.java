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

@WebServlet("/AddCoinServlet")
public class AddCoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddCoinServlet() { // constructor
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// to give data to java == > use doPost() method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. set type of response
		response.setContentType("text/html");
		
		//to fetch data - PrintWriter = iostream class  // 2. create Stream
		PrintWriter out = response.getWriter();  // out.println() = to print on browser
		
		// All data of the html page is comming in form of object of request 
		// So now we require that data so create coin object and get all data from request object
		
		try {
			String country = request.getParameter("country");
			String denomination = request.getParameter("denomination");
			int yearOfMinting = Integer.parseInt(request.getParameter("yearOfMinting"));
			BigDecimal currValue = new BigDecimal(request.getParameter("currentValue"));
			
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date acquiredDate = dateFormat.parse(request.getParameter("acquiredDate"));

            Coin coin = new Coin(country, denomination, yearOfMinting, currValue, acquiredDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = outputFormat.format(coin.getAcquiredDate());
			// We have to save the given coin object to database
			CoinDAL coinDAL = new CoinDAL();
			int addCoin = coinDAL.addCoin(coin);
			if (addCoin > 0) {
				// Added successfully  -- Write html code to show on browser that coin is added successfully
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Adding Coin</title>");
				out.println("<style>");
				out.println("body {");
				out.println("    font-family: Arial, sans-serif;");
				out.println("    background-color: #f4f4f4;");
				out.println("    margin: 0;");
				out.println("    padding: 0;");
				out.println("    display: flex;");
				out.println("    justify-content: center;");
				out.println("    align-items: center;");
				out.println("    height: 100vh;");
				out.println("}");
				out.println(".container {");
				out.println("    text-align: center;");
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
				out.println("<h1>Coin Added Successfully</h1>");
				// out.println("<p>COIN ID: " + coin.getId() + "</p>"); // Every time we get 0 as id
				out.println("<p>COIN COUNTRY: " + coin.getCountry() + "</p>");
				out.println("<p>COIN DENOMINATION: " + coin.getDenomination() + "</p>");
				out.println("<p>COIN YEAR OF MINTING: " + coin.getYearOfMinting() + "</p>");
				out.println("<p>COIN CURRENT VALUE: " + coin.getCurrentValue() + "</p>");
				out.println("<p>COIN ACQUIRED DATE: " + formattedDate + "</p>");
				out.println("<br>");
				out.println("<button><a href='index.jsp' style='text-decoration: none; color: white;'>Add Another Coin</a></button>");
				out.println("<button><a href='/ManagingCoinCollection/DisplayAllCoins' style='text-decoration: none; color: white;'>Show All Coins Collection</a></button>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			} else {
				// Not able to add coin in Database
				out.println("<html>");
				out.println("<head> <title>");
				out.println("Adding Coin");
				out.println("</title> </head>");
				out.println("<body>");
				out.println("<h1> Coin Not Added </h1>");
				out.println(
						"<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
				out.println("<input type ='button'> <a href = 'index.jsp'> Try Adding Coin </a>");
				out.println("</body>");
				out.println("</html>");
			} 
		} catch (Exception e) {
			out.println("<html>");
			out.println("<head> <title>");
			out.println("Adding a Coin");
			out.println("</title> </head>");
			out.println("<body>");
			out.println("<h1> Error While Adding a Coin </h1>");
			out.println("<p> ERROR : " + e.getMessage() + " </p>");
			out.println("<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
			out.println("<input type ='button'> <a href = 'index.jsp'> Back To Home </a>");
			out.println("</body>");
			out.println("</html>");
		}
		
		
	}

}
