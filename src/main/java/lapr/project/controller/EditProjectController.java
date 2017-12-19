package lapr.project.controller;

import lapr.project.model.Project;
import lapr.project.utils.Session;

public class EditProjectController {

    private Project project;

    public EditProjectController() {
        project = Session.getActiveProject();
//        project = new Project("This is a Project", "Great Description!!!");
    }

    public void editNewProject(String name , String description){
        project.setName(name);
        project.setDescription(description);
    }
    
    public String getActiveProjectName(){
        return project.getName();
    }
    public Project getActiveProject(){
        return project;
    }
}
