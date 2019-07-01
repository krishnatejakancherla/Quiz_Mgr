package fr.epita.quiz.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase {

	public static void main(String[] args) throws SQLException {
		
		
		Connection connection = getConnection();
		
		
		search(connection);
		
		//
		

	
		
		PreparedStatement pstmt = connection.prepareStatement("update QUIZ set NAME = ? where name=?");
		pstmt.setString(1,"Test");
		pstmt.setString(2, "Test Quiz");
		pstmt.execute();
		// TODO get the generated ID
		ResultSet rs = pstmt.getGeneratedKeys();
		int id = rs.getInt(1);
		System.out.println("generated ID : " + id);
//		
		
		rs.close();
		pstmt.close();
		connection.close();
		
		
		

	}

	private static void search(Connection connection) throws SQLException {
		String query = "select ID, NAME from QUIZ";
		
		PreparedStatement pstmt = connection.prepareStatement(query);
	
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String topic = rs.getString("NAME");
			System.out.println("id : " + id + " topic:" +  topic);
		}
		
		pstmt.close();
		rs.close();
		connection.close();
	}

	private static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:h2:~/test","sa","");
		System.out.println("Connection Success");
		return connection;
	}

}
