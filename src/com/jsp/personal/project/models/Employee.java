package com.jsp.personal.project.models;
public class Employee {
	private int id;
	private String name;
	private String dob;
	private String department;
	
	

	public Employee(int id, String name, String dob, String department) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + ", department=" + department + "]";
	}

}
