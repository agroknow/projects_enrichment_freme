package gr.agrisap.normalization;

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





public class Normalize {
public static void main(String[] args) {
		
		//ParamManager.getInstance().setParam( args ) ;
		File inputDirectory = new File( "C:\\Users\\papou_000\\Desktop\\agroknow\\test_list") ;
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
		
		     Creators readxml = new  Creators();
		    // Subject readxml = new  Subject();
		     readxml.setDocument(doc); //pass the xml document to parser
		    // isAgResourse = readxml.explodeSubject(fileEntry.getPath() );//explodeCreators
		     isAgResourse = readxml.explodeCreators(fileEntry.getPath() );
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(fileEntry.getPath()));
				transformer.transform(source, result);
		 
				if (!isAgResourse){
					System.out.println("This file has not a agDescription or it is not agrisAP file");
				}else{
				    System.out.println("Record Normalization for authors done");
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
		
	}
	
	    System.out.println("Agroknow AKStem Subjects Explode Done");
  }

}