package gr.agroknow.manipulation.jsonld;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import org.apache.log4j.BasicConfigurator;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.github.jsonldjava.core.JsonLdError;
//import com.github.jsonldjava.core.JsonLdOptions;
//import com.github.jsonldjava.core.JsonLdProcessor;
//import com.github.jsonldjava.utils.JsonUtils;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.JsonProcessingException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONArray;
//object
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import com.google.gson.Gson;





public class ParseResponse {



    public static ArrayList<String>  getAgrovoc(String responseObj) throws JsonParseException, JsonMappingException, IOException, ParseException{
	//public static void main(String[] args)  {	
        List<String> agrovocUriList = new ArrayList<String>();
        String arrayLabell = new String();
        
    	JSONParser parser = new JSONParser();	 	
    	 Map<String, String> agrovocsUri = new HashMap<String, String>();
    	Object context = new Object();
    	Object obj = new Object();
    	JSONObject	 cntx = new JSONObject();
    	JSONObject jsonObject = new JSONObject();
    	String valuelabel = null ;
    	String arrayLabel = null;
		String termUri =null;
		String[] toLabel1 = null;
		String[] toLabel2 = null;
    	//agrovocUriList = null;
		try {
    	 System.out.println("*********get Project()**************** ");
		//FILE Object obj = parser.parse(new FileReader("C:\\Users\\papou_000\\Desktop\\agroknow\\rest\\responseOK.json"));
    	//FILE JSONObject jsonObject = (JSONObject) obj;
    	 obj =  parser.parse(responseObj) ;
         jsonObject = (JSONObject) obj;				
		// loop array
				JSONArray graph = (JSONArray) jsonObject.get("@graph");//jsonObject
				//////////System.out.print("@@@@graph ");
				Iterator<String> iterator = graph.iterator();
				
				while (iterator.hasNext()) {			
					 context = iterator.next();					 
					 cntx = (JSONObject) context;
					 if(cntx.get("taIdentRef")!=null){
			         termUri = (String) cntx.get("taIdentRef");
			         //System.out.print("Parse response termUri"+ termUri+" ");
				     agrovocUriList.add(termUri.trim());			      	
					// System.out.println("-----//////----" );
					//// JSONArray labels = (JSONArray) p.get("anchorOf");
					// loop array
					
					 //System.out.println("labelllllllllllllllll" + p.get("anchorOf").toString());
					// JSONArray l = (JSONArray) new  JSONValue().parse(arrayLabel);
//				     String s = cntx.get("anchorOf").toString();
//				     if(s !=null){
//						 arrayLabell = s;// (String) cntx.get("anchorOf");	
//						 String[] toLabel3 = arrayLabell.split("\\:");
//						 valuelabel = toLabel3[1];
//						 String[] toLabel4 = valuelabel.split("\\,");
//						 valuelabel = toLabel4[0];
//						 valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes
//						 System.out.println("\"" +valuelabel + "\"" );
//						 //if(valuelabel.trim()!=null)
//						 // agrovocUriList.add(valuelabel.trim());
//				     }
				//	System.out.println("\"" +valuelabel + "\"" );
		//			 agrovocsUri.put(valuelabel, arrayLabel.trim());
			        // valuelabel = (String) termUri.get("anchorOf"); 
				//}//if first  
										
//					 if(termUri != null)//					 System.out.print(termUri);//					 }
			         
			/*         
			         arrayLabel = cntx.get("anchorOf").toString();
///					 System.out.println("labelllllllllllllllll" + arrayLabel.get(2).toString());					 
					 toLabel1 = arrayLabel.split("\\:");
					 valuelabel = toLabel1[1];
					 toLabel2 = valuelabel.split("\\,");
					 valuelabel = toLabel2[0];
					 valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes
					// System.out.println("valuelabel 777777777777 " + valuelabel);
					 System.out.println("\"" +valuelabel + "\"" );*/
				/*	 FileWriter fw= null;
				        File file =null;
				        try {
				            file=new File("C:\\Users\\papou_000\\Desktop\\agroknow\\WriteFile.txt");
				            if(!file.exists()) {
				                file.createNewFile();
				            }
				            fw = new FileWriter(file);
				            fw.write("\""+ termUri+"\":" + "\"" +valuelabel + "\"" );
				            fw.write("dverg");
				            fw.flush();
				            fw.close();
				            System.out.println("File written Succesfully");
				        } catch (IOException e) {
				            e.printStackTrace();
				        }*/
					 } 
				}//end of while
				
				// agrovocUriList.add("akstemLabels");
				
				
				//get label
		/*okkk		for(int i = 1; i < graph.size(); i++) {
					///Object o =new Object();
					////////o = parser.parse(graph.get(i).toString());
					JSONObject p = (JSONObject) graph.get(i);
					// System.out.println("-----//////----" );
					//// JSONArray labels = (JSONArray) p.get("anchorOf");
					// loop array
					 String arrayLabell = p.get("anchorOf").toString();
					 //System.out.println("labelllllllllllllllll" + p.get("anchorOf").toString());
					// JSONArray l = (JSONArray) new  JSONValue().parse(arrayLabel);
					 
					 String[] toLabel3 = arrayLabell.split("\\:");
					 valuelabel = toLabel3[1];
					 String[] toLabel4 = valuelabel.split("\\,");
					 valuelabel = toLabel4[0];
					 valuelabel = valuelabel.replaceAll("^\"|\"$", "");//remove double quotes
					 //System.out.println("valuelabel " + valuelabel);
					 agrovocUriList.add(valuelabel.trim());
					// System.out.println("\"" +valuelabel + "\"" );
	        	}
				
				for (int j=1; j<agrovocUriList.size();j++)
					 System.out.println("\"" +agrovocUriList.get(j) + "\"" );

			
		
				for (Map.Entry<String, String> entry : agrovocsUri.entrySet())
				{
				    System.out.println(entry.getKey() + "/" + entry.getValue());
				}*/	
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		agrovocUriList.removeAll(Collections.singleton(null));
		return (ArrayList<String>) agrovocUriList;
	 
		
		
	}
    	
    	
	

}
