package com.jsp.personal.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jsp.personal.project.models.Employee;
import com.jsp.personal.project.util.ConnectionFactory;

public class EmployeeDao {

	private List<Employee> employeeList = new ArrayList<>();

	public List<Employee> getEmployees() {
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from employee");
			while (result.next()) {
				employeeList.add(
						//the new keyword creates a new employee object for each database row and add it to the list.
						//if you use a specific instance of the employee object you would be able to only add the last row.
						//Because each next row overwrites the previous one.
						new Employee(result.getInt(1), result.getString(2), result.getString(3), result.getString(4)));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}
	public Employee getEmployee(int id) {
		Connection conn = ConnectionFactory.getConnection();
		Employee employee = null;
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from employee where id = " + id);
			while (result.next()) {
				employee = new Employee(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	public int updateEmployee(int id, String name, String dob, String department) {
		List<Employee> employeeList = new ArrayList<>();
		//used as a flag to determine whether update happened or not.
		int updated = 0;
		Connection conn = ConnectionFactory.getConnection();
		employeeList = this.getEmployees();
		String query = "update employee set name = '" + name 
				+ "', dob = '" + dob 
				+ "', department = '" + department + "' where id = " + id ;
		try {
			for(Employee employee: employeeList) {
				//Here we compare the employee id coming from users to those already save in the database. If the matched,
				//the update will happen. Otherwise it will be rejected and the function return a 0 which is is passed to 
				//the front-end to notify the user about the employee id;.
				if(id == employee.getId()) {
					PreparedStatement statement = conn.prepareStatement(query);
					updated = statement.executeUpdate();
					conn.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
	public boolean addEmployee(String name, String dob, String department) {
		Connection conn = ConnectionFactory.getConnection();
		boolean added = false;
		String query = "insert into employee (name, dob, department) values(?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, dob);
			statement.setString(3, department);
			added = statement.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	public int deleteEmployee(int id) {
		Connection conn = ConnectionFactory.getConnection();
		int deleted = 0;
		String query = "delete from employee where id = " + id;
		try {
			Statement statement = conn.createStatement();
			deleted = statement.executeUpdate(query);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}
	

}
