package data;
/*
 * Deparments class
 * This holds a list of department objects to pass
 * to the controller
 */
import java.util.List;

public class Departments {
	
	private List<Department> departmentList;

	public Departments() {
		super();
	}

	public Departments(List<Department> departmentList) {
		super();
		this.departmentList = departmentList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	@Override
	public String toString() {
		return "Departments [departmentList=" + departmentList + "]";
	}
	
	
	
}
