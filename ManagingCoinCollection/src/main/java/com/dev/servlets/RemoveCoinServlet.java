package com.dev.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dal.CoinDAL;


@WebServlet("/RemoveCoinServlet")
public class RemoveCoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveCoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int coinId = Integer.parseInt(request.getParameter("coinId"));
		CoinDAL coinDAL = new CoinDAL();
		try {
			if(coinDAL.deleteCoin(coinId)>0) {
//				response.sendRedirect("/ManagingCoinCollection/DisplayAllCoins");  //if we delete the coin the it should redirect to same page
				out.println("<html>");
				out.println("<head> <title>");
				out.println("Deleting Coin");
				out.println("</title> </head>");
				out.println("<body>");
				out.println("<h1> Coin Delete Successfully </h1>");
				out.println("<br><button> <a href = 'index.jsp'> Add Another Coin </a> </button>");
				out.println(
						"<br><br><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
				out.println("</body>");
				out.println("</html>");
			}
			else {
				out.println("<html>");
				out.println("<head> <title>");
				out.println("Removing a Coin");
				out.println("</title> </head>");
				out.println("<body>");
				out.println("<h1> Not able to Remove a Coin </h1>");
				out.println("<br><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
				out.println("<br><input type ='button'> <a href = 'index.jsp'> Back To Home </a>");
				out.println("</body>");
				out.println("</html>");
			}
		}
		catch(NumberFormatException e) {
			out.println("<html>");
			out.println("<head> <title>");
			out.println("Removing a Coin");
			out.println("</title> </head>");
			out.println("<body>");
			out.println("<h1> Error While Deleting a Coin </h1>");
			out.println("<p> ERROR : " + e.getMessage() + " </p>");
			out.println("<BR><button> <a href = '/ManagingCoinCollection/DisplayAllCoins'> Show All Coins Collection </a> </button>");
			out.println("<input type ='button'> <a href = 'index.jsp'> Back To Home </a>");
			out.println("</body>");
			out.println("</html>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
