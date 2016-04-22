package data;

public class DATATESTMETHOD {

	public static void main(String[] args) {

//		Employee emptyE = new Employee(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//		
//		System.out.println(emptyE);
		
		Employee e = new Employee();
		EmployeeQuery eq = new EmployeeQuery();
		EmployeeSQLDAO d = new EmployeeSQLDAO();
		d.getEmployees(eq);
		
	}

}
