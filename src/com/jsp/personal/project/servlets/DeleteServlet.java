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

public class DeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	List<Employee> employeeList = new ArrayList<>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int deleted = new EmployeeDao().deleteEmployee(Integer.valueOf(req.getParameter("id")));
		req.setAttribute("deleted", deleted);
		employeeList = new EmployeeDao().getEmployees();
		if(employeeList != null) {
			req.setAttribute("employeeList", employeeList);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/employee-list.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
