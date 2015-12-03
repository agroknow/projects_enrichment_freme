package gr.agrisap.normalization;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Identifier {
	
	
	static Document doc;

	
	public  Boolean createIdentifier(String filepath ) {
			
			Document doc = getDocument();
			int counter =0;
			Element dc_subject = null;
			String langAttr;
			Boolean isAgResourse =false;
			//String label="";
				
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
				 NodeList listResourse = ags_resource.getChildNodes();
				//for each element
					for (int i = 0; i < listResourse.getLength(); i++) {
			                   Node node = listResourse.item(i);				 
					   if (!( "dc:identifier".equals(node.getNodeName()) ) ) {
						   counter++;
					   }
					}//listresourses , for each resource
					if (counter == listResourse.getLength()){
						 isAgResourse = true;
						for (int i = 0; i < listResourse.getLength(); i++) {
					   
						  
						   
						   Element     dc_identifier = doc.createElement("dc:identifier"); 
						   dc_identifier.setAttribute("xml:lang", "");

						   dc_subject.appendChild(doc.createTextNode(""));//.setNodeValue(subjects[k1]);							            
						            
						            ags_resource.appendChild(dc_subject);
						  // node.removeChild(subjectNode);
					   }//dc:description
					 		   
					}//end for each element
			
				
				  }//listresourses , for each resource
			return isAgResourse;
	
		    
		    //return isAgResourse; //return if it has description
		    
		    
		    
		  }//end 
 
	
	public static Document getDocument() {
		return doc;
	}
	
	public void setDocument(Document doc) {
		this.doc = doc;
	}

}
