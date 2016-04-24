<%@include file="includes/header.jsp"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>


<section class="bg-primary" id="about">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2 text-center">
				<h2 class="section-heading">Employee Search</h2>
				<hr class="light">
				<p class="text-faded">

					<form:form action="search.do" modelAttribute="EmployeeQuery">
					Search by one or more of the following parameters:<br>
						&nbsp;&nbsp;ID: <form:input type="text" path="id" size="6"/><br>
						First:<form:input type="text" path="firstname" /><br>
						&nbsp;Last:<form:input type="text" path="lastname" /><br>


						<button type="submit" value="search"
							class="btn btn-default btn-xl custom-submit">Search
							Employee Database</button>

					</form:form>



				</p>
			</div>
		</div>
	</div>
</section>

<section id="services">
	<div class="container">

		<c:choose>
			<c:when test="${!empty(message)}">

	${message}


</c:when>

			<c:when test="${!empty(results.rowsReturned)}">

				<c:forEach var="row" items="${results.rowsReturned}">


					<div class="row result-border">
						<div class="col-lg-12 text-center">
							<h2 class="section-heading">Employee ID# ${row.get(0)}</h2>
							<!-- <hr class="primary"> -->
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Name:</span> ${row.get(1)} ${row.get(2)}
								${row.get(3)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Gender: </span>${row.get(4)}
							</div>





							<div class="col-xs-2 col-sm-6 col-md-4">
								<span class="attribute">Extension:</span> ${row.get(6)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Salary:</span> ${row.get(8)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Commission Percent:</span>${row.get(9)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Department:</span> ${row.get(10)}
							</div>



							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Job: </span> ${row.get(11)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Email</span> ${row.get(5)}
							</div>



							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Street: </span> ${row.get(12)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">City:</span> ${row.get(13)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">State:</span> ${row.get(14)}
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Zip</span> ${row.get(15)}
							</div>

							<div class="col-xs-12 col-sm-6 col-md-4">
								<span class="attribute">Hired: </span> ${row.get(7)}
							</div>


						</div>


						<div class="row">
							<div class="col-xs-6 col-sm-3 col-sm-offset-3">
								<form action="modifyEmployee.do">
									<input type="hidden" name="idToModify" value="${row.get(0)}" />
									<button type="submit" name="choice" value="modify"
										class="btn btn-default btn-xl mod-delete">Modify</button>
								</form>
							</div>
								<div class="col-xs-6 col-sm-3">
							<form action="deleteEmployee.do" method="POST">
									<input type="hidden" name="idToModify" value="${row.get(0)}" />
									<button class="btn btn-default btn-xl mod-delete" type="submit">Delete</button>
							</form>
								</div>
								
						</div>
					</div>
	</c:forEach>
	</div>


	</c:when>
	<c:otherwise>
	No employee found - please check the search conditions.
</c:otherwise>
	</c:choose>
</section>



<%@include file="includes/footer.jsp"%>