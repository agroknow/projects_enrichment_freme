package gr.agrisap.projects;

import gr.agroknow.manipulation.jsonld.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;








//import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
public class ReadXMLFile {
	
	static Document doc;
	
	public static Document getDocument() {
		return doc;
	}

	public void setDocument(Document doc) {
		this.doc = doc;
	}
	
	/* function createAgrovoElement 
	 * args filepath: xml file of agrisap
	 * return void
	 * takes as parameter filepath of xml file
	 * read all ags:resource nodes and appends 
	 * additional information for authors
	 * with values based on calling freme service
	 * 
	 * 
	 * 
	 * */
	
	
	public static Boolean createAgrovoElement(String filepath ) {
		
		ClientResponse clResponse;
		ParseResponse res = new ParseResponse();
		Document doc = getDocument();
		File file =new File("C:\\Users\\papou_000\\Desktop\\agroknow\\freme\\projects.csv");
		String abstr;
		String uri="";
		String projects[] = null;
		Boolean isAgResourse =false;
		//String label="";
		Projects projct = new Projects();
		projct.setProjectUri(uri);
		ArrayList<String> myProjectsIDlist = new  ArrayList<String>();
		String outputClResp;
		//String projects[] = null;	
		String projectURI=null;
		String projectID=null;
	    try {
	 /*
		File file = new File(filepath);	 
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
	                             .newDocumentBuilder();		
		 doc = dBuilder.parse(file);
		*/	    	
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		//ags:resources
		//Element root = doc.getDocumentElement();
		//Node root = doc.getFirstChild();
		
		NodeList listResourses = doc.getElementsByTagName("ags:resource");//doc.getChildNodes();//list resources
		//for each resource
		for (int k = 0; k < listResourses.getLength(); k++) {  
			 Node ags_resource = listResourses.item(k);		
			 Node tr =  ags_resource.getAttributes().getNamedItem("ags:ARN"); 			   			   
			 NodeList listResourse = ags_resource.getChildNodes();
			 String dcterms_abst ="";
			//for each element
				for (int i = 0; i < listResourse.getLength(); i++) {
		                   Node node = listResourse.item(i);
		                   //System.out.println(node.getNodeName());
				   if ("dc:description".equals(node.getNodeName())) {		
					   	/////////	   System.out.println(node.getNodeName() + " inside if i -dcdescription");
					   //OKdcterms_abst = node.getTextContent();
					   isAgResourse = true;
					   NodeList descriptionList = node.getChildNodes();
					   
					   for (int j = 0; j < descriptionList.getLength(); j++) {
						   Node nodeAbstr = descriptionList.item(j);
						   ///////System.out.println(nodeAbstr.getNodeName() + " -dcabstractttt");
						   if ("dcterms:abstract".equals(nodeAbstr.getNodeName())) 
							   dcterms_abst = nodeAbstr.getTextContent();
					   }
					   //call the freme utilities
					  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
					   //postText
					  // fremeClient.postText(node.getTextContent(), "en", "en");
				   }//dc:description
		      
				   /* explode to ; 
				    * */
				}//end for each element
				
			abstr =  dcterms_abst;//get abstract			
			////////////////////////////////////////////////////			
			//abstr =   getXMLabstract(ags_resource);//get abstract			
			/*
			 * test out
			 * */
			System.out.println("call freme client");					
			//call the freme utilities
		    FREMEProjectsClient fremeClient= new FREMEProjectsClient();
			   //postText
		    
		    //System.out.println("call abstr" + abstr);		
		    clResponse = fremeClient.postText(abstr,"en","en");	 //outputClResp = clResponse.getEntity(String.class);	    
		    clResponse.bufferEntity();
		    outputClResp = clResponse.getEntity(String.class);
	////		System.out.println("clientresponse" + outputClResp); //System.out.println("ParseResponse");	
		    if (outputClResp!=null){
			myProjectsIDlist = (ArrayList<String>) res.getAgrovoc(outputClResp);	    
		    
			//check client response
	     	//  if (clResponse!=null){}    
		    //Do some staff here with jsonld
		    
			 // if(myAgrovoURIlist!=null)  {
		    	//Create elements dc:subject with AGROVOC uri
			    for (int i = 0; i < myProjectsIDlist.size(); i++) {
					//System.out.println("\""+ myAgrovoURIlist.get(i).toString()+"\":");
					// agrovoc elements
		            Element dc_relation = doc.createElement("dc:relation");            
		            //enrich with agrovoc
		            projectURI= myProjectsIDlist.get(i).toString();
		              
						 String[] toUri = projectURI.split("/");
						 projectID = (String)toUri[toUri.length-2];
		//οκ				 System.out.println("projects idde : "+toUri[toUri.length-1]);
						 if(projectID.contains("projects")){
						    //System.out.println("projects idde : "+toUri[toUri.length-2]);
							 projectID = (String)toUri[toUri.length-1];
			                 //System.out.println("Resource: "+  tr.toString()  +  " project id : "+projectID +"  project url: http://cordis.europa.eu/project/rcn/"+projectID+"_en.html ," + abstr);

							 
					    		
					    		//if file doesnt exists, then create it
					    		if(!file.exists()){
					    			file.createNewFile();
					    		}
					    		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\papou_000\\Desktop\\agroknow\\freme\\projects.csv", true)));
					    		 out.println(tr.getNodeValue().toString()  +  " , "+projectID + " ,  http://cordis.europa.eu/project/rcn/"+projectID+"_en.html , " + abstr);
					    		out.close();
					    		// FileWriter fileWritter = new FileWriter("C:\\Users\\papou_000\\Desktop\\agroknow\\freme\\"+file.getName(),true);
//				    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//				    	        bufferWritter.write(tr.toString()  +  " , "+projectID + "  project url: http://cordis.europa.eu/project/rcn/"+projectID+"_en.html , " + abstr);
//				    	        bufferWritter.close();
				    	    
							 
							 
							 
			               System.out.println(tr.toString()  +  " , "+projectID + "  project url: http://cordis.europa.eu/project/rcn/"+projectID+"_en.html , " + abstr);
							// valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes //if(valuelabel.trim()!=null)
							  //add to list  agrovocUriList.add(valuelabel.trim()); 						 projectID="http://cordis.europa.eu/project/rcn/"+projectID+"_en.html";
			                 dc_relation.appendChild(doc.createTextNode(projectID));//doc.createTextNode(mylist.get(i).toString())
			                 //dc_relation.appendChild(doc.createTextNode("http://cordis.europa.eu/project/rcn/"+projectID+"_en.html);
			                	 
			                 ags_resource.appendChild(dc_relation);
			                 doc.getDocumentElement().normalize();
						 }
						 
						 
//						 projects[i] = toUri[toUri.length-1];
//						 System.out.println("projects id : "+projects[i]);
//						// valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes
//						 //if(valuelabel.trim()!=null)
//						  //add to list  agrovocUriList.add(valuelabel.trim());
//		                 projects[i]="http://cordis.europa.eu/project/rcn/"+projects[i]+"_en.html";
//		                 System.out.println("projects idde : "+projects[i]);
//		            dc_relation.appendChild(doc.createTextNode(projects[i]));//doc.createTextNode(mylist.get(i).toString())
//
//		            ags_resource.appendChild(dc_relation);
//		            
		            toUri=null;
		            projects=null;
		            projectID=null;
				}
			 // }//check if response exists 
								

			  }//listresourses , for each resource
		 }//if not null the response else {write the bad request to a file}
	    } catch (Exception e) {
		   System.out.println(e.getMessage());
	    }
	 
	    
	    return isAgResourse; //return if it has description
	    
	    
	    
	  }//end 
	 
		
	
	

	/*A function that grabs the abstract from AgrisAP original file  
	 * in order to post to freme services.
	 * 
	 * Input  Node ags:resource
	 * Output String abastract 
	 * 
	 * 
	 * 
	 */
	private static String getXMLabstract(Node ags_resource){
		
		String dcterms_abstract ="";
		NodeList list = ags_resource.getChildNodes();
		 
		for (int i = 0; i < list.getLength(); i++) {
                   Node node = list.item(i);
 
		   // get the salary element, and update the value
		   if ("dcterms:abstract".equals(node.getNodeName())) {		
			   dcterms_abstract = node.getTextContent();
			   //call the freme utilities
			  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
			   //postText
			  // fremeClient.postText(node.getTextContent(), "en", "en");		   
			   /*
			    * test
			    * 
			    * */
			   System.out.println(dcterms_abstract);
			   
		   }
      
		}
		
		return dcterms_abstract;
				
		
	}
	
	

	
	
	  //get xml 
	  private static void printNote(NodeList nodeList) {
	 
	    for (int count = 0; count < nodeList.getLength(); count++) {
	 
		Node tempNode = nodeList.item(count);
	 
		// make sure it's element node.
		if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
	 
			// get node name and value
			System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			System.out.println("Node Value =" + tempNode.getTextContent());
	 
			if (tempNode.hasAttributes()) {
	 
				// get attributes names and values
				NamedNodeMap nodeMap = tempNode.getAttributes();
	 
				for (int i = 0; i < nodeMap.getLength(); i++) {
	 
					Node node = nodeMap.item(i);
					System.out.println("attr name : " + node.getNodeName());
					System.out.println("attr value : " + node.getNodeValue());
	 
				}
	 
			}
	 
			if (tempNode.hasChildNodes()) {
	 
				// loop again if has child nodes
				printNote(tempNode.getChildNodes());
	 
			}
	 
			System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	 
		}
	 
	    }
	 
	  }
	 

}
