<%@include file="includes/header.jsp"%>


<section id="services">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<h1 class="section-heading">ABOUT</h1>
				<!-- <hr class="primary"> -->
			</div>
			<div class="row">
				<h2 text-center>More Information</h2>
				<p>For more detailed information on this project, or to see my other projects, visit <a target="_blank" href="http://www.alexmpeterson.com/projects.php">alexmpeterson.com/HRDatabaseProject.php</a></p>
			</div>
			
			
			<div class="row">
				<h2 text-center>About this project</h2>
				<p>This is my week 8 project at Skill Distillery.  This application is an
				 interface to an employee database that allows the user to create, read,
				  update, or delete employees ( a C.R.U.D.) application.  Technologies 
				  used in this project include Java, Spring MVC (Spring Model View Controller),
				   JDBC (Java Database Connectivity),  mySQL, JSP (Java ServerPages), and JSTL
				    (Java Server Pages Standard Tag Library).  On the front end, a bootstrap 
				    template from startbootstrap.com was used and modified by integrating JSTL 
				    tags and modifying the HTML and CSS to suit the needs of the application.  
				    The picture on the index.jsp page is Toby Flenderson, an HR employee form the
				     TV show The Office.  I attempted to design the site around the brownish
				      colors of his suit.</p>
			</div>
			
			
			<div class="row">
				<h2 text-center>Per Jamie's Instructions: Grading - How I think I did</h2>
				<p>For more detailed information on this project, or to see my other projects, visit <a target="_blank" href="http://www.alexmpeterson.com/projects.php">alexmpeterson.com/HRDatabaseProject.php</a></p>
				<ul>
					<li>HTML/JSP<ul>
					<li><span class="attribute">3 </span>- Reason: For the most part, my data is layout is appropriate.  However, on phones, the tables have an odd view.</li>
					</ul></li>
					
					
					
					<li>CSS<ul>
					<li><span class="attribute">3 </span>- Reason: Most of the CSS is from the bootstrap files, however, I did override some of it in order to style the page how I wanted.  I used special IDs and classes to target certain items that I wanted to override / change, and I sometimes targeted other tags as well (like the body background).</li>
					</ul></li>
					
					
					<li>Spring<ul>
					<li><span class="attribute">4 </span>- Reason: Everything passed between the DAO, the Controller, and the JSP files is an object.</li>
					</ul></li>
					
					
					<li>JSTL<ul>
					<li><span class="attribute">4 </span>- Reason: JSTL conditionals are used to 
					display messages such as "employee added", and loops are iterated through 
					using "forEach" loops to display result data, and dropdown menu options.</li>
					</ul></li>
					
					
					<li>SQL<ul>
					<li><span class="attribute">3 </span>- Reason: CRUD is fully implemented,
					 and the input is sanitized using prepared statements, however I did not
					  use transactions.</li>
					</ul></li>
					
					
					<li>JDBC<ul>
					<li><span class="attribute">4 </span>- Reason: All SQL exceptions are handled,
					 or thrown to a calling method that handles the exception.  I believe all 
					 of my objects and fields are scoped properly.  Everything has private fields
					  with getters and setters, and the helper methods in the DAO are marked 
					  private.</li>
					</ul></li>
					
					
					<li>Aesthetic<ul>
					<li><span class="attribute">3 </span>- Reason: I attempted to make the site's style
					 consistent, using the same colors throughout the pages.  I think the site 
					 is intuitive, however it is not very modern looking</li>
					</ul></li>
					
					
					
					
					
					<li>Object Oriented Programming<ul>
					<li><span class="attribute">3 </span>- Reason: I did not use polymorphism, except for the DAO in the controller.  
					In the case of the results for the Employee Query, I returned the results as lists (inside of a Results object), because 
					it is iterated over and printed out in a JSP file.  Otherwise, objects are mapped from SQL to an Object type.</li>
					</ul></li>
					

					
				</ul>
				
			</div>
			
			


		</div>
	</div>

</section>




<%@include file="includes/footer.jsp"%>