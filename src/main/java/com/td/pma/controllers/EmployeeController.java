package com.td.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.td.pma.entities.Employee;
import com.td.pma.services.EmployeeService;

	@Controller
	@RequestMapping("/employees")
	public class EmployeeController {
		
		@Autowired
		EmployeeService empService;
		
		@GetMapping
		public String displayEmployees(Model model) {
			List<Employee> employees=empService.getAll();
			model.addAttribute("employees", employees);
			return "employees/list-employees";
		}
		
		@GetMapping("/new")
		public String displayEmployeeForm(Model model) {
			model.addAttribute("employee", new Employee());
			return "employees/new-employee";
		}
		
		@RequestMapping(value="/save", method=RequestMethod.POST)
		public String createEmployee(Model model,@Valid Employee emp, Errors errors) {
			if(errors.hasErrors())
				return "employees/new-employee";
			
			empService.save(emp);
			return "redirect:/employees";
		}
		
		@RequestMapping(value="/update", method=RequestMethod.GET)
		public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
			Employee theEmp=empService.findByEmployeeId(theId);
			model.addAttribute("employee", theEmp);
			return "employees/new-employee";
		}
		
		@RequestMapping(value="/delete", method=RequestMethod.GET)
		public String deleteEmployee(@RequestParam("id") long theId, Model model) {
			Employee theEmp=empService.findByEmployeeId(theId);
			empService.delete(theEmp);
			return "redirect:/employees";
		}
	
}
