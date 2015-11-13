package gr.agroknow.config;



public class ParamManager 
{


	private String inputFolder = "C:\\Users\\papou_000\\Desktop\\agroknow" ;
	private String outputFolder = null ;
	private String badFolder = null ;
	private String set = null ;
	
	
	
	public String getInputFolder()
	{
		return inputFolder ;
	}
	
	
	public String getOutputFolder()
	{
		return outputFolder ;
	}
	
	
	public String getBadFolder()
	{
		return badFolder ;
	}
	
	
	public String getSet()
	{
		return set ;
	}
	


	private static ParamManager instance ;
	
	ParamManager()
	{
		
	}
	
	public void setParam(  String[] args  )
	{
		int check = 0 ;
		
		for( int i = 0; i< args.length ; i++ )
		{
			if ( "-input".equals( args[i] ) )
			{
				i++ ;
				inputFolder = args[i] ;
				check = check + 1 ;
			}
			if ( "-output".equals( args[i] ) )
			{
				i++ ;
				outputFolder = args[i] ;
				check = check + 2 ;
			}
			if ( "-bad".equals( args[i] ) )
			{
				i++ ;
				badFolder = args[i] ;
				check = check + 4 ;
			}
			if ( "-set".equals( args[i] ) )
			{
				i++ ;
				set = args[i] ;
				check = check + 8 ;
			}
			
			
			
			
		}
		
		if ( ((args.length % 2) != 0) || !((check == 15 )||(check == 31 ) )  )
		{
			System.err.println( "Usage : java -jar <name>.jar -input <INPUT_FOLDER> -output <OUTPUT_FOLDER>" ) ;
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
