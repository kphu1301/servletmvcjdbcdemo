package com.kphu1301.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class StudentDAO {
	
	private DataSource dataSource;
	
	public StudentDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents(){
		Connection conn = null;
		List<Student> students = new ArrayList<>();
		
		try {
		
			conn = dataSource.getConnection();
		
			Statement sql = conn.createStatement();
			
			String query = "SELECT * FROM student;";
			ResultSet rs = sql.executeQuery(query);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				students.add(new Student(id, firstName, lastName, email));
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
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return students;
	}

	public boolean addStudent(String firstName, String lastName, String email) {
		
		Connection conn = null;
		boolean result = false;
		
		try {
			conn = dataSource.getConnection();
			Statement sql = conn.createStatement();
			String query = "INSERT INTO student "
					+ "(first_name, last_name, email) VALUES ('"
					+ firstName + "','" + lastName + "','" + email + "');";
			sql.execute(query);
			
			result = true;
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public void updateStudent(int id, String firstName, String lastName, String email) {
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			Statement sql = conn.createStatement();
			String query = "UPDATE student SET "
					+	"first_name = '" + firstName + "', last_name = '" 
					+ lastName + "', email = '" + email + "'"
					+ "WHERE id = " + id + ";";
			
			sql.execute(query);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteStudent(int id) {
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			Statement sql = conn.createStatement();
			String query = "DELETE FROM student WHERE id=" + id + ";";
			
			sql.execute(query);
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
		
	}

}
