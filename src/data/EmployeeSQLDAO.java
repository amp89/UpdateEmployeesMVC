package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeSQLDAO implements EmployeeDAO {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "mysql:jdbc://localhost:3306/companydb";
	private static final String USR = "student";
	private static final String PWD = "student";

	@Override
	public Results getEmployees(EmployeeQuery employeeQuery) {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		String errorMessage = "";
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = createPreparedStatement(employeeQuery, connection);
			resultSet = ps.
		} catch (SQLException sqle) {

		}

		return null;
	}// getEmployees

	private PreparedStatement createPreparedStatement(EmployeeQuery eq, Connection c) throws SQLException{ //caught in getEmployees
		String sql = "SELECT id,firstname,middlename,lastname,gender,email,extension,hiredate,salary,commission_pct,department_id,job_id,address,city,state,zipcode,version FROM employees";
		/*
		 * TODO: add if's and things
		 */
		
		
		PreparedStatement ps = c.prepareStatement(sql);
		
		
		return ps;
		
	}

	@Override
	public Employee getEmployee(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Results modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Results addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void testDeleteMe(Results r){ //TODO: BYE
		
	}

}
