package railway;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility 
{

	
	    public static int getInt(Scanner scan,String sentence)
	    {
	    	System.out.println(sentence);
	    	
	    	int output=0;
	    	
	    	while( true )
	    	{
	    		
	    		    try
	    		    {
	    		    	output=scan.nextInt();
	    		    	scan.nextLine();
	    		    	break;
	    		    }
	    		    catch( InputMismatchException ex )
	    		    {
	    		    	System.out.println( "Enter correct Data type" );
	    		    	scan.nextLine();
	    		    }
	    		
	    	}
	    	
	    	return output;
	    }
	    
	    public static String getString(Scanner scan,String sentence)
	    {
	    	System.out.println(sentence);
	    	String output="";
	    	
	    	while( true )
	    	{
	    		
	    		    try
	    		    {
	    		    	output=scan.nextLine();
	    		    	stringCheck(output);
	    		    	break;
	    		    }
	    		    catch( CustomException ex )
	    		    {
	    		    	System.out.println( ex.getMessage() );
	    		    }
	    		
	    	}
	    	
	    	return output;
	    }
	    
	    public static void stringCheck(String inputStr) throws CustomException
	    {
	    	if( inputStr==null || inputStr.isEmpty() )
	    	{
	    		throw new CustomException( "Don't enter null or empty");
	    	}
	    }
	    
	    public static void ObjectCheck(Object object,String objName) throws CustomException
	    {
	    	if( object==null  )
	    	{
	    		throw new CustomException( objName+" is null");
	    	}
	    }
	
}
