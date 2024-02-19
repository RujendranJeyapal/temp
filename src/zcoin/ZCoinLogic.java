package zcoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ZCoinLogic 
{
   
	private Map<String,User> usersMap=new HashMap<>();
	private Map<String,Account> accountsMap=new HashMap<>();
	private Map<String,List<RCTransaction>> rcTransHistory=new HashMap<>();
	private Map<String,List<ZCTransaction>> zcTransHistory=new HashMap<>();
	
	private int zID=1;
	
	private int rcToZCoinRate=2;

	public boolean isAdmin( String mailID )
	{	
		return getUser( mailID ).isAdmin() ;
		
	}

	
	
	
	public void setRcToZCoinRate(int rcToZCoinRate) {
		this.rcToZCoinRate = rcToZCoinRate;
	}




	public Map<String, List<RCTransaction>> getRcTransHistory() {
		return rcTransHistory;
	}




	public Map<String, List<ZCTransaction>> getZcTransHistory() {
		return zcTransHistory;
	}




	public List<User> getAllRequests()
	{
		List<User> requestsUser=new ArrayList<>();
		
		for( String mailId:usersMap.keySet() )
		{
			User user=usersMap.get(mailId);
			
			if( user.getApprovalStatus().equals("Waiting") )
			{
				requestsUser.add(user);
			}
		}
		
		return requestsUser;
	}
	
	public void passwordCheck( User user,String pass ) throws CustomException
	{
		if( !user.getPassword().equals(pass) )
		{
			throw new CustomException( "Password wrong" );
		}
	}
	
	private String getZId()
	{
		return "ZID "+zID++;
	}
	
	public boolean depositeRC( double amount,String zId )
	{
		 Account account=  accountsMap.get(zId);
		 
		 account.setRcWallet( account.getRcWallet()+amount );
		 
		 addRCTransaction(new  RCTransaction(zId,"Cash deposit "+amount,  amount, account.getRcWallet()));
		 
		 return true;
		 
	}
	
	public boolean withDrawRC( double amount,String zId  ) throws CustomException
	{
		 Account account=  accountsMap.get(zId);
		 
		 if( account.getRcWallet()<amount )
		 {
			 throw new CustomException( "Amount too large...!" );
		 }
		 
		 
		 account.setRcWallet( account.getRcWallet()-amount );
		 
		 addRCTransaction(new  RCTransaction(zId,"Cash withdraw "+amount,  amount, account.getRcWallet()));

		 
		 return true;
	}
	
	public boolean rcToZCoinConvert( double amount,String zId )throws CustomException
	{
		Account account=  accountsMap.get(zId);
		 
		 if( account.getRcWallet()<amount )
		 {
			 throw new CustomException( "Amount too large...!" );
		 }
		 
		 account.setRcWallet(  account.getRcWallet()-amount  );
		 
		double zcAmount=amount/( rcToZCoinRate );
		 
		account.setZcoinWallet( account.getZcoinWallet()+zcAmount );
		
		addZCTransaction(new  ZCTransaction(zId,"RC to ZC",  amount, account.getZcoinWallet()));

		 
		 addRCTransaction(new  RCTransaction(zId,"ZC to RC",  amount, account.getRcWallet()));
		
		
		 return true;
	}
	
	public boolean transferZCoin( String from,String to,double zCoin )throws CustomException
	{
		Account fromUser=  accountsMap.get(from);
		Account toUser=  accountsMap.get(to);
		
		Utility.objectCheck(to, "Receiver ");
		
		 if( fromUser.getRcWallet()<zCoin )
		 {
			 throw new CustomException( "Amount too large...!" );
		 }
		
		 fromUser.setZcoinWallet( fromUser.getZcoinWallet()- zCoin);
		 toUser.setZcoinWallet( toUser.getZcoinWallet()+zCoin); 
		 
		 addZCTransaction(new  ZCTransaction(from,"Transfer to "+to,  zCoin, fromUser.getZcoinWallet()));

		 addZCTransaction(new  ZCTransaction(to,"Transfer from "+from,  zCoin, toUser.getZcoinWallet()));

		 return true;	
	}
	
	
	public boolean ZCToRC( String zId,double zCoin )throws CustomException
	{
		Account account=  accountsMap.get(zId);
		 
		 if( account.getZcoinWallet()<zCoin )
		 {
			 throw new CustomException( "Amount too large...!" );
		 }
		
		 double converSionAmount=( rcToZCoinRate*zCoin )-(( rcToZCoinRate*zCoin )*0.15);

		 account.setZcoinWallet( account.getZcoinWallet()-zCoin );
		 account.setRcWallet( account.getRcWallet()+ converSionAmount);
		 
		 addZCTransaction(new  ZCTransaction(zId,"ZC to RC",  zCoin, account.getZcoinWallet()));

		 
		 addRCTransaction(new  RCTransaction(zId,"ZC to RC",  zCoin, account.getRcWallet()));

		 
		return true;
	}
	
	public boolean isPasswordConditionCorrect(String password ,String name,String mailId,long mobileNumber ) throws CustomException
	{
		
		if( password.equals(mailId.split("@")[0]) || password.equals(name) || password.equals( ""+mobileNumber )  )
		{
			throw new CustomException( "Password doesnot contains mailId,userName and phonenumber and above 8 characters" );
		}
		
		if(! Pattern.matches( "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!#%><&*]).{8,}" , password) )
		{
			throw new CustomException( "Enter correct type" );
		}
		return true;
	}
	
	public double getRCWallet( String zId )
	{
		return accountsMap.get(zId).getRcWallet();
	}
	
	public double getZCWallet( String zId )
	{
		return accountsMap.get(zId).getZcoinWallet();
	}
	
	public void passwordChange( String newPsssword,String userId )
	{
		getUser( userId ).setPassword(newPsssword);
	}
	
	public void approveRequest(User user)
	{
		user.setApprovalStatus("Approved");
	}
	
	public void requestRequest(User user)
	{
		user.setApprovalStatus("Reject");
	}
	
	public void addUser( User user )
	{
		usersMap.put( user.getMailId() , user);
	}
	
	public User getUser( String mailId )
	{
		return usersMap.get(mailId);
	}
	
	public void addAccount( Account account )
	{
		account.setZ_id(getZId()); 
			accountsMap.put( account.getZ_id() , account);
	}
	
	public Account getAccount(String z_id)
	{
		return accountsMap.get(z_id);
	}
	
	public List<RCTransaction> getRCTrans( String zId )
	{
		return rcTransHistory.get(zId);
	}
	
	public List<ZCTransaction> getZCTrans( String zId )
	{
		return zcTransHistory.get(zId);
	}
	
	public void addRCTransaction(RCTransaction transaction)
	{
		
		
	          List<RCTransaction>	rcTransList= rcTransHistory.get( transaction.getZ_id() );
	          
	          if( rcTransList==null )
	          {
	        	  rcTransList=new ArrayList<>();
	        	  rcTransHistory.put(transaction.getZ_id()  , rcTransList);
	          }
	          
	          rcTransList.add(transaction);
	}
	
	public void addZCTransaction(ZCTransaction transaction)
	{
		
		
	          List<ZCTransaction>	zcTransList= zcTransHistory.get( transaction.getZ_id() );
	          
	          if( zcTransList==null )
	          {
	        	  zcTransList=new ArrayList<>();
	        	  zcTransHistory.put(transaction.getZ_id()  , zcTransList);
	          }
	          
	          zcTransList.add(transaction);
	}
	
	public ZCoinLogic()
	{
		 addUser( new User( "Admin","admin@zoho.com",9940971162l,"Admin1","admin2000!",0,true,"Approved" ) );	
	}




	public void accountCheck(String zId, String mailId)throws CustomException
	{
		Account account=getAccount( zId );
		
		Utility.objectCheck(account, "Account");
		
		if(! account.getMailId().equals(mailId) )
		{
			throw new CustomException( "Enter correct emailID" );
		}
		
	}
	
}
