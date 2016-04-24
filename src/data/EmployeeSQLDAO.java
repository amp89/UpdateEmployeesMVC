package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class EmployeeSQLDAO implements EmployeeDAO {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/companydb?zeroDateTimeBehavior=convertToNull";;
	private static final String USR = "student";
	private static final String PWD = "student";

	@Autowired
	private ApplicationContext ac;

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
		// connection stuff
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;

		// result stuff
		Results results = null;

		// TODO: i think this stuff is in the other place
		// List<List<String>> rows = null;
		// List<String> cols = null;
		// int rowsAffected = 0;
		String errorMessage = "";

		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = createPreparedStatement(employeeQuery, connection);
			resultSet = preparedStatement.executeQuery();
			rsmd = resultSet.getMetaData();
			results = getQueryResults(resultSet, rsmd, employeeQuery);
		} catch (SQLException sqle) {
			System.out.println(sqle);
			sqle.printStackTrace();
			errorMessage = "THERE WAS AN ERROR WITH THE SQL. ERROR MESSAGE: " + sqle;
			results = new Results(errorMessage);
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

		testSysoutPrint(results);
		return results;
	}// getEmployees

	// TODO: remove employequery object form this, it is no longer needed
	private Results getQueryResults(ResultSet resultSet, ResultSetMetaData rsmd, EmployeeQuery eq) throws SQLException { // caught

		List<List<String>> rows = new ArrayList<>();

		List<String> cols = new ArrayList();
		int rowsAffected = 0;
		String errorMessage = "";

		int colCount = rsmd.getColumnCount();
		for (int i = 1; i <= colCount; i++) {
			System.out.println("reading in column " + i + ", NAME: " + rsmd.getColumnName(i));
			cols.add(rsmd.getColumnName(i)); // TODO null pointer
		}

		while (resultSet.next()) {
			List<String> row = new ArrayList<>();
			row.add(resultSet.getString(1)); // TODO: GETSTRING FOR INT... WILL
												// THEY WORK!!?!?
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			row.add(resultSet.getString(5));
			row.add(resultSet.getString(6));
			row.add(resultSet.getString(7));
			row.add(resultSet.getString(8)); // TODO: fixed in URI
			row.add(resultSet.getString(9));
			row.add(resultSet.getString(10));
			row.add(resultSet.getString(11));
			row.add(resultSet.getString(12));
			row.add(resultSet.getString(13));
			row.add(resultSet.getString(14));
			row.add(resultSet.getString(15));
			row.add(resultSet.getString(16));
			row.add(resultSet.getString(17));
			rows.add(row);
		}

		Results r = new Results(rows, cols);

		return r;
	}

	private PreparedStatement createPreparedStatement(EmployeeQuery eq, Connection c) throws SQLException { // caught
																											// in
																											// calling
		PreparedStatement ps = null;

		if (eq.getId() != null && eq.getFirstname() == null && eq.getLastname() == null) {
			ps = createIdPreparedStatement(eq, ps, c);
		} else if (eq.getId() != null && eq.getFirstname() != null && eq.getLastname() == null) {
			ps = createIdFirstnamePreparedStatement(eq, ps, c);
		} else if (eq.getId() != null && eq.getFirstname() == null && eq.getLastname() != null) {
			ps = createIdLastnamePreparedStatement(eq, ps, c);
		} else if (eq.getId() != null && eq.getFirstname() != null && eq.getLastname() != null) {
			ps = createIdFirstnameLastnamePreparedStatement(eq, ps, c);
		}

		else if (eq.getId() == null) {

			if (eq.getFirstname() != null && eq.getLastname() != null) {
				ps = createFirstnameLastnamePreparedStatement(eq, ps, c);
			} else if (eq.getFirstname() != null && eq.getLastname() == null) {
				ps = createFirstnamePreparedStatement(eq, ps, c);
			} else if (eq.getFirstname() == null && eq.getLastname() != null) {
				ps = createLastnamePreparedStatement(eq, ps, c);
			}

		}
		return ps;

	}

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

	@Override
	public Employee getEmployee(int employeeID) {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		Employee e = new Employee();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		Results result;
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			System.out.println("Q on id: " + employeeID);
			preparedStatement = connection.prepareStatement("SELECT "
					+ "id,firstname,middlename,lastname,gender,email,extension,YEAR(hiredate),MONTH(hiredate),DAY(hiredate),salary,commission_pct,department_id,job_id,address,city,state,zipcode,version "
					+ "FROM employees WHERE id = ?");
			preparedStatement.setInt(1, employeeID);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
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

			}
		} catch (SQLException sqle) {
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
		System.out.println("employee returned: " + e);
		return e;
	}

	@Override
	public Results modifyEmployee(int employeeID, Employee updatedEmployee) {
		// Employee employee = getEmployee(employeeID);
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;
		Results result;
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = createModifyStatement(connection, preparedStatement, employeeID, updatedEmployee);
			result = getAddResults(resultSet, preparedStatement);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	private PreparedStatement createModifyStatement(Connection c, PreparedStatement ps, int employeeID, Employee e)
			throws SQLException { // caught
		// in
		// calling
		// method:
		// add
		// employee
		String dml = "UPDATE employees SET firstname = ?,middlename = ?,lastname = ?,gender = ?,email = ?,extension = ?,hiredate = ?,salary = ?,commission_pct = ?,department_id = ?,job_id = ?,address = ?,city = ?,state = ?,zipcode = ?,version = ? WHERE id = ?";
		ps = c.prepareStatement(dml);

		ps.setString(1, e.getFirstname());
		ps.setString(2, e.getMiddlename());
		ps.setString(3, e.getLastname());
		ps.setString(4, e.getGender());
		ps.setString(5, e.getEmail());
		if (e.getExtention() != null) {

			ps.setInt(6, e.getExtention());
		} else {
			ps.setNull(6, java.sql.Types.INTEGER);
		}

		// TODO fix date stuff
		if ((e.getHireYear() != null && e.getHireYear() != 0)
				&& (e.getHireMonth() != null && e.getHireMonth() != 0 ) 
				&& (e.getHireDay() != null &&  e.getHireDay() != 0)
				) {
			Calendar cal = Calendar.getInstance();
			cal.set(e.getHireYear(), e.getHireMonth(), e.getHireDay());
			Date date = (Date) cal.getTime();

			ps.setDate(7, date);

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

	private PreparedStatement createAddStatement(Connection c, PreparedStatement ps, Employee e) throws SQLException { // caught
																														// in
																														// employee
		String dml = "INSERT INTO employees (firstname,middlename,lastname,gender,email,extension,hiredate,salary,commission_pct,department_id,job_id,address,city,state,zipcode,version) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ps = c.prepareStatement(dml);

		ps.setString(1, e.getFirstname());
		ps.setString(2, e.getMiddlename());
		ps.setString(3, e.getLastname());
		ps.setString(4, e.getGender());
		ps.setString(5, e.getEmail());
		if (e.getExtention() != null) {

			ps.setInt(6, e.getExtention());
		} else {
			ps.setNull(6, java.sql.Types.INTEGER);
		}

		// TODO fix date stuff
		if (e.getHireYear() != null && e.getHireMonth() != null && e.getHireDay() != null) {
			Calendar cal = Calendar.getInstance();
			cal.set(e.getHireYear(), e.getHireMonth(), e.getHireDay());
			Date date = (Date) cal.getTime();

			ps.setDate(7, date);

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

	public Results getAddResults(ResultSet rs, PreparedStatement ps) throws SQLException { // caught
																							// in
																							// calling
																							// method:
		System.out.println(ps); // addEmploye()
		Results results = new Results(ps.executeUpdate());

		return results;
	}

	@Override
	public Results addEmployee(Employee employee) {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;
		Results result;
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = createAddStatement(connection, preparedStatement, employee);
			result = getAddResults(resultSet, preparedStatement);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	private void testSysoutPrint(Results r) { // TODO: BYE
		List<List<String>> rows = r.getRowsReturned();

		List<String> cols = r.getColsReturned();

		for (String c : cols) { // TODO: possible null pointer here
			System.out.print(c + "\t");
		}
		for (List<String> row : rows) {
			System.out.println("");
			for (String element : row) {
				System.out.print(element + "\t");
			}
		}

	}

	@Override
	public Results removeEmployee(int employeeID) {
		// Employee employee = getEmployee(employeeID);
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;
		Results result;
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
			preparedStatement.setInt(1, employeeID);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}
	
	@Override
	public Departments getDepartments(){
		
		/*
		 * TODO; change to get depts
		 */
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
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = connection.prepareStatement("SELECT "
					+ "id, name, manager_id, location_id "
					+ "FROM departments");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Department d = new Department(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				departmentList.add(d);
			}
			if (departmentList.size() > 0){
				departments = new Departments(departmentList);
			}
			
		} catch (SQLException sqle) {
			
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
		System.out.println("dept list returned");
		return departments;
	}
	
	@Override
	public Jobs getJobs(){
		
		/*
		 * TODO; change to get depts
		 */
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
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = connection.prepareStatement("SELECT "
					+ "id, name, minimum_salary, maximum_salary "
					+ "FROM jobs");
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Job j = new Job(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
				jobList.add(j);
			}
			if (jobList.size() > 0){
				jobs = new Jobs(jobList);
			}
			
		} catch (SQLException sqle) {
			
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
		System.out.println("job list returned");
		return jobs;
	}
	


}
