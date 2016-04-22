package data;

public class DATATESTMETHOD {

	public static void main(String[] args) {

//		Employee emptyE = new Employee(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//		
//		System.out.println(emptyE);
		EmployeeSQLDAO dao = new EmployeeSQLDAO();
		Employee e = new Employee();
		e.setFirstname("sdfafsda");
		e.setLastname("jlsdfkjlsdf");
		e.setJob_id(1);
		e.setDepartment_id(1);
		
		Results r = dao.addEmployee(e);
		System.out.println("Affected Rows: " + r.getRowsAffected());
		
	}

}
