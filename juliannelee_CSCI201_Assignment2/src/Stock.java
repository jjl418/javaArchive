
public class Stock {
    /**
	 * Here: all the needed class members and their getters and setters
	 */
	
	//1. declaring data members
	private String name, ticker, startDate, description, exchangeCode; 
	private int stockBrokers;
    
	//2. create/initialize constructor
	public Stock(String n, String t, String sd, int sb, String des, String exC) 
	{
    	this.name = n;
    	this.ticker = t;
    	this.startDate = sd;
    	this.stockBrokers = sb;
    	this.description = des;
    	this.exchangeCode = exC;
    }
	
	//3. create getters for data members
	public String getName()
	{
		return this.name;
	}
	
	public String getTicker()
	{
		return this.ticker;
	}
	
	public String getStartDate()
	{
		return this.startDate;
	}
	
	public int getStockBrokers() 
	{
		return this.stockBrokers;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getExchangeCode()
	{
		return this.exchangeCode;
	}
}

