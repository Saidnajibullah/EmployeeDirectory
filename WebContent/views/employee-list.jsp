<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.container {
	padding-top: 50px;
}

a {
	text-decoration: none;
}

table, th, td {
	text-align: center;
}
label{
 font-weight: bold;
}
</style>
<!-- to use bootstrap 4, search for bootstrap 4 unpkg, shoose the bootstrap.min.css, select view raw and then copy and past the url here -->
<link
	href="https://unpkg.com/bootstrap@4.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-md-4">

				<form action="Controller" method="post">
					<div class="form-group">
						<!-- <label for="id">Employee ID</label>  -->
						<input type="hidden" value = "${employee.id}"
							class="form-control" id="id" name="id" placeholder="Number only">
					</div>
					<div class="form-group">
						<label for="name">Employee Name</label> <input type="text" value = "${employee.name}"
							class="form-control" id="name" name="name" required>
					</div>
					<div class="form-group">
						<label for="dob">Employee Date of Birth</label> <input type="date" value = "${employee.dob}"
							class="form-control" id="dob" name="dob" required>
					</div>
					<div class="form-group">
						<label for="dept">Employee Department</label> <input type="text" value = "${employee.department}"
							class="form-control" id="dept" name="dept" required>
					</div>
					<input type="submit" class="btn btn-primary mb-2"
						value="Add Employee" name="submit"> <input type="submit"
						class="btn btn-primary mb-2" value="Update Employee" name="submit">
				</form>
			</div>
			<div class="col-sm-12 col-md-8">
				<h1 class="h1">Welcome to Employee Directory</h1>
				<!-- <p class="text-info">Note: to add an employee, ID is not required but it's required for updating</p> -->
				<br>
				
				<!-- These lines of code were used when the Employee ID field was not hidden -->
				<c:if test="${noId.equals('noId')}">
					<p style="color: red">You cannot update an employee before adding</p>
				</c:if>
			<%-- 	<c:if test="${updated == 0}">
					<p style="color: red">The Employee ID does not match</p>
				</c:if>
				<c:if test="${id != null}">
					<p style="color: red">You do not need to add employee ID; it's
						created randomly</p>
				</c:if> --%>
				<hr>
				<table class="table table-striped table-bordered">
					<tr>
						<th>Employee Name</th>
						<th>Date of Birth</th>
						<th>Department</th>
						<th>Delete | Edit</th>
					</tr>

					<c:forEach items="${employeeList}" var="employee">
						<tr>
							<td>${employee.name}</td>
							<td>${employee.dob}</td>
							<td>${employee.department}</td>
							<td style="text-align: center"><a
								href="DeleteServlet?id=${employee.getId()}">Delete</a>
								<a
								href="EditServlet?id=${employee.getId()}"> | Edit</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<hr>

			<hr>
		</div>
	</div>
</body>
</html>