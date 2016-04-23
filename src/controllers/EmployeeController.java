package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	/*
	 * TODO:
	 * stuff i need to have from forms
	 * pick an action (go to which page(
	 * findEmployees
	 * addEmployee
	 * modifyEmployee
	 * removeEmployee
	 */
	
	@RequestMapping("menu.do")
	private ModelAndView selectMenu(@RequestParam("choice") String choice){
		ModelAndView mv = new ModelAndView();
		System.out.println("choice: " + choice);
		
		//todo: switch to change
		switch (choice) {
		case "search":
			mv.addObject("EmployeeQuery",new EmployeeQuery());
			mv.setViewName("search.jsp");
			break;
		case "add":
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
		
		//TODO DEBUGS:
			System.out.println("search.do ... ");
			System.out.println(employeeQuery.getId());
			System.out.println(employeeQuery.getFirstname());
			System.out.println(employeeQuery.getLastname());
		Results results = dao.getEmployees(employeeQuery);
		
			
		mv.addObject(results);	
		mv.addObject("results",results);
		mv.addObject("EmployeeQuery", employeeQuery);
		mv.setViewName("search.jsp");
		return mv;
	}
	
	@RequestMapping("add.do")
	private ModelAndView searchEmployees(Employee employee){
		ModelAndView mv = new ModelAndView();
		employee.setHiredate();
		Results results = dao.addEmployee(employee);
		
		mv.addObject("results",results);
		mv.addObject("Employee",new Employee());
		mv.setViewName("add.jsp");
		
		
		return mv;
		
	}
	
	
}
