package atm;

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
	
}
