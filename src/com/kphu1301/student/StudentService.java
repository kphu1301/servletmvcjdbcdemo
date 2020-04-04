package com.kphu1301.student;

import java.util.List;

import javax.sql.DataSource;

public class StudentService {
	
	private StudentDAO studentDAO;
	
	public StudentService(DataSource dataSource) {
		studentDAO = new StudentDAO(dataSource);
	}
	
	public List<Student> getStudents(){
		return studentDAO.getStudents();
	}
	
	public boolean addStudent(String firstName, String lastName, String email) {
		return studentDAO.addStudent(firstName, lastName, email);
	}

	public void updateStudent(int id, String firstName, String lastName, String email) {
		studentDAO.updateStudent(id, firstName, lastName, email);
	}

	public void deleteStudent(int id) {
		studentDAO.deleteStudent(id);
		
	}

}
