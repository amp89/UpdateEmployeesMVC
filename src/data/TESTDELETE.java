package data;

public class TESTDELETE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeSQLDAO dao = new EmployeeSQLDAO();
		dao.removeEmployee(10000000);
		dao.removeEmployee(10000001);
		dao.removeEmployee(10000002);
		dao.removeEmployee(10000003);
		dao.removeEmployee(10000004);
		dao.removeEmployee(10000005);
		
	}

}
