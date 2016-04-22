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
}
