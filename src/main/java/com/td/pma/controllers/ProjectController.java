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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.pma.dto.TimeChartData;
import com.td.pma.entities.Employee;
import com.td.pma.entities.Project;
import com.td.pma.services.EmployeeService;
import com.td.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	
	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		List<Employee> employees=empService.getAll();
		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String createProject(Model model,@Valid Project project, Errors errors) {
		if(errors.hasErrors())
			return "projects/new-project";
		
		proService.save(project);
		return "redirect:/projects";
	}
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects=proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/update")
	public String displayProjectUpdateForm(@RequestParam("id") long theId, Model model) {
		Project project=proService.findById(theId);
		List<Employee> employees=empService.getAll();
		model.addAttribute("allEmployees", employees);
		model.addAttribute("project", project);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam("id") long theId, Model model) {
		Project project=proService.findById(theId);
		proService.delete(project);
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) {
		List<TimeChartData> timelineData=proService.getTimeData();
		
		// now let's convert projectData structure into a json structure for use in javascript
		ObjectMapper objectMapper=new ObjectMapper();
		String jsonTimelineString=null;
		try {
			jsonTimelineString = objectMapper.writeValueAsString(timelineData);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		model.addAttribute("projectTimeList",jsonTimelineString);
		
		return "projects/project-timelines";
	}

}
