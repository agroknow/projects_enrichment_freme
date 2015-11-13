package gr.agrisap.normalization;

import gr.agroknow.manipulation.jsonld.ParseResponse;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;








public class Subject {
	
	static Document doc;

	
		public  Boolean explodeSubject(String filepath ) {
				
				Document doc = getDocument();
				Element dc_subject = null;
				String langAttr;
				Boolean isAgResourse =false;
				Boolean hasSubject = false;
				//String label="";
				ArrayList<String> SubjectsList = new  ArrayList<String>();
					
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
					 Node ags_resource = listResourses.item(k);		
						//System.out.println("node name " + ags_resource.getNodeName() + "k==="+ k);
					 NodeList listResourse = ags_resource.getChildNodes();
					//for each element
						for (int i = 0; i < listResourse.getLength(); i++) {
							       hasSubject = false;
				                   Node node = listResourse.item(i);				 
						   if ("dc:subject".equals(node.getNodeName())) {		
							   System.out.println(node.getNodeName() + " inside  -dc:subject");
							   //OKdcterms_abst = node.getTextContent();
							   isAgResourse = true;
							   hasSubject = true;
							   Node subjectNode = node;
							   String subject = subjectNode.getTextContent();//.getNodeValue();
							   System.out.println("subject :" + subject);
							  // System.out.println (java.util.Arrays.toString(subject.split("\\;", -1)));
							   String[] subjects = subject.split("\\;");
							   NamedNodeMap attr = node.getAttributes();
							   Node nodeAttr = attr.getNamedItem("xml:lang");
							   System.out.println(nodeAttr.getNodeValue().toString()+"    dcjwkjc");
							   
							   node.setTextContent(subjects[0]);
//							   subjectNode.setNodeValue(subjects[0]);
							   System.out.println("subjects[0]" + subjects[0] + "subject :" + subject + "node.getTextContent =" + node.getTextContent());
							   
							   if( subjects.length > 0 ){
									//Create elements dc:subject with AGROVOC uri
								    for (int k1 = 1; k1 < subjects.length; k1++) {
										System.out.println("subjects[k1]" +subjects[k1] + "k1====="+k1);
							            dc_subject = doc.createElement("dc:subject"); 
							            if(nodeAttr.getNodeValue().toString()!=null){
											   langAttr = nodeAttr.getNodeValue().toString();
							                   dc_subject.setAttribute("xml:lang", langAttr);
							            }
							            dc_subject.appendChild(doc.createTextNode(subjects[k1].trim()));//.setNodeValue(subjects[k1]);							            
							            //ags_resource.appendChild(dc_subject);OK
							            //in order to append to next of subject
							            ags_resource.insertBefore(dc_subject, node.getNextSibling());

									}
							   }
							  // node.removeChild(subjectNode);
						   }//dc:description
						 		   
						}//end for each element
				

					  }//listresourses , for each resource
					
			    } catch (Exception e) {
				   System.out.println(e.getMessage());
			    }
			 
			    return true;
			    //return isAgResourse; //return if it has description
			    
			    
			    
			  }//end 
	 
		
		public static Document getDocument() {
			return doc;
		}
		
		public void setDocument(Document doc) {
			this.doc = doc;
		}

}
