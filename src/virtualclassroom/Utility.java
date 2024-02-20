package virtualclassroom;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility 
{

	public static int getInt( String sentence,Scanner scan )
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
				System.out.println("Enter correct data type");
				scan.nextLine();
			}
		}
		return output;
	}
	
	public static long getLong( String sentence,Scanner scan )
	{
		System.out.println(sentence);
		
		long output=0;
		
		
		while( true )
		{
			
			try
			{
			  output=scan.nextLong();
			  scan.nextLine();
			  break;
			  
			}
			catch( InputMismatchException ex )
			{
				System.out.println("Enter correct data type");
				scan.nextLine();
			}
		}
		return output;
	}
	
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
	
	private static void stringCheck( String inputStr )throws CustomException
	{
		if( inputStr==null || inputStr.isEmpty() )
		{
			throw new CustomException( "Don't enter null or empty" );
		}
	}
	
	public static boolean ObjectCheck( Object object,String str )throws CustomException
	{
		if( object==null  )
		{
			throw new CustomException( str+" is null" );
		}
		
		return true;
	}
	
}
