<%@include file="includes/header.jsp" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>
<form action="menu.do" method="POST">
	<button type="submit" name=choice value="search">Seach / Modify / Delete</button>
	<button type="submit" name=choice value="add">Add</button>
</form>

<%-- <c:if test="${1==1}">
	hi dsafdfsfdsa 
	fdsa
	fdsa
	fdsa
	fdsa
	fdsa
	fasd
</c:if> --%>

<%@include file="includes/footer.jsp" %>