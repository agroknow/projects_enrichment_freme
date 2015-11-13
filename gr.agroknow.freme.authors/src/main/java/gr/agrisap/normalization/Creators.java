package gr.agrisap.normalization;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Creators {

	
	
	static Document doc;

	
	public  Boolean explodeCreators(String filepath ) {
			
			Document doc = getDocument();
			//Element dc_creator = null;
			Element dc_creator =null;
			Element newcreatorPersonal =null;
			Node ags_resource =null;
			Node node =null;
			Node nodecreatorPersonal=null;
			NodeList listCreators = null;
			NodeList listResourse= null;
			String creator = null;
			String langAttr;
			Boolean isAgResourse =false;
			Boolean hasSubject = false;
			//String label="";
		    ArrayList<String> CreatorsList = new  ArrayList<String>();
			isAgResourse = true;
			   hasSubject = true;
		    try {    	
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			//ags:resources
			//Element root = doc.getDocumentElement();
			//Node root = doc.getFirstChild();
			
			NodeList listResourses = doc.getElementsByTagName("ags:resource");//doc.getChildNodes();//list resources
			//for each resource
			for (int k = 0; k < listResourses.getLength(); k++) {  
				  ags_resource = listResourses.item(k);		
					//System.out.println("node name " + ags_resource.getNodeName() + "k==="+ k);
				  listResourse = ags_resource.getChildNodes();
				//for each element dc:creator
					for (int i = 0; i < listResourse.getLength(); i++) {
						       //hasSubject = false;
			                    node = listResourse.item(i);				 
					   if ("dc:creator".equals(node.getNodeName())) {		
						   //System.out.println(node.getNodeName() + " inside  -dccreator");
						    listCreators = node.getChildNodes();
						    if(listCreators.getLength() >1){
							   for (int c = 0; c < listCreators.getLength(); c++) {
								    nodecreatorPersonal = listCreators.item(c);
								       //   System.out.println(nodecreatorPersonal.getNodeValue() + " nodecreatorPersonal");
									   if ("ags:creatorPersonal".equals(nodecreatorPersonal.getNodeName())) {		
								           //  System.out.println(nodecreatorPersonal.getNodeName()+" inside  ---------ags:creatorPersonal");
										   isAgResourse = true;
										   hasSubject = true;
										   //Node authorNode = listCreators.item(c);
									   creator = nodecreatorPersonal.getTextContent();//.getNodeValue();
									   CreatorsList.add(creator);
									   System.out.println("creator :" + nodecreatorPersonal.getTextContent());
									   //write the creator new nodes
									  // node.removeChild(nodecreatorPersonal);
							           }//if     
						      }//ags:creatorPersonal   //list creators
							 //  ags_resource.removeChild(node);	  
					       }//check for >1 creatorPersonals
					   }//dc:creator

					   
					   if( CreatorsList.size() > 0 && listCreators.getLength() >1  ){////check if it is nalready normalized

							//Create elements 
						    for (int k1 = 0; k1 < CreatorsList.size(); k1++){
						    	//for (String crtr : CreatorsList) {
									dc_creator = doc.createElement("dc:creator");
									newcreatorPersonal = doc.createElement("ags:creatorPersonal");
									newcreatorPersonal.appendChild(doc.createTextNode(CreatorsList.get(k1).toString()));
									//newcreatorPersonal.appendChild(doc.createTextNode(crtr));
									//newcreatorPersonal.setNodeValue(crtr);
									
									//doc.createTextNode(CreatorsList.get(k1).trim())
									//dc_creator.appendChild(doc.createTextNode(CreatorsList.get(k1).trim()));//.setNodeValue(subjects[k1]);							            
									dc_creator.appendChild(newcreatorPersonal);
									 //in order to append to next of subject
						            //ags_resource.insertBefore(dc_creator, ags_resource.getNextSibling());
						            ags_resource.appendChild(dc_creator);
							    }
						    	doc.normalize();

						    	listResourse.item(i).removeChild(nodecreatorPersonal);
						    //	listResourse.item(i).removeChild(node);
						    	ags_resource.removeChild(node);
					   }//end if check 
					  // ags_resource.removeChild(node);	
					  // listResourse=null;
					
					}//listresourse end for each element
					//ags_resource=null;
					
				  }//listresourses , for each resource
			   //listResourses=null;
		    } catch (Exception e) {
			   System.out.println(e.getMessage());
		    }
		 
		    
		    
		    

		    return isAgResourse; //return if it has description
		    
		    
		    
		  }//end 
 
	
	public static Document getDocument() {
		return doc;
	}
	
	public void setDocument(Document doc) {
		this.doc = doc;
	}

	
	
	
	
	
	
}
