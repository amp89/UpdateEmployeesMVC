package data;
/*
 * THIS IS JUST A TEST CLASS
 */
public class TESTUPDATE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EmployeeSQLDAO dao = new EmployeeSQLDAO();
		int idToModify = 1003;
		Employee e = dao.getEmployee(1003);
		System.out.println(e);
		e.setFirstname("updatedPerson");
		e.setLastname("updatedPersonln");
		e.setDepartment_id(1);
		e.setJob_id(2);
		dao.modifyEmployee(idToModify,e);
				
	}

}
