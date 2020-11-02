package com.td.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.pma.dao.EmployeeRepository;
import com.td.pma.dto.EmployeeProject;
import com.td.pma.entities.Employee;
import com.td.pma.entities.Project;

@Service
public class EmployeeService {
	
	
	@Autowired
	EmployeeRepository empRepo;

	public List<Employee> getAll() {
		return empRepo.findAll();
	}
	

	public void save(Employee emp) {
		empRepo.save(emp);
	}
	
	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}
	
	public Employee findByEmployeeId(long empId) {
		return empRepo.findByEmployeeId(empId);
		}


	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
	}
	
	
	
}
