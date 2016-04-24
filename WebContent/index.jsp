<%@include file="includes/header.jsp" %>


<header>
    <div class="header-content">
        <div class="header-content-inner">
            <h1>Human Resources Database</h1>
			<br>
            <form action="menu.do" method="POST">
            <button type="submit" name=choice value="search" class="btn btn-primary btn-xl page-scroll brown-button">Search / Modify / Remove</button>
            <button type="submit" name=choice value="add" class="btn btn-primary btn-xl page-scroll brown-button">Add New Employee</button>
</form>
        </div>
    </div>
</header>



<%@include file="includes/footer.jsp" %>
