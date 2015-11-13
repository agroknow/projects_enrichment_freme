package gr.agroknow.author;

import java.util.ArrayList;
import java.util.Collection;


public class Author {
	
	 String dummy;
	 String name;
	 String surname;
	 String orcid;
	 String organization;
	 String project;
	 String arn;
	 String uri;
	 static Collection<Author> authors = new ArrayList<Author>();
	 
	 
	 public String getAuthorDummy() { return dummy; }     
     public void setAuthorDummy(String dummy) {
			this.dummy = dummy;
	 }
	 
	 public String getAuthorName() { return name; }     
     public void setAuthorName(String name) {
			this.name = name;
	 }
     
     public String getAuthorLastName() { return surname; }     
     public void setAuthorLastName(String surname) {
			this.surname = surname;
	 }
	 
     public String getAuthorOrganization() { return organization; }     
     public void setAuthorOrganization(String organization) {
			this.organization = organization;
	 }
     
	 
     public String getAuthorArn() { return arn; }     
     public void setAuthorArn(String arn) {
			this.arn = arn;
	 }
     
     
     public String getAuthorProject() { return project; }     
     public void setAuthorProject(String project) {
			this.project = project;
	 }
     
	 public String getAuthorUri() { return uri; }
	 public void setAuthorUri(String uri) {
			this.uri = uri;
	 }
	 
	 
     public String getAuthorOrcid() { return orcid; }     
     public void setAuthorOrcid(String orcid) {
			this.orcid = orcid;
	 }
     
     
     public Collection<Author> getAuthorList() { return authors; }     
     
     public void addToAuthorList(Author author) {
    	 authors.add(author);
	 }
	
	
	
	
}
