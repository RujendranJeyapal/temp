package chess;

import java.util.Scanner;

public class PlayingChess 
{

	public static void main(String[] args)
	{
		Scanner scan=new Scanner( System.in );
		ChessLogic callLogic=new ChessLogic();
		
		  callLogic.printBoard();
		
		while( true )
		{
		  
		    
		    
		    while( true )
		    {
		    	String whiteCoinPosition=Utility.getString("WhiteMove", scan);
		        
		        if( whiteCoinPosition.equals("exit") )
		        {
		        	return;
		        }
		        
		        try
		        {
		             callLogic.isWhiteCoin(whiteCoinPosition);
		             
		             
		             
		             break;
		        }
		        catch( CustomException ex )
		        {
		        	System.out.println(ex.getMessage());
		        }
		    
		    }
		    
            callLogic.printBoard();
		    
		    
		    while( true )
		    {
		    	String blackCoinPosition=Utility.getString("BlackMove", scan);
		        
		    	if( blackCoinPosition.equals("exit") )
		        {
		        	return;
		        }
		    	
		        try
		        {
		             callLogic.isBlackCoin(blackCoinPosition);
		             break;
		        }
		        catch( CustomException ex )
		        {
		        	System.out.println(ex.getMessage());
		        }
		    
		    }
		    
		    callLogic.printBoard();
		    
		}
		

	}

}
