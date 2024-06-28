package com.dev.dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dev.models.Coin;
import com.dev.utils.ConnectionUtils;

public class CoinDAL  { 
	//CoinDAO  // data access layer/data access object   - used to access data from the database
	// for one row there is one coin java object
	// so we need list of java objects - for every rows 
	public List<Coin> getAllCoins(){ // get the coins from database to java class i.e. - Coin which is stored in database in one row to Object of the Java Class (stored in list form)
		List<Coin> coinsList = new ArrayList<>();  // we not need to write generics at object side i.e at ArrayList<> ==> After Java 8 ==> TYPEINFERENCE
		try(Connection connection = ConnectionUtils.getConnection()){	// Not need to close connection
			
			Statement stmt = connection.createStatement();
			String selectQuery = "select * from coin_collection";
			ResultSet resultSet = stmt.executeQuery(selectQuery);  // It maintain a cursor pointing to the current row of the data
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");  // int id = resultSet.getInt(0);  // Also get by column index
				String country = resultSet.getString("country"); 
				String denomination = resultSet.getString("denomination"); 
				int yearOfMinting = resultSet.getInt("year_of_minting"); 
				BigDecimal currValue = resultSet.getBigDecimal("current_value"); 
				Date aquiredDate = resultSet.getDate("acquired_date"); 
				
				Coin coin = new Coin(id, country, denomination, yearOfMinting, currValue, aquiredDate);
				
				coinsList.add(coin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle properly in real time project
		}
		return coinsList;
	}

	public int addCoin(Coin coin) {  // Adding coin in Databases
		try(Connection connection = ConnectionUtils.getConnection()){
			String insertQuery = "insert into coin_collection (country, denomination, year_of_minting, current_value, acquired_date) values (?,?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insertQuery);
			pstmt.setString(1, coin.getCountry()); // Java object to relational/database 
			pstmt.setString(2, coin.getDenomination());
			pstmt.setInt(3, coin.getYearOfMinting());
			pstmt.setBigDecimal(4, coin.getCurrentValue());
			pstmt.setDate(5, new Date(coin.getAcquiredDate().getTime()));  
			return pstmt.executeUpdate();  // Execute query
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
//	public static void main(String[] args) {   We have do it by html
////		new CoinDAL().addCoin(new Coin("India", "INR", 1867, new BigDecimal(1000), new java.util.Date()));
//		System.out.println(new CoinDAL().getAllCoins());
//	}
	
	public Coin getById(int coinId){ 
		Coin coin = new Coin();
		String selectByIdQuery = "select * from coin_collection where id = ?"; 
		try(Connection connection = ConnectionUtils.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectByIdQuery)){
			pstmt.setInt(1, coinId);
			ResultSet resultSet = pstmt.executeQuery(); // we not need to give query here repeatly it will show error
			while(resultSet.next()) {
				coin.setId(resultSet.getInt("id"));
				coin.setCountry(resultSet.getString("country"));
				coin.setDenomination(resultSet.getString("denomination"));
				coin.setYearOfMinting(resultSet.getInt("year_of_minting")); 
				coin.setCurrentValue(resultSet.getBigDecimal("current_value")); 
			}

//			while(resultSet.next()){
//				coin.setCountry(resultSet.getString("country"));
//				coin.setDenomination(resultSet.getString("denomination"));
//				coin.setYearOfMinting(resultSet.getInt("year_of_minting")); 
//				coin.setCurrentValue(resultSet.getBigDecimal("current_value")); 
//			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle properly in real time project
			coin = null;
		}
		return coin;
	}
	
	public int updateCoin(Coin coin) {  // Adding coin in Databases
		int updatedRows = 0;
		try(Connection connection = ConnectionUtils.getConnection()){
			String updateCoinQuery = "update coin_collection set country=?, denomination=?, year_of_minting=?, current_value=?, acquired_date=? where id=?";
			PreparedStatement pstmt = connection.prepareStatement(updateCoinQuery);
			pstmt.setString(1, coin.getCountry()); // Java object to relational/database 
			pstmt.setString(2, coin.getDenomination());
			pstmt.setInt(3, coin.getYearOfMinting());
			pstmt.setBigDecimal(4, coin.getCurrentValue());
			pstmt.setDate(5, new Date(coin.getAcquiredDate().getTime()));  
			pstmt.setInt(6, coin.getId());
//			System.out.println("Coin Updated successfully");
			updatedRows = pstmt.executeUpdate();  // Execute query
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRows;
	}
	
	public int deleteCoin(int coinId){ 
		int deletedRow = 0;
		try(Connection connection = ConnectionUtils.getConnection()){
			String deleteCoinQuery = "delete from coin_collection where id=?";
			PreparedStatement pstmt = connection.prepareStatement(deleteCoinQuery);
			pstmt.setInt(1, coinId);
			deletedRow = pstmt.executeUpdate();  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletedRow;
	}

	
	// For update we firstly required to fetch all data from the database and then update that data and save them on database
	
}
