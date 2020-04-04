package com.kphu1301.student;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	private StudentService studentService;
	
    public StudentControllerServlet() {
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



	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listStudents(request, response);
		}
		catch (Exception e) {
			throw new ServletException(e);
		}	
	}
	
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		//get students
		List<Student> students = studentService.getStudents();
		request.setAttribute("student_list", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
    	dispatcher.forward(request, response);
	}

}
