package com.bnpparibas.creditconso.api.projectsassistant.boundary;

import java.util.List;

import com.bnpparibas.creditconso.api.projectsassistant.response.ProjectResponse;

public interface IGetProjects {

	List<ProjectResponse> getProjects(int pageNumber, int elementNumber);

	List<ProjectResponse> getProjectsByClient(Long idClient, int pageNumber, int elementNumber);

	Long getTotalAmountByClient(Long idClient);

	ProjectResponse getProject(Long idClient);

	List<Object[]> getProjects(String search, List<String> fields);

}
