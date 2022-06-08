package com.atlassian;

import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

import com.atlassian.jira.component.ComponentAccessor;

@Path("/main")
public class Rest {

    ProjectManager projectManager = ComponentAccessor.getProjectManager();
    String path = "http://localhost:8080";

    @GET
    @Path("projects")
    public String projects() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"data\":[");
        List<Project> projectList = projectManager.getProjects();
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            sb.append("[");
            sb.append("\"" + project.getId() + "\"");
            sb.append(",");
            sb.append("\"<a href=\\\"" + path + "/browse/" + project.getKey() + "\\\">" + project.getName() + "</a> \"");
            sb.append(",");
            sb.append("\"" + (project.getProjectCategory() == null ? "No Category" : project.getProjectCategory().getName()) + "\"");
            sb.append("]");
            if (i != projectList.size() - 1)
                sb.append(",");
        }
        sb.append("]}");
//        return "{\"data\":[[\"1\",\"hi\",\"bye\"]]}";
        return sb.toString();
    }
}
