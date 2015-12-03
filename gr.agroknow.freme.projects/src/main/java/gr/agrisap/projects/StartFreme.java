package gr.agrisap.projects;

import gr.agroknow.config.ParamManager;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;




public class StartFreme {

	public static void main(String[] args) {
		
		ParamManager.getInstance().setParam( args ) ;
		if(args[0]==null){
			 System.out.println("Please set projects input path (without trailing slash)!");	
			 System.out.println("java -jar <java_name.jar> <projects path> <CSV PATH> <DATASET> ");
		}else{
			
		
			//File inputDirectory = new File( "C:\\Users\\papou_000\\Desktop\\agroknow\\freme\\projects") ;
			File inputDirectory = new File( args[0].toString()) ;
			Boolean isAgResourse =false;
		    // String filepath = "C:\\Users\\papou_000\\Desktop\\agroknow\\test_list";
		for (final File fileEntry : inputDirectory.listFiles() )
		{
				//			 System.out.println(fileEntry.getName() );
				//			 System.out.println(fileEntry.getPath());
				//			 System.out.println(fileEntry.getParent());
	      try {  	  
		     DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
     	     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		     Document doc = docBuilder.parse(fileEntry.getPath());
		
		     ReadXMLFile readxml = new  ReadXMLFile();
		     readxml.setDocument(doc); //pass the xml document to parser
		     isAgResourse = readxml.createProjectElement(fileEntry.getPath() );
		     
				  //call the freme utilities
				  // FREMEClientAgrovoc fremeClient= new FREMEClientAgrovoc();
				  //postText
				  // fremeClient.postText(node.getTextContent(), "en", "en");		
				  //http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
				  // write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(fileEntry.getPath()));
				transformer.transform(source, result);
		 
				if (!isAgResourse){
					System.out.println("This file has not a agDescription or it is not agrisAP file");
				}else{
				    System.out.println("Record Enrichment Done");
				}
				
	    } catch (ParserConfigurationException pce) {
		 pce.printStackTrace();
	    } catch (TransformerException tfe) {
		 tfe.printStackTrace();
	    } catch (IOException ioe) {
		 ioe.printStackTrace();
	    } catch (SAXException sae) {
		 sae.printStackTrace();
	    }
		
	    System.out.println("Agroknow AKStem Projects Enrichment Done");
	}//check parameters
	
	    

  }//files
 }//end if
	
}
