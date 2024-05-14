package route;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility
{
   
	
	   public static int getInt(String message,Scanner scan)
	   {
		   System.out.println(message);
		   
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
			   System.out.println("Enter correct Data type");
			   scan.nextLine();
		   }
		   }
		   return output;
	   }
	   
	   
	   public static String getString(String message,Scanner scan)
	   {
		   System.out.println(message);
		   
		   String output="";
		   
		   while( true )
		   {
		   try
		   {
			   output= scan.nextLine();
			   stringCheck( output );
			   break;
		   }
		   catch( Exception ex )
		   {
			   System.out.println(ex.getMessage());
		   }
		   }
		   return output;
	   }
	   
	   
	   public static void stringCheck( String inputStr ) throws Exception
	   {
		   if( inputStr==null || inputStr.isEmpty() )
		   {
			   throw new Exception( "Don't enter null or empty" );
		   }
	   }
}
