import java.io.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class PA2 {
	/*QUESTIONS 8/7/21:
	  1. check all comments in CAPS (PA2.JAVA)
	  2. I created an arrayList that keeps track of the trade threads and got rid of Schedule.java
	  3. Do we need to worry about CSV file not being in order (not ordered by lowest time)
	  4. Do I need to put thread.yield?
	  5. Throws errors when reading in the file
	  6. Difference between getTimestamp and getZeroTimestamp
	  7. How to execute trades at a give time*/
	private static Scanner sc;
	private static GsonData jsonData;

	
     /**
      * Read Stock Json File inputed by user using GSON
      */
    private static void readStockFile() {
    	System.out.print("What is the name of the file containing the company information?\n");
    	Boolean keepread = true;
    	while(keepread)
    	{
    		sc = new Scanner(System.in);
        	String fname = sc.nextLine();
        	FileReader fileReader = null;
        	try 
        	{
        		fileReader = new FileReader(fname);
        		Gson gson = new Gson();
        		jsonData =  gson.fromJson(fileReader, GsonData.class);
        		keepread = false;
        	} 
        	
        	catch(InputMismatchException e)
    		{
    			System.out.println("that is not a valid input string");
    		}
    		catch(IOException e)
    		{
    			System.out.println("The file " + fname + " could not be found.\n");
    			System.out.print("What is the name of the file containing the company information?\n");
    		}
    		catch(JsonSyntaxException e)
    		{
    			System.out.println("Error: Corrupted JSON file. Incorrect JSON format");
    			System.out.print("What is the name of the file containing the company information?\n");
    		}
    	}
    	
    }

    /**
     * Read Stock Trades CSV File inputed by user
     */
    public static void readScheduleFile(List<Trade> sList, Map<String, Integer> TickerToSb)
    {
    	
    	System.out.print("What is the name of the file containing the schedule information?\n");
    	Boolean keepread = true;
    	while(keepread)
    	{
    		sc = new Scanner(System.in);
        	String fname = sc.nextLine();
        	String info = "";
        	try
        	{
        		FileReader fileReader = new FileReader(fname);
        		BufferedReader br = new BufferedReader(fileReader);
        		while((info = br.readLine()) != null)
        		{
        			//get rid of all the commas from csv file
        			String[] lineArray = info.split(",");
        			//get rid of spaces if there are any
        			for(int i = 0; i < lineArray.length; i++)
        			{
        				lineArray[i] = lineArray[i].replaceAll("[\\s]", "");
        				//get rid of NON-PRINTABLE character
        				lineArray[i] = lineArray[i].replaceAll("[\\p{C}]", "");
        			}
        			
        			//create a trade and add it to the trade scheduleList;
        			Semaphore sema = new Semaphore(TickerToSb.get(lineArray[1]));
        			Trade singleTrade = new Trade(sema, Integer.parseInt(lineArray[0]), lineArray[1], Integer.parseInt(lineArray[2]));
        			
        			sList.add(singleTrade);
        		}
        		br.close();
        		keepread = false;
        		
        	}
        	catch(InputMismatchException e)
    		{
    			System.out.println("that is not a valid input string");
    		}
    		catch(IOException e)
    		{
    			System.out.println("The file " + fname + " could not be found.\n");
    			System.out.print("What is the name of the file containing the schedule information?\n");
    		}
    	}
    }

    /**
     *Set up Semaphore for Stock Brokers
     */
    
    /*private static Semaphore initializeSemaphor(int sb) 
    {
    	Semaphore sema = new Semaphore(sb);
    	return sema;
    }*/

    private static void executeTrades(List<Trade> sList) throws InterruptedException 
    {
    	long startTime = System.currentTimeMillis();
    	long elapsedTime = 0;
    	int nextTaskIndex = 0;
    	ExecutorService exec = Executors.newCachedThreadPool();
    	while(true) 
    	{
    		
    		while((elapsedTime/1000) >= sList.get(nextTaskIndex).tradeTime)
			{
				exec.execute(sList.get(nextTaskIndex));
				nextTaskIndex++;
				if(nextTaskIndex == sList.size())
	    		{
	    			break;
	    		}
			}
			Thread.sleep(1000);
			elapsedTime = (System.currentTimeMillis() - startTime);
			
    		if(nextTaskIndex == sList.size())
    		{
    			break;
    		}
    	}
    	
    	exec.shutdown();
    	while (!exec.isTerminated()) 
    	{
    		Thread.yield();
    	}
    }

    public static void main(String[] args) throws InterruptedException 
    {
    	
    	readStockFile();
    	Map<String, Integer> TickerToSb = new HashMap<>();
    	//building ticker to stock broker map
		for(int i = 0; i < jsonData.data.length; i++)
    	{
			TickerToSb.put(jsonData.data[i].getTicker(), jsonData.data[i].getStockBrokers());
    	}
    	
    	List<Trade> scheduleList = new ArrayList<>(); 
    	readScheduleFile(scheduleList, TickerToSb);
   
    	executeTrades(scheduleList);
    }
}