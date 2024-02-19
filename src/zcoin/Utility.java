package zcoin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility 
{
    public static int getInt( String sentence,Scanner scan )
    {
    	int output=0;
    	
    	System.out.println(sentence);
    	
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
  	       scan.nextLine();
    		System.out.println("Enter correct data type");
    	}
    	}
    	return output;
    	
    }
    
    public static long getLong( String sentence,Scanner scan )
    {
    	long output=0;
    	
    	System.out.println(sentence);
    	
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
  	       scan.nextLine();
    		System.out.println("Enter correct data type");
    	}
    	}
    	return output;
    	
    }
    
    public static double getDouble( String sentence,Scanner scan )
    {
    	double output=0;
    	
    	System.out.println(sentence);
    	
    	while( true )
    	{
    	
    	try
    	{
     	       output=scan.nextDouble();
     	       scan.nextLine();
     	       break;
    	}
    	catch( InputMismatchException ex )
    	{
  	       scan.nextLine();
    		System.out.println("Enter correct data type");
    	}
    	}
    	return output;
    	
    }
    
    public static String getString( String sentence,Scanner scan )
    {
    	String output="";
    	
    	System.out.println(sentence);
    	
    	while( true )
    	{
    	
    	try
    	{
     	       output=scan.nextLine();
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

	private static void stringCheck(String inputStr)throws CustomException
	{
		if( inputStr==null || inputStr.isEmpty() )
		{
			 throw new  CustomException("Don't enter empty or null String...!");
		}
	}
	
	public static void objectCheck(Object inputObj,String str)throws CustomException
	{
		if( inputObj==null  )
		{
			throw new  CustomException(str+" is null");
		}
	}
    
    
}
