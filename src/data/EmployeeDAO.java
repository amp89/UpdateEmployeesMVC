package data;

public interface EmployeeDAO {
	//searching employees
	public Results getEmployees(EmployeeQuery employeeQuery);
	
	//get single employees
	public Employee getEmployee(int employeeID);
	
	public Results removeEmployee(int employeeID);
	
	//new employees
	public Results addEmployee(Employee employee);

	//modify employees
	public Results modifyEmployee(int employeeID, Employee updatedEmployee);
	
	//get departments
	public Departments getDepartments(); 
	
	//get jobs
	public Jobs getJobs();

	
}
