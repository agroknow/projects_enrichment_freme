package gr.agroknow.manipulation.jsonld;

import gr.agroknow.author.Author;






import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.ClientResponse;
import org.json.simple.JSONArray;
//object
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

//import com.google.gson.Gson;





public class ParseResponse {

	//best to return agrovoc object
	/*ClientResponse responseObj JSONObject responseObj*/
    public static ArrayList<String>  getAuthor(String responseObj) throws JsonParseException, JsonMappingException, IOException, ParseException{
	//public static void main(String[] args)  {	
        List<String> authorOrcidListist = new ArrayList<String>();
        String arrayLabell = new String();
        Author ag = new Author();
        
    	JSONParser parser = new JSONParser();	 	
    	 //Map<String, String> agrovocsUri = new HashMap<String, String>();
    	Object context = new Object();
    	Object obj = new Object();
    	JSONObject	 cntx = new JSONObject();
    	JSONObject jsonObject = new JSONObject();
    	float personConfid = 0;
    	String aartaClassRef[];
    	String tmpConfFloat=null;
    	String taClassRef=null;
    	String locationConf = null;
		String orcid =null;
		String[] location = null;
		String[] person = null;
		String authorName=null;
		String entityValue=null;
		String str="Person";
		 boolean blnFound=false;
    	//authorOrcidListist = null;
		try {
    	 System.out.println("*********getAuthor()**************** ");
		//FILE Object obj = parser.parse(new FileReader("C:\\Users\\papou_000\\Desktop\\agroknow\\rest\\responseOK.json"));
    	//FILE JSONObject jsonObject = (JSONObject) obj;
    	 obj =  parser.parse(responseObj) ;
         jsonObject = (JSONObject) obj;				
		// loop array
				JSONArray graph = (JSONArray) jsonObject.get("@graph");//jsonObject
				Iterator<String> iterator = graph.iterator();
				
				while (iterator.hasNext()) {			
					 context = iterator.next();					 
					 cntx = (JSONObject) context;
					 taClassRef =(String) cntx.get("taClassRef");
					// aartaClassRef = taClassRef.split("#");
					// System.out.print("testttt:");
					/////ok System.out.print("\""+ taClassRef+"\":");
					 //blnFound = taClassRef.contains(taClassRef.toString());
				//bad	 System.out.print(""+ taClassRef.toLowerCase().contains(str.toLowerCase()) + " ");
				//	 System.out.print(""+ cntx.get("taClassRef")+" :");
					 
					 
					 
					 if(cntx.get("taIdentRef")!= null ){//&& personConfid > 0.4
						 orcid = (String) cntx.get("taIdentRef");
						 tmpConfFloat =  cntx.get("itsrdf:taConfidence").toString();
			             System.out.print("taIdentRef"+ orcid+" "); //print "http://orcid.org/0000-0003-3347-8265":null
			             entityValue = cntx.get("nif:anchorOf").toString();
						 authorOrcidListist.add(taClassRef+ " " +orcid.trim() + " " + entityValue.trim()+tmpConfFloat);
					          System.out.println("authorName" + entityValue + "hngdh" );
					    } //taIdentRef
					 
					 
					 
					 
					 
//					 if(taClassRef.toLowerCase().contains(str.toLowerCase())){
//					     //tmpFloat = (String) cntx.get("itsrdf:taConfidence");
//						 //personConfid = Float.parseFloat(tmpFloat);
//						// System.out.print("  "+  cntx.get("itsrdf:taConfidence")+" :");
//					   if(cntx.get("taIdentRef")!= null ){//&& personConfid > 0.4
//						 orcid = (String) cntx.get("taIdentRef");
//			             System.out.print("taIdentRef"+ orcid+" "); //print "http://orcid.org/0000-0003-3347-8265":null
//						     authorName = cntx.get("nif:anchorOf").toString();
//						     authorOrcidListist.add(orcid.trim() + " " + authorName.trim() + " ");
//					          System.out.println("authorName" + authorName + "hngdh" );
//					    } //taIdentRef
//				  }else if (taClassRef.contains("Location")){//is location?
//					  System.out.print("\"orcid"+ orcid+"\":");
//					  
//				  }else if (taClassRef.contains("Organization")){
//					  System.out.print("in Organisation");
//				  }else{
//					  System.out.print("no at any case");
//					  
//				  }
					 
				  
					 cntx = null;
					 aartaClassRef=null;
					 taClassRef=null;
					 authorName=null;
				}//end of while
				
				// authorOrcidListist.add("akstemLabels");
				
				
				//get label
		
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		authorOrcidListist.removeAll(Collections.singleton(null));
		return (ArrayList<String>) authorOrcidListist;
	 
		
		
	}
    	
    	
	

}
