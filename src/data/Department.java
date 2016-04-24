package data;
/*
 * DEPARTMENT CLASS
 */
public class Department {
	private Integer id;
	private String name;
	private int manager_id;
	private int location_id;

	public Department() {

	}
	
	public Department(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Department(Integer id, String name, int manager_id, int location_id) {
		super();
		this.id = id;
		this.name = name;
		this.manager_id = manager_id;
		this.location_id = location_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", manager_id=" + manager_id + ", location_id=" + location_id
				+ "]";
	}

}
