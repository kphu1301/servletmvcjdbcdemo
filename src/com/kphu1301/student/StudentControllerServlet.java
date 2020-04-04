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
    	doPost(request, response);
   
    }

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		
		if (command == null) {
			command = "LIST";
		}
		
		try {
			switch (command) {
			
			case "LIST":
				listStudents(request, response);
				break;
	
			case "ADD":
				addStudent(request, response);
				break;
				
			case "EDIT":
				editStudent(request, response);
				break;
				
			case "DELETE":
				deleteStudent(request, response);
				break;
			default:
				break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
			
	}


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("student-id"));
		studentService.deleteStudent(id);
		listStudents(request, response);
	}


	private void editStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("student-id"));
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		
		studentService.updateStudent(id, firstName, lastName, email);
		listStudents(request, response);
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		
		studentService.addStudent(firstName, lastName, email);
		response.sendRedirect("StudentControllerServlet");
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		//get students
		List<Student> students = studentService.getStudents();
		request.setAttribute("student_list", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-students.jsp");
    	dispatcher.forward(request, response);
	}

}
