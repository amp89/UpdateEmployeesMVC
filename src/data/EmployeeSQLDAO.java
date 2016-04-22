package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSQLDAO implements EmployeeDAO {

	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/companydb?zeroDateTimeBehavior=convertToNull";;
	private static final String USR = "student";
	private static final String PWD = "student";

	@Override
	public Results getEmployees(EmployeeQuery employeeQuery) {
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
		//connection stuff
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		ResultSetMetaData rsmd = null;

		//result stuff
		Results results = null;
		List<List<String>> rows = null;
		List<String> cols = null;
		int rowsAffected = 0;
		String errorMessage = "";
		
		
		try {
			connection = DriverManager.getConnection(URL, USR, PWD);
			preparedStatement = createPreparedStatement(employeeQuery, connection);
			resultSet = preparedStatement.executeQuery();
			rsmd = resultSet.getMetaData();
			results = getResults(resultSet, rsmd, employeeQuery);
		} catch (SQLException sqle) {
			System.out.println(sqle);
			sqle.printStackTrace();
			errorMessage = "THERE WAS AN ERROR WITH THE SQL. ERROR MESSAGE: " + sqle;
			results = new Results(errorMessage);
		}

		
		//make a result (will be passed to dao)
		//change to results.seterror msg, if its null
		testSysoutPrint(results);
		return results;
	}// getEmployees

	//TODO: remove employequery object form this, it is no longer needed
	private Results getResults(ResultSet resultSet, ResultSetMetaData rsmd, EmployeeQuery eq) throws SQLException { //caught in calling method: getEmployees();
		
		List<List<String>> rows = new ArrayList<>();
		
		List<String> cols = new ArrayList();
		int rowsAffected = 0;
		String errorMessage = "";
		
		int colCount = rsmd.getColumnCount(); 
		for (int i = 1; i <= colCount; i++) {
			System.out.println("reading in column " + i + ", NAME: " + rsmd.getColumnName(i));
			cols.add(rsmd.getColumnName(i)); //TODO null pointer
		}
		
		while(resultSet.next()){
			List<String> row = new ArrayList<>();
			row.add(resultSet.getString(1)); //TODO: GETSTRING FOR INT... WILL THEY WORK!!?!?
			row.add(resultSet.getString(2));
			row.add(resultSet.getString(3));
			row.add(resultSet.getString(4));
			row.add(resultSet.getString(5));
			row.add(resultSet.getString(6));
			row.add(resultSet.getString(7));
			row.add(resultSet.getString(8)); //TODO: fix date 0000-00-00//date sql exceptoin here, readin gin 00-00-0000 is a no go
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
		
		Results r = new Results(rows,cols);
		
		return r;
	}

	private PreparedStatement createPreparedStatement(EmployeeQuery eq, Connection c) throws SQLException{ //caught in calling method: getEmployees()
		StringBuilder sql = new StringBuilder("SELECT id, firstname, middlename, lastname, gender, email, extension, hiredate, salary, commission_pct, department_id, job_id, address, city, state, zipcode, version FROM employees");
		
		//TODO i guess enter any critera or the search will crash
//		sql.append(" WHERE ")
//		
//		if(eq.getId() != null){
//			if(eq.getIdRange() != null){
//				
//			}
//		}
		
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		
		
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
	
	private void testSysoutPrint(Results r){ //TODO: BYE
		List<List<String>> rows = r.getRowsReturned();
		
		List<String> cols = r.getColsReturned();
		
		for (String c : cols) { //TODO: possible null pointer here
			System.out.print(c + "\t");
		}
		for (List<String> row : rows) {
			System.out.println("");
			for (String element : row) {
				System.out.print(element + "\t");
			}
		}
		
	}

}
