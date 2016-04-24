package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class EmployeeSQLDAO implements EmployeeDAO {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/companydb?zeroDateTimeBehavior=convertToNull";;
	private static final String USR = "student";
	private static final String PWD = "student";

	// This class is autowired in the controller
	@PostConstruct
	public void init() {
	}

	@Override
	public Results getEmployees(EmployeeQuery employeeQuery) {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		// init connection, prepared statement, result set, and metadata
		// information
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;
		// include a set of results
		Results results = null;

		String errorMessage = "";

		try {
			// make a new connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			// create a prepared statement using this method
			preparedStatement = createPreparedQueryStatement(employeeQuery, connection);
			// execute query
			resultSet = preparedStatement.executeQuery();
			rsmd = resultSet.getMetaData();
			// get the results of the query
			results = getQueryResults(resultSet, rsmd, employeeQuery);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			errorMessage = "THERE WAS AN ERROR. ERROR MESSAGE: " + sqle;
			results = new Results(errorMessage);
		} finally {
			try {
				// close all of the connections / statements / result sets if
				// they were opened
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// return the results to the calling method (controller)
		return results;
	}// getEmployees

	// get the results of the query - exception is handled in getEmployees, the
	// calling method.
	private Results getQueryResults(ResultSet resultSet, ResultSetMetaData rsmd, EmployeeQuery eq) throws SQLException {
		// create new lists to hold results of query
		// list of row item lists
		List<List<String>> rows = new ArrayList<>();
		// list of columns
		List<String> cols = new ArrayList<>();
		// column count
		int colCount = rsmd.getColumnCount();
		// assign columns to column list
		for (int i = 1; i <= colCount; i++) {
			System.out.println("reading in column " + i + ", NAME: " + rsmd.getColumnName(i));
			cols.add(rsmd.getColumnName(i));
		}

		// for each result set, add a list of the row elements to the array list
		while (resultSet.next()) {
			List<String> row = new ArrayList<>();
			row.add(resultSet.getString(1));
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			row.add(resultSet.getString(5));
			row.add(resultSet.getString(6));
			row.add(resultSet.getString(7));
			row.add(resultSet.getString(8));
			row.add(resultSet.getString(9));
			row.add(resultSet.getString(10));
			// get the department list, and add the name of the matching
			// department
			// this will allow the jsp to show the department name instead of
			// the id
			List<Department> dl = getDepartments().getDepartmentList();
			for (Department department : dl) {
				if (department.getId() == resultSet.getInt(11)) {
					row.add(department.getName());
					break;
				}
			}
			// this is doing the same thing as the department list (above),
			// except with jobs
			List<Job> jl = getJobs().getJobList();
			for (Job job : jl) {
				if (job.getId() == resultSet.getInt(12)) {
					row.add(job.getName());
					break;
				}
			}
			row.add(resultSet.getString(13));
			row.add(resultSet.getString(14));
			row.add(resultSet.getString(15));
			row.add(resultSet.getString(16));
			row.add(resultSet.getString(17));
			rows.add(row);
		}
		// add the rows and results to a Results object, and return it
		Results r = new Results(rows, cols);
		return r;
	}

	// creates a prepared statement for the query - exception is handled in
	// calling method, getEmployees().
	private PreparedStatement createPreparedQueryStatement(EmployeeQuery eq, Connection c) throws SQLException { // caught
		/*
		 * I used three parameters, and i wanted them to
		 *  be queried with any combination of the
		 * three, while also using prepared statements
		 * 
		 * The 7 methods after this "createPreparedQueryStatement"
		 * method are called by this method.  It would be shorter to
		 * put the prepared statements in this method, but i thought
		 * it was easier to break them up for ease of reading
		 */
		PreparedStatement ps = null;

		//if the id isn't null, query with only the id
		if (eq.getId() != null && eq.getFirstname() == null && eq.getLastname() == null) {
			ps = createIdPreparedStatement(eq, ps, c);
		}
		//if the id and first name are not null, query with those
		else if (eq.getId() != null && eq.getFirstname() != null && eq.getLastname() == null) {
			ps = createIdFirstnamePreparedStatement(eq, ps, c);
		} 
		//if id and last name aren't null, query with
		else if (eq.getId() != null && eq.getFirstname() == null && eq.getLastname() != null) {
			ps = createIdLastnamePreparedStatement(eq, ps, c);
		} 
		//if the id, firstname, and lastname are not null, query with all three
		else if (eq.getId() != null && eq.getFirstname() != null && eq.getLastname() != null) {
			ps = createIdFirstnameLastnamePreparedStatement(eq, ps, c);
		}
		//if the id is null....
		else if (eq.getId() == null) {
			//if the firstname and last name aren't null, query with both
			if (eq.getFirstname() != null && eq.getLastname() != null) {
				ps = createFirstnameLastnamePreparedStatement(eq, ps, c);
			} 
			//if the firstname isn't null, query with that.
			else if (eq.getFirstname() != null && eq.getLastname() == null) {
				ps = createFirstnamePreparedStatement(eq, ps, c);
			} 
			//if the lastname isn't null, query with that
			else if (eq.getFirstname() == null && eq.getLastname() != null) {
				ps = createLastnamePreparedStatement(eq, ps, c);
			}

		}
		return ps;

	}
/*
 * START QUERY METHODS
 * all exceptions handled in getEmployees() method.
 */
	private PreparedStatement createIdPreparedStatement(EmployeeQuery eq, PreparedStatement ps, Connection c)
			throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE id = ?");
		ps.setInt(1, eq.id);
		return ps;

	}

	private PreparedStatement createIdFirstnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps, Connection c)
			throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE id = ? AND firstname LIKE ?");
		ps.setInt(1, eq.id);
		ps.setString(2, "%" + eq.getFirstname() + "%");
		return ps;

	}

	private PreparedStatement createIdLastnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps, Connection c)
			throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE id = ? AND lastname LIKE ?");
		ps.setInt(1, eq.id);
		ps.setString(2, "%" + eq.getLastname() + "%");
		return ps;

	}

	private PreparedStatement createIdFirstnameLastnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps,
			Connection c) throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE id = ? AND firstname LIKE ? AND lastname LIKE ?");
		ps.setInt(1, eq.id);
		ps.setString(2, "%" + eq.getFirstname() + "%");
		ps.setString(3, "%" + eq.getLastname() + "%");
		return ps;

	}

	private PreparedStatement createFirstnameLastnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps,
			Connection c) throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE firstname LIKE ? AND lastname LIKE ?");
		ps.setString(1, "%" + eq.getFirstname() + "%");
		ps.setString(2, "%" + eq.getLastname() + "%");
		return ps;
	}

	private PreparedStatement createFirstnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps, Connection c)
			throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE firstname LIKE ?");
		ps.setString(1, "%" + eq.getFirstname() + "%");

		return ps;
	}

	private PreparedStatement createLastnamePreparedStatement(EmployeeQuery eq, PreparedStatement ps, Connection c)
			throws SQLException {
		ps = c.prepareStatement("SELECT * FROM employees WHERE lastname LIKE ?");

		ps.setString(1, "%" + eq.getLastname() + "%");
		return ps;
	}
/*
 * END QUERY METHODS
 */
	@Override
	public Employee getEmployee(int employeeID) {
		//variable inits
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		Employee e = new Employee();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		@SuppressWarnings("unused")
		Results result;
		try {
			//make connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//create prepared statement
			preparedStatement = connection.prepareStatement("SELECT "
					+ "id,firstname,middlename,lastname,gender,email,extension,YEAR(hiredate),MONTH(hiredate),DAY(hiredate),salary,commission_pct,department_id,job_id,address,city,state,zipcode,version "
					+ "FROM employees WHERE id = ?");
			//set the id to query on
			preparedStatement.setInt(1, employeeID);
			//execute
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
			//set the employee attributes
			e = setEmployeeAttributes(e, rs, employeeID);
			}
		} catch (SQLException sqle) {
			//add an error message to the Results object
			String errorMessage = "THERE WAS AN ERROR WITH THE SQL. ERROR MESSAGE: " + sqle;
			result = new Results(errorMessage);
			System.out.println("SQL QUERY FAILED: " + sqle);
			sqle.printStackTrace();

		} finally {
			try {
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sqle) {
				// TODO Auto-generated catch block
				sqle.printStackTrace();
			}

		}
		//return employee
		return e;
	}

	//set the employee attributes, exception handled in calling method, getEmployee()
	private Employee setEmployeeAttributes(Employee e, ResultSet rs, int employeeID) throws SQLException{
		e.setId(employeeID);
		e.setFirstname(rs.getString(2));
		e.setMiddlename(rs.getString(3));
		e.setLastname(rs.getString(4));
		e.setGender(rs.getString(5));
		e.setEmail(rs.getString(6));
		e.setExtention(rs.getInt(7));
		e.setHireYear(rs.getInt(8));
		e.setHireMonth(rs.getInt(9));
		e.setHireDay(rs.getInt(10));
		e.setSalary(rs.getInt(11));
		e.setCommission_pct(rs.getInt(12));
		e.setDepartment_id(rs.getInt(13));
		e.setJob_id(rs.getInt(14));
		e.setAddress(rs.getString(15));
		e.setCity(rs.getString(16));
		e.setState(rs.getString(17));
		e.setZipcode(rs.getInt(18));
		e.setVersion(rs.getInt(19));
		e.setHiredate();
		return e;
	}
	
	//modify employees
	@Override
	public Results modifyEmployee(int employeeID, Employee updatedEmployee) {
		//init variables
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Results result;
		try {
			//make conection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//create prepared statement
			preparedStatement = createModifyStatement(connection, preparedStatement, employeeID, updatedEmployee);
			//execute query and get the results
			result = executeAndGetResults(resultSet, preparedStatement);
		} catch (SQLException sqle) {
			String errorMessage = "THERE WAS AN ERROR WITH THE SQL. ERROR MESSAGE: " + sqle;
			result = new Results(errorMessage);
			sqle.printStackTrace();

		} finally {
			try {
				//close connections if they were opened
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}
	//createModifyStatement, exception handled in callign method, modifyEmployee()
	private PreparedStatement createModifyStatement(Connection c, PreparedStatement ps, int employeeID, Employee e)
			throws SQLException {
		//string of statement
		String dml = "UPDATE employees SET firstname = ?,middlename = ?,lastname = ?,gender = ?,email = ?,extension = ?,hiredate = ?,salary = ?,commission_pct = ?,department_id = ?,job_id = ?,address = ?,city = ?,state = ?,zipcode = ?,version = ? WHERE id = ?";
		//create prepared statement
		ps = c.prepareStatement(dml);
		//set values in prepared statement
		ps.setString(1, e.getFirstname());
		ps.setString(2, e.getMiddlename());
		ps.setString(3, e.getLastname());
		ps.setString(4, e.getGender());
		ps.setString(5, e.getEmail());
		/*
		 * all of the number values must be checked for being null
		 * in the object, they are of the wrapper class type
		 * (Integer instead of int), but sql had problems with 
		 * trying to pass in null values.
		 */
		if (e.getExtention() != null) {

			ps.setInt(6, e.getExtention());
		} else {
			ps.setNull(6, java.sql.Types.INTEGER);
		}

		// date
		if (e.getHireYear() != null && e.getHireMonth() != null && e.getHireDay() != null) {
			//append the year to make the correctly formatted date
			ps.setString(7, ("" + e.getHireYear() + "-" + e.getHireMonth() + "-" + e.getHireDay()));

		} else {
			ps.setNull(7, java.sql.Types.DATE);
		}
		if (e.getSalary() != null) {
			ps.setInt(8, e.getSalary());
		} else {
			ps.setNull(8, java.sql.Types.INTEGER);
		}
		if (e.getCommission_pct() != null) {
			ps.setInt(9, e.getCommission_pct());
		} else {
			ps.setNull(9, java.sql.Types.INTEGER);
		}
		if (e.getDepartment_id() != null) {
			ps.setInt(10, e.getDepartment_id());
		} else {
			ps.setNull(10, java.sql.Types.INTEGER);
		}
		if (e.getJob_id() != null) {
			ps.setInt(11, e.getJob_id());
		} else {
			ps.setNull(11, java.sql.Types.INTEGER);
		}

		ps.setString(12, e.getAddress());
		ps.setString(13, e.getCity());
		ps.setString(14, e.getState());

		if (e.getZipcode() != null) {
			ps.setInt(15, e.getZipcode());

		} else {
			ps.setNull(15, java.sql.Types.INTEGER);
		}

		if (e.getVersion() != null) {
			ps.setInt(16, e.getVersion());
		} else {
			ps.setNull(16, java.sql.Types.INTEGER);
		}

		// it will have an id, in the where clause.
		ps.setInt(17, employeeID);

		return ps;

	}
	//create add prepared statement, exception handled in calling method, addEmployees();
	private PreparedStatement createAddStatement(Connection c, PreparedStatement ps, Employee e) throws SQLException {																											// employee
		//and INSERT string
		String dml = "INSERT INTO employees (firstname,middlename,lastname,gender,email,extension,hiredate,salary,commission_pct,department_id,job_id,address,city,state,zipcode,version) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//use string as prepared statement
		ps = c.prepareStatement(dml);
		//set prepared statement params
		ps.setString(1, e.getFirstname());
		ps.setString(2, e.getMiddlename());
		ps.setString(3, e.getLastname());
		ps.setString(4, e.getGender());
		ps.setString(5, e.getEmail());
		/*
		 * all of the number values must be checked for being null
		 * in the object, they are of the wrapper class type
		 * (Integer instead of int), but sql had problems with 
		 * trying to pass in null values.
		 */
		if (e.getExtention() != null) {

			ps.setInt(6, e.getExtention());
		} else {
			ps.setNull(6, java.sql.Types.INTEGER);
		}

		// date
		if (e.getHireYear() != null && e.getHireMonth() != null && e.getHireDay() != null) {
			// concatenate date parts together
			ps.setString(7, ("" + e.getHireYear() + "-" + e.getHireMonth() + "-" + e.getHireDay()));

		} else {
			ps.setNull(7, java.sql.Types.DATE);
		}
		if (e.getSalary() != null) {
			ps.setInt(8, e.getSalary());
		} else {
			ps.setNull(8, java.sql.Types.INTEGER);
		}
		if (e.getCommission_pct() != null) {
			ps.setInt(9, e.getCommission_pct());
		} else {
			ps.setNull(9, java.sql.Types.INTEGER);
		}
		if (e.getDepartment_id() != null) {
			ps.setInt(10, e.getDepartment_id());
		} else {
			ps.setNull(10, java.sql.Types.INTEGER);
		}
		if (e.getJob_id() != null) {
			ps.setInt(11, e.getJob_id());
		} else {
			ps.setNull(11, java.sql.Types.INTEGER);
		}

		ps.setString(12, e.getAddress());
		ps.setString(13, e.getCity());
		ps.setString(14, e.getState());

		if (e.getZipcode() != null) {
			ps.setInt(15, e.getZipcode());

		} else {
			ps.setNull(15, java.sql.Types.INTEGER);
		}

		if (e.getVersion() != null) {
			ps.setInt(16, e.getVersion());
		} else {
			ps.setNull(16, java.sql.Types.INTEGER);
		}

		return ps;

	}

	//sql exception handled in calling method (add employe / modify employee methods)
	public Results executeAndGetResults(ResultSet rs, PreparedStatement ps) throws SQLException { // caught
		//execute the satement, and return a new Result object to the calling method.
		System.out.println(ps); // addEmploye()
		Results results = new Results(ps.executeUpdate());
		System.out.println(results.getRowsAffected());
		return results;
	}

	//and employees
	@Override
	public Results addEmployee(Employee employee) {
		//init variables
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Results result;
		try {
			//make connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//make add statement
			preparedStatement = createAddStatement(connection, preparedStatement, employee);
			//execute query and get results
			result = executeAndGetResults(resultSet, preparedStatement);
		} catch (SQLException sqle) {
			//add error message
			String errorMessage = "THERE WAS AN ERROR. ERROR MESSAGE: " + sqle;
			result = new Results(errorMessage);
			sqle.printStackTrace();
		} finally {
			try {
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}

	//this is a test method to see the query results.
	//I commented out the call to this method
	//to make it perform faster
	@SuppressWarnings("unused")
	private void testSysoutPrint(Results r) { // TODO: BYE
		List<List<String>> rows = r.getRowsReturned();

		List<String> cols = r.getColsReturned();

		for (String c : cols) {
			System.out.print(c + "\t");
		}
		for (List<String> row : rows) {
			System.out.println("");
			for (String element : row) {
				System.out.print(element + "\t");
			}
		}

	}
	
	//remove employee
	@Override
	public Results removeEmployee(int employeeID) {
		// init variables
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Results result;
		try {
			//make connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//make statmenet
			preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
			//set the id to delete
			preparedStatement.setInt(1, employeeID);
//			assign the results (rows affected) to the results variable
			result = new Results(preparedStatement.executeUpdate());

		} catch (SQLException sqle) {
			String errorMessage = "THERE WAS AN ERROR WITH THE SQL. ERROR MESSAGE: " + sqle;
			result = new Results(errorMessage);

		} finally {
			try {
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		//return results to calling method (in controller)
		return result;
	}

	//get deparments
	@Override
	public Departments getDepartments() {

		//Init variables
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		Departments departments = null;
		List<Department> departmentList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			//make a connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//prepare stament
			preparedStatement = connection
					.prepareStatement("SELECT " + "id, name, manager_id, location_id " + "FROM departments");
			//execute
			rs = preparedStatement.executeQuery();
			//for each result row, make a new deparrtment and add it to the list
			while (rs.next()) {
				Department d = new Department(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				departmentList.add(d);
			}
			//if it found departments, made a new department object,
			//passing the list to the constructor
			if (departmentList.size() > 0) {
				departments = new Departments(departmentList);
			}

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		} finally {
			try {
				//close connections if opened
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sqle) {

				sqle.printStackTrace();
			}

		}
		//return departments to the calling method ( in controller)
		return departments;
	}

	//get jobs
	@Override
	public Jobs getJobs() {

		//init variables
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		Jobs jobs = null;
		List<Job> jobList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			//make a connection
			connection = DriverManager.getConnection(URL, USR, PWD);
			//prepare statement to select jobs
			preparedStatement = connection
					.prepareStatement("SELECT " + "id, name, minimum_salary, maximum_salary " + "FROM jobs");
			//execute the query
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				//for each returned row, make a new job object
				Job j = new Job(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				jobList.add(j);
			}
			if (jobList.size() > 0) {
				//if there were jobs found, create a new Jobs object,
				//passing the list of jobs to the constructor
				jobs = new Jobs(jobList);
			}

		} catch (SQLException sqle) {

			sqle.printStackTrace();

		} finally {
			try {
				//close everything that was opened
				if (connection != null) {

					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException sqle) {

				sqle.printStackTrace();
			}

		}

		return jobs;
	}

}

