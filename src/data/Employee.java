package data;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

/*
 * EMPLOYEE CLASS
 */
public class Employee {

	
	private Integer id;
	@Size(min=2, max=50, message="2-50 Characterss")
	private String firstname;
	@Size(max=50, message="Enter Valid Data")
	private String middlename;
	@Size(min=1, max=50, message="Enter Valid Data")
	private String lastname;
	@Size(max=1, message="Enter Valid Data")
	private String gender;
	@Size(max=100, message="Enter Valid Data")
	private String email;
	@Range(max=9999, message="Enter Valid Data")
	private Integer extention;
	private String hiredate;
	@Range(max=9999, message="Enter Valid Data")
	private Integer hireYear;
	@Range(min=0, max=12, message="Enter Valid Data")
	private Integer hireMonth;
	@Range(min=0, max=31, message="Enter Valid Data")
	private Integer hireDay;
	@Range(min=0, max=999999999, message="Enter Valid Data")
	private Integer salary;
	@Range(min=0, max=100, message="Enter Valid Data")
	private Integer commission_pct;
	@Range(min=1, message="Enter Valid Data")
	private Integer department_id;
	@Range(min=1, message="Enter Valid Data")
	private Integer job_id;

	@Size(max=100, message="Enter Valid Data")
	private String address;

	@Size(max=100, message="Enter Valid Data")
	private String city;
	@Size(max=2)
	private String state;
	@Range(min=0, max=99999, message="Enter Valid Data")	
	private Integer zipcode;
	private Integer version;

	public Employee() {
	}

	public Employee(Integer id, String firstname, String middlename, String lastname, String gender, String email,
			Integer extention, Integer hireYear, Integer hireMonth, Integer hireDay, Integer salary,
			Integer commission_pct, Integer department_id, Integer job_id, String address, String city, String state,
			Integer zipcode, Integer version) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.gender = gender;
		this.email = email;
		this.extention = extention;
		this.hireYear = hireYear;
		this.hireMonth = hireMonth;
		this.hireDay = hireDay;
		this.salary = salary;
		this.commission_pct = commission_pct;
		this.department_id = department_id;
		this.job_id = job_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.version = version;

		setHiredate();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getExtention() {
		return extention;
	}

	public void setExtention(Integer extention) {
		this.extention = extention;
	}

	public String getHiredate() {
		return hiredate;
	}
	//in the add and modify parts of this app,
	//the hire date is set separately (year, month, day)
	//so i changed the setHiredate method to conacatinate
	//those values.
	public void setHiredate() {
		if (hireYear != null && hireMonth != null && hireDay != null) {
			this.hiredate = "" + hireYear + "-" + hireMonth + "-" + hireDay;
		}
	}

	public Integer getHireYear() {
		return hireYear;
	}

	public void setHireYear(Integer hireYear) {
		this.hireYear = hireYear;
	}

	public Integer getHireMonth() {
		return hireMonth;
	}

	public void setHireMonth(Integer hireMonth) {
		this.hireMonth = hireMonth;
	}

	public Integer getHireDay() {
		return hireDay;
	}

	public void setHireDay(Integer hireDay) {
		this.hireDay = hireDay;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(Integer commission_pct) {
		this.commission_pct = commission_pct;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", middlename=" + middlename + ", lastname="
				+ lastname + ", gender=" + gender + ", email=" + email + ", extention=" + extention + ", hiredate="
				+ hiredate + ", hireYear=" + hireYear + ", hireMonth=" + hireMonth + ", hireDay=" + hireDay
				+ ", salary=" + salary + ", commission_pct=" + commission_pct + ", department_id=" + department_id
				+ ", job_id=" + job_id + ", address=" + address + ", city=" + city + ", state=" + state + ", zipcode="
				+ zipcode + ", version=" + version + ", hireDate=" + hiredate + "]";
	}

	// TODO: figure this out:
	// Project project;
	// Assignment assignment;
	// assignments

}
