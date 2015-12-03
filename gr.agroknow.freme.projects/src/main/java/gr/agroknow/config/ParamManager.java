package gr.agroknow.config;


public class ParamManager 
{


	private String projectsFolder = "" ;
	private String csvFolder = null ;	
//	private String informat= "text";
//	private String outformat = "json-ld";
//	private String language= "en" ; //source language
	private String dataset= "cordis-fp7";
		 

	 
	public String getInputFolder()
	{
		return projectsFolder ;
	}
	
	
	public String getCsvFolder()
	{
		return csvFolder ;
	}
	

	
	public String getDataSet()
	{
		return dataset ;
	}
	


	private static ParamManager instance;
	
	
	
	public void setParam(  String[] args  )
	{
		int check = 0 ;
		
			
				projectsFolder = args[0] ;
				csvFolder = args[1] ;
				dataset = args[2] ;

	
		
		if (args.length !=3  )
		{
			System.err.println( "Usage : java -jar <name>.jar <PROJECTS_FOLDER>  <CSV_FOLDER> <DATASET>" ) ;
			System.exit( -1 ) ;
		}
	}
	
	public static ParamManager getInstance()
	{
		if (instance == null)
        {
                if (instance == null)
                {
                    instance = new ParamManager() ;
                }
            
        }
        return instance ;
	}
	
}
