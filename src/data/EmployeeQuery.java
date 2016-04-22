package data;

public class EmployeeQuery {

	// TODO tostring

	// general
	Integer id; //exact, +/-
	Integer idRange; //i
	String firstname; // TODO: make sure query LIKE %%
	String middlename;//TODO: make sure query LIKE %%
	String lastname; //  TODO: make sure query LIKE %%
	String gender;
	String email;
	Integer extention;

	Integer hireYear; // TODO: //exact, +/-
	Integer hireYearRange; // TODO: //exact, +/-
	Integer hireMonth; // TODO://exact, +/-
	Integer hireMonthRange; // TODO://exact, +/-
	Integer hireDay; // TODO: //exact, +/-
	Integer hireDayRange; // TODO: //exact, +/-
	Integer salary;
	Integer salaryRange;
	Integer commission_pct;
	Integer commission_pctRange;
	Integer department_id;
	Integer job_id;
	String address;
	String city;
	String state;
	Integer zipcode;
	Integer version;
	
	public EmployeeQuery(){}
	
	public EmployeeQuery(Integer id, Integer idRange, String firstname, String middlename, String lastname,
			String gender, String email, Integer extention, Integer hireYear, Integer hireYearRange, Integer hireMonth,
			Integer hireMonthRange, Integer hireDay, Integer hireDayRange, Integer salary, Integer salaryRange,
			Integer commission_pct, Integer commission_pctRange, Integer department_id, Integer job_id, String address,
			String city, String state, Integer zipcode, Integer version) {
		super();
		this.id = id;
		this.idRange = idRange;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.gender = gender;
		this.email = email;
		this.extention = extention;
		this.hireYear = hireYear;
		this.hireYearRange = hireYearRange;
		this.hireMonth = hireMonth;
		this.hireMonthRange = hireMonthRange;
		this.hireDay = hireDay;
		this.hireDayRange = hireDayRange;
		this.salary = salary;
		this.salaryRange = salaryRange;
		this.commission_pct = commission_pct;
		this.commission_pctRange = commission_pctRange;
		this.department_id = department_id;
		this.job_id = job_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdRange() {
		return idRange;
	}
	public void setIdRange(Integer idRange) {
		this.idRange = idRange;
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
	public Integer getHireYear() {
		return hireYear;
	}
	public void setHireYear(Integer hireYear) {
		this.hireYear = hireYear;
	}
	public Integer getHireYearRange() {
		return hireYearRange;
	}
	public void setHireYearRange(Integer hireYearRange) {
		this.hireYearRange = hireYearRange;
	}
	public Integer getHireMonth() {
		return hireMonth;
	}
	public void setHireMonth(Integer hireMonth) {
		this.hireMonth = hireMonth;
	}
	public Integer getHireMonthRange() {
		return hireMonthRange;
	}
	public void setHireMonthRange(Integer hireMonthRange) {
		this.hireMonthRange = hireMonthRange;
	}
	public Integer getHireDay() {
		return hireDay;
	}
	public void setHireDay(Integer hireDay) {
		this.hireDay = hireDay;
	}
	public Integer getHireDayRange() {
		return hireDayRange;
	}
	public void setHireDayRange(Integer hireDayRange) {
		this.hireDayRange = hireDayRange;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Integer getSalaryRange() {
		return salaryRange;
	}
	public void setSalaryRange(Integer salaryRange) {
		this.salaryRange = salaryRange;
	}
	public Integer getCommission_pct() {
		return commission_pct;
	}
	public void setCommission_pct(Integer commission_pct) {
		this.commission_pct = commission_pct;
	}
	public Integer getCommission_pctRange() {
		return commission_pctRange;
	}
	public void setCommission_pctRange(Integer commission_pctRange) {
		this.commission_pctRange = commission_pctRange;
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
		return "EmployeeQuery [id=" + id + ", idRange=" + idRange + ", firstname=" + firstname + ", middlename="
				+ middlename + ", lastname=" + lastname + ", gender=" + gender + ", email=" + email + ", extention="
				+ extention + ", hireYear=" + hireYear + ", hireYearRange=" + hireYearRange + ", hireMonth=" + hireMonth
				+ ", hireMonthRange=" + hireMonthRange + ", hireDay=" + hireDay + ", hireDayRange=" + hireDayRange
				+ ", salary=" + salary + ", salaryRange=" + salaryRange + ", commission_pct=" + commission_pct
				+ ", commission_pctRange=" + commission_pctRange + ", department_id=" + department_id + ", job_id="
				+ job_id + ", address=" + address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", version=" + version + "]";
	}
	
	
	
	
	
}



