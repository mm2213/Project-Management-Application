package com.td.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.td.pma.dto.ChartData;
import com.td.pma.dto.TimeChartData;
import com.td.pma.entities.Project;

@RepositoryRestResource(collectionResourceRel="apiprojects", path="apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value="SELECT stage as Label, COUNT(project_id) as Value "
			+ "FROM project "
			+ "GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	public Project findById(long id);
	
	@Query(nativeQuery = true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate "
			+ "FROM project WHERE start_date is NOT NULL")
	public List<TimeChartData> getTimeData();
	
}
