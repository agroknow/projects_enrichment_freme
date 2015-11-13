package gr.agroknow.freme.service;

import gr.agroknow.author.Author;
import gr.agroknow.manipulation.jsonld.*;

import java.io.File;
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
	 * args filemath: xml file of agrisap
	 * return void
	 * takes as parameter filepath of xml file
	 * read all ags:resource nodes and appends authors info
	 * with values based on calling freme service
	 * 
	 * 
	 * 
	 * */
	
	
	public static Boolean createAgrovoElement(String filepath ) {
		
		ClientResponse clResponse;
		ParseResponse res = new ParseResponse();
		Document doc = getDocument();
		 FremeAuthorClient fremeClient = new FremeAuthorClient();
		String ags_creatorPersonal[] ={""};
		String uri="";
		Boolean isAgResourse =false;
		Author author = new Author();
		//agrovc.setAgrovocLabel(label);
		author.setAuthorUri(uri);
		ArrayList<String> myAuthorURIlist = new  ArrayList<String>();
	 //   Map<String, Author> agrovocsUri = new HashMap<String, Author>();
        String outputClResp;
        Element nameIdentifier;	
        Element affiliation;
        Element geolocation = null;
       // String auth=null;
        String entityValue=null;
        String conf=null;
        String entity=null;
        String orcid = null;
        
        
        
        
        
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
				//System.out.println("node name " + ags_resource.getNodeName() + "k==="+ k);
			    // myAgrovoURIlist = null;			
			 NodeList listResourse = ags_resource.getChildNodes();
			 String creatorPersonal[] =null;
			//for each element
				for (int i = 0; i < listResourse.getLength(); i++) {
		                   Node node = listResourse.item(i);
		                   //System.out.println(node.getNodeName());
				   if ("dc:creator".equals(node.getNodeName())) {		
                       ////    System.out.println(node.getNodeName() + " inside if i -dcdescription");
					   System.out.println(" inside dc:creator"+node.getNodeName()); 
					   //OKdcterms_abst = node.getTextContent();
					   isAgResourse = true;
					   if(node.hasChildNodes()){
						  // System.out.println(" has child nodes "); 
						   
					   NodeList authorList = node.getChildNodes();
					   //foreach author
					   for (int j = 0; j < authorList.getLength(); j++) {
						   Node nodeAuthor = authorList.item(j);
						  // System.out.println(" inside ++ ags:creatorPersonal"+ nodeAuthor.getNodeName());
						   if ("ags:creatorPersonal".equals(nodeAuthor.getNodeName())){ 
							   System.out.println("-- inside ags:creatorPersonal" + nodeAuthor.getTextContent()+ " <-gettext"); 
							   //get author from xml file
							   //   creatorPersonal[j] = nodeAuthor.getTextContent();//.getTextContent();
						      //call here the client   
						      fremeClient= new FremeAuthorClient();
						      //get FREME responce for the given author
					          clResponse = fremeClient.postText(nodeAuthor.getTextContent(),"en","en");
					          clResponse.bufferEntity();
					          outputClResp = clResponse.getEntity(String.class);
					          //get responce 
					          myAuthorURIlist = (ArrayList<String>) res.getAuthor(outputClResp);
					          
					          ///////taClassRef+ " " +orcid.trim() + " " + authorName.trim()+tmpConfFloat);
					          for (int z = 0; z < authorList.getLength(); z++) {
					          String[] toLabel3 = myAuthorURIlist.get(z).toString().split(" ");
								 entity = toLabel3[0];
								 orcid = toLabel3[1];
								 entityValue = toLabel3[2];
								 conf = toLabel3[3];
								 float f_cond = Float.parseFloat(conf);
						         System.out.println("-- inside entityValue" +entityValue+ "bla bla"); 
					          
					          ///////
					          System.out.println("-- inside ags:creatorPersonal" +myAuthorURIlist.get(1).toString()+ "bla bla"); 
					          
					            //dc_subject.appendChild(dc_subject);         
					        
					          entity = (String)entity;
				//ok	          if(){//check 
					          if(entity.contains("Organization")){
					        	  affiliation =  doc.createElement("Organization");
					              affiliation.appendChild(doc.createTextNode(entityValue));
					          }else if (entity.contains("Location")){
					        	  geolocation.appendChild(doc.createTextNode(entityValue));
					          }else {
					        	  
					        	  nameIdentifier = doc.createElement("nameIdentifier"); 
						          
						          //nameIdentifier.appendChild(doc.createTextNode(myAuthorURIlist.get(1).toString()));//doc.createTextNode(mylist.get(i).toString())
						          nameIdentifier.appendChild(doc.createTextNode(orcid));//doc.createTextNode(mylist.get(i).toString())					          
						          nameIdentifier.setAttribute("schemeURI","http://orcid.org/");
						          nameIdentifier.setAttribute("nameIdentifierScheme","ORCID");
						          node.appendChild(nameIdentifier);
					          }
				//ok	          }
					          doc.getDocumentElement().normalize();
					          
					         // node.appendChild(nameIdentifier);
					          nodeAuthor=null;
					          node=null;
					          fremeClient=null;
					          }//z for
					      }
					   }//for j   
					   }//haschild
					   //call the freme utilities
					
				   }//dc:creator 

				}//end for each element
				fremeClient=null;
			//	ags_creatorPersonal =  creatorPersonal;//get abstract			
			////////////////////////////////////////////////////			
			//abstr =   getXMLabstract(ags_resource);//get abstract			
			/*
			 * test out
			 * */
			System.out.println("call freme client");					
	

			 // if(myAgrovoURIlist!=null)  {
		    	//Create elements dc:subject with AGROVOC uri
//			    for (int i = 0; i < 1; i++) {//myAuthorURIlist.size()
//					//System.out.println("list" + mylist.get(i).toString());
//					System.out.println("testtttttt"+ myAuthorURIlist.get(i).toString()+"testtt");
//					// agrovoc elements
//		            Element nameIdentifier = doc.createElement("nameIdentifier");            
//		            //Element newAgrovoc = doc.createElement("ags:subjectThesaurus");
//		            //enrich with author
//		            
//		            nameIdentifier.appendChild(doc.createTextNode(myAuthorURIlist.get(i).toString()));//doc.createTextNode(mylist.get(i).toString())
//		            nameIdentifier.setAttribute("schemeURI","http://orcid.org/");
//		            nameIdentifier.setAttribute("nameIdentifierScheme","ORCID");
//		            //dc_subject.appendChild(dc_subject);         
//		            ags_resource.appendChild(nameIdentifier);
//				}

			  }//listresourses , for each resource
			
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
