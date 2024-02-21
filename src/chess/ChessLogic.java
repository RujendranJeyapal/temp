package chess;

import java.util.LinkedHashMap;

public class ChessLogic 
{
	private LinkedHashMap<String,String> chessBoard=new LinkedHashMap<>();
	
	
	
	
	public boolean isWhiteCoin(String position) throws CustomException
	{
		
		String coin=chessBoard.get(position);
		
		Utility.stringCheck(coin);
		
		if( coin.startsWith("B") )
		{
			throw new CustomException( "Move your coin only" );
		}
		
		return true;
	}
	
	public boolean isBlackCoin(String position) throws CustomException
	{
		
		String coin=chessBoard.get(position);
		
		Utility.stringCheck(coin);
		
		if( coin.startsWith("W") )
		{
			throw new CustomException( "Move your coin only" );
		}
		
		return true;
	}
	
	public ChessLogic()
	{
	
	chessBoard.put( "a8" ,"B_R" );
	chessBoard.put( "b8" ,"B_N" );
	chessBoard.put( "c8" ,"B_B" );
	chessBoard.put( "d8" ,"B_Q" );
	chessBoard.put( "e8" ,"B_K" );
	chessBoard.put( "f8" ,"B_B" );
	chessBoard.put( "g8" ,"B_N" );
	chessBoard.put( "h8" ,"B_R" );
	
	chessBoard.put( "a7" ,"B_P" );
	chessBoard.put( "b7" ,"B_P" );
	chessBoard.put( "c7" ,"B_P" );
	chessBoard.put( "d7" ,"B_P" );
	chessBoard.put( "e7" ,"B_P" );
	chessBoard.put( "f7" ,"B_P" );
	chessBoard.put( "g7" ,"B_P" );
	chessBoard.put( "h7" ,"B_P" );
	
	
	chessBoard.put( "a6" ," " );
	chessBoard.put( "b6" ," " );
	chessBoard.put( "c6" ," " );
	chessBoard.put( "d6" ," " );
	chessBoard.put( "e6" ," " );
	chessBoard.put( "f6" ," " );
	chessBoard.put( "g6" ," " );
	chessBoard.put( "h6" ," " );
	
	chessBoard.put( "a5" ," " );
	chessBoard.put( "b5" ," " );
	chessBoard.put( "c5" ," " );
	chessBoard.put( "d5" ," " );
	chessBoard.put( "e5" ," " );
	chessBoard.put( "f5" ," " );
	chessBoard.put( "g5" ," " );
	chessBoard.put( "h5" ," " );
	
	
	chessBoard.put( "a4" ," " );
	chessBoard.put( "b4" ," " );
	chessBoard.put( "c4" ," " );
	chessBoard.put( "d4" ," " );
	chessBoard.put( "e4" ," " );
	chessBoard.put( "f4" ," " );
	chessBoard.put( "g4" ," " );
	chessBoard.put( "h4" ," " );
	
	
	chessBoard.put( "a3" ," " );
	chessBoard.put( "b3" ," " );
	chessBoard.put( "c3" ," " );
	chessBoard.put( "d3" ," " );
	chessBoard.put( "e3" ," " );
	chessBoard.put( "f3" ," " );
	chessBoard.put( "g3" ," " );
	chessBoard.put( "h3" ," " );
	
	
	chessBoard.put( "a2" ,"W_P" );
	chessBoard.put( "b2" ,"W_P" );
	chessBoard.put( "c2" ,"W_P" );
	chessBoard.put( "d2" ,"W_P" );
	chessBoard.put( "e2" ,"W_P" );
	chessBoard.put( "f2" ,"W_P" );
	chessBoard.put( "g2" ,"W_P" );
	chessBoard.put( "h2" ,"W_P" );
	
	chessBoard.put( "a1" ,"W_R" );
	chessBoard.put( "b1" ,"W_N" );
	chessBoard.put( "c1" ,"W_B" );
	chessBoard.put( "d1" ,"W_Q" );
	chessBoard.put( "e1" ,"W_K" );
	chessBoard.put( "f1" ,"W_B" );
	chessBoard.put( "g1" ,"W_N" );
	chessBoard.put( "h1" ,"W_R" );

	}
	
	
	public void printBoard()
	{
	      Object positions[] =chessBoard.keySet().toArray();
 
	 
	      int i=0;
	      int j=8;
	 
	       System.out.println("\ta\tb\tc\td\te\tf\tg\th");
	 
	      while( i<chessBoard.size() )
	      {
		     System.out.println(   (j--)+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])+"\t"+chessBoard.get(positions[i++])   );
	      }
	}
}
