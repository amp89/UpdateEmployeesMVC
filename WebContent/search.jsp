<%@include file="includes/header.jsp" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>

Search:

Search by id, first, last

<form:form action="search.do" modelAttribute="EmployeeQuery">
	<form:input type="text" path="id" />
	<form:input type="text" path="firstname" />
	<form:input type="text" path="lastname" />
	
	<button type="submit" value="search">Search</button>

</form:form>


<c:choose>
<c:when test="${!empty(results.rowsReturned)}">

<c:forEach var="row" items="${results.rowsReturned}">
	<br>
	<c:forEach var="item" items="${row}">
		${item}
	</c:forEach>

</c:forEach>


</c:when>
<c:otherwise>
	nothing found
</c:otherwise>
</c:choose>



<%@include file="includes/footer.jsp" %>