package com.td.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.td.pma.dao.EmployeeRepository;
import com.td.pma.dao.ProjectRepository;
import com.td.pma.dto.ChartData;
import com.td.pma.dto.TimeChartData;
import com.td.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proRepo;

	public void save(Project project) {
		proRepo.save(project);
	}

	public List<Project> getAll() {
		return proRepo.findAll();
	}

	public Project findById(long theId) {
		return proRepo.findById(theId);
	}

	public void delete(Project project) {
		proRepo.delete(project);
	}
	
	public List<ChartData> getProjectStatus(){
		return proRepo.getProjectStatus();
	}
	
	public List<TimeChartData> getTimeData(){
		return proRepo.getTimeData();
	}
	
}
