package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.Employee;
import data.EmployeeDAO;
import data.EmployeeQuery;
import data.Results;

@Controller
public class EmployeeController {

	
	
	@Autowired
	private EmployeeDAO dao;
	

	//make a menu choice
	@RequestMapping("menu.do")
	private ModelAndView selectMenu(@RequestParam("choice") String choice){
		ModelAndView mv = new ModelAndView();
		System.out.println("choice: " + choice);
		
		//do the corresponding action based on which
		//button the suer checked
		switch (choice) {
		case "search":
			//add a query object to the model view, and go
			//to the serach page
			mv.addObject("EmployeeQuery",new EmployeeQuery());
			mv.setViewName("search.jsp");
			break;
		case "add":
			//add jobs and departments objects, so the
			//jsp can get their Lists, and iterate over them
			//also pass a new employee object to modify.
			mv.addObject("Jobs",dao.getJobs());
			mv.addObject("Departments",dao.getDepartments());
			mv.addObject("Employee", new Employee());
			mv.setViewName("add.jsp");
			break;

		default:
			mv.setViewName("index.jsp");
			break;
		}
		
		return mv;
	}
	
	@RequestMapping("search.do")
	private ModelAndView searchEmployees(EmployeeQuery employeeQuery){
		ModelAndView mv = new ModelAndView();
		//get the results of the employee query
		Results results = dao.getEmployees(employeeQuery);
		
		//add the results to the model view	
		mv.addObject("results",results);
		//add the query to the model view
		mv.addObject("EmployeeQuery", employeeQuery);
		mv.setViewName("search.jsp");
		return mv;
	}
	
	//add an employee
	@RequestMapping("add.do")
	private ModelAndView searchEmployees(@Valid Employee employee, Errors errors){
		ModelAndView mv = new ModelAndView();
		mv.addObject("Jobs",dao.getJobs());
		mv.addObject("Departments",dao.getDepartments());

		if (errors.getErrorCount() != 0){
			mv.addObject("Employee", employee);
			mv.setViewName("add.jsp");
			System.out.println(errors);
			mv.addObject("invalidData","Invalid Parameters. Please check all parameters and try again.");
			return mv;
			}
		//set the hire date (the year, month, and date
		//is entered separately, and the setHireDate()
		//method concatenates them and adds them to the
		//hiredate field.
		employee.setHiredate();

		Results results = dao.addEmployee(employee);
		//and results, jobs, departments, and a new employee to the
		//add form, and go to add.jsp
		mv.addObject("results",results);
		mv.addObject("Employee",new Employee());
		mv.setViewName("add.jsp");
		
		
		return mv;
	}
	
	//remove an employee
	@RequestMapping("deleteEmployee.do")
	private ModelAndView deleteEmployee(@RequestParam("idToModify") String idToModify){
		//change the string ID to an integer id (the id comes
		//from the results String array list in the search results,
		//so it must be converted back to an integer
		int id = Integer.parseInt(idToModify);
		ModelAndView mv = new ModelAndView();
		//remove the employee
		dao.removeEmployee(id);
		//return to the search 
		mv.addObject("EmployeeQuery",new EmployeeQuery());
		mv.addObject("message","Employee removed.");
		mv.setViewName("search.jsp");
		
		return mv;
	}
	
	//change an employee
	@RequestMapping("modifyEmployee.do")
	private ModelAndView modifyEmployee(@RequestParam("idToModify") String idToModify){
		int id = Integer.parseInt(idToModify);
		ModelAndView mv = new ModelAndView();
		//get an employee to change by the id.
		Employee employee = dao.getEmployee(id);
		
		//add jobs and departments for their lists
		//to populate the dropdowns
		mv.addObject("Jobs",dao.getJobs());
		mv.addObject("Departments",dao.getDepartments());
		//add an employee and go to the modify page
		mv.addObject("Employee",employee);
		mv.setViewName("modify.jsp");
		return mv;
	}
	
	//perform the modification
	@RequestMapping("modify.do")
	private ModelAndView submitModification(@Valid Employee employee, Errors errors){
		ModelAndView mv = new ModelAndView();
		mv.addObject("Jobs",dao.getJobs());
		mv.addObject("Departments",dao.getDepartments());
		
		
		if (errors.getErrorCount() != 0){
			mv.addObject("Employee", employee);
			mv.setViewName("modify.jsp");
			System.out.println(errors);
			mv.addObject("invalidData","Invalid Parameters. Please check all parameters and try again.");
			return mv;
			}
		//modify the employee and save the result (modifyEmployee returns
		//a result)
		Results results = dao.modifyEmployee(employee.getId(), employee);
		//and the relevenat objects, and return the the modify page.
		mv.addObject("results",results);
		mv.addObject("Employee", employee);
		mv.setViewName("modify.jsp");
		
		return mv;
		
	}
	
	
}
