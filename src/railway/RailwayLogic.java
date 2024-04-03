package railway;
import java.util.*;

public class RailwayLogic 
{
      
	private Map<String,Passenger> allPassengers=new HashMap<>();
	  private Map<String,List<Seat>> allSeats=new HashMap<>();
	  private Map<String,Ticket> bookedTickets=new HashMap<>();
	  private LinkedHashMap<String,Ticket> bookedRACTickets=new LinkedHashMap<>();
	  private List<Passenger> waitingList=new ArrayList<>();
	  private int ticketId=1;
	  
	  
	  
	  
	  public Map<String, List<Seat>> getAllSeats() {
		return allSeats;
	}



	public Map<String, Ticket> getBookedTickets() {
		return bookedTickets;
	}



	public Passenger getPassengers(String passId)
	  {
		  return allPassengers.get(passId);
	  }
	  
	  public void addPassenger(Passenger pass)
	  {
		  allPassengers.put( pass.getPassId() , pass);
	  }
	  
	  public void addSeat( Seat seat )
	  {
		  String berth=seat.getBerth();
		  
		  List<Seat> seats=allSeats.get(berth);
		  
		  if( seats==null )
		  {
			  seats=new ArrayList<>();
			  allSeats.put(berth, seats);
		  }
		  
		  seats.add(seat);
		  
	  }
	  
	  public void addWaitingList(Passenger pass)
	  {
		  waitingList.add(pass);
	  }
	  
	  public void cancelAllTicket(String tickId) throws CustomException
	  {
		  Ticket ticket=bookedTickets.get(tickId);
		  
		  
		  Map<String,Seat> bookings=ticket.getBookings();
		  
		  for( String passId:bookings.keySet() )
		  {
			  cancelTicket( tickId,passId );
		  }
		  
	  }
	  
	  public void cancelTicket(String tickId,String passId) throws CustomException
	  {
		  Ticket ticket=bookedTickets.get(tickId);
		  
		  if( ticket==null )
		  {
			  cancelRACTicket( tickId,passId);
			  return;
		  }
		  
		 
		  
		  Map<String,Seat> bookings=ticket.getBookings();
		  
		  Seat seat=bookings.get(passId);
		  
		  Utility.ObjectCheck(seat, "Seat");
		  
		  bookings.remove(passId);
		  ticket.setTotalCounnt(-1);
		  
		  if( bookings.size()==0 )
		  {
			  bookedTickets.remove(tickId);
		  }
		  
		  moveRACToBooked(seat);
	  }
	  
	  public void cancelRACTicket(String tickId,String passId) throws CustomException
	  {
		  Ticket ticket=bookedRACTickets.get(tickId);
		  
		  Utility.ObjectCheck(ticket, "Ticket");
		  
		  Map<String,Seat> bookings=ticket.getBookings();
		  
		  Seat seat=bookings.get(passId);
		  Utility.ObjectCheck(seat, "Seat");
		  
		  bookings.remove(passId);
		  ticket.setTotalCounnt(-1);
		  
		  if( bookings.size()==0 )
		  {
			  bookedTickets.remove(tickId);
		  }
		  
		  moveWLToRAC( seat);
	  }
	  
	  public void moveRACToBooked(Seat cancelSeat)
	  {
		  if( !bookedRACTickets.isEmpty()  )
		  {
			Ticket ticket=(Ticket) bookedRACTickets.keySet().toArray()[0];
			Map<String,Seat> bookings=ticket.getBookings();
			
			String passId= (String) bookings.keySet().toArray()[0];
			
			Seat seat=bookings.get(passId);
			
			bookings.remove(passId);
			
			if( bookings.size()==1 )
			{
				  bookedRACTickets.remove(ticket.getTicketrId());
			}
			
			createTicket(generateTicketId(), cancelSeat,getPassengers(passId));
			moveWLToRAC(seat);
		  }
		  else
		  {
			  addSeat(cancelSeat);
		  }
	  }
	  
	  public void moveWLToRAC(Seat racSeat)
	  {
		  if( !waitingList.isEmpty()  )
		  {
			  Passenger waitingPassenger=waitingList.get(0);
			  
			  createTicket(generateTicketId(), racSeat,waitingPassenger);
					  
			  waitingList.remove(0);
		  }
		  else
		  {
			  addSeat(racSeat);
		  }
	  }
	  
	  public Ticket createTicket(String id,Seat seat,Passenger pass)
	  {
		  
		  Ticket ticket=bookedTickets.get(id);
		  
		  if( ticket==null )
		  {	  
			  ticket=new Ticket( id,0,new HashMap<>() );
			  bookedTickets.put(id, ticket);
		  }
		
		  ticket.setTotalCounnt(1);
		  
		  Map<String ,Seat> booking=ticket.getBookings();
		  booking.put(pass.getPassId(),seat);
		  
		  ticket.setBookings(booking);
		  
		  return ticket;
	  }
	  
	  public Ticket createRACTicket(String id,Seat seat,Passenger pass)
	  {
		  
		  Ticket ticket=bookedRACTickets.get(id);
		  
		  if( ticket==null )
		  {	  
			  ticket=new Ticket( id,0,new HashMap<>() );
			  bookedRACTickets.put(id, ticket);
		  }
		
		  ticket.setTotalCounnt(1);
		  
		  Map<String ,Seat> booking=ticket.getBookings();
		  booking.put(pass.getPassId(),seat);
		  
		  ticket.setBookings(booking);
		  
		  return ticket;
	  }
	  
	  
	  public String book( List<Passenger> passList )
	  {
		  
		  int booked=0;
		  int rac=0;
		  int waitingList=0;
		  boolean flag=false;
		  String id="";
		
		  
		  String output="";
		  
		  for( int i=0;i<passList.size();i++ )
		  {
			  
			  boolean isPreferBerthHere=false;
			  
			      Passenger pass=passList.get(i);
			      
			      int age=pass.getAge();
			      
			      String berthPrefer=pass.getBerthPreference();
			      
			      if( age>5 )
			      {
						if( age>60 )
			    	       {
			    	    	   Seat seat=getLowerBerth();
			    	    	   
			    	    	   if( seat!=null  )
			    	    	   {
			    	    		   booked++;
			    	    		   
			    	    		   if( !flag )
			    	    		   {
			    	    			   id=generateTicketId();
			    	    			   flag=true;
			    	    		   }
			    	    		   
			    	    		   createTicket(id,seat,pass);
			    	    		   addPassenger(pass);
			    	    		   isPreferBerthHere=true;
			    	    	   }
			    	    	   
			    	       }
			    	       
			    	       
			    	       else if( berthPrefer.equals("Lower")  )
			    	       {
                               Seat seat=getLowerBerth();
			    	    	   
			    	    	   if( seat!=null  )
			    	    	   {
			    	    		   booked++;
			    	    		   
			    	    		   if( !flag )
			    	    		   {
			    	    			   id=generateTicketId();
			    	    			   flag=true;
			    	    		   }
			    	    		   
			    	    		   createTicket(id,seat,pass);
			    	    		   addPassenger(pass);
			    	    		   isPreferBerthHere=true;
			    	    	   }
			    	    	
			    	       }
			    	       
			    	       else if( berthPrefer.equals("Upper") )
			    	       {
                               Seat seat=getUpperBerth();
			    	    	   
			    	    	   if( seat!=null  )
			    	    	   {
			    	    		   booked++;
			    	    		   
			    	    		   if( !flag )
			    	    		   {
			    	    			   id=generateTicketId();
			    	    			   flag=true;
			    	    		   }
			    	    		   
			    	    		   createTicket(id,seat,pass);
			    	    		   addPassenger(pass);
			    	    		   isPreferBerthHere=true;
			    	    	   }
			    	       }
			    	       
			    	       else if( berthPrefer.equals("Middle") )
			    	       {
                               Seat seat=getMiddleBerth();
			    	    	   
			    	    	   if( seat!=null  )
			    	    	   {
			    	    		   booked++;
			    	    		   
			    	    		   if( !flag )
			    	    		   {
			    	    			   id=generateTicketId();
			    	    			   flag=true;
			    	    		   }
			    	    		   
			    	    		   createTicket(id,seat,pass);
			    	    		   addPassenger(pass);
			    	    		   isPreferBerthHere=true;
			    	    	   }
			    	       }
			    	       
			    	       else if( berthPrefer.equals("Side-Upper") )
			    	       {
                               Seat seat=getSideUpperBerth();
			    	    	   
			    	    	   if( seat!=null  )
			    	    	   {
			    	    		   booked++;
			    	    		   
			    	    		   if( !flag )
			    	    		   {
			    	    			   id=generateTicketId();
			    	    			   flag=true;
			    	    		   }
			    	    		   
			    	    		   createTicket(id,seat,pass);
			    	    		   addPassenger(pass);
			    	    		   isPreferBerthHere=true;
			    	    	   }
			    	       }
			    	       
			    	       if( !isPreferBerthHere )
			    	       {
                                   Seat seat=getLowerBerth();
			    	    	   
			    	    	       if( seat!=null  )
			    	    	       {  
			    	    		        booked++;
			    	    		   
			    	    		         if( !flag )
			    	    		         {
			    	    			       id=generateTicketId();
			    	    			       flag=true;
			    	    		         }
			    	    		   
			    	    		        createTicket(id,seat,pass);
			    	    		        addPassenger(pass);
			    	    	       }
			    	    	       else
			    	    	       {
			    	    	    	   
			    	    	    	    seat=getMiddleBerth();
					    	    	   
					    	    	   if( seat!=null  )
					    	    	   {
					    	    		   booked++;
					    	    		   
					    	    		   if( !flag )
					    	    		   {
					    	    			   id=generateTicketId();
					    	    			   flag=true;
					    	    		   }
					    	    		   
					    	    		   createTicket(id,seat,pass);
					    	    		   addPassenger(pass);
					    	    		   
					    	    	   }
					    	    	   else
					    	    	   {
					    	    		    seat=getUpperBerth();
						    	    	   
						    	    	   if( seat!=null  )
						    	    	   {
						    	    		   booked++;
						    	    		   
						    	    		   if( !flag )
						    	    		   {
						    	    			   id=generateTicketId();
						    	    			   flag=true;
						    	    		   }
						    	    		   
						    	    		   createTicket(id,seat,pass);
						    	    		   addPassenger(pass);
						    	    	   }
						    	    	   else
						    	    	   {
						    	    		    seat=getSideUpperBerth();
							    	    	   
							    	    	   if( seat!=null  )
							    	    	   {
							    	    		   booked++;
							    	    		   
							    	    		   if( !flag )
							    	    		   {
							    	    			   id=generateTicketId();
							    	    			   flag=true;
							    	    		   }
							    	    		   
							    	    		   createTicket(id,seat,pass);
							    	    		   addPassenger(pass);
							    	    	   }
							    	    	   else
							    	    	   {
							    	    		    seat=getRAC();
								    	    	   
								    	    	   if( seat!=null  )
								    	    	   {
								    	    		   rac++;
								    	    		   
								    	    		   if( !flag )
								    	    		   {
								    	    			   id=generateTicketId();
								    	    			   flag=true;
								    	    		   }
								    	    		   
								    	    		   createRACTicket(id,seat,pass);
								    	    		   addPassenger(pass);
								    	    	   }
								    	    	   else if(! isWaitingListFull()  )
								    	    	   {
								    	    		     addWaitingList( pass );
								    	    		     waitingList++;
								    	    		     addPassenger(pass);
								    	    	   }
							    	    	   }
							    	    	   
						    	    	   }
					    	    	   }
			    	    	       }
			    	       }
			    	       
			    	  
			      }
			  
		  }
		  
		  if( booked==0 && rac==0 && waitingList==0 )
		  {
			  return "No Tickets Available";
		  }
		  
		  if( booked>0 )
		  {
			  output+="Booked ->"+booked;
		  }
		  
		  if( rac>0 )
		  {
			  output+="RAC ->"+rac;
		  }
		  
		  if( waitingList>0 )
		  {
			  output+="waitingList ->"+waitingList;
		  }
		  
		  
		  return output;
	  }
	  
	  public boolean isWaitingListFull()
	  {
		  return waitingList.size()==10;
	  }
	  
	  public Seat getLowerBerth()
	  {
		  Seat seat=null;
		  List<Seat> seats= allSeats.get("Lower");
		  
		  if( !seats.isEmpty() )
		  {
			  seat= seats.get(0) ;
			  seats.remove( 0 );
		  }
		  
			 return seat;
	  }
	  
	  public Seat getMiddleBerth()
	  {
		  Seat seat=null;
		  List<Seat> seats= allSeats.get("Middle");
		  
		  if( !seats.isEmpty() )
		  {
			  seat= seats.get(0) ;
			  seats.remove( 0 );
		  }
		  
			 return seat; 
	  }
	  
	  public Seat getUpperBerth()
	  {
		  Seat seat=null;
		  List<Seat> seats= allSeats.get("Upper");
		  
		  if( !seats.isEmpty() )
		  {
			  seat= seats.get(0) ;
			  seats.remove( 0 );
		  }
		  
			 return seat; 
	  }
	  
	  public Seat getSideUpperBerth()
	  {
		  Seat seat=null;
		  List<Seat> seats= allSeats.get("Side-Upper");
		  
		  if( !seats.isEmpty() )
		  {
			  seat= seats.get(0) ;
			  seats.remove( 0 );
		  }
		  
			 return seat;  
	  }
	  
	  public Seat getRAC()
	  {
		  Seat seat=null;
		  List<Seat> seats= allSeats.get("RAC");
		  
		  if( !seats.isEmpty() )
		  {
			  seat= seats.get(0) ;
			  seats.remove( 0 );
		  }
			 			 
		  return seat;
		  
	  }
	  
	  private String generateTicketId()
	  {
		  return "Ticket "+ticketId++;
	  }
	  
	  public RailwayLogic()
	  {
		  int k=1;
		  
		  for( int i=1;i<=18;i++ )
		  {			  
			 addSeat(new Seat( "Lower",k++ ));
		  }
		  
		  for( int i=1;i<=18;i++ )
		  {
			  addSeat(new Seat( "Middle",k++ ));
		  }
		  
		  for( int i=1;i<=18;i++ )
		  {
			  addSeat(new Seat( "Upper",k++ ));
		  }
		  
		  for( int i=1;i<=9;i++ )
		  {
			  addSeat(new Seat( "Side-Upper",k++ ));
	      }
		  
		  for( int i=1;i<=18;i++ )
		  {
			  addSeat(new Seat( "RAC",k++ ));
	      }
		  
	  }
	
}
