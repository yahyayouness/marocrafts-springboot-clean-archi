package com.bnpparibas.creditconso.api.projectsassistant.boundary;

import com.bnpparibas.creditconso.api.projectsassistant.request.DeleteProjectRequest;
import com.bnpparibas.creditconso.api.projectsassistant.response.DeleteProjectResponse;

@FunctionalInterface
public interface IDeleteProject {

		void execute(DeleteProjectRequest request, DeleteProjectResponse response);
}
