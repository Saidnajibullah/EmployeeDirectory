package com.jsp.personal.project.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.personal.project.dao.EmployeeDao;
import com.jsp.personal.project.models.Employee;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	List<Employee> employeeList = new ArrayList<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String username = "student";
//		String password = "student";
//		String url = "jdbc:mysql://localhost:3306/employeeDirectory";
//		String driver = "com.mysql.cj.jdbc.Driver";
//		try {
//			PrintWriter writer = resp.getWriter();
//			writer.println("connecting to " + url);
//			Class.forName(driver);
//			Connection conn = (Connection) DriverManager.getConnection(url, username, password);
//			writer.println("Connection successull");
//			conn.close();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// This list is assigned to a new instance of EmployeeDao. It creates a new instance whenever a new employee is
		//added and then queries the database for the latest updates.
		employeeList = new EmployeeDao().getEmployees();
		if(employeeList != null) {
			req.setAttribute("employeeList", employeeList);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/employee-list.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String department = req.getParameter("dept");
		String submit = req.getParameter("submit");
		
		//since we use a single form for both adding and updating, we have two submit buttons and based on the value of
		//each we determine the desired task to be done by calling the appropriate function. 
		if(submit.equals("Add Employee")) {
			
			boolean added = new EmployeeDao().addEmployee(name, dob, department);
			this.doGet(req, resp);
			
			//****** These comments and code are related UI where we had an Employee ID field.******
			//Since the id input is not need for adding a new employee and the it is created randomly, here we check whether
			//this field has a value or not. If it's was empty, the new employee will be added. Otherwise, it's value will
			// to notify the user that the id is not needed. 
//			if(id.equals("")) {
//				boolean added = new EmployeeDao().addEmployee(name, dob, department);
//				req.setAttribute("added", added);
//			}else {
//				req.setAttribute("id", id);
//			}
//			this.doGet(req, resp);
		}if(submit.equals("Update Employee")){
			
			
			if(id.equals("")) {
				id = "noId";
				req.setAttribute("noId", id);
			}else {
				int updated = new EmployeeDao().updateEmployee(Integer.valueOf(id), name, dob, department);
				req.setAttribute("updated", updated);
			}
			this.doGet(req, resp);
		}
		
	}
}
