package data;

public interface EmployeeDAO {
	//searching employees
	public Results getEmployees(String query);
	
	//modifying employees
	public Results modifyEmployees(String query);

	
	
}
