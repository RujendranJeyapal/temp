package parking;

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
		     catch(  InputMismatchException ex  )
		     {
		    	 System.out.println("Enter corect data type only");
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
		     catch(  InputMismatchException ex  )
		     {
		    	 System.out.println("Enter corect data type only");
		    	 scan.nextLine();
		     }
		   }  
		   
		   
		   return output;   
	   }
	   
	   public static double getDouble( String sentence,Scanner scan )
	   {
		   System.out.println(sentence);
		   
		   double output=0;
		   
		   while( true )
		   {
		     try
		     {
		         output=scan.nextDouble();
		         scan.nextLine();
		         break;
		     }
		     catch(  InputMismatchException ex  )
		     {
		    	 System.out.println("Enter corect data type only");
		    	 scan.nextLine();
		     }
		   }  
		   
		   
		   return output;   
	   }
	   
	   public static String getString( String sentence,Scanner scan )
	   {
		  
		   
		   String output="";
		   
		   while( true )
		   {
			   System.out.println(sentence);
		     try
		     {
		         output=scan.nextLine();
		         stringCheck( output );
		         break;
		     }
		     catch(  CustomException ex  )
		     {
		    	 System.out.println(ex.getMessage());
		     }
		   }  
		   
		   
		   return output;   
	   }
	   
	   public static void stringCheck( String inputStr ) throws CustomException
	   {
		   if( inputStr==null || inputStr.isEmpty() )
		   {
			   throw new CustomException ( "String is null or Empty" );
		   }
	   }
	
	   public static void nullCheck( Object object,String objName )throws CustomException
	   {
		   if( object==null )
		   {
			   throw new CustomException( objName+" is null" );
		   }
	   }
	
}
