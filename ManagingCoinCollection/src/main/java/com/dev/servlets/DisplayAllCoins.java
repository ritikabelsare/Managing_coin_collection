package com.dev.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dal.CoinDAL;
import com.dev.models.Coin;

@WebServlet("/DisplayAllCoins")
public class DisplayAllCoins extends HttpServlet {  // WE retrinvinf all coins so we need get method
	private static final long serialVersionUID = 1L;

    public DisplayAllCoins() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CoinDAL coinDAL = new CoinDAL();
			List<Coin> allCoins = coinDAL.getAllCoins();
			request.setAttribute("allCoins", allCoins);
			request.getRequestDispatcher("displayAllCoins.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
        
//		response.setContentType("text/html"); 
//		PrintWriter out = response.getWriter();  // html response
//		
//		try {
//			CoinDAL coinDAL = new CoinDAL();
//			List<Coin> allCoins = coinDAL.getAllCoins(); //retriving all the coin object from database
//			if (allCoins != null) {
//				out.println("<html>");
//				out.println("<head> <title>");
//				out.println("Displaying All Coins");
//				out.println("</title> </head>");
//				out.println("<body>");
//				out.println("<h1> All Coins Collections Data </h1>");
//
//				// here we need to add 5 columns and n number of rows - depend on our data
//				out.println("<table border = '1'");
//
//				out.println("<thead>");
//				out.println("<tr>");
//				out.println("<th> COIN ID </th>");
//				out.println("<th> COUNTRY </th>");
//				out.println("<th> DENOMINATION </th>");
//				out.println("<th> YEAR OF MINTING </th>");
//				out.println("<th> CURRENT VALUE </th>");
//				out.println("<th> ACQUIRED DATE </th>");
//				out.println("<th> UPDATE </th>");
//				out.println("<th> REMOVE </th>");
//				out.println("</tr>");
//				out.println("</thead>");
//
//				for (Coin coin : allCoins) {
//					out.println("<tr>");
//					out.println("<td>" + coin.getId() + "</td>");
//					out.println("<td>" + coin.getCountry() + "</td>");
//					out.println("<td>" + coin.getDenomination() + "</td>");
//					out.println("<td>" + coin.getYearOfMinting() + "</td>");
//					out.println("<td>" + coin.getCurrentValue() + "</td>");
//					out.println("<td>" + coin.getAcquiredDate() + "</td>");
//					out.println(
//							"<td>" + "<a href='FetchToUpdate?coinId=" + coin.getId() + "'> EDIT </a>" + "</td>");
//					out.println(
//							"<td>" + "<a href='RemoveCoinServlet?coinId=" + coin.getId() + "'> DELETE </a>" + "</td>"); // we have to pass the id to public int deleteCoin(int coinId); method so do it like this  == giving value to query parameter 
//					out.println("</tr>");
//				}
//
//				out.println("</table>");
//
//				out.println("<br><button> <a href = 'index.html'> Add Another Coin </a> </button>");
//				out.println("<br><br><button> <a href = 'index.html'> Back To Home </a> </button>");
//				out.println("</body>");
//				out.println("</html>");
//			} else {
//				// Not able to Display all coins
//				out.println("<html>");
//				out.println("<head> <title>");
//				out.println("Displaying All Coins");
//				out.println("</title> </head>");
//				out.println("<body>");
//				out.println("<h1> Not able to Retrive the Coin List </h1>");
//				out.println("<input type ='button'> <a href = 'index.html'> Back To Home </a>");
//				out.println("</body>");
//				out.println("</html>");
//			} 
//		} catch (Exception e) {
//			out.println("<html>");
//			out.println("<head> <title>");
//			out.println("Displaying All Coins");
//			out.println("</title> </head>");
//			out.println("<body>");
//			out.println("<h1> Error While Displaying All Coins </h1>");
//			out.println("<p> ERROR : " + e.getMessage() + " </p>");
//			out.println("<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
//			out.println("<input type ='button'> <a href = 'index.html'> Back To Home </a>");
//			out.println("</body>");
//			out.println("</html>");
//		}
//		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
