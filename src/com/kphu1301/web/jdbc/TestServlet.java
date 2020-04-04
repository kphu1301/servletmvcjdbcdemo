package com.kphu1301.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
    public TestServlet() {
        super();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// step 1: set up printwriter
    	PrintWriter out = response.getWriter();
    	response.setContentType("text/plain");
    	
    	// step 2: get connection to db
    	Connection conn = null;
    	
    	try {
    		
    		System.out.println("Trying Connnection...");
    		conn = dataSource.getConnection();
    		
    		System.out.println("Got Connnection!");
    		//step 3: create sql statements
    		String query = "SELECT * FROM student;";
        	Statement sql = conn.createStatement();
        	
        	//step 4: execute sql query
        	ResultSet rs = sql.executeQuery(query);
        	
        	//step 5: process result set        	
        	while (rs.next()) {
        		String email = rs.getString("email");
        		out.println(email);
        	}
        	
        
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	finally {
    		if (conn != null) {
    			try {
    				conn.close();
    			}
    			catch(SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	//
    }

}
