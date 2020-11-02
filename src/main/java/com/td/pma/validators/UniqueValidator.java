package com.td.pma.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.td.pma.dao.EmployeeRepository;
import com.td.pma.entities.Employee;
import com.td.pma.services.EmployeeService;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Entered validation method");
		Employee emp=empRepo.findByEmail(value);
		
		if(emp!=null)
			return false;
		else
			return true;
		
	}

}
