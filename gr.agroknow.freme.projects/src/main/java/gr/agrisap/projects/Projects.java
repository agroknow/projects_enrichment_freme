package gr.agrisap.projects;

import java.util.ArrayList;
import java.util.Collection;

public class Projects {
	String uri;
	 String label;
	 static Collection<Projects> projects = new ArrayList<Projects>();
	 
	 public String getProjectUri() { return uri; }
	 public void setProjectUri(String uri) {
			this.uri = uri;
	 }
	 
	 
    public String getProjectUriLabel() { return label; }     
    public void setProjectLabel(String label) {
			this.label = label;
	 }
    
    
    public Collection<Projects> getProjectList() { return projects; }     
    
    public void addToProjectList(Projects project) {
			
   	 projects.add(project);
   	 
   	 
	 }
    
 
}
