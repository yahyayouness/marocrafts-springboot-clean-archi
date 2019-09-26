package com.bnpparibas.creditconso.api.projectsassistant.boundary;

import com.bnpparibas.creditconso.api.projectsassistant.request.SaveProjectRequest;
import com.bnpparibas.creditconso.api.projectsassistant.response.SaveProjectResponse;

public interface IUpdateProject {

	void execute(SaveProjectRequest request, SaveProjectResponse response);

}
