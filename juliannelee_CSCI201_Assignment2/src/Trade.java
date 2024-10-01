import java.util.concurrent.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//import java.util.TimestampUtil;

public class Trade extends Thread {
	//1. data members read in from CSV file
	Semaphore companyLock;
	int tradeTime;
	String tickerName;
	int quantity;
	
	// In main build map of ticker symbol to sempahore (lock in this case) argument to semaphore is num stock brokers
	// Figure out body of while true loop in main, 
	
	public Trade(Semaphore companyLock, int tradeTime, String tickerName, int quantity) {
		this.companyLock = companyLock;
		this.tradeTime = tradeTime;
		this.tickerName = tickerName;
		this.quantity = quantity;
	}

	public void run() 
	{
		try
		{
			//TimestampUtil.getZeroTimestamp()
			companyLock.acquire();
			System.out.printf("[%s] Starting %s of %d stocks of %s\n", Utility.getZeroTimestamp(), quantity > 0 ? "purchase" : "sale", Math.abs(quantity),
					tickerName);
			Thread.sleep(1000);
			System.out.printf("[%s] Finished %s of %d stocks of %s\n", Utility.getZeroTimestamp(), quantity > 0 ? "purchase" : "sale", Math.abs(quantity),
					tickerName);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			companyLock.release();
		}
	}
}
