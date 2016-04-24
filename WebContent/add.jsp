<%@include file="includes/header.jsp" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>

Add:

<c:choose>
	<c:when test="${results.rowsAffected > 0}">
	ADDED.  Add another employee, or return to the main menu.
	<a href="index.jsp">Back to main menu</a>
	</c:when>
	<c:otherwise>
	Add an employee:
	</c:otherwise>


</c:choose>


<form:form action="add.do" name="add" modelAttribute="Employee">
	*First Name:<form:input  path="firstname"  required="required"/>
	Middle Name: <form:input path="middlename" />
	*Last Name: <form:input path="lastname"  required="required"/>
	Gender<form:input path="gender" />
	Email<form:input path="email" />
	Hire Year: <form:input path="hireYear" />
	Hire Month: <form:input path="hireMonth" />
	Hire Day: <form:input path="hireDay" />
	Salary: <form:input path="salary" />
	Commission Percentage: <form:input path="commission_pct" />
	<%-- *Department: <form:input path="department_id" /> --%>
	*Department: 
	 		<form:select path="department_id" >
	 			<option>Select One</option>
	 			<c:forEach var="d" items="${Departments}">
	 				<option value="${d.id}">${d.name}</option>
	 				<%-- <option value="${department.id}">${department.name}</option> --%>
	 			</c:forEach>
	 		</form:select>
	<%-- *Job: <form:input path="job_id" /> --%>
	*Job:
		<form:select path="job_id" >
			<option>Select One</option>
			<c:forEach var="j" items="${Jobs}">
				<option value="${j.id}">${j.name}</option>
			</c:forEach>
		</form:select>
	Address<br>
	Street: <form:input path="address" />
	City: <form:input path="city" />
	State: <form:input path="state" />
	Zip: <form:input path="zipcode" />
	
	<button type=submit>Add Employee</button>
</form:form>


<%@include file="includes/footer.jsp" %>