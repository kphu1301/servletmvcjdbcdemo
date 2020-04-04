package com.kphu1301.student;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentEditControllerServlet")
public class StudentEditControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private StudentService studentService;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	public StudentEditControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentService = new StudentService(dataSource);
		}			
		catch (Exception e) {
			throw new ServletException(e);
		}
	}
	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("student-id"));
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		
		studentService.updateStudent(id, firstName, lastName, email);
		response.sendRedirect("StudentControllerServlet");
	}

}
