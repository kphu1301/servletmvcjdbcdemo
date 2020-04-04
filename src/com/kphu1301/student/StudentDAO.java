package com.kphu1301.student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
			String sql = "SELECT * FROM student;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
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

	public void addStudent(String firstName, String lastName, String email) {
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO student (first_name, last_name, email) "
					+ "VALUES (?, ? ,?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			
			ps.executeUpdate();
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

	public void updateStudent(int id, String firstName, String lastName, String email) {
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE student SET first_name = ?, last_name = ?, email = ?"
					+ " WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setInt(4, id);
			ps.executeUpdate();
			
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
			String sql = "DELETE FROM student WHERE id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
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
