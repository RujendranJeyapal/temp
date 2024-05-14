package route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouteLogic 
{
      private Map<String,Map<String,Integer>> routeMap=new HashMap<>(); 
      
      
      public String getBusyCity()
      {
    	  String busyCity="";
    	  int max=Integer.MIN_VALUE;
    	  
    	  for( String city:routeMap.keySet()  )
    	  {
    		  if( max<routeMap.get(city).size() )
    		  {
    			  max=routeMap.get(city).size();
    			  busyCity=city;
    		  }
    	  }
    	  
    	  return busyCity;
      }
      
      public String deleteRoot( String city1,String city2 ) throws Exception
      {
    	  
    	  if(  !isCityExist(city1) || !isCityExist(city2)  )
    	  {
    		  throw new Exception("Route ("+city1+", "+city2+") not found");
    	  }
    	  
    	  Map<String,Integer> routes= routeMap.get(city1);
    	  
    	  if( routes.get(city2)==null )
    	  {
    		  throw new Exception("Route ("+city1+", "+city2+") not found");
    	  }
    	  else
    	  {
    		  routes.remove( city2 );
    		  
    		  Map<String,Integer> reverseRoutes= routeMap.get(city2);
    		  
    		  reverseRoutes.remove( city1 );
    		  
    	  }
    	  return "Route ("+city1+", "+city2+") deleted";
      }
      
      public void addMap(String city1,String city2,int distance) throws Exception
      {
    	  Map<String,Integer> routes= routeMap.get(city1);
    	  
    	  if( routes==null )
    	  {
    		  routes=new HashMap<>();
    		  routeMap.put(city1, routes);
    	  }
    	  
    	  if( routes.get( city2 )==null )
    	  {
    		  routes.put( city2,distance );
    	  }
    	  else
    	  {
    		  throw new Exception( "Routes already exists" );
    	  }
    	  
    	  
         Map<String,Integer> reverseRoutes= routeMap.get(city2);
    	  
    	  if( reverseRoutes==null )
    	  {
    		  reverseRoutes=new HashMap<>();
    		  routeMap.put(city2, reverseRoutes);
    	  }
    	  
    	  if( reverseRoutes.get( city1 )==null )
    	  {
    		  reverseRoutes.put( city1,distance );
    	  }
    	  else
    	  {
    		  throw new Exception(  "Routes already exists" );
    	  }
    	  
      }
      
      public String addRoot(String city1,String city2,int distance) throws Exception
      {
    	  if( !isCityExist(city1) )
    	  {
    		  throw new Exception("Route cannot be added as city "+city1+" does not exist");
    	  }
    	  
    	  boolean flag=isCityExist(city2);
    	  
    	  addMap( city1,city2,distance );
    	  
    	  if( !flag )
    	  {
    		  return "Route added between "+city1+" and "+city2+" with distance "+distance+". "+city2+" is a new city that will also get added";
    	  }
    	  
    	  return "New Route added";
      }
      
      public Map<Object,Integer> getRoots(String city1,String city2) throws Exception
      {
    	  
    	  Map<Object,Integer> allRoutes=new HashMap<>();
    	  
    	  if( isCityExist(city1) && isCityExist(city2) )
    	  {
    		  Set<Set<String>> roots =new LinkedHashSet<>();
				List<Integer> distances= new ArrayList<>();
    		  
    		  getRoots(city1, city2, 0, new LinkedHashSet<>(),distances, roots);
    		  
    		 Object[] array= roots.toArray();
    		 
    		  for( int i=0;i<distances.size();i++ )
              {
    			  allRoutes.put(  array[i] , distances.get(i));
              }
    	  }
    	  else
    	  {
    		  throw new Exception("City Doesn't Exist");
    	  }
    	  
    	  return allRoutes;
      }
      
      public   Map<Integer,List<Object>> getShortestRoots( String city1,String city2 ) throws Exception
      {
    	  Map<Object,Integer> allRoutes=getRoots(city1, city2);
    	  
    	  Map<Integer,List<Object>> output=new HashMap<>();
    	  
    	  int min=Integer.MAX_VALUE;
    	  
    	  for(  Object key:allRoutes.keySet() )
    	  {
    		  Integer value=allRoutes.get(key);
    		  
    		  if( min>=value )
    		  {
    			  min=value;
    		  }
    		  
    		  List<Object> object=output.get(value);
    		  
    		  if( object==null )
    		  {
    			  object=new ArrayList<>();
    			  output.put(value, object);
    		  }
    		  object.add(key);
    	  }
    	  
    	  List<Object> objList=  output.get(min);

    	  Map<Integer,List<Object>> result=new HashMap<>();
    	  
    	  result.put( min , objList);
    	  
    	 return result;
      }
      
      public void getRoots( String city1,String city2,int distance,
    		  Set<String> root,List<Integer> distances,Set<Set<String>> roots )
      {
    	 Map<String,Integer> routes=routeMap.get(city1);
    	 
    	 
    	 Set<String> tempList=new LinkedHashSet<>();
    	 
    	 tempList.addAll(root);
    	 tempList.add(city1);
    	 
    	 for( String temp:routes.keySet() )
    	 {
    		 
    	   if(! tempList.contains(temp) )
    	   {
    		 if( temp.equals(city2) )
    		 {
    			 Set<String> temp2=new LinkedHashSet<>();
    			 temp2.addAll(tempList);
    			 temp2.add(city2);
    			     			 
    			     roots.add(temp2);
  
    			     distances.add(distance+routes.get(temp));
    		 }
    		 else
    		 {
    			 getRoots( temp,city2,distance+routes.get(temp) ,tempList,distances,roots);
    		 }
    	   }
    	 }
    	 
    	 
      }
      
      public boolean isCityExist( String city )
      {
    	  return routeMap.get(city)!=null ;
      }
      
      public Set<String> getAllCities()
      {
    	  
    	 return routeMap.keySet();

      }
      
      public RouteLogic()
      {
    	  try
    	  {
    	  
    	     addMap("A","B",20);
    	     addMap("A","C",45);
    	     addMap("A","D",25);
    	     addMap("B","F",90);
    	     addMap("B","G",35);
    	     addMap("C","E",10);
    	     addMap("D","G",30);
    	     addMap("E","H",35);
    	     addMap("F","G",30);
    	     addMap("G","H",40);
    	     
    	  }
    	  catch( Exception ex )
    	  {
    		  System.out.println(ex.getMessage());
    	  }
      }
      
     
      
}
