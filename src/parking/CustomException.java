package parking;

public class CustomException extends Exception
{

	
	public CustomException( String message )
	{
		super(message);
	}
	
	public CustomException( Exception ex )
	{
		super(ex);
	}
	
}
