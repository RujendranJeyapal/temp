package chess;

import java.util.Scanner;


public class Utility 
{
	public static String getString( String sentence,Scanner scan )
	{
		System.out.println(sentence);
		
		String output="";
		
		
		while( true )
		{
			
			try
			{
			  output=  scan.nextLine();
			  stringCheck( output );
			  break;
			  
			}
			catch( CustomException ex )
			{
				System.out.println(ex.getMessage());
			}
		}
		return output;
	}
	
	public static void stringCheck( String inputStr )throws CustomException
	{
		if( inputStr==null || inputStr.isEmpty() )
		{
			throw new CustomException( "This position has no coins" );
		}
	}
}
