package com.td.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.pma.dao.EmployeeRepository;
import com.td.pma.dao.ProjectRepository;
import com.td.pma.dto.ChartData;
import com.td.pma.dto.EmployeeProject;
import com.td.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		// quering the db for projects
		List<Project> projects=proRepo.findAll();
		model.addAttribute("projects", projects);
		
		List<ChartData> projectData=proRepo.getProjectStatus();
		
		// now let's convert projectData structure into a json structure for use in javascript
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectStatusCnt",jsonString);
		
		// quering the db for employees
		List<EmployeeProject> employeeProjectCnt=empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeeProjectCnt);
		
		return "main/home";
	}
	
	
}
