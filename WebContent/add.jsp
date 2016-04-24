<%@include file="includes/header.jsp" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
    <section class="bg-primary" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                <h2 class="section-heading">Add Employees</h2>
                    <hr class="light">

<c:choose>
	<c:when test="${results.rowsAffected > 0}">
	ADDED.  Add another employee, or return to the main menu.
	<a href="index.jsp">Back to main menu</a>
	</c:when>
	<c:otherwise>
	Add an employee:
	</c:otherwise>


</c:choose>

<div class="row">
<div class="col-sm-8 col-offset-sm-3">
<form:form action="add.do" name="add" modelAttribute="Employee">
*=required
	<table>
	<tr>
		<td>*First Name:</td>
		<td><form:input  path="firstname"  required="required"/></td>
		<td>Middle Name:</td>
		<td><form:input path="middlename" /></td>
	</tr>

	
	<tr>
		<td>*Last Name:</td>
		<td><form:input path="lastname"  required="required"/></td>
		<td>Gender:</td>
		<td><form:input path="gender" /></td>
	</tr>
	

	
	<tr>
		<td>Email:</td>
		<td><form:input path="email" /></td>
		<td>Extention:</td>
		<td><form:input path="extention" /></td>
	</tr>
	

	<tr>
		<td>Hire Year:</td>
		<td><form:input path="hireYear" /></td>
		<td>Hire Month:</td>
		<td><form:input path="hireMonth" /></td>
	</tr>
	

	
	<tr>
		<td>Hire Day:</td>
		<td><form:input path="hireDay" /></td>
		<td>Salary:</td>
		<td><form:input path="salary" /></td>
	</tr>
	
	
	
	<tr>
		<td>Commission %</td>
		<td><form:input path="commission_pct" /></td>
	</tr>
	
	<tr>
		<td>*Department</td>
		<td><form:select path="department_id" >
	 			<option>Select One</option>
	 			<c:forEach var="d" items="${Departments}">
	 				<option value="${d.id}">${d.name}</option>
	 				<%-- <option value="${department.id}">${department.name}</option> --%>
	 			</c:forEach>
	 		</form:select></td>
		<td>*Job</td>
		<td><form:select path="job_id" >
			<option>Select One</option>
			<c:forEach var="j" items="${Jobs}">
				<option value="${j.id}">${j.name}</option>
			</c:forEach>
		</form:select></td>
	</tr>
	

	<tr>
		<td>Street:</td>
		<td><form:input path="address" /></td>
		<td>City:</td>
		<td><form:input path="city" /></td>
	</tr>
	

	
	<tr>
		<td>State:</td>
		<td><form:input path="state" /></td>
		<td>Zip Code:</td>
		<td><form:input path="zipcode" /></td>
	</tr>
	
	
	

	
	
	</table>
	
	<button class="btn btn-default btn-xl custom-submit" type=submit>Add Employee</button>
</form:form>
</div>
                </div> <!-- row -->
            </div>
        </div>
        
    </div>
    </section>



<%@include file="includes/footer.jsp" %>