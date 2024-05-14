package route;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RouteRunner 
{

	public static void main(String[] args) 
	{
		Scanner scan=new Scanner( System.in );
		RouteLogic callLogic=new RouteLogic();
		
		boolean condition=true;
		
		while( condition )
		{
			int option=Utility.getInt("Press -->1. List Cities\n"
					+ "Press -->2. Find City\n"
					+ "Press -->3. Shortest Route\n"
					+ "Press -->4. Print Routes\n"
					+ "Press -->5. Add Route\n"
					+ "Press -->6. Delete Route\n"
					+ "Press -->7. Busy City\n"
					+ "Press -->8. Exit" , scan);
			
			
			switch( option )
			{
			
			case 1:
				
		         System.out.println(  callLogic.getAllCities() );
		         break;
				
		         
			case 2:
				
				String city=Utility.getString("Enter city to find", scan);
				
				if( callLogic.isCityExist(city) )
				{
					System.out.println("City "+city+" found");
				}
				else
				{
					System.out.println("City "+city+" not "
							+ "found");
				}
		         
				break;
				
				
			case 3:
				
				try {
					Map<Integer,List<Object>>  allRoutes=callLogic.getShortestRoots(Utility.getString("Enter city1:", scan), Utility.getString("Enter city2:", scan));
					  
					  for( Integer key:allRoutes.keySet() )
					  {
						  List<Object> vals=allRoutes.get(key);
						  
						  for( int i=0;i<vals.size();i++ )
						  {
							  System.out.println(vals.get(i)+" – Distance "+key+"Km");
						  }
					  }
					  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				
				
				break;
				
			case 4:
				
				
				
				try {
					  Map<Object,Integer> allRoutes=callLogic.getRoots(Utility.getString("Enter city1:", scan), Utility.getString("Enter city2:", scan));
					  
					  for( Object key:allRoutes.keySet() )
					  {
						  System.out.println(key+" – Distance "+allRoutes.get(key)+"Km");
					  }
					  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				
             
				
				
				break;
				
				
			case 5:
				
				try {
					 System.out.println( callLogic.addRoot( Utility.getString("Enter city1", scan) ,Utility.getString("Enter adding city", scan), Utility.getInt("Enter distance", scan)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 6:
				
				try {
					System.out.println(  callLogic.deleteRoot(  Utility.getString("Enter city1", scan) ,Utility.getString("Enter adding city", scan)) );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());				}
				break;
				
			case 7:
				
				System.out.println("Busy City is --> "+callLogic.getBusyCity() );
				break;
				
			  default:
				    condition=false; 
			
			}
		}
		

	}

}
