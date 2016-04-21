package data;

public interface EmployeeDAO {
	//searching employees
	public Results getEmployees(String query);
	
	//get single employees
	public Employee getEmployee();
	
	//modifying employees
	public Results modifyEmployees(Employee employee);
	
	//new employees
	public Results addEmployee(Employee employee);
	
	

	/*
	 * TODO: maybe make gets for the assignments and projects of each employee.
	 * TODO: implement this interface and create queries to do the things
	 */
	
}
